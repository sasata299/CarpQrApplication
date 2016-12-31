package com.example.sasata299.carpqrapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AsyncTaskCallback {
    @BindView(R.id.listView) ListView listView;
    @BindView(R.id.emptyView) TextView emptyView;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.github.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                GitHubService service = retrofit.create(GitHubService.class);

//                // User API
//                Call<User> call = service.user("sasata299");
//                Response<User> response = null;
//                try {
//                    response = call.execute();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                User user = response.body();
//                Log.d("login", user.getLogin());

                // User List API
                Call<List<Repo>> call = service.listRepos("sasata299");
                Response<List<Repo>> response = null;
                try {
                    response = call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                List<Repo> repos = response.body();
                for (Repo repo : repos) {
                    Log.d("name", repo.getFullName());
                }
            }
        }).start();

        setupViewPager();

        ButterKnife.bind(this);

//        createSwipeRefreshLayout();

//        reloadTimeline();
    }

    public void setupViewPager() {
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        final String[] pageTitles = { "試合速報", "選手情報", "その他" };

        FragmentManager fm = getSupportFragmentManager();
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(fm, pageTitles);

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
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
