package com.example.sasata299.carpqrapplication.model;

import android.util.Log;

import com.example.sasata299.carpqrapplication.MessageEvent;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by sasata299 on 2016/12/26.
 */

public class Repo {
    private String name;

    @SerializedName("full_name")
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
