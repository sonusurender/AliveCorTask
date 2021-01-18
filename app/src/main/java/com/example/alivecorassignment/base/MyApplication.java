package com.example.alivecorassignment.base;

import android.app.Application;

/**
 * Created by sonu on 14:00, 18/01/21
 * Copyright (c) 2021 . All rights reserved.
 */
public class MyApplication  extends Application {
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
