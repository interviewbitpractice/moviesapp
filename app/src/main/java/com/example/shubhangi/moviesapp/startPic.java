package com.example.shubhangi.moviesapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class startPic extends AppCompatActivity {

    MediaPlayer song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_pic);

        song = MediaPlayer.create(this,R.raw.sound);
        song.setLooping(true);
        song.start();

        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent i = new Intent();
                    i.setClass(startPic.this,MainActivity.class);
                    startActivity(i);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        song.start();
    }
}
