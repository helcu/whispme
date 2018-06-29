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
import android.widget.TextView;

import com.whispcorp.whispme.R;
import com.whispcorp.whispme.util.Constants;
import com.whispcorp.whispme.util.SharedPreferencesUtil;
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
    TextView profileNameTextView, profileUsernameTextView;

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

        String username = SharedPreferencesUtil.getValue(Constants.SharedPreferencesConstant.USER_NAME, "John Doe");
        String email = SharedPreferencesUtil.getValue(Constants.SharedPreferencesConstant.USER_EMAIL, "john.doe@gmail.com");

        profileNameTextView = view.findViewById(R.id.profileNameTextView);
        profileUsernameTextView = view.findViewById(R.id.profileUsernameTextView);
        profileNameTextView.setText(username);
        profileUsernameTextView.setText(email);

        profileTabLayout = view.findViewById(R.id.profileTabLayout);
        profileViewPager = view.findViewById(R.id.profileViewPager);
        profileCollapsingToolbarLayout = view.findViewById(R.id.profileCollapsingToolbarLayout);
        profileViewPagerAdapter = new ViewPagerAdapter(getFragmentManager());

        profileViewPagerAdapter.addFragment(new FollowersFragment(), "Followers");
        profileViewPagerAdapter.addFragment(new FollowingFragment(), "Folowing");

        profileCollapsingToolbarLayout.setTitle(username);
        profileCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.space_transparent));
        profileCollapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        profileCollapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.colorPrimaryDark));

        profileViewPager.setAdapter(profileViewPagerAdapter);
        profileTabLayout.setupWithViewPager(profileViewPager);

        return view;
    }

}
