package Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.deepeshnaini.go_git_leaderboard.R;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import models.User;

/**
 * Created by deepeshnaini on 28/08/15.
 */
public class UserAdapter extends ArrayAdapter<User> {
    private Context c;
    public UserAdapter(Context context, ArrayList<User> users) {
        super(context, 0,users);
        c=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user =new User();
        user=getItem(position);
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

        email.setText(user.getEmail());
        score.setText(Double.toString(user.getScore()));
        return convertView;
    }
}
