package com.example.apiapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {

    private final PostRepository postRepository;
    private LiveData<List<Post>> favoritePostsLiveData;

    public FavoritesViewModel(Application application) {
        super(application);
        postRepository = new PostRepository(application);
        favoritePostsLiveData = postRepository.getAllFavorites();
    }

    public LiveData<List<Post>> getFavoritePosts() {
        return favoritePostsLiveData;
    }

    // Метод для обновления списка избранных постов
    public void refreshFavorites() {
        favoritePostsLiveData = postRepository.getAllFavorites();
    }
}
