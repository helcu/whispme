package com.whispcorp.whispme.view.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hluhovskyi.camerabutton.CameraButton;
import com.whispcorp.whispme.R;
import com.whispcorp.whispme.util.Constants;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class CameraActivity extends AppCompatActivity {

    CameraView cameraView=null;
    CameraButton cameraButton=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        cameraView = findViewById(R.id.camera);
        cameraButton = findViewById(R.id.camera_button);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {

                Bitmap imagen = cameraKitImage.getBitmap();
                String route= createImageFromBitmap(imagen);
                Intent intent = new Intent();
                intent.putExtra(Constants.Whisp.TYPE_PHOTO, Constants.Whisp.TYPE_PHOTO);
                intent.putExtra(Constants.Whisp.TYPE_PHOTO_IMAGE,route);
                setResult(RESULT_OK, intent);
                finish();

            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });



        cameraButton.setOnPhotoEventListener(new CameraButton.OnPhotoEventListener() {
            @Override
            public void onClick() {
                cameraView.captureImage();
            }
        });/*
        cameraButton.setOnHoldEventListener(new CameraButton.OnHoldEventListener() {
            @Override public void onStart() {
                startRecordVideo();
            }
            @Override public void onFinish() {
                finishRecordVideo();
            }
            @Override public void onCancel() {
                cancelRecordVideo();
            }
        });
        cameraButton.setOnStateChangeListener(new CameraButton.OnStateChangeListener() {
            @Override public void onStateChanged(@NonNull CameraButton.State state) {
                dispatchStateChange(state);
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    public String createImageFromBitmap(Bitmap bitmap) {
        File directory = getApplicationContext().getDir("imageDir", Context.MODE_PRIVATE);
        File mypath=new File(directory,"image.jpg");
        String fileName = "myImage";//no .png or .jpg needed
        FileOutputStream fos = null;
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            FileOutputStream fo = openFileOutput(fileName, Context.MODE_PRIVATE);

            fo.write(bytes.toByteArray());
            // remember close file output
            fo.close();

            fos = new FileOutputStream(mypath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            fileName = null;
        }
        return directory.getAbsolutePath();
    }

}
