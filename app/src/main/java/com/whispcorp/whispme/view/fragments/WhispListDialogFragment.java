package com.whispcorp.whispme.view.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.whispcorp.whispme.util.Constants;
import com.whispcorp.whispme.view.activities.CommentsActivity;
import com.whispcorp.whispme.view.activities.FullscreenImageActivity;
import com.whispcorp.whispme.view.activities.UserProfileActivity;
import com.whispcorp.whispme.view.adapters.WhispAdapter;

import java.util.List;

import cz.intik.overflowindicator.OverflowPagerIndicator;
import cz.intik.overflowindicator.SimpleSnapHelper;

public class WhispListDialogFragment extends DialogFragment {

    WhispAdapter adapter;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    OverflowPagerIndicator overflowPagerIndicator;
    boolean seAsignoLayout = false;
    int position = -1;

    public WhispListDialogFragment() {
        adapter = new WhispAdapter(new WhispAdapter.WhispAdapterClickListener() {
            @Override
            public void onPhotoClicked(Whisp whisp) {
                Intent intent = new Intent(getContext(), FullscreenImageActivity.class);
                intent.putExtra(Constants.Extra.MAIN_FULLSCREENIMAGE, whisp);
                startActivity(intent);
            }

            @Override
            public void onCommentClicked(Whisp whisp) {
                Intent intent = new Intent(getContext(), CommentsActivity.class);
                intent.putExtra(Constants.Extra.MAIN_COMMENTS, whisp);
                startActivity(intent);
            }

            @Override
            public void onProfileCircle(Whisp whisp) {
                Intent intent = new Intent(getContext(), UserProfileActivity.class);
                intent.putExtra(Constants.Extra.MAIN_USERPROFILE, whisp);
                startActivity(intent);
            }
        });
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

        /*if (!seAsignoLayout) {
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
            seAsignoLayout = true;
        }*/
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.smoothScrollToPosition(position);

        overflowPagerIndicator.attachToRecyclerView(recyclerView);
        new SimpleSnapHelper(overflowPagerIndicator).attachToRecyclerView(recyclerView);
        overflowPagerIndicator.onPageSelected(position);

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
        position = adapter.getWhispPosition(whisp);
        show(manager, tag);
        /*if (position != -1) {
            if (layoutManager != null) {
                layoutManager.scrollToPosition(position);
            } else {
                layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                seAsignoLayout = true;
            }
        }*/
    }
}

