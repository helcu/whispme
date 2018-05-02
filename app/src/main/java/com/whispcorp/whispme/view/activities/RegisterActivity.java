package com.whispcorp.whispme.view.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.whispcorp.whispme.R;
import com.whispcorp.whispme.database.entities.User;
import com.whispcorp.whispme.network.WhispRemoteProvider;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText usernameTextInputEditText;
    TextInputEditText emailTextInputEditText;
    TextInputEditText passwordTextInputEditText;
    TextInputEditText confirmPasswordTextInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        LinearLayout constraintLayout = findViewById(R.id.registerLinearLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

        usernameTextInputEditText = findViewById(R.id.usernameTextInputEditText);
        emailTextInputEditText = findViewById(R.id.emailTextInputEditText);
        passwordTextInputEditText = findViewById(R.id.passwordTextInputEditText);
        confirmPasswordTextInputEditText = findViewById(R.id.confirmPasswordTextInputEditText);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    User user = new User();
                    user.setUsername(usernameTextInputEditText.getText().toString());
                    user.setEmail(emailTextInputEditText.getText().toString());
                    user.setPassword(passwordTextInputEditText.getText().toString());
                    WhispRemoteProvider.registerUser(user, new WhispRemoteProvider.ProviderRequestListener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject entities) {
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finish();
                        }

                        @Override
                        public void onError(String message) {
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private boolean validate() {
        String username = usernameTextInputEditText.getText().toString().trim();
        String email = emailTextInputEditText.getText().toString().trim();
        String password = passwordTextInputEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordTextInputEditText.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(getBaseContext(), "Please, complete all the information.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getBaseContext(), "This email address is invalid.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.length() <= 4) {
            Toast.makeText(getBaseContext(), "The password is to short.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (confirmPassword.length() <= 4) {
            Toast.makeText(getBaseContext(), "The confirm password is to short.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(getBaseContext(), "The passwords aren't equals.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}
