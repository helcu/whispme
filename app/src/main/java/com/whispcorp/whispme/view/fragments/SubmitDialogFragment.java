package com.whispcorp.whispme.view.fragments;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.whispcorp.whispme.R;
import com.whispcorp.whispme.database.entities.User;
import com.whispcorp.whispme.database.entities.Whisp;
import com.whispcorp.whispme.util.Constants;
import com.whispcorp.whispme.util.SharedPreferencesUtil;
import com.whispcorp.whispme.util.UUIDGenerator;
import com.whispcorp.whispme.view.activities.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;

import static android.content.Context.MODE_PRIVATE;

public class SubmitDialogFragment extends DialogFragment {

    String image = null;
    Button closeButton = null;
    Button uploadButton = null;
    EditText title = null;
    String type = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private ProgressDialog mProgress;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;

    }

    ImageView previewImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        View rootView = inflater.inflate(R.layout.submitdialog_fragment, container);
        previewImage = (ImageView) rootView.findViewById(R.id.preview);
        if (type.equals("photo")) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getApplicationContext()
                        .openFileInput("myImage"));
                previewImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (type.equals("audio")) {

            previewImage.setVisibility(View.GONE);

        }
        closeButton = rootView.findViewById(R.id.btnCancel);
        uploadButton = rootView.findViewById(R.id.btnPublish);
        title = rootView.findViewById(R.id.title);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (type.equals("photo")) {
                        uploadWhisp();
                    } else if (type.equals("audio")) {
                        uploadWhispAudio();


                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        this.getDialog().setTitle("TV Shows");
        mProgress = new ProgressDialog(getActivity().getApplicationContext());


        return rootView;
    }


    public void uploadWhispAudio() {

        //mProgress.setMessage("Creating your whisp!");
        //mProgress.show();

        StorageReference mStorage = FirebaseStorage.getInstance().getReference();
        final String audioName = UUIDGenerator.generateId();
        StorageReference filepath = mStorage.child("whisps_audios").child(audioName + ".mp3");

        Uri recordedWhispUri = Uri.fromFile(new File(Constants.Default.WHISP_AUDIO_FILE_PATH));

        filepath.putFile(recordedWhispUri)
                .addOnSuccessListener(taskSnapshot -> {

                    StorageReference storageRef = FirebaseStorage.getInstance()
                            .getReference().child("whisps_audios/" + audioName + ".mp3");
                    storageRef.getDownloadUrl().addOnSuccessListener(downloadUrl -> {
                        String userId = SharedPreferencesUtil.getValue(Constants.SharedPreferencesConstant.USER_ID, "1");
                        Log.d("ggx", "URL: (AFTER) " + downloadUrl.toString());
                        Whisp whisp = new Whisp();
                        whisp.setOwner(new User());
                        whisp.setOwnerServerId("");
                        whisp.setType("audio");
                        whisp.setContent(downloadUrl.toString());
                        whisp.setTitle(title.getText().toString());
                        whisp.setLatitude(MapMenuFragment.LATITUDE);
                        whisp.setLongitude(MapMenuFragment.LONGITUDE);
                        ((MainActivity) getActivity()).apiUploadWhisp(whisp, userId);
                        dismiss();
                    }).addOnFailureListener(e ->
                            Toast.makeText(getActivity().getApplicationContext(),
                                    e.getMessage(),
                                    Toast.LENGTH_SHORT).show());
                });
    }

    private void uploadWhisp() throws FileNotFoundException {

        //mProgress.setMessage("Creating your whisp!");
        //mProgress.show();

        StorageReference mStorage = FirebaseStorage.getInstance().getReference();

        final String audioName = UUIDGenerator.generateId();
        StorageReference filepath = mStorage.child("whisps_photos").child(audioName + ".jpg");

        String file_name = getActivity().getApplicationContext().getFilesDir() + "/" + "myImage.jpg";
        File f = new File(image, "image.jpg");
        Uri recordedWhispUri = Uri.fromFile(f);

        filepath.putFile(recordedWhispUri)
                .addOnSuccessListener(taskSnapshot -> {

                    StorageReference storageRef = FirebaseStorage.getInstance()
                            .getReference().child("whisps_photos/" + audioName + ".jpg");

                    storageRef.getDownloadUrl().addOnSuccessListener(downloadUrl -> {
                        String userId = SharedPreferencesUtil.getValue(Constants.SharedPreferencesConstant.USER_ID, "1");
                        Log.d("ggx", "URL: (AFTER) " + downloadUrl.toString());

                        Whisp whisp = new Whisp();
                        whisp.setOwner(new User());
                        whisp.setOwnerServerId("");
                        whisp.setType("photo");
                        whisp.setContent(downloadUrl.toString());
                        whisp.setTitle(title.getText().toString());
                        whisp.setLatitude(MapMenuFragment.LATITUDE);
                        whisp.setLongitude(MapMenuFragment.LONGITUDE);
                        ((MainActivity) getActivity()).apiUploadWhisp(whisp, userId);
                        dismiss();

                    }).addOnFailureListener(e ->
                            Toast.makeText(getActivity().getApplicationContext(),
                                    e.getMessage(),
                                    Toast.LENGTH_SHORT).show());
                });
    }


}
