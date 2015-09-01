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

import Adapters.RepoAdapter;
import models.File;
import models.Repo;
import models.User;
import service.CallApi;
import service.ResponseListener;

public class RepoStatsFragment extends Fragment {
    private ArrayList<Repo> repos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_repo_stats, container, false);
        try {
            getLeaderBoardJson();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return rootView;
    }
    private void getLeaderBoardJson() throws ExecutionException, InterruptedException {
        final CallApi callApi=new CallApi("api/commits/getRepoBoard");
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
            repos=new ArrayList<Repo>();
            Iterator<String> iter= jObj.keys();
            while(iter.hasNext()){
                String repoName=iter.next();
                ArrayList<User> users=new ArrayList<User>();
                JSONObject repoObj= (JSONObject) jObj.get(repoName);
                Double score= (Double) repoObj.getDouble("impact");
                Repo repo=new Repo();
                repo.setName(repoName);
                repo.setScore(score);
                JSONObject usersObj= (JSONObject) repoObj.get("users");
                Iterator userIter=usersObj.keys();
                while(userIter.hasNext()){
                    User user=new User();
                    ArrayList<File> files=new ArrayList<File>();
                    String email= (String) userIter.next();
                    JSONObject userObj= (JSONObject) usersObj.get(email);
                    Double userScore= (Double) userObj.getDouble("impact");
                    JSONObject filesObj= (JSONObject) userObj.get("files");
                    Iterator fileIter=filesObj.keys();
                    while (fileIter.hasNext()){
                        File file=new File();
                        String fileName= (String) fileIter.next();
                        file.setName(fileName);
                        file.setScore((Double) filesObj.getDouble(fileName));
                        files.add(file);
                    }
                    user.setEmail(email);
                    user.setScore(userScore);
                    user.setFiles(files);
                    users.add(user);
                }
                repo.setUsers(users);
                repos.add(repo);
            }
            Collections.sort(repos);
            RepoAdapter repoAdapter=new RepoAdapter(getContext(), repos);
            ListView listView = (ListView) getView().findViewById(R.id.list_view);
            listView.setAdapter(repoAdapter);
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
        Repo clickRepo=repos.get(position);
        System.out.println(clickRepo);
        Intent intent =new Intent(getActivity(),RepoDetailActivity.class);
        intent.putExtra("clickedRepo",clickRepo);
        startActivity(intent);
    }
}