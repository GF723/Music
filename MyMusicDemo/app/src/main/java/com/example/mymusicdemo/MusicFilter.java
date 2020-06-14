package com.example.mymusicdemo;

import java.io.File;
import java.io.FilenameFilter;

public class MusicFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith("mp3");
    }
}
