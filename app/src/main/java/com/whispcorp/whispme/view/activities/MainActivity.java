package com.whispcorp.whispme.view.activities;

import android.Manifest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.franmontiel.fullscreendialog.FullScreenDialogFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.whispcorp.whispme.R;
import com.whispcorp.whispme.database.entities.User;
import com.whispcorp.whispme.database.entities.Whisp;
import com.whispcorp.whispme.util.Constants;
import com.whispcorp.whispme.util.SharedPreferencesUtil;
import com.whispcorp.whispme.util.UUIDGenerator;
import com.whispcorp.whispme.view.adapters.ViewPagerAdapter;
import com.whispcorp.whispme.view.fragments.*;
import com.whispcorp.whispme.util.ViewPagerCustom;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder;
import cafe.adriel.androidaudiorecorder.model.AudioChannel;
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate;
import cafe.adriel.androidaudiorecorder.model.AudioSource;

public class MainActivity extends AppCompatActivity {

    Context mContext = this;
    ViewPagerCustom viewPager;
    MapMenuFragment map_menu_rag;
    NotificationFragment notificationFragmentAdapter;
    UserFragment userFragmentAdapter;
    ProfileFragment profileFragmentAdapter;
    FloatingActionButton voiceFab, textFab, photoFab;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFloatingActionButtons();

        mProgress = new ProgressDialog(this);

        viewPager = findViewById(R.id.viewpager);
        viewPager.setPagingEnabled(false);

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (prevMenuItem != null) {
//                    prevMenuItem.setChecked(false);
//                }
//                else
//                {
//                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
//                }
//                Log.d("page", "onPageSelected: "+position);
//                bottomNavigationView.getMenu().getItem(position).setChecked(true);
//                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


        setupViewPager(viewPager);

        SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("HOME", R.drawable.ic_home));
        spaceNavigationView.addSpaceItem(new SpaceItem("NOTIFICATION", R.drawable.ic_flash));
        spaceNavigationView.addSpaceItem(new SpaceItem("USER", R.drawable.ic_bell));
        spaceNavigationView.addSpaceItem(new SpaceItem("WORLD", R.drawable.ic_person_black_24dp));
        spaceNavigationView.setCentreButtonIcon(R.drawable.ic_record_voice_over);
        spaceNavigationView.showIconOnly();


        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                toggleFabsVisibility();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                switch (itemIndex) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        break;
                    case 3:
                        viewPager.setCurrentItem(3);
                        break;
                }


                Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });


        //Disable ViewPager Swipe
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.d("GGx", "MainActivity: onRequestPermissionsResult: request: " + requestCode + " result: " + grantResults[0]);

        switch (requestCode) {
            case Constants.RequestCode.RECORD_AUDIO:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestWritePermission();
                }
                break;
            case Constants.RequestCode.WRITE_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    recordAudio(this.getCurrentFocus());
                }
                break;
            default:
                //runtimePermissions();
                Toast.makeText(mContext, "Whispme funcionará de forma limitada.", Toast.LENGTH_SHORT).show();
                break;
        }

        /*if (requestCode == Constants.RequestCode.LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestWritePermission();
            }
        } else {
            //runtimePermissions();
            Toast.makeText(mContext, "Whispme funcionará de forma limitada.", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == Constants.RequestCode.WRITE_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                recordAudio(this.getCurrentFocus());
            }
        } else {
            //runtimePermissions();
            Toast.makeText(mContext, "Whispme funcionará de forma limitada.", Toast.LENGTH_SHORT).show();
        }*/
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RequestCode.WHISP_RECORDED_AUDIO) {
            if (resultCode == RESULT_OK) {
                uploadWhisp();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Audio was not recorded", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        map_menu_rag = new MapMenuFragment();
        notificationFragmentAdapter = new NotificationFragment();
        userFragmentAdapter = new UserFragment();
        profileFragmentAdapter = new ProfileFragment();
        adapter.addFragment(map_menu_rag);
        adapter.addFragment(notificationFragmentAdapter);
        adapter.addFragment(userFragmentAdapter);
        adapter.addFragment(profileFragmentAdapter);
        viewPager.setAdapter(adapter);
    }

    private void setupFloatingActionButtons() {
        voiceFab = findViewById(R.id.voiceFab);
        textFab = findViewById(R.id.textFab);
        photoFab = findViewById(R.id.photoFab);

        voiceFab.setOnClickListener(view -> {
            requestPermission();//this, Manifest.permission.RECORD_AUDIO, Constants.RequestCode.RECORD_AUDIO);
            //Toast.makeText(MainActivity.this, "voiceFab", Toast.LENGTH_SHORT).show();
            toggleFabsVisibility();
        });

        textFab.setOnClickListener(view -> {
            //Toast.makeText(MainActivity.this, "textFab", Toast.LENGTH_SHORT).show();


        });

        photoFab.setOnClickListener(view -> {
            //Toast.makeText(MainActivity.this, "photoFab", Toast.LENGTH_SHORT).show();
            toggleFabsVisibility();
        });
    }

    private void toggleFabsVisibility() {
        if (voiceFab.isShown()) {
            voiceFab.setVisibility(View.GONE);
            textFab.setVisibility(View.GONE);
            photoFab.setVisibility(View.GONE);
        } else {
            voiceFab.setVisibility(View.VISIBLE);
            textFab.setVisibility(View.VISIBLE);
            photoFab.setVisibility(View.VISIBLE);
        }
    }

    public void recordAudio(View v) {
        AndroidAudioRecorder.with(this)
                // Required
                .setFilePath(Constants.Default.WHISP_AUDIO_FILE_PATH)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setRequestCode(Constants.RequestCode.WHISP_RECORDED_AUDIO)

                // Optional
                .setSource(AudioSource.MIC)
                .setChannel(AudioChannel.STEREO)
                .setSampleRate(AudioSampleRate.HZ_48000)
                .setAutoStart(false)
                .setKeepDisplayOn(true)

                // Start recording
                .record();
    }

    public void requestPermission() {//Activity activity, String permission, int requestCode) {
        /*if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                new AlertDialog.Builder(mContext)
                        .setCancelable(true)
                        .setTitle(Constants.Message.PERMISSION_RATIONALE_RECORD_AUDIO_TITLE)
                        .setMessage(Constants.Message.PERMISSION_RATIONALE_RECORD_AUDIO_MESSAGE)
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> requestPermissions(
                                new String[]{Manifest.permission.RECORD_AUDIO}, // Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE
                                Constants.RequestCode.RECORD_AUDIO))
                        .show();

            } else {
                requestWritePermission();
            }
        }
    }

    public void requestWritePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                new AlertDialog.Builder(mContext)
                        .setCancelable(true)
                        .setTitle(Constants.Message.PERMISSION_RATIONALE_WRITE_EXTERNAL_STORAGE_TITLE)
                        .setMessage(Constants.Message.PERMISSION_RATIONALE_WRITE_EXTERNAL_STORAGE_MESSAGE)
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> requestPermissions(
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, // Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE
                                Constants.RequestCode.WRITE_EXTERNAL_STORAGE))
                        .show();

            } else {
                recordAudio(this.getCurrentFocus());
            }
        }
    }

    private void uploadWhisp() {

        mProgress.setMessage("Creating your whisp!");
        mProgress.show();

        StorageReference mStorage = FirebaseStorage.getInstance().getReference();

        final String audioName = UUIDGenerator.generateId();
        StorageReference filepath = mStorage.child("whisps_audios").child(audioName + ".mp3");

        Uri recordedWhispUri = Uri.fromFile(new File(Constants.Default.WHISP_AUDIO_FILE_PATH));

        filepath.putFile(recordedWhispUri)
                .addOnSuccessListener(taskSnapshot -> {

                    StorageReference storageRef = FirebaseStorage.getInstance()
                            .getReference().child("whisps_audios/" + audioName + ".mp3");

                    storageRef.getDownloadUrl().addOnSuccessListener(downloadUrl -> {
                        String userId = SharedPreferencesUtil.getValue("userId", "1");
                        Log.d("ggx", "URL: (AFTER) " + downloadUrl.toString());

                        Whisp whisp = new Whisp();
                        whisp.setOwner(new User());
                        whisp.setOwnerServerId("");
                        whisp.setType("audio");
                        whisp.setContent(downloadUrl.toString());
                        whisp.setTitle("My Whisp");
                        whisp.setLatitude(MapMenuFragment.LATITUDE);
                        whisp.setLongitude(MapMenuFragment.LONGITUDE);
                        apiUploadWhisp(whisp);

                    }).addOnFailureListener(e ->
                            Toast.makeText(mContext,
                                    e.getMessage(),
                                    Toast.LENGTH_SHORT).show());
                });
    }

    private void apiUploadWhisp(Whisp whisp) {
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("owner", "5ae935c7a12114001036bbe5");//whisp.getOwner().toString());
            jsonObject.put("type", whisp.getType());
            jsonObject.put("content", whisp.getContent());
            jsonObject.put("title", whisp.getTitle());
            jsonObject.put("place", "myPlace");
            jsonObject.put("latitude", whisp.getLatitude());
            jsonObject.put("longitude", whisp.getLongitude());

        } catch (JSONException e) {
            //"SIGN IN ERROR";
            e.printStackTrace();
            return;
        }

        AndroidNetworking.post("https://whispme-202021.appspot.com/api/whisp/")
                .addJSONObjectBody(jsonObject)
                .setTag("TAG")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response == null) {
                                Toast.makeText(mContext, "RESPONSE NULL :(", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "El whisp ha sido creado :)", Toast.LENGTH_SHORT).show();
                                Log.d("ggx", "Main: POST: " + response);
                            }
                        } catch (Exception e) {
                            //"SIGN IN ERROR";
                            Toast.makeText(mContext, "ERROR?", Toast.LENGTH_SHORT).show();
                            Log.d("ggx", "Main: POST: CATCH: " + e.getMessage());
                            e.printStackTrace();
                        } finally {
                            mProgress.dismiss();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(mContext, "ERROR?", Toast.LENGTH_SHORT).show();
                        Log.d("ggx", "Main: POST: ERROR: " + anError.getErrorCode());
                        Log.d("ggx", "Main: POST: ERROR: " + anError.getErrorBody());
                        Log.d("ggx", "Main: POST: ERROR: " + anError.getErrorDetail());
                        mProgress.dismiss();
                    }
                });
    }
}
