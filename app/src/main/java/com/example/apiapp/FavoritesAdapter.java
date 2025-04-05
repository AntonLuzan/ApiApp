package com.example.apiapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apiclientapp.databinding.ItemPostBinding;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {

    private List<Post> favoritePosts;

    public FavoritesAdapter(List<Post> favoritePosts) {
        this.favoritePosts = (favoritePosts != null) ? favoritePosts : new ArrayList<>();
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FavoriteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        holder.bind(favoritePosts.get(position));
    }

    @Override
    public int getItemCount() {
        return favoritePosts.size();
    }

    public void setData(List<Post> newFavoritePosts) {
        DiffUtil.Callback diffCallback = new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return favoritePosts.size();
            }

            @Override
            public int getNewListSize() {
                return newFavoritePosts.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return favoritePosts.get(oldItemPosition).getId() == newFavoritePosts.get(newItemPosition).getId();
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return favoritePosts.get(oldItemPosition).equals(newFavoritePosts.get(newItemPosition));
            }
        };

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.favoritePosts = newFavoritePosts;
        diffResult.dispatchUpdatesTo(this);
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private final ItemPostBinding binding;

        public FavoriteViewHolder(ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Post post) {
            binding.title.setText(post.getTitle());
            binding.body.setText(post.getBody());
        }
    }
}




