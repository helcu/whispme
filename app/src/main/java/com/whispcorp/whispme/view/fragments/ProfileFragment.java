package com.whispcorp.whispme.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whispcorp.whispme.R;
import com.whispcorp.whispme.view.adapters.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    Context mContext;
    CollapsingToolbarLayout profileCollapsingToolbarLayout;
    ViewPagerAdapter profileViewPagerAdapter;
    ViewPager profileViewPager;
    TabLayout profileTabLayout;

    public ProfileFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileTabLayout = view.findViewById(R.id.profileTabLayout);
        profileViewPager = view.findViewById(R.id.profileViewPager);
        profileCollapsingToolbarLayout = view.findViewById(R.id.profileCollapsingToolbarLayout);
        profileViewPagerAdapter = new ViewPagerAdapter(getFragmentManager());

        profileViewPagerAdapter.addFragment(new FollowersFragment(), "Followers\n200");
        profileViewPagerAdapter.addFragment(new FollowingFragment(), "Folowing\n300");

        profileCollapsingToolbarLayout.setTitle("My title");
        profileCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.space_transparent));
        profileCollapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        profileCollapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.colorPrimaryDark));

        profileViewPager.setAdapter(profileViewPagerAdapter);
        profileTabLayout.setupWithViewPager(profileViewPager);

        return view;
    }

}
