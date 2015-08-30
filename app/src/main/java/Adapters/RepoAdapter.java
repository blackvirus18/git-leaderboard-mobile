package Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackvirus.deepeshnaini.go_git_leaderboard.R;

import java.util.ArrayList;

import models.Repo;

/**
 * Created by deepeshnaini on 28/08/15.
 */
public class RepoAdapter extends ArrayAdapter<Repo> {
    private Context c;
    public RepoAdapter(Context context, ArrayList<Repo> repos) {
        super(context, 0,repos);
        c=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Repo repo =new Repo();
        repo=getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        ImageView image=(ImageView) convertView.findViewById(R.id.list_image);
        String uri = "drawable/avatar";
        int imageResource = getContext().getResources().getIdentifier(uri,"drawable",c.getPackageName());

        Drawable res = c.getResources().getDrawable(imageResource);
        image.setImageDrawable(res);

        TextView email= (TextView) convertView.findViewById(R.id.user);
        TextView score= (TextView) convertView.findViewById(R.id.user_score);

        email.setText(repo.getName());
        score.setText(Double.toString(repo.getScore()));
        return convertView;
    }
}
