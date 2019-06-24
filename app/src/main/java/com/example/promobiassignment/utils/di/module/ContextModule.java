package com.example.promobiassignment.utils.di.module;

import android.content.Context;

import com.example.promobiassignment.utils.di.qualifier.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
