package com.whispcorp.whispme.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.whispcorp.whispme.R;
import com.whispcorp.whispme.database.entities.Whisp;
import com.whispcorp.whispme.util.Constants;

public class FullscreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        Whisp whisp = (Whisp) getIntent().getExtras().get(Constants.Extra.WHISPADAPTER_FULLSCREENIMAGE);

        ImageView photoImageView = findViewById(R.id.photoImageView);
        Picasso.get()
                .load(whisp.getContent())
                .into(photoImageView);
    }
}
