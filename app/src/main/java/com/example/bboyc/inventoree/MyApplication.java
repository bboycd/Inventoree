package com.example.bboyc.inventoree;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class MyApplication extends Application{
    public void onCreate(){
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        Stetho.Initializer initializer = initializerBuilder.build();

        Stetho.initialize(initializer);
    }
}
