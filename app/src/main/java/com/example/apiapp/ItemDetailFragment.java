package com.example.apiapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.apiapp.databinding.FragmentPostDetailBinding;

public class ItemDetailFragment extends Fragment {

    private FragmentPostDetailBinding binding;
    private PostViewModel postViewModel;
    private Post currentPost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Initialize binding
        binding = FragmentPostDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();  // Return the root view from ViewBinding
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            currentPost = getArguments().getParcelable("post");
        }

        if (currentPost != null) {
            // Bind data to UI elements
            binding.postTitle.setText(currentPost.getTitle());
            binding.postBody.setText(currentPost.getBody());
        }

        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);

        binding.favoritesButton.setOnClickListener(v -> {
            if (currentPost != null) {
                postViewModel.addToFavorites(currentPost);
                Toast.makeText(getContext(), "Post added to favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;  // Prevent memory leaks
    }
}
