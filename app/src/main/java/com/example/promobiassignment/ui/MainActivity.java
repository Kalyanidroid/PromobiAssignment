package com.example.promobiassignment.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.promobiassignment.MyApplication;
import com.example.promobiassignment.R;
import com.example.promobiassignment.adapter.RecyclerViewAdapter;
import com.example.promobiassignment.di.component.ApplicationComponent;
import com.example.promobiassignment.di.component.DaggerMainActivityComponent;
import com.example.promobiassignment.di.component.MainActivityComponent;
import com.example.promobiassignment.di.module.MainActivityContextModule;
import com.example.promobiassignment.di.qualifier.ActivityContext;
import com.example.promobiassignment.di.qualifier.ApplicationContext;
import com.example.promobiassignment.pojo.Result;
import com.example.promobiassignment.pojo.ReviewDataResponse;
import com.example.promobiassignment.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener {

    private ArrayList<Result> resultArrayList;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    MainActivityComponent mainActivityComponent;

    @BindView(R.id.et_search)
    EditText searchEditText;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    public APIInterface apiInterface;

    @Inject
    @ApplicationContext
    public Context mContext;

    @Inject
    @ActivityContext
    public Context activityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (recyclerViewAdapter != null) {
                        recyclerViewAdapter.getFilter().filter(s);
                    } else {
                        Log.d("filter", "no filter availible");
                    }
                }
                catch (NullPointerException e){
                    Log.e("NP Exception","In searchEditText");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        apiInterface.getReview("godfather","3uX5yegGVPrNAKpQYYDfVa9glfHdGiIV").enqueue(new Callback<ReviewDataResponse>() {
            @Override
            public void onResponse(Call<ReviewDataResponse> call, Response<ReviewDataResponse> response) {
                try {
                    if(response.body().getStatus().equalsIgnoreCase("ok")) {
                        populateRecyclerView(response.body().getResults());
                        resultArrayList = (ArrayList<Result>) response.body().getResults();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ReviewDataResponse> call, Throwable t) {

            }
        });

    }

    private void populateRecyclerView(List<Result> response) {
        recyclerViewAdapter.setData(response);
    }

    @Override
    public void launchIntent(int position) {
        resultArrayList = recyclerViewAdapter.refreshResults();
        Result result = resultArrayList.get(position);
        Toast.makeText(mContext, result.getDisplayTitle(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(activityContext, DetailsActivity.class).putExtra("result", result));
    }
}

