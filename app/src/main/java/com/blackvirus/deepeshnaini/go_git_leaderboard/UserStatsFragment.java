package com.blackvirus.deepeshnaini.go_git_leaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import Adapters.UserAdapter;
import models.File;
import models.Repo;
import models.User;
import service.CallApi;
import service.ResponseListener;

import static java.util.Collections.*;

public class UserStatsFragment extends Fragment {
    private ArrayList<User> users;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user_stats, container, false);
        try {
            getLeaderBoardJson();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return rootView;
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
            users=new ArrayList<User>();
            Iterator<String> iter= jObj.keys();
            while(iter.hasNext()){
                String email=iter.next();
                JSONObject userObj= (JSONObject) jObj.get(email);
                Double score= (Double) userObj.getDouble("impact");
                User user=new User();
                user.setEmail(email);
                user.setScore(score);
                user.setImage(email);
                JSONObject reposObj=(JSONObject) userObj.get("repos");
                Iterator repoIter=reposObj.keys();
                ArrayList<Repo> repos=new ArrayList<Repo>();
                while(repoIter.hasNext()){
                    String repoName= (String) repoIter.next();
                    JSONObject repoObj=(JSONObject) reposObj.get(repoName);
                    JSONObject filesObj= (JSONObject) repoObj.get("files");
                    Double repoScore= (Double) repoObj.getDouble("impact");
                    ArrayList<File> files=new ArrayList<File>();
                    Repo repo=new Repo();
                    repo.setName(repoName);
                    repo.setScore(repoScore);
                    Iterator fileIter=filesObj.keys();
                    while (fileIter.hasNext()){
                        File file=new File();
                        String fileName= (String) fileIter.next();
                        file.setName(fileName);
                        file.setScore((Double) filesObj.getDouble(fileName));
                        files.add(file);
                    }
                    repo.setFiles(files);
                    repos.add(repo);
                }
                user.setRepos(repos);
                users.add(user);
            }
            Collections.sort(users);
            System.out.println(users);
            UserAdapter userAdapter=new UserAdapter(getContext(), users);
            ListView listView = (ListView) getView().findViewById(R.id.list_view);
            listView.setAdapter(userAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    displayDetails(position);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void displayDetails(int position) {
        User clickUser=users.get(position);
        System.out.println(clickUser);
        Intent intent =new Intent(getActivity(),DetailActivity.class);
        intent.putExtra("clickedUser",clickUser);
        startActivity(intent);
    }
}