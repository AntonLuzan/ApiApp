package com.example.apiapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostViewModel extends AndroidViewModel {

    private PostRepository postRepository;
    private MutableLiveData<List<Post>> postsLiveData;
    private MutableLiveData<String> errorMessageLiveData; // For error handling
    private CompositeDisposable compositeDisposable; // To manage RxJava disposables

    public PostViewModel(Application application) {
        super(application);
        postRepository = new PostRepository(application);
        postsLiveData = new MutableLiveData<>();
        errorMessageLiveData = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
    }

    public LiveData<List<Post>> getPostsLiveData() {
        return postsLiveData;
    }

    public LiveData<String> getErrorMessageLiveData() {
        return errorMessageLiveData; // To observe error messages
    }

    // Method for adding post to favorites
    public void addToFavorites(Post post) {
        postRepository.insertToFavorites(post);  // Call to insert post in DB
    }

    // Loading posts via RxJava
    public void loadPosts() {
        // Fetch posts from API
        Single<List<Post>> postsSingle = postRepository.getPosts();

        // Subscribe and handle result or error
        compositeDisposable.add(
                postsSingle
                        .subscribeOn(Schedulers.io())  // API call in background thread
                        .observeOn(AndroidSchedulers.mainThread())  // Observing on main thread
                        .subscribe(
                                posts -> {
                                    postsLiveData.setValue(posts);
                                    // Optionally, you can save posts to the local DB for offline use here
                                    for (Post post : posts) {
                                        postRepository.insertToFavorites(post);  // Save posts to DB
                                    }
                                },
                                throwable -> {
                                    // Handle error and propagate to the UI
                                    errorMessageLiveData.setValue("Failed to load posts: " + throwable.getMessage());
                                }
                        )
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // Dispose of any RxJava disposables to prevent memory leaks
        compositeDisposable.clear();
    }
}
