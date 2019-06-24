package com.example.promobiassignment.modules.feeds;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.promobiassignment.BuildConfig;
import com.example.promobiassignment.MyApplication;
import com.example.promobiassignment.R;
import com.example.promobiassignment.database.DataFactory;
import com.example.promobiassignment.database.entities.Feed;
import com.example.promobiassignment.modules.feeds.adapter.RecyclerViewAdapter;
import com.example.promobiassignment.modules.feeds.presenter.FeedsContract;
import com.example.promobiassignment.utils.di.component.ApplicationComponent;
import com.example.promobiassignment.utils.di.qualifier.ApplicationContext;
import com.example.promobiassignment.utils.services.FeedService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedListActivity extends AppCompatActivity implements FeedsContract.View, RecyclerViewAdapter.ClickListener, TextWatcher {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.et_search)
    EditText searchEditText;

    @Inject
    public FeedService feedService;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ApplicationContext
    public DataFactory dataFactory;

    public RecyclerViewAdapter recyclerViewAdapter;
    public FeedsPresenter feedsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        applicationComponent.inject(this);

        this.feedsPresenter = new FeedsPresenter(this.feedService, this.dataFactory, this);
        this.feedsPresenter.loadData("godfather", BuildConfig.API_KEY);
    }

    @Override
    public void launchIntent(int position) {
        ArrayList<Feed> feedArrayList = recyclerViewAdapter.refreshResults();
        Feed feed = feedArrayList.get(position);
        Toast.makeText(mContext, feed.getDisplayTitle(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, FeedDetailsActivity.class).putExtra("feed", feed));
    }

    @Override
    public void prepareUI() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(FeedListActivity.this));
        this.recyclerViewAdapter = new RecyclerViewAdapter(this);
        this.recyclerView.setAdapter(recyclerViewAdapter);
        this.searchEditText.addTextChangedListener(this);
        this.searchEditText.setSelected(false);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFeeds(List<Feed> feeds) {
        this.recyclerViewAdapter.setData(feeds);
    }

    @Override
    public void showEmptyList() {
        this.recyclerViewAdapter.setData(new ArrayList<>());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.feedsPresenter.filterData(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

