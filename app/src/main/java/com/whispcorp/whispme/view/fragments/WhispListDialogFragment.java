package com.whispcorp.whispme.view.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.whispcorp.whispme.R;
import com.whispcorp.whispme.database.entities.Whisp;
import com.whispcorp.whispme.view.adapters.WhispAdapter;

import java.util.ArrayList;
import java.util.List;

import cz.intik.overflowindicator.OverflowPagerIndicator;
import cz.intik.overflowindicator.SimpleSnapHelper;

public class WhispListDialogFragment extends DialogFragment {

    WhispAdapter adapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    OverflowPagerIndicator overflowPagerIndicator;
    boolean seAsignoLayout = false;

    public WhispListDialogFragment() {
        adapter = new WhispAdapter();
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
    }

    public void setWhisps(List<Whisp> whisps) {
        adapter.setWhisps(whisps);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_whisps_detail, container);

        recyclerView = view.findViewById(R.id.recycler_view);
        overflowPagerIndicator = view.findViewById(R.id.view_pager_indicator);

        if (!seAsignoLayout) {
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
            seAsignoLayout = true;
        }

        recyclerView.setAdapter(adapter);

        overflowPagerIndicator.attachToRecyclerView(recyclerView);
        new SimpleSnapHelper(overflowPagerIndicator).attachToRecyclerView(recyclerView);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        seAsignoLayout = false;
        layoutManager = null;
    }

    public void customShow(Whisp whisp, FragmentManager manager, String tag) {
        show(manager, tag);

        int position = adapter.getWhispPosition(whisp);
        if (position != -1) {
            if (layoutManager != null) {
                layoutManager.scrollToPosition(position);
                if (overflowPagerIndicator != null) {
                    overflowPagerIndicator.onPageSelected(position);
                }
            }
        }
    }
}

