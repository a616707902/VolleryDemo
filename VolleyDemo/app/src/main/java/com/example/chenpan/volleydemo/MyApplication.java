package com.example.chenpan.volleydemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2016/5/19.
 */
public class MyApplication extends Application {
    public  static RequestQueue queues;
    @Override
    public void onCreate() {
        super.onCreate();
        queues= Volley.newRequestQueue(getApplicationContext());

    }
    public static RequestQueue getQueues(){
        return  queues;
    }
}
