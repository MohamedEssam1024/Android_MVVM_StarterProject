package com.essam.starterproject.utils;

import com.essam.starterproject.base.APIErrorResponse;
import com.essam.starterproject.data.APIResponce.Root;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Response;

public class Resource <T>{
    private T data;
    private Status status;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public Status getStatus() {
        return status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public enum Status {
        SUCCESS, ERROR
    }

    public Resource<T> success (T data){
        this.data = data;
        status = Status.SUCCESS;
        return this;
    }

    public Resource<T> error (Response response){
        Gson gson = new Gson();
        APIErrorResponse apiErrorResponse = new APIErrorResponse();
        try {
            apiErrorResponse = gson.fromJson(response.errorBody().string(), APIErrorResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        status = Status.ERROR;
        errorMsg = apiErrorResponse.getErrors().get(0).getMsg();
        return this;
    }

    public Resource<T> error (Throwable throwable){
        status = Status.ERROR;
        errorMsg = "Check your internet connection";
        return this;
    }
}
