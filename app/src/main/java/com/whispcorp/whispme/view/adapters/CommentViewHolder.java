package com.whispcorp.whispme.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.whispcorp.whispme.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentViewHolder extends RecyclerView.ViewHolder {
    public CircleImageView profileCircleImageView;
    public TextView commentTextView;

    public CommentViewHolder(View itemView) {
        super(itemView);
        profileCircleImageView = itemView.findViewById(R.id.profileCircleImageView);
        commentTextView = itemView.findViewById(R.id.commentTextView);
    }
}
