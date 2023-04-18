package com.chat.app.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;
    public RetrofitService()
    {
        initializeRetrofit();
    }
    private void initializeRetrofit()
    {
        retrofit=new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/").addConverterFactory(GsonConverterFactory.create(new Gson())).build();
    }
    public Retrofit getRetrofit()
    {
        return retrofit;
    }
}
