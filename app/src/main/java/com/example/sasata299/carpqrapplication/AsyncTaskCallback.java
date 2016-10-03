package com.example.sasata299.carpqrapplication;

import org.json.JSONObject;

/**
 * Created by sasata299 on 16/09/22.
 */
public interface AsyncTaskCallback {
    void preExecute();
    void postExecute(JSONObject result);
    void progressUpdate(int progress);
    void cancel();
}