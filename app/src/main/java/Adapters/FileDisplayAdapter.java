package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.blackvirus.deepeshnaini.go_git_leaderboard.R;

import java.util.ArrayList;

import models.File;

/**
 * Created by deepeshnaini on 28/08/15.
 */
public class FileDisplayAdapter extends ArrayAdapter<File> {
    private Context c;
    public FileDisplayAdapter(Context context, ArrayList<File> files) {
        super(context, 0,files);
        c=context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        File file =new File();
        file=getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_file, parent, false);
        }

        TextView email= (TextView) convertView.findViewById(R.id.lblListItem);
        TextView score= (TextView) convertView.findViewById(R.id.lblListItemScore);

        email.setText(file.getName());
        score.setText(Double.toString(file.getScore()));
        return convertView;
    }
}
