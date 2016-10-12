package com.example.sasata299.carpqrapplication;

import java.util.ArrayList;

/**
 * Created by sasata299 on 16/09/22.
 */
public interface AsyncTaskCallback {
    void preExecute();
    void postExecute(ArrayList<ScoreReport> result);
    void progressUpdate(int progress);
    void cancel();
}