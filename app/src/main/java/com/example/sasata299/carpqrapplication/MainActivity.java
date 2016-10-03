package com.example.sasata299.carpqrapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements AsyncTaskCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAsyncTask task = new MyAsyncTask(MainActivity.this);
                task.execute();
            }
        });
    }

    public void preExecute() {
        Log.i("logger", "preExecute");
    }

    public void postExecute(JSONObject result) {
        String detail = null;
        Log.i("logger", "postExecute");

        TextView text = (TextView) findViewById(R.id.text);

        try {
            detail = result.getString("detail");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        text.setText(detail);
    }

    public void progressUpdate(int progress) {
        Log.i("logger", "progreessUpdate");
    }

    public void cancel() {
        Log.i("logger", "cancel");
    }
}
