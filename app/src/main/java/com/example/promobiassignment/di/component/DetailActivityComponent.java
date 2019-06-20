package com.example.promobiassignment.di.component;

import com.example.promobiassignment.di.scopes.ActivityScope;

import dagger.Component;

@Component(dependencies = ApplicationComponent.class)
@ActivityScope
public interface DetailActivityComponent {

    //void inject(DetailActivity detailActivity);
}
