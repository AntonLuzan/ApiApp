package com.example.apiapp;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiInterface {

    // Define the API endpoint
    @GET("posts")  // Assuming you're fetching posts from this endpoint
    Single<List<Post>> getPosts();
}

