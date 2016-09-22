package com.example.sasata299.carpqrapplication;

/**
 * Created by sasata299 on 16/09/22.
 */
public interface AsyncTaskCallback {
    void preExecute();
    void postExecute(String result);
    void progressUpdate(int progress);
    void cancel();
}