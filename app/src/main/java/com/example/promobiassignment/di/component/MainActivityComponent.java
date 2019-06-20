package com.example.promobiassignment.di.component;

import android.content.Context;

import com.example.promobiassignment.di.module.AdapterModule;
import com.example.promobiassignment.di.qualifier.ActivityContext;
import com.example.promobiassignment.di.scopes.ActivityScope;
import com.example.promobiassignment.ui.MainActivity;

import dagger.Component;


@ActivityScope
@Component(modules = AdapterModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();


    void injectMainActivity(MainActivity mainActivity);
}
