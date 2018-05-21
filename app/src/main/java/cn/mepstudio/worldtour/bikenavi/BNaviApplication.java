/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package cn.mepstudio.worldtour.bikenavi;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;


public class BNaviApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }
}
