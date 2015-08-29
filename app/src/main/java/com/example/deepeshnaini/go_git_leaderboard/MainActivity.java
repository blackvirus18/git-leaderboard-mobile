package com.example.deepeshnaini.go_git_leaderboard;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Adapters.UserAdapter;
import models.User;
import service.CallApi;
import service.ResponseListener;

public class MainActivity extends Activity {
    private String reviews;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout= (LinearLayout) findViewById(R.id.list_item);
        try {
            getLeaderBoardJson();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void getLeaderBoardJson() throws ExecutionException, InterruptedException {
        final CallApi callApi=new CallApi("api/commits/getLeaderBoard");
        callApi.setResponseListner(new ResponseListener() {
            @Override
            public void onResponse(String response) {
                createView(response);
            }
        });
        callApi.execute();
    }

    private void createView(String response) {
        try {
            JSONObject jObj=new JSONObject(response);
            /*JSONArray userLeaderBoard=new JSONArray();
            Iterator<String> strArr= jObj.keys();
            List<String> email=new ArrayList<String>();
            while (strArr.hasNext()){
                JSONObject newObj=new JSONObject();
                newObj.put("email",strArr.next());
                //email.add(strArr.next());
                *//*JSONObject jUser=new JSONObject((String) jObj.get(strArr.next()));
                newObj.put("score",jUser.get("score"));
                newObj.put("repos",jUser.get("repos"));*//*
                userLeaderBoard.put(newObj);
            }
            ListView listView = (ListView) findViewById(R.id.list_view);

            ArrayAdapter<String> arrayAdapter
                    = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    email);

            listView.setAdapter(arrayAdapter);*/
            ArrayList<User> users=new ArrayList<User>();
            Iterator<String> iter= jObj.keys();
            while(iter.hasNext()){
                String email=iter.next();
                JSONObject userObj= (JSONObject) jObj.get(email);
                Double score= (Double) userObj.getDouble("score");
                User user=new User();
                user.setEmail(email);
                user.setScore(score);
                user.setImage(email);
                users.add(user);
            }
            UserAdapter userAdapter=new UserAdapter(this, users);
            ListView listView = (ListView) findViewById(R.id.list_view);
            listView.setAdapter(userAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
