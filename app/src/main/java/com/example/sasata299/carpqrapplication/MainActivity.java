package com.example.sasata299.carpqrapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncTaskCallback {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        MyAsyncTask task = new MyAsyncTask(MainActivity.this);
        task.execute();
    }

    public void preExecute() {
        Log.i("logger", "preExecute");
    }

    public void postExecute(ArrayList<ScoreReport> result) {
        Log.i("logger", "postExecute");

        listView = (ListView) findViewById(R.id.listView);

        MyAdapter myAdapter = new MyAdapter(this);
        myAdapter.setScoreReports(result);

        // Adapterの指定
        listView.setEmptyView(findViewById(R.id.emptyView));
        listView.setAdapter(myAdapter);
    }

    public void progressUpdate(int progress) {
        Log.i("logger", "progreessUpdate");
    }

    public void cancel() {
        Log.i("logger", "cancel");
    }
}
