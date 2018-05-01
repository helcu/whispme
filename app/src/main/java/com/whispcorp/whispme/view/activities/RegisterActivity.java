package com.whispcorp.whispme.view.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.whispcorp.whispme.R;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText usernameTextInputEditText;
    TextInputEditText emailTextInputEditText;
    TextInputEditText passwordTextInputEditText;
    TextInputEditText confirmPasswordTextInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameTextInputEditText = findViewById(R.id.usernameTextInputEditText);
        emailTextInputEditText = findViewById(R.id.emailTextInputEditText);
        passwordTextInputEditText = findViewById(R.id.passwordTextInputEditText);
        confirmPasswordTextInputEditText = findViewById(R.id.confirmPasswordTextInputEditText);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    //TODO: Added services of registered
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
