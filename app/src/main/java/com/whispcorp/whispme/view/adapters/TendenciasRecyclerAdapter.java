package com.whispcorp.whispme.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whispcorp.whispme.R;
import com.whispcorp.whispme.database.entities.Trend;

import java.util.ArrayList;
import java.util.List;

public class TendenciasRecyclerAdapter extends RecyclerView.Adapter<TendenciasRecyclerAdapter.TendeciasViewHolder> {

    List<Trend> trendList = new ArrayList<>();

    @NonNull
    @Override
    public TendeciasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TendeciasViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_trends_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TendeciasViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return trendList.size();
    }

    public class TendeciasViewHolder extends RecyclerView.ViewHolder {
        public TendeciasViewHolder(View itemView) {
            super(itemView);


        }
    }
}
