package com.example.promobiassignment.utils.di.component;

import com.example.promobiassignment.MyApplication;
import com.example.promobiassignment.modules.feeds.FeedListActivity;
import com.example.promobiassignment.utils.di.module.ContextModule;
import com.example.promobiassignment.utils.di.module.DataModule;
import com.example.promobiassignment.utils.di.module.RetrofitModule;
import com.example.promobiassignment.utils.di.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class, DataModule.class})
public interface ApplicationComponent {
    void injectApplication(MyApplication myApplication);
    void inject(FeedListActivity activity);
}
