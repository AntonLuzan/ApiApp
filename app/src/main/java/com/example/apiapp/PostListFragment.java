package com.example.apiapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostListFragment extends Fragment {

    private PostViewModel postViewModel;
    private PostAdapter postAdapter;
    private ProgressBar progressBar;
    private TextView emptyStateMessage;

    public PostListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize the adapter with an empty list
        postAdapter = new PostAdapter(new ArrayList<>());
        recyclerView.setAdapter(postAdapter);

        // Initialize ViewModel
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);

        // Initialize the ProgressBar and Empty State Message
        progressBar = view.findViewById(R.id.progress_bar);
        emptyStateMessage = view.findViewById(R.id.empty_state_message);

        // Observe LiveData from ViewModel
        postViewModel.getPostsLiveData().observe(getViewLifecycleOwner(), posts -> {
            if (posts != null && !posts.isEmpty()) {
                postAdapter.updatePosts(posts);  // Update the adapter with new posts
                progressBar.setVisibility(View.GONE);  // Hide the progress bar once data is loaded
                emptyStateMessage.setVisibility(View.GONE);  // Hide the empty state message
            } else {
                progressBar.setVisibility(View.GONE);  // Hide the progress bar if no posts
                emptyStateMessage.setVisibility(View.VISIBLE);  // Show empty state message
            }
        });

        // Load posts from ViewModel
        progressBar.setVisibility(View.VISIBLE);  // Show the progress bar when loading data
        postViewModel.loadPosts();
    }
}
