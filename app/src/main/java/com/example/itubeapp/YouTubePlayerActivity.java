package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.jaedongchicken.ytplayer.YoutubePlayerView;
import com.jaedongchicken.ytplayer.YoutubePlayerView.STATE;
import com.jaedongchicken.ytplayer.model.PlaybackQuality;
import com.jaedongchicken.ytplayer.model.YTParams;

public class YouTubePlayerActivity extends AppCompatActivity {
    YoutubePlayerView ytPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);
        ytPlayer = findViewById(R.id.yt_player);

        String url = getIntent().getStringExtra(Utils.URL);
        String videoId;
        if (url.contains("watch")) {
            videoId = url.split("v=")[1];
        } else {
            String[] substrings = url.split("/");
            videoId = substrings[substrings.length - 1];
        }
        ytPlayer.initialize(videoId, new YTParams(), new YoutubePlayerView.YouTubeListener() {
            @Override
            public void onReady() {
            }

            @Override
            public void onStateChange(STATE state) {
            }


            @Override
            public void onPlaybackQualityChange(String arg) {
            }

            @Override
            public void onPlaybackRateChange(String arg) {
            }

            @Override
            public void onError(String arg) {
                Toast.makeText(YouTubePlayerActivity.this, "Invalid URL", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onApiChange(String arg) {
            }

            @Override
            public void onCurrentSecond(double second) {
            }

            @Override
            public void onDuration(double duration) {
            }

            @Override
            public void logs(String log) {
            }
        });
        ytPlayer.play();
    }
}