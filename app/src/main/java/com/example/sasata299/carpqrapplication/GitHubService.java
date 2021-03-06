package com.example.sasata299.carpqrapplication;

import com.example.sasata299.carpqrapplication.model.Repo;
import com.example.sasata299.carpqrapplication.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sasata299 on 2016/12/24.
 */

public interface GitHubService {
    @GET("users/{user}")
    Call<User> user(@Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
