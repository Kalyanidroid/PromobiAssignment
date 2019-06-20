package com.example.promobiassignment.retrofit;


import com.example.promobiassignment.pojo.ReviewDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {


    @GET("svc/movies/v2/reviews/search.json?")
    Call<ReviewDataResponse> getReview(@Query("query") String query,@Query("api-key") String api_key);

    /*@GET
    Call<Film> getFilmData(@Url String url, @Query("format") String format);*/
}
