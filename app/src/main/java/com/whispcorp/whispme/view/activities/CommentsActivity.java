package com.whispcorp.whispme.view.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.whispcorp.whispme.R;
import com.whispcorp.whispme.database.entities.firebase.FirebaseComment;
import com.whispcorp.whispme.database.entities.firebase.FirebaseWhisp;
import com.whispcorp.whispme.util.Constants;
import com.whispcorp.whispme.util.SharedPreferencesUtil;
import com.whispcorp.whispme.view.adapters.CommentViewHolder;

import java.util.Objects;

public class CommentsActivity extends AppCompatActivity {

    Context context = this;
    DatabaseReference reference;
    RecyclerView commentRecyclerView;
    Button addCommentButton;
    Toolbar commentToolbar;
    EditText commentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        Intent intent = getIntent();
        FirebaseWhisp firebaseWhisp = (FirebaseWhisp) Objects.requireNonNull(intent.getExtras()).get(Constants.Extra.MAIN_COMMENTS);
        commentRecyclerView = findViewById(R.id.commentRecyclerView);
        addCommentButton = findViewById(R.id.addCommentButton);
        commentToolbar = findViewById(R.id.commentToolbar);
        commentEditText = findViewById(R.id.commentEditText);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        commentRecyclerView.setLayoutManager(linearLayoutManager);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("whisps");
        reference.child(firebaseWhisp.getServerId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    reference.child(firebaseWhisp.getServerId()).setValue(firebaseWhisp);
                }

                DatabaseReference commentsReference = dataSnapshot.getRef().child("comments");
                FirebaseRecyclerAdapter<FirebaseComment, CommentViewHolder> commentViewHolderFirebaseRecyclerAdapter;
                commentViewHolderFirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FirebaseComment, CommentViewHolder>(
                        FirebaseComment.class, R.layout.item_comment, CommentViewHolder.class, commentsReference
                ) {
                    @Override
                    protected void populateViewHolder(CommentViewHolder viewHolder, FirebaseComment model, int position) {
                        viewHolder.commentTextView.setText(model.getContent());
                        // TODO: consuming and endpoint to get owner's url image
                    }
                };
                commentRecyclerView.setAdapter(commentViewHolderFirebaseRecyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "We have some problem, please try in a few minutes.", Toast.LENGTH_SHORT);
            }
        });

        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!commentEditText.getText().toString().equals("")) {
                    String commentId = reference.push().getKey();
                    FirebaseComment firebaseComment = new FirebaseComment();
                    firebaseComment.setServerId(commentId);
                    firebaseComment.setOwnerId(SharedPreferencesUtil.getValue(Constants.SharedPreferencesConstant.USER_ID, null));
                    firebaseComment.setWhispId(firebaseWhisp.getServerId());
                    firebaseComment.setContent(commentEditText.getText().toString());
                    reference.child(firebaseWhisp.getServerId()).child("comments").child(commentId).setValue(firebaseComment);

                    commentEditText.setText(null);
                }
            }
        });
    }
}
