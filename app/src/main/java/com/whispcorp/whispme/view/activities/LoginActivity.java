package com.whispcorp.whispme.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import android.graphics.drawable.AnimationDrawable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.whispcorp.whispme.R;
import com.whispcorp.whispme.network.ApiProvider;
import com.whispcorp.whispme.network.WhispRemoteProvider;
import com.whispcorp.whispme.network.apiService.UserService;
import com.whispcorp.whispme.network.modelService.BaseResponse;
import com.whispcorp.whispme.network.modelService.OwnerResponse;
import com.whispcorp.whispme.util.Constants;
import com.whispcorp.whispme.util.SharedPreferencesUtil;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;

    UserService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LinearLayout constraintLayout = findViewById(R.id.root_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);

        service = ApiProvider.getUserService();

        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(view -> {

            service.loginUser(usernameEditText.getText().toString(), passwordEditText
                    .getText().toString()).enqueue(new Callback<BaseResponse<OwnerResponse>>() {
                @Override
                public void onResponse(Call<BaseResponse<OwnerResponse>> call, Response<BaseResponse<OwnerResponse>> response) {

                    if (response.isSuccessful()) {
                        OwnerResponse item = response.body().getData();
                        SharedPreferencesUtil.setValue(Constants.SharedPreferencesConstant
                                .USER_ID, item.getId());
                        SharedPreferencesUtil.setValue(Constants.SharedPreferencesConstant
                                .USER_NAME, item.getUsername());
                        SharedPreferencesUtil.setValue(Constants.SharedPreferencesConstant
                                .USER_EMAIL, item.getEmail());
                        SharedPreferencesUtil.setValue(Constants.SharedPreferencesConstant
                                .USER_PHOTO, item.getPhoto());
                        SharedPreferencesUtil.setValue(Constants.SharedPreferencesConstant
                                .USER_BIO, item.getBio());

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        finish();
                    } else {

                        Toast.makeText(LoginActivity.this, "Usuario y/o contraseña incorrectos",
                                Toast.LENGTH_SHORT)
                                .show();

                    }

                }

                @Override
                public void onFailure(Call<BaseResponse<OwnerResponse>> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Servicio no disponible", Toast.LENGTH_SHORT)
                            .show();
                }
            });


            /*WhispRemoteProvider.login(usernameEditText.getText().toString(), passwordEditText
                            .getText().toString(),
                    new WhispRemoteProvider.ProviderRequestListener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject entities) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onError(String message) {
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });*/
        });

        TextView registerTextView = findViewById(R.id.registerTextView);
        registerTextView.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this,
                RegisterActivity.class)));
    }

}


