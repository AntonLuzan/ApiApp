package com.example.apiapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.apiclientapp.databinding.FragmentFavoritesBinding;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private PostAdapter postAdapter;
    private FavoritesViewModel favoritesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewFavorites.setHasFixedSize(true);  // Оптимизация

        postAdapter = new PostAdapter(new ArrayList<>());
        binding.recyclerViewFavorites.setAdapter(postAdapter);

        favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

        favoritesViewModel.getFavoritePosts().observe(getViewLifecycleOwner(), posts -> {
            if (posts != null) {
                postAdapter.updatePosts(posts);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;  // Предотвращаем утечки памяти
    }
}


