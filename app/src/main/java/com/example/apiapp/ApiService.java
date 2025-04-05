package com.example.apiapp;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";
    private static ApiService instance;
    private final ApiInterface apiInterface;

    private ApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    // Singleton pattern to get the instance of ApiService
    public static ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }
}
