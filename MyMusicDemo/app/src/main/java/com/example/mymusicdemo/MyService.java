package com.example.mymusicdemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class MyService extends Service {
    private MediaPlayer mediaPlayer;
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }

//        File file = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
//        File[] filename = file.listFiles();
        String file0 = intent.getStringExtra("musicPath");
        mediaPlayer.reset();

        try {
            mediaPlayer.setDataSource(file0);
            mediaPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d("MyMusicService","onStartCommand");
                mediaPlayer.start();
            }

        });
        return super.onStartCommand(intent, flags, startId);
    }
}
