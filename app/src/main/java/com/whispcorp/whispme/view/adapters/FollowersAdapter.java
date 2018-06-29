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
import com.whispcorp.whispme.network.modelService.FollowersResponse;

import java.util.ArrayList;
import java.util.List;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.FollowViewHolder> {

    List<FollowersResponse> followerList = new ArrayList<>();

    @NonNull
    @Override
    public FollowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FollowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follow, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FollowViewHolder holder, int position) {
        if (!followerList.get(position).getFollower().getPhoto().isEmpty()) {
            Picasso.get()
                    .load(followerList.get(position).getFollower().getPhoto())
                    .resize(holder.profileImageView.getWidth(), holder.profileImageView.getHeight())
                    .centerCrop()
                    .into(holder.profileImageView);
        }
        holder.emailTextView.setText(followerList.get(position).getFollower().getEmail());
        holder.userTextView.setText(followerList.get(position).getFollower().getUsername());
    }

    @Override
    public int getItemCount() {
        return followerList.size();
    }

    public List<FollowersResponse> getFollowerList() {
        return followerList;
    }

    public void setFollowerList(List<FollowersResponse> followerList) {
        this.followerList = followerList;
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
