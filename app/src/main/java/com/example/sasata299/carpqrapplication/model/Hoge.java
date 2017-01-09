package com.example.sasata299.carpqrapplication.model;

import android.util.Log;

import com.example.sasata299.carpqrapplication.ClickEvent;
import com.example.sasata299.carpqrapplication.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by sasata299 on 2017/01/07.
 */

public class Hoge {
    public static void doAction() {
        Log.d("event bus", "aaa");
        EventBus.getDefault().post(new MessageEvent("Hello world"));
        Log.d("event bus", "bbb");
    }
}
