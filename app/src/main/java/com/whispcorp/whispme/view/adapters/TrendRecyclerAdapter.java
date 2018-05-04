package com.whispcorp.whispme.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.whispcorp.whispme.R;
import com.whispcorp.whispme.database.entities.Trend;

import java.util.ArrayList;
import java.util.List;

public class TrendRecyclerAdapter extends RecyclerView.Adapter<TrendRecyclerAdapter.TendeciasViewHolder> {

    List<Trend> trendList = new ArrayList<>();

    @NonNull
    @Override
    public TendeciasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TendeciasViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_trends_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TendeciasViewHolder holder, int position) {

        holder.title.setText(trendList.get(position).getTitle());
        holder.place.setText(trendList.get(position).getPlace());
       // holder.time .setText(trendList.get(position).getTimeAsString());
        holder.userName.setText(trendList.get(position).getUserName());
        holder.likeNUmber.setText(String.valueOf(trendList.get(position).getLikes()));
        holder.profile.setImageUrl("https://www.weact.org/wp-content/uploads/2016/10/Blank-profile.png");

    }

    @Override
    public int getItemCount() {
        return trendList.size();
    }

    public class TendeciasViewHolder extends RecyclerView.ViewHolder {

        private ANImageView profile;
        private TextView title;
        private TextView place;
        private TextView time;
        private TextView userName;
        private TextView likeNUmber;


        public TendeciasViewHolder(View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.profile_trend_image);
            title = itemView.findViewById(R.id.title_trend);
            place = itemView.findViewById(R.id.place_trend);
            time  = itemView.findViewById(R.id.time_trend);
            userName = itemView.findViewById(R.id.userName_trend);
            likeNUmber = itemView.findViewById(R.id.likesNumber_trend);
        }
    }

    public List<Trend> getTrendList() {
        return trendList;
    }

    public void setTrendList(List<Trend> trendList) {
        this.trendList = trendList;
        notifyDataSetChanged();
    }
}
