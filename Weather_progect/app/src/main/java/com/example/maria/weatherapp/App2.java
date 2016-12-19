package com.example.maria.weatherapp;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

import Data.CityActive;

/**
 * Created by Maria on 17/12/16.
 */
public class App2 extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Configuration.Builder configurationBuilder = new Configuration.Builder(this);

        configurationBuilder.addModelClass(CityActive.class);



        ActiveAndroid.initialize(configurationBuilder.create());
    }
}
