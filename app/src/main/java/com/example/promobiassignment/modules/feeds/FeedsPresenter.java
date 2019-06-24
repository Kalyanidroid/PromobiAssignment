package com.example.promobiassignment.modules.feeds;

import com.example.promobiassignment.BuildConfig;
import com.example.promobiassignment.database.DataFactory;
import com.example.promobiassignment.database.entities.Feed;
import com.example.promobiassignment.models.ReviewDataResponse;
import com.example.promobiassignment.modules.feeds.presenter.FeedsContract;
import com.example.promobiassignment.utils.services.FeedService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedsPresenter implements FeedsContract.Presenter, Callback<ReviewDataResponse> {

    private FeedService service;
    private DataFactory dataFactory;
    private FeedsContract.View view;

    public FeedsPresenter(FeedService service, DataFactory dataFactory, FeedsContract.View view) {
        this.service = service;
        this.dataFactory = dataFactory;
        this.view = view;
        this.view.prepareUI();
    }

    @Override
    public void loadData(String query, String api) {
        List<Feed> feeds = dataFactory.feedsDao().getAll();
        if (feeds != null && feeds.size() > 0) {
            this.view.showFeeds(feeds);
        } else {
            this.view.showLoading();
            this.service.getReview(query, api).enqueue(this);
        }
    }

    @Override
    public void filterData(String query) {
        if (query != null && query.length() > 0 && query.trim().length() > 0) {
            List<Feed> feeds = dataFactory.feedsDao().searchFeeds("%"+query+"%");
            if (feeds != null && feeds.size() > 0) {
                this.view.showFeeds(feeds);
            } else {
                this.view.showLoading();
                this.service.getReview(query, BuildConfig.API_KEY).enqueue(this);
            }
        } else {
            List<Feed> feeds = dataFactory.feedsDao().getAll();
            if (feeds != null && feeds.size() > 0) {
                this.view.showFeeds(feeds);
            } else {
                this.view.showEmptyList();
            }
        }
    }

    @Override
    public void onResponse(Call<ReviewDataResponse> call, Response<ReviewDataResponse> response) {
        try {
            this.view.stopLoading();
            if (response.body() != null && response.body().getStatus().equalsIgnoreCase("ok")) {
                List<Feed> feeds = response.body().getFeeds();
                if (feeds != null && feeds.size() > 0) {
                    this.view.showFeeds(feeds);
                    this.dataFactory.feedsDao().insertAll(feeds);
                } else {
                    this.view.showEmptyList();
                }
            }
            //throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
            this.view.showError("Error while processing you request");
        }
    }

    @Override
    public void onFailure(Call<ReviewDataResponse> call, Throwable t) {
        this.view.stopLoading();
        this.view.showError("Error while processing you request");
    }
}