package com.whispcorp.whispme.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.whispcorp.whispme.R;
import com.whispcorp.whispme.network.modelService.FollowingResponse;

import java.util.ArrayList;
import java.util.List;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.FollowViewHolder> {
    List<FollowingResponse> followingList = new ArrayList<>();

    @NonNull
    @Override
    public FollowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FollowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follow, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FollowViewHolder holder, int position) {
        if (!followingList.get(position).getFollowed().getPhoto().isEmpty()) {
            Picasso.get()
                    .load(followingList.get(position).getFollowed().getPhoto())
                    .resize(holder.profileImageView.getWidth(), holder.profileImageView.getHeight())
                    .centerCrop()
                    .into(holder.profileImageView);
        }
        holder.emailTextView.setText(followingList.get(position).getFollowed().getEmail());
        holder.userTextView.setText(followingList.get(position).getFollowed().getUsername());
    }

    @Override
    public int getItemCount() {
        return followingList.size();
    }

    public List<FollowingResponse> getFollowingList() {
        return followingList;
    }

    public void setFollowingList(List<FollowingResponse> followingList) {
        this.followingList = followingList;
        notifyDataSetChanged();
    }

    public class FollowViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImageView;
        TextView userTextView;
        TextView emailTextView;

        public FollowViewHolder(View itemView) {
            super(itemView);
            profileImageView = itemView.findViewById(R.id.profileCircleImageView);
            userTextView = itemView.findViewById(R.id.usernameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
        }
    }
}
