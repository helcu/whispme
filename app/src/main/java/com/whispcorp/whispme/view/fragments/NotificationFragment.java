package com.whispcorp.whispme.view.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.whispcorp.whispme.R;
import com.whispcorp.whispme.database.entities.Trend;
import com.whispcorp.whispme.view.adapters.TrendRecyclerAdapter;
import com.whispcorp.whispme.viewmodels.TrendViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private Context mContext;
    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewFragment = inflater.inflate(R.layout.fragment_notification, container, false);

        Toolbar toolbar = (Toolbar) viewFragment.findViewById(R.id.toolbar_tendence);

        toolbar.setTitle("#Tendencias");
        toolbar.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        //activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TrendRecyclerAdapter adapter = new TrendRecyclerAdapter();
        RecyclerView recycler = viewFragment.findViewById(R.id.tendencias_recyclerView);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(mContext));

        TrendViewModel trendiewModel=  new ViewModelProviders().of(this).get(TrendViewModel.class);
        trendiewModel.initData();
        trendiewModel.getTrendList().observe(this, new Observer<List<Trend>>() {
            @Override
            public void onChanged(@Nullable List<Trend> trends) {
                if(trends != null)
                adapter.setTrendList(trends);
            }
        });

        return viewFragment;
    }

}
