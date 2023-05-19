package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.itubeapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.etUsername.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();

                if (username.isEmpty()) {
                    binding.etUsername.setError("Username is Required");
                    binding.etUsername.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    binding.etPassword.setError("Password is Required");
                    binding.etPassword.requestFocus();
                    return;
                }

                DBHelper helper = new DBHelper(LoginActivity.this);
                if (!helper.userExist(username)) {
                    binding.etUsername.setError("No user exists with this username");
                    binding.etUsername.requestFocus();
                    return;
                }
                if (!helper.isUsernameAndPasswordValid(username, password)) {
                    binding.etPassword.setError("Incorrect password");
                    binding.etPassword.requestFocus();
                    return;
                }
                Utils.currentUsername = username;
                startActivity(new Intent(LoginActivity.this, MainActivity.class));

            }
        });

        binding.signupBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }
}