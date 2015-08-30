package com.blackvirus.deepeshnaini.go_git_leaderboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import Adapters.FileDisplayAdapter;
import Adapters.RepoDetailDisplayAdapter;
import models.File;
import models.Repo;
import models.User;

public class RepoDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent=getIntent();
        Repo repo= (Repo) intent.getSerializableExtra("clickedRepo");
        System.out.println(repo);
        ListView lv= (ListView) findViewById(R.id.listViewDetail);
        RepoDetailDisplayAdapter repoDetailDisplayAdapter=new RepoDetailDisplayAdapter(this);
        for(User user:repo.getUsers()){
            repoDetailDisplayAdapter.addSection(user,new FileDisplayAdapter(this, (ArrayList<File>) user.getFiles()));
        }
        lv.setAdapter(repoDetailDisplayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_repo_detail, menu);
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
