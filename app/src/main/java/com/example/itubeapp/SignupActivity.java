package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.itubeapp.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.signupBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.etName.getText().toString().trim();
                String username = binding.etUsername.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();
                String confirmPassword = binding.etConfirmPassword.getText().toString().trim();

                if (username.isEmpty()) {
                    binding.etUsername.setError("Username is Required");
                    binding.etUsername.requestFocus();
                    return;
                }
                if (name.isEmpty()) {
                    binding.etName.setError("UserName is Required");
                    binding.etName.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    binding.etPassword.setError("Password is Required");
                    binding.etPassword.requestFocus();
                    return;
                }
                if (confirmPassword.isEmpty()) {
                    binding.etConfirmPassword.setError("Password is Required");
                    binding.etConfirmPassword.requestFocus();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    binding.etConfirmPassword.setError("Password do not match");
                    binding.etConfirmPassword.requestFocus();
                    return;
                }

                User user = new User(name, username, password);
                DBHelper helper = new DBHelper(SignupActivity.this);
                if (helper.userExist(username)) {
                    Toast.makeText(SignupActivity.this, "Username already in use", Toast.LENGTH_SHORT).show();
                } else {
                    helper.createAccount(user);
                    Toast.makeText(SignupActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                    Utils.currentUsername = username;
                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                }
            }
        });
    }

}