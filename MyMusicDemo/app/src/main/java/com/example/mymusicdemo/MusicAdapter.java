package com.example.mymusicdemo;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends ArrayAdapter<File> {
    private int viewId;
    public MusicAdapter(Context context, int Id, List<File> listFile) {
        super(context,Id,listFile);
        viewId = Id;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        File file = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(viewId,parent,false);
        TextView textViewMusicName = (TextView)view.findViewById(R.id.textview_music_name);
        TextView textViewMusicAuthor = (TextView)view.findViewById(R.id.textview_author);
        textViewMusicName.setText(file.getName());

        MediaMetadataRetriever mp = new MediaMetadataRetriever();
        mp.setDataSource(file.getPath());
        String author = mp.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        if(author == null) {
            textViewMusicAuthor.setText("未知");
        } else {
            textViewMusicAuthor.setText(author);
        }
        return view;
    }

    //    public List<File> listMusic;
//    public LayoutInflater layoutInflater;
//
//    public MusicAdapter (LayoutInflater layoutInflater ,List<File> listMusic) {
//        this.layoutInflater = layoutInflater;
//        this.listMusic = listMusic;
//    }
//
//    @Override
//    public int getCount() {
//        return listMusic.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return listMusic.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//         if(convertView == null) {
//                viewHolder = new ViewHolder();
//                convertView = layoutInflater.inflate(R.layout.list_view_music,null);
//                viewHolder.musicAuthor = (TextView) convertView.findViewById(R.id.textview_author);
//                viewHolder.musicName = (TextView) convertView.findViewById(R.id.textview_music_name);
//                convertView.setTag(viewHolder);
//        }
//
//        viewHolder = (ViewHolder)convertView.getTag();
//        viewHolder.musicName.setText(listMusic.get(position).getName());
//
//        MediaMetadataRetriever mp = new MediaMetadataRetriever();
//        mp.setDataSource(listMusic.get(position).getPath());
//        String author = mp.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
//        if(author == null) {
//            viewHolder.musicAuthor.setText("未知");
//        } else {
//            viewHolder.musicAuthor.setText(author);
//        }
//        return null;
//    }
//
//    class ViewHolder {
//        TextView musicName;
//        TextView musicAuthor;
//    }


}
