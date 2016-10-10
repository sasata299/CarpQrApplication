package com.example.sasata299.carpqrapplication;

import android.graphics.Bitmap;

/**
 * Created by sasata299 on 2016/10/10.
 */

public class QuickReport {
    long id;
    private Bitmap icon;
    private String inning;
    private String detail;

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
