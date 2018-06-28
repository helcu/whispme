package com.whispcorp.whispme.view.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whispcorp.whispme.R;
import com.whispcorp.whispme.network.modelService.FollowersResponse;
import com.whispcorp.whispme.util.Constants;
import com.whispcorp.whispme.util.SharedPreferencesUtil;
import com.whispcorp.whispme.view.adapters.FollowersAdapter;
import com.whispcorp.whispme.viewmodels.FollowersViewModel;

import java.util.List;

public class FollowersFragment extends Fragment {

    Context mContext;
    FollowersViewModel followersViewModel;
    FollowersAdapter followersAdapter = new FollowersAdapter();
    RecyclerView followersRecyclerView;

    public FollowersFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        followersViewModel = new ViewModelProviders().of(this).get(FollowersViewModel.class);

        followersViewModel.getFollowersLiveData().observe(this, new Observer<List<FollowersResponse>>() {
            @Override
            public void onChanged(@Nullable List<FollowersResponse> followersResponses) {
                Log.d("Observer call", "Observer activated");
                    followersAdapter.setFollowerList(followersResponses);
            }
        });

        followersViewModel.initFollowers(SharedPreferencesUtil.getValue(Constants.SharedPreferencesConstant.USER_ID, ""));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_followers, container, false);

        followersRecyclerView = view.findViewById(R.id.followersRecyclerView);
        followersRecyclerView.setAdapter(followersAdapter);
        followersRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

}
