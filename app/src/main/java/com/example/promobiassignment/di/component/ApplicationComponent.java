package com.example.promobiassignment.di.component;

import android.content.Context;

import com.example.promobiassignment.MyApplication;
import com.example.promobiassignment.di.module.ContextModule;
import com.example.promobiassignment.di.module.RetrofitModule;
import com.example.promobiassignment.di.qualifier.ApplicationContext;
import com.example.promobiassignment.di.scopes.ApplicationScope;
import com.example.promobiassignment.retrofit.APIInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    public APIInterface getApiInterface();

    @ApplicationContext
    public Context getContext();

    public void injectApplication(MyApplication myApplication);
}
