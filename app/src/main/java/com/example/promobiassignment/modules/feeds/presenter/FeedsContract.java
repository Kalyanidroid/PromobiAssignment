package com.example.promobiassignment.modules.feeds.presenter;

import com.example.promobiassignment.database.entities.Feed;

import java.util.List;

public abstract class FeedsContract {

    public interface View {
        void prepareUI();
        void showLoading();
        void stopLoading();
        void showError(String error);
        void showFeeds(List<Feed> feeds);
        void showEmptyList();
    }

    public interface Presenter {
        void loadData(String query, String api);
        void filterData(String query);
    }

}
