package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.itubeapp.databinding.ActivityPlaylistBinding;

public class PlaylistActivity extends AppCompatActivity {
    ActivityPlaylistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaylistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("My Playlist");

        DBHelper helper = new DBHelper(this);
        binding.playlistRv.setLayoutManager(new LinearLayoutManager(this));
        binding.playlistRv.setAdapter(new PlaylistAdapter(this, helper.getPlaylist()));
    }
}