package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blackvirus.deepeshnaini.go_git_leaderboard.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import models.User;

public class RepoDetailDisplayAdapter extends BaseAdapter {

    public final Map<User,Adapter> sections = new LinkedHashMap<User,Adapter>();
    public final ArrayList<User> headers;
    public final static int TYPE_SECTION_HEADER = 0;
    public Context context;
    public RepoDetailDisplayAdapter(Context context) {
        this.context=context;
        headers = new ArrayList<User>();
    }

    public void addSection(User user, Adapter adapter) {
        this.headers.add(user);
        this.sections.put(user, adapter);
    }

    public Object getItem(int position) {
        for(Object section : this.sections.keySet()) {
            Adapter adapter = sections.get(section);
            int size = adapter.getCount() + 1;

            // check if position inside this section
            if(position == 0) return section;
            if(position < size) return adapter.getItem(position - 1);

            // otherwise jump into next section
            position -= size;
        }
        return null;
    }

    public int getCount() {
        // total together all sections, plus one for each section header
        int total = 0;
        for(Adapter adapter : this.sections.values())
            total += adapter.getCount() + 1;
        return total;
    }

    public int getViewTypeCount() {
        // assume that headers count as one, then total all sections
        int total = 1;
        for(Adapter adapter : this.sections.values())
            total += adapter.getViewTypeCount();
        return total;
    }

    public int getItemViewType(int position) {
        int type = 1;
        for(Object section : this.sections.keySet()) {
            Adapter adapter = sections.get(section);
            int size = adapter.getCount() + 1;

            // check if position inside this section
            if(position == 0) return TYPE_SECTION_HEADER;
            if(position < size) return type + adapter.getItemViewType(position - 1);

            // otherwise jump into next section
            position -= size;
            type += adapter.getViewTypeCount();
        }
        return -1;
    }

    public boolean areAllItemsSelectable() {
        return false;
    }

    public boolean isEnabled(int position) {
        return (getItemViewType(position) != TYPE_SECTION_HEADER);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int sectionnum = 0;
        for(User user : this.sections.keySet()) {
            Adapter adapter = sections.get(user);
            int size = adapter.getCount() + 1;

            // check if position inside this section
            if(position == 0){
                if (convertView == null) {
                    convertView = LayoutInflater.from(this.context).inflate(R.layout.repo_header, parent, false);
                }
                TextView repoName= (TextView) convertView.findViewById(R.id.lblListItem);
                TextView repoScore= (TextView) convertView.findViewById(R.id.lblListItemScore);
                repoName.setText(user.getEmail());
                repoScore.setText(Double.toString(user.getScore()));
                return convertView;
            }
            if(position < size) return adapter.getView(position - 1, convertView, parent);

            // otherwise jump into next section
            position -= size;
            sectionnum++;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
