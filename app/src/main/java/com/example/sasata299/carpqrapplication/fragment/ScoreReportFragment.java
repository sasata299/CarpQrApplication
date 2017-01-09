package com.example.sasata299.carpqrapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sasata299.carpqrapplication.AsyncTaskCallback;
import com.example.sasata299.carpqrapplication.ClickEvent;
import com.example.sasata299.carpqrapplication.MyAdapter;
import com.example.sasata299.carpqrapplication.MyAsyncTask;
import com.example.sasata299.carpqrapplication.R;
import com.example.sasata299.carpqrapplication.activity.DetailActivity;
import com.example.sasata299.carpqrapplication.model.ScoreReport;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sasata299 on 2016/11/04.
 */

public class ScoreReportFragment extends Fragment implements AsyncTaskCallback {

    @BindView(R.id.listView)
    ListView listView;

    @BindView(R.id.emptyView)
    TextView emptyView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    MyAdapter myAdapter;

    public ScoreReportFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("logger", "call onCreateView");

        View view = inflater.inflate(R.layout.score_report_listview, container, false);

        ButterKnife.bind(this, view);

        prepareSwipeRefreshLayout();
        loadScoreReport();

        return view;
    }

    public void loadScoreReport() {
        MyAsyncTask task = new MyAsyncTask(this);
        task.execute();
    }

    public void prepareSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorScheme(R.color.red, R.color.blue, R.color.green, R.color.yellow);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            // 引っ張ったときの処理
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                loadScoreReport();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void preExecute() {
        Log.i("logger", "preExecute");
    }

    public void postExecute(ArrayList<ScoreReport> result) {
        Log.i("logger", "postExecute");

        myAdapter = new MyAdapter(getActivity());
        myAdapter.setScoreReports(result);

        // Adapterの指定
        listView.setEmptyView(emptyView);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("logger", "clicked! " + String.valueOf(position));
                Intent intent = new Intent(getActivity(), DetailActivity.class);

                ScoreReport scoreReport = (ScoreReport) parent.getItemAtPosition(position);
                intent.putExtra("detail", scoreReport.getDetail());

                scoreReport.click();

//                startActivity(intent);
            }
        });
    }

    public void progressUpdate(int progress) {
        Log.i("logger", "progreessUpdate");
    }

    public void cancel() {
        Log.i("logger", "cancel");
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickEvent(ClickEvent event) {
        Log.d("hoge", "subscribe");
        myAdapter.notifyDataSetChanged();
        listView.invalidateViews();
    }
}
