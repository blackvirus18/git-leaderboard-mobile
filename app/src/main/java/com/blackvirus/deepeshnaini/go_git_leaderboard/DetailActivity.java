package com.blackvirus.deepeshnaini.go_git_leaderboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;

import Adapters.DetailDisplayAdapter;
import Adapters.FileDisplayAdapter;
import models.File;
import models.Repo;
import models.User;

public class DetailActivity extends Activity {
    private ExpandableListView expListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent=getIntent();
        User user= (User) intent.getSerializableExtra("clickedUser");
        ListView lv= (ListView) findViewById(R.id.listViewDetail);
        DetailDisplayAdapter detailDisplayAdapter=new DetailDisplayAdapter(this);
        for(Repo repo:user.getRepos()){
            detailDisplayAdapter.addSection(repo,new FileDisplayAdapter(this, (ArrayList<File>) repo.getFiles()));
        }
        lv.setAdapter(detailDisplayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
