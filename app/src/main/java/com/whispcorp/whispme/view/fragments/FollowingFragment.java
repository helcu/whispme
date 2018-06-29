package com.whispcorp.whispme.view.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import com.whispcorp.whispme.network.modelService.FollowingResponse;
import com.whispcorp.whispme.util.Constants;
import com.whispcorp.whispme.util.SharedPreferencesUtil;
import com.whispcorp.whispme.view.adapters.FollowingAdapter;
import com.whispcorp.whispme.viewmodels.FollowingViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowingFragment extends Fragment {

    FollowingViewModel followingViewModel;
    FollowingAdapter followingAdapter = new FollowingAdapter();
    RecyclerView followingRecyclerView;

    public FollowingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        followingViewModel = new ViewModelProviders().of(this).get(FollowingViewModel.class);

        followingViewModel.getFollowingLiveData().observe(this, new Observer<List<FollowingResponse>>() {
            @Override
            public void onChanged(@Nullable List<FollowingResponse> followingResponses) {
                Log.d("Observer call", "Observer activated");
                    followingAdapter.setFollowingList(followingResponses);
            }
        });

        followingViewModel.initFollowing(SharedPreferencesUtil.getValue(Constants.SharedPreferencesConstant.USER_ID, ""));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_following, container, false);

        followingRecyclerView = view.findViewById(R.id.followingRecyclerView);
        followingRecyclerView.setAdapter(followingAdapter);
        followingRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

}
