package com.whispcorp.whispme.view.adapters;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.whispcorp.whispme.R;
import com.whispcorp.whispme.database.entities.Whisp;

import java.io.IOException;
import java.util.List;

public class WhispAdapter extends RecyclerView.Adapter<WhispAdapter.WhispViewHolder> {

    //private String[] colors = new String[]{"#2196F3", "#00BCD4", "#4CAF50", "#CDDC39", "#FFC107", "#FF5722"};
    private List<Whisp> whisps;


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
    }

    @Override
    public WhispViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_whisp, parent, false);
        return new WhispViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WhispViewHolder holder, int position) {
        //int color = Color.parseColor(colors[position % colors.length]);
        //holder.itemView.setBackgroundColor(color);
        Whisp whisp = whisps.get(position);
        holder.titleTextView.setText(whisp.getTitle());
        holder.contentTextView.setText(whisp.getContent().substring(0, 5));
        holder.playButton.setOnClickListener(v -> {
            try {
                String url = whisp.getContent();
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return whisps.size();
    }


    class WhispViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, contentTextView;
        Button playButton;

        WhispViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.titleTextView);
            contentTextView = view.findViewById(R.id.contentTextView);
            playButton = view.findViewById(R.id.playButton);
        }
    }
}