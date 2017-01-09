package com.example.sasata299.carpqrapplication.model;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.sasata299.carpqrapplication.ClickEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sasata299 on 2016/10/10.
 */

public class ScoreReport {
    long id;
    private Bitmap icon;
    private String inning;
    private String score;
    private String name;
    private String detail;

    public Boolean isRead; // 既読管理

    public ScoreReport(JSONObject scoreReport) throws JSONException {
        this.inning = scoreReport.getString("inning");
        this.score = scoreReport.getString("score");
        this.name = scoreReport.getString("name");
        this.detail = scoreReport.getString("detail");
        this.isRead = false;
    }

    public long getId() {
        return id;
    }

    public String getInning() {
        return inning;
    }

    public String getScore() {
        return score;
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

    public void click() {
        this.isRead = true;
        Log.d("hoge", "発火");
        EventBus.getDefault().post(new ClickEvent());
    }
}
