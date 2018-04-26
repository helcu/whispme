package com.whispcorp.whispme.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whispcorp.whispme.R;

public class WhispAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //private String[] colors = new String[]{"#2196F3", "#00BCD4", "#4CAF50", "#CDDC39", "#FFC107", "#FF5722"};
    private int itemCount = 0;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_whisp, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //int color = Color.parseColor(colors[position % colors.length]);
        //holder.itemView.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void updateItemCount(int newCount) {
        itemCount = newCount;
        notifyDataSetChanged();
    }
}