package com.essam.starterproject.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.essam.starterproject.data.APIResponce.Operators;
import com.essam.starterproject.data.Repository;
import com.essam.starterproject.utils.Constants;
import com.essam.starterproject.utils.Resource;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private Repository repository = new Repository();
    int page = Constants.INITIAL_PAGE;
    public LiveData<Resource<List<Operators>>> getItems(){
        return repository.getItems(page++);
    }

    void reset(){
        page = Constants.INITIAL_PAGE;
    }
}
