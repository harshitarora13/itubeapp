package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.itubeapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListeners();
    }

    private void setListeners() {
        binding.addToPlaylistBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = binding.etUrl.getText().toString().trim();
                if (url.isEmpty()) {
                    binding.etUrl.setError("Enter URL");
                    return;
                }
                DBHelper helper = new DBHelper(MainActivity.this);
                helper.addUrlToPlaylist(url);
                binding.etUrl.setText("");
                Toast.makeText(MainActivity.this, "Added to playlist", Toast.LENGTH_SHORT).show();
            }
        });

        binding.myPlaylistBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PlaylistActivity.class));
            }
        });

        binding.playBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = binding.etUrl.getText().toString().trim();
                if (url.isEmpty()) {
                    binding.etUrl.setError("Enter URL first");
                    return;
                }
                startActivity(new Intent(MainActivity.this, YouTubePlayerActivity.class).putExtra(Utils.URL, url));
            }
        });
    }
}