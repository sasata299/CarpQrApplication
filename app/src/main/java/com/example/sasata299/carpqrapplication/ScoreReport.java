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
    private String detail;

    public ScoreReport(JSONObject scoreReport) throws JSONException {
        this.inning = scoreReport.getString("inning");
        this.detail = scoreReport.getString("detail");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getInning() {
        return inning;
    }

    public void setInning(String inning) {
        this.inning = inning;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
