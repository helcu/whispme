package com.whispcorp.whispme.view.fragments;


import android.content.Context;
import android.os.Bundle;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    Context mContext;
    int pos_x = 0;
    int pos_y = 0;

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
        RelativeLayout profileRelativeLayout = view.findViewById(R.id.profileRelativeLayout);
        ImageView photoProfileImageView = view.findViewById(R.id.photoProfileImageView);

        /*profileRelativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    *//*case MotionEvent.ACTION_DOWN:
                        pos_x = x - layoutParams.leftMargin;
                        pos_y = y - layoutParams.topMargin;
                        break;

*//*                    case MotionEvent.ACTION_MOVE:
                        int width = v.getLayoutParams().width;
                        int height = v.getLayoutParams().height;
                        v.getLayoutParams().height = v.getLayoutParams().height - 1;
                        v.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
                        break;
                }
                return true;
            }
        });*/


        return view;
    }

}
