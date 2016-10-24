package com.example.sasata299.carpqrapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AsyncTaskCallback {
    @BindView(R.id.listView) ListView listView;
    @BindView(R.id.emptyView) TextView emptyView;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        createSwipeRefreshLayout();

        reloadTimeline();
    }

    public void reloadTimeline() {
        MyAsyncTask task = new MyAsyncTask(MainActivity.this);
        task.execute();
    }

    public void createSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorScheme(R.color.red, R.color.blue, R.color.green, R.color.yellow);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            // 引っ張ったときの処理
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                reloadTimeline();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void preExecute() {
        Log.i("logger", "preExecute");
    }

    public void postExecute(ArrayList<ScoreReport> result) {
        Log.i("logger", "postExecute");

        MyAdapter myAdapter = new MyAdapter(this);
        myAdapter.setScoreReports(result);

        // Adapterの指定
        listView.setEmptyView(emptyView);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("logger", "clicked! " + String.valueOf(position));
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                ScoreReport scoreReport = (ScoreReport) parent.getItemAtPosition(position);
                intent.putExtra("detail", scoreReport.getDetail());

                startActivity(intent);
            }
        });
    }

    public void progressUpdate(int progress) {
        Log.i("logger", "progreessUpdate");
    }

    public void cancel() {
        Log.i("logger", "cancel");
    }
}
