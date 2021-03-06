package com.whispcorp.whispme.view.adapters;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.whispcorp.whispme.R;
import com.whispcorp.whispme.database.entities.Whisp;
import com.whispcorp.whispme.util.Constants;

import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class WhispAdapter extends RecyclerView.Adapter<WhispAdapter.WhispViewHolder> {

    private List<Whisp> whisps;
    WhispAdapterClickListener clickListener;


    public WhispAdapter(WhispAdapterClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public int getWhispPosition(Whisp whisp) {
        int index = -1;
        for (int i = 0; i < whisps.size(); i++) {
            if (whisps.get(i).getServerId().equals(whisp.getServerId())) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void setWhisps(List<Whisp> whisps) {
        this.whisps = whisps;
        notifyDataSetChanged();
    }

    @Override
    public WhispViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case Constants.Whisp.TYPE_TEXT_VALUE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_whisp, parent, false);
                break;
            case Constants.Whisp.TYPE_AUDIO_VALUE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_whisp_audio, parent, false);
                break;
            case Constants.Whisp.TYPE_PHOTO_VALUE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_whisp_photo, parent, false);
                break;
        }

        return new WhispViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WhispViewHolder holder, int position) {
        Whisp whisp = whisps.get(position);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("whisps").child(whisp.getServerId()).child("comments");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Long dd = dataSnapshot.getChildrenCount();
                holder.titleTextView.setText(whisp.getTitle());
                holder.likesTextView.setText(whisp.getLikes() + " likes");
                holder.viewsTextView.setText(whisp.getViews() + " views");
                holder.commentsTextView.setText(dataSnapshot.getChildrenCount() + " comments");
                holder.commentsTextView.setOnClickListener(v -> {
                    clickListener.onCommentClicked(whisp);
                });

                holder.profileCircleImageView.setOnClickListener(v -> {
                    clickListener.onProfileCircle(whisp);
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        switch (whisp.getType()) {
            case Constants.Whisp.TYPE_TEXT:
                holder.contentTextView.setText(whisp.getContent());//.substring(0, 5));
                break;
            case Constants.Whisp.TYPE_AUDIO:
                holder.playButton.setOnClickListener(v -> {
                    holder.playButton.setEnabled(false);
                    String url = whisp.getContent();
                        /*MediaPlayer mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.setDataSource(url);
                        mediaPlayer.prepare();
                        mediaPlayer.start();*/

                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mediaPlayer.setDataSource(url);
                        mediaPlayer.prepareAsync();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                                          @Override
                                                          public void onPrepared(MediaPlayer mp) {
                                                              holder.playButton.setEnabled(true);
                                                              mp.start();
                                                              holder.updateTime = new Runnable() {
                                                                  public void run() {
                                                                      holder.whispSeekBar.setProgress(mp.getCurrentPosition());
                                                                      holder.handler.postDelayed(this, 100);
                                                                  }
                                                              };

                                                              holder.whispSeekBar.setMax(mp.getDuration());
                                                              holder.whispSeekBar.setProgress(mp.getCurrentPosition());
                                                              holder.whispSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                                                                  int progress = 0;

                                                                  @Override
                                                                  public void onStopTrackingTouch(SeekBar seekBar) {
                                                                      mp.seekTo(progress);
                                                                      mp.start();
                                                                  }

                                                                  @Override
                                                                  public void onStartTrackingTouch(SeekBar seekBar) {
                                                                      mp.pause();
                                                                  }

                                                                  @Override
                                                                  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                                                      if (fromUser) {
                                                                          this.progress = progress;
                                                                      }
                                                                  }
                                                              });
                                                              holder.handler.postDelayed(holder.updateTime, 100);
                                                          }
                                                      }
                    );
                });
                break;
            case Constants.Whisp.TYPE_PHOTO:
                Picasso.get()
                        .load(whisp.getContent())
                        .resize(50, 50)
                        .centerCrop()
                        .into(holder.photoImageView);
                holder.photoImageView.setOnClickListener(v -> {
                    clickListener.onPhotoClicked(whisp);
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return whisps.size();
    }

    @Override
    public int getItemViewType(int position) {
        int type = -1;
        switch (whisps.get(position).getType()) {
            case Constants.Whisp.TYPE_TEXT:
                type = Constants.Whisp.TYPE_TEXT_VALUE;
                break;
            case Constants.Whisp.TYPE_AUDIO:
                type = Constants.Whisp.TYPE_AUDIO_VALUE;
                break;
            case Constants.Whisp.TYPE_PHOTO:
                type = Constants.Whisp.TYPE_PHOTO_VALUE;
                break;
        }
        return type;
    }

    class WhispViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, contentTextView;
        TextView likesTextView, viewsTextView, commentsTextView;
        Button playButton;
        SeekBar whispSeekBar;
        Handler handler = new Handler();
        Runnable updateTime;
        ImageView photoImageView;
        CircleImageView profileCircleImageView;

        WhispViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.titleTextView);
            contentTextView = view.findViewById(R.id.contentTextView);
            playButton = view.findViewById(R.id.playButton);
            whispSeekBar = view.findViewById(R.id.whispSeekBar);
            photoImageView = view.findViewById(R.id.photoImageView);
            likesTextView = view.findViewById(R.id.likesTextView);
            viewsTextView = view.findViewById(R.id.viewsTextView);
            commentsTextView = view.findViewById(R.id.commentsTextView);
            profileCircleImageView = view.findViewById(R.id.profileCircleImageView);
        }
    }

    public interface WhispAdapterClickListener {
        void onPhotoClicked(Whisp whisp);

        void onCommentClicked(Whisp whisp);

        void onProfileCircle(Whisp whisp);
    }
}