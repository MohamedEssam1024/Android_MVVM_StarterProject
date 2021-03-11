package com.essam.starterproject.data;

import com.essam.starterproject.data.APIResponce.Operators;
import com.essam.starterproject.data.APIResponce.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitCall {

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("operators")
    Call<Root> getItems(@Header("Authorization") String auth, @Query("page") int page);

//    if request needs header
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
//    @GET("photos")
//    Call<List<Items>> getItems(@Header("Authorization") String auth);
}
