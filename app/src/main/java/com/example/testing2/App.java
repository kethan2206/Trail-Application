package com.example.testing2;

import android.app.Application;

public class App extends Application {

    //Config
    public static Config mConfig;

    //Beacon
    public static Config.Beacon mBeacon;

    @Override
    public void onCreate(){
        super.onCreate();
    }
}
