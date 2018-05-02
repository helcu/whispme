package com.whispcorp.whispme.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whispcorp.whispme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorldFragment extends Fragment {

    Context mContext;
    public WorldFragment() {
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
        View viewFragment =  inflater.inflate(R.layout.fragment_world, container, false);
        Toolbar toolbar = (Toolbar) viewFragment.findViewById(R.id.toolbar_notification);

        toolbar.setTitle("Cuenta");
        toolbar.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        return viewFragment;
    }

}
