package com.example.promobiassignment.utils.services;


import com.example.promobiassignment.models.ReviewDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FeedService {

    @GET("/svc/movies/v2/reviews/search.json?")
    Call<ReviewDataResponse> getReview(@Query("query") String query,@Query("api-key") String api_key);
}
