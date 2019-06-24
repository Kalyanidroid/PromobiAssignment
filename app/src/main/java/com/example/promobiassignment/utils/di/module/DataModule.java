package com.example.promobiassignment.utils.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.promobiassignment.database.DataFactory;
import com.example.promobiassignment.utils.di.qualifier.ApplicationContext;
import com.example.promobiassignment.utils.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    private DataFactory dataFactory;

    public DataModule(Context context) {
        this.dataFactory = Room.databaseBuilder(context, DataFactory.class, "promobidatabase.sqlite")
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public DataFactory provideDataFactory() {
        return dataFactory;
    }
}
