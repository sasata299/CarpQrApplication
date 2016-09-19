package com.example.sasata299.carpqrapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Properties;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sasata299 on 16/09/19.
 */
public class MyAsyncTask extends AsyncTask<Void, Void, String> {

    OkHttpClient client = new OkHttpClient();
    Context context;

    public MyAsyncTask(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        String res = null;

        try {
            String result = run("http://api.openweathermap.org/data/2.5/weather?APPID=<apiKey>&q=Tokyo");
            Log.i("show result", result);
            JSONObject resJson = new JSONObject(result);
            JSONArray weathers = resJson.getJSONArray("weather");
            JSONObject weather = weathers.getJSONObject(0);
            String description = weather.getString("description");
            Log.i("show description", description);
            res = description;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    protected void onPostExecute(String res) {
        super.onPostExecute(res);

//        Toast.makeText(context, res, Toast.LENGTH_LONG).show();
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.setTextView(res);
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