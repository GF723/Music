package com.example.mymusicdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Musices extends AppCompatActivity {
    List<File> fileList_music= new ArrayList<>();
    ListView listView;
    MusicFilter musicFilter = new MusicFilter();
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musices);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle("我的音乐");
        listView = (ListView) findViewById(R.id.music_listview);
        initAcvtity();
        startPlaying();

        //setSupportActionBar(toolbar);
//        getSupportActionBar();
//        setSupportActionBar();
    }

    public void initAcvtity() {
        File musicFile = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        String str = musicFile.getAbsolutePath();
        Log.d("str:",str);
        File[] files = musicFile.listFiles();
        Log.d("musicFile:",""+files.length);
        //File[] files = musicFile.listFiles();
        //String musicName = files[0].getAbsolutePath().toString();
        //Log.d("music:",musicName);
        //File[] fileList = musicFile.listFiles();
        for(File file: musicFile.listFiles()) {
            if (musicFilter.accept(musicFile,file.getName())) {
                fileList_music.add(file);
            }
        }
        Log.d("fileList_music:",""+fileList_music.size());
        MusicAdapter musicAdapter = new MusicAdapter(this,R.layout.list_view_music,fileList_music);
        Log.d("number:",""+musicAdapter.getCount());
        listView.setAdapter(musicAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File file = fileList_music.get(position);
                Intent intent = new Intent(Musices.this,MyService.class);
                intent.putExtra("musicPath",file.getAbsolutePath().toString());
                startService(intent);
                TextView playingMusicName = (TextView)findViewById(R.id.playing_title);
                TextView playingMusicAuthor = (TextView)findViewById(R.id.playing_author);
                playingMusicName.setText(file.getName());
                MediaMetadataRetriever mp = new MediaMetadataRetriever();
                mp.setDataSource(file.getPath());
                String author = mp.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                if(author == null) {
                    playingMusicAuthor.setText("未知");
                } else {
                    playingMusicAuthor.setText(author);
                }
            }
        });
    }

    public void startPlaying() {
        relativeLayout = (RelativeLayout)findViewById(R.id.music_playing);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Musices.this,MusicPlaying.class);
                startActivity(intent);
            }
        });
    }
}