package com.whispcorp.whispme.view.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.whispcorp.whispme.R;
import com.whispcorp.whispme.database.entities.Comment;
import com.whispcorp.whispme.database.entities.Whisp;
import com.whispcorp.whispme.util.Constants;
import com.whispcorp.whispme.view.adapters.CommentViewHolder;

import java.util.Objects;

public class CommentsActivity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView commentRecyclerView;
    Button addCommentButton;
    Toolbar commentToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        Intent intent = getIntent();
        Whisp whisp = (Whisp) Objects.requireNonNull(intent.getExtras()).get(Constants.Extra.MAIN_COMMENTS);
        commentRecyclerView = findViewById(R.id.commentRecyclerView);
        addCommentButton = findViewById(R.id.addCommentButton);
        commentToolbar = findViewById(R.id.commentToolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        commentRecyclerView.setLayoutManager(linearLayoutManager);

        /*
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("whisps");
        reference.child(whisp.getServerId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DatabaseReference commentsReference = dataSnapshot.getRef().child("comments");
                FirebaseRecyclerAdapter<Comment, CommentViewHolder> commentViewHolderFirebaseRecyclerAdapter;
                commentViewHolderFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(
                        Comment.class, R.layout.item_comment, CommentViewHolder.class, commentsReference
                ) {
                    @Override
                    protected void populateViewHolder(CommentViewHolder viewHolder, Comment model, int position) {
                        viewHolder.commentTextView.setText(model.getContent());
                        // TODO: consuming and endpoint to get owner's url image
                    }
                };
                commentRecyclerView.setAdapter(commentViewHolderFirebaseRecyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                reference.child(whisp.getServerId()).setValue(whisp);
            }
        });
        */
    }
}
