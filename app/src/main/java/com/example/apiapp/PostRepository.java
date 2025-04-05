package com.example.apiapp;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostRepository {

    private final PostDao postDao;
    private final ApiInterface apiService;  // Now using ApiInterface instead of ApiService directly
    private final ExecutorService executorService; // For background tasks

    public PostRepository(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        postDao = db.postDao();
        apiService = ApiService.getInstance().getApiInterface();  // Get the ApiInterface from ApiService
        executorService = Executors.newSingleThreadExecutor(); // Executor for DB operations
    }

    // Fetch posts from the API
    public Single<List<Post>> getPosts() {
        return apiService.getPosts()  // Request to API
                .subscribeOn(Schedulers.io())  // Perform network request on I/O thread
                .observeOn(AndroidSchedulers.mainThread())  // Observing on main thread for UI updates
                .doOnError(error -> {
                    // Handle the error (logging, show a message, etc.)
                    // Example: Log the error
                    Log.e("PostRepository", "Error fetching posts", error);
                });
    }

    // Insert post into the database (running in background)
    public void insertToFavorites(Post post) {
        executorService.execute(() -> postDao.insert(post));  // Async insertion to DB
    }

    // Get all favorite posts (returns LiveData for observing changes)
    public LiveData<List<Post>> getAllFavorites() {
        return postDao.getAllPosts(); // Get LiveData of posts from database
    }
}
