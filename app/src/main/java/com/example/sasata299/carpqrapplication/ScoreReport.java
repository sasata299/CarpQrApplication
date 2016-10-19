package com.example.sasata299.carpqrapplication;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sasata299 on 2016/10/10.
 */

public class ScoreReport {
    long id;
    private Bitmap icon;
    private String inning;
    private String name;
    private String detail;

    public ScoreReport(JSONObject scoreReport) throws JSONException {
        this.inning = scoreReport.getString("inning");
        this.name = scoreReport.getString("name");
        this.detail = scoreReport.getString("detail");
    }

    public long getId() {
        return id;
    }

    public String getInning() {
        return inning;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        if (name == null) {
            return detail;
        } else {
            return name + "が" + detail;
        }
    }
}
