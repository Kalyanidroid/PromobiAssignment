package com.example.promobiassignment;

import android.app.Activity;
import android.app.Application;

import com.example.promobiassignment.utils.di.component.ApplicationComponent;
import com.example.promobiassignment.utils.di.component.DaggerApplicationComponent;
import com.example.promobiassignment.utils.di.module.ContextModule;
import com.example.promobiassignment.utils.di.module.DataModule;

public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .dataModule(new DataModule(this))
                .build();

        applicationComponent.injectApplication(this);


    }

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

