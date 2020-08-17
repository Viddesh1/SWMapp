package com.example.simpleapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.media.MediaPlayer;

import android.view.View;
import android.widget.Toast;

public class Mainwhitemusic extends AppCompatActivity {
    MediaPlayer player;
    MediaPlayer player1;
    MediaPlayer player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.white_music);
    }
    public void play(View v) {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.rain);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        player.start();
    }

    public void play1(View v) {
        if (player1 == null) {
            player1 = MediaPlayer.create(this, R.raw.busycafe);
            player1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer1();
                }
            });
        }

        player1.start();
    }

    public void play2(View v) {
        if (player2 == null) {
            player2 = MediaPlayer.create(this, R.raw.campfire);
            player2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer2();
                }
            });
        }

        player2.start();
    }



    public void pause(View v) {
        if (player != null) {
            player.pause();
        }
    }

    public void pause1(View v) {
        if (player1 != null) {
            player1.pause();
        }
    }

    public void pause2(View v) {
        if (player2 != null) {
            player2.pause();
        }
    }

    public void stop(View v) {
        stopPlayer();
    }

    public void stop1(View v) {
        stopPlayer1();
    }

    public void stop2(View v) {
        stopPlayer2();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(this, "Rain white noise is stop", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopPlayer1() {
        if (player1 != null) {
            player1.release();
            player1 = null;
            Toast.makeText(this, "Busy Cafe white noise is stop", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopPlayer2() {
        if (player2 != null) {
            player2.release();
            player2 = null;
            Toast.makeText(this, "Campfire white noise is stop", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }


    protected void onStop1() {
        super.onStop();
        stopPlayer1();
    }

    protected void onStop2() {
        super.onStop();
        stopPlayer2();
    }

}



