package com.essam.starterproject.data;


import androidx.lifecycle.MutableLiveData;

import com.essam.starterproject.data.APIResponce.Operators;
import com.essam.starterproject.data.APIResponce.Root;
import com.essam.starterproject.utils.Constants;
import com.essam.starterproject.utils.Resource;

import java.net.HttpURLConnection;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {

    protected MutableLiveData<Resource<List<Operators>>> loadData(int page) {
        MutableLiveData<Resource<List<Operators>>> itemsList = new MutableLiveData<Resource<List<Operators>>>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitCall retrofitCall = retrofit.create(RetrofitCall.class);
        Call<Root> call = retrofitCall.getItems("Bearer "+ Constants.TOKEN, page);
        call.enqueue(new Callback<Root>() {

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                itemsList.postValue(new Resource().error(t));
            }

            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {

                Resource<List<Operators>> resource = new Resource() ;
                if (response.code() == HttpURLConnection.HTTP_OK){
                    itemsList.postValue(resource.success(response.body().getData().getData()));
                }
                else {
                    itemsList.postValue(resource.error(response));
                }
            }
        });
        return itemsList;
    }
}
