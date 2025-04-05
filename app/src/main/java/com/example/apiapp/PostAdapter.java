package com.example.apiapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> posts = new ArrayList<>(); // Initialize with an empty list

    // Constructor
    public PostAdapter(List<Post> posts) {
        if (posts != null) {
            this.posts = posts; // Avoid potential NPE by checking for null
        }
    }

    // Update posts data
    public void updatePosts(List<Post> posts) {
        if (posts != null) {
            this.posts = posts;
            notifyDataSetChanged(); // Notify adapter of data change
        }
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view); // Return the new ViewHolder
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        // Get post at the given position
        Post post = posts.get(position);

        // Set title and body text
        holder.title.setText(post.getTitle());
        holder.body.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        // Return the size of the posts list
        return posts.size();
    }

    // ViewHolder class for Post items
    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView title, body;

        public PostViewHolder(View itemView) {
            super(itemView);
            // Bind the views from the XML layout to the ViewHolder
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }
}
