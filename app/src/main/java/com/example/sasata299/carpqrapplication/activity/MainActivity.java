package com.example.sasata299.carpqrapplication.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.sasata299.carpqrapplication.MyFragmentPagerAdapter;
import com.example.sasata299.carpqrapplication.R;
import com.example.sasata299.carpqrapplication.model.Repo;
import com.example.sasata299.carpqrapplication.GitHubService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

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
}
