package com.essam.starterproject.data;

import androidx.lifecycle.LiveData;
import com.essam.starterproject.data.APIResponce.Operators;
import com.essam.starterproject.utils.Resource;

import java.util.List;

public class Repository {
    private APIService apiService = new APIService();

    //we will call this method to get the data
    public LiveData<Resource<List<Operators>>> getItems(int page){
       return apiService.loadData(page);
    }
}
