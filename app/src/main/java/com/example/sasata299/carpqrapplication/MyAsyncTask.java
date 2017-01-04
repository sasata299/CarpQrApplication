package com.example.sasata299.carpqrapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sasata299 on 16/09/19.
 */
public class MyAsyncTask extends AsyncTask<Void, Integer, ArrayList<ScoreReport>> {

    OkHttpClient client = new OkHttpClient();
    AsyncTaskCallback callback;

    public MyAsyncTask(AsyncTaskCallback callback) {
        super();
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callback.preExecute();
    }

    @Override
    protected ArrayList<ScoreReport> doInBackground(Void... params) {
        ArrayList<ScoreReport> data = new ArrayList<>();

        try {
            String result = run("http://10.0.2.2:3000/score_reports");
            Log.i("show result", result);
            JSONObject resJson = new JSONObject(result);
            JSONArray scoreReports = resJson.getJSONArray("score_reports");

            if (scoreReports != null) {
                for (int i = 0; i < scoreReports.length(); i++){
                    ScoreReport scoreReport = new ScoreReport(scoreReports.getJSONObject(i));
                    data.add(scoreReport);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onPostExecute(ArrayList<ScoreReport> result) {
        super.onPostExecute(result);
        callback.postExecute(result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        callback.progressUpdate(values[0]);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        callback.cancel();
    }

    // OkHttpを使った処理
    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                            .url(url)
                            .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}