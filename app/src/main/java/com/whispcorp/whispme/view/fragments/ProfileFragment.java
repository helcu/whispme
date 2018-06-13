package com.whispcorp.whispme.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.whispcorp.whispme.R;
import com.whispcorp.whispme.view.adapters.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    Context mContext;
    CollapsingToolbarLayout profileCollapsingToolbarLayout;
    ViewPagerAdapter viewPagerAdapter;

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

        Toolbar toolbar = view.findViewById(R.id.profileToolbar);
        profileCollapsingToolbarLayout = view.findViewById(R.id.profileCollapsingToolbarLayout);
        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());

        /*viewPagerAdapter.addFragment(new AllCoursesFragment(), "All Courses");
        viewPagerAdapter.addFragment(new CoursesCompletedFragment(), "Courses Completed");*/

        profileCollapsingToolbarLayout.setTitle("My title");
        profileCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.space_transparent));
        profileCollapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        profileCollapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.colorPrimaryDark));

        return view;
    }

}
