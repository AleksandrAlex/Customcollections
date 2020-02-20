package com.suslovalex.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.suslovalex.customcollections.R;
import com.suslovalex.model.Song;


import java.util.List;

public class SelectSongRecyclerAdapter extends RecyclerView.Adapter<SelectSongRecyclerAdapter.SelectSongViewHolder> {

    private List<Song> mSongs;

    public SelectSongRecyclerAdapter(List<Song> songs) {
        mSongs = songs;
    }

    @NonNull
    @Override
    public SelectSongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_item_recycler_view, parent, false);
        return new SelectSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectSongViewHolder holder, int position) {
        Song song = mSongs.get(position);
        String textValue = song.getArtist() + " - " + song.getTitle() + " - " + song.getGenre();
        holder.artist.setText(textValue);

    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public List<Song> getSongs() {
        return mSongs;
    }

    public void setSongs(List<Song> songs) {
        mSongs = songs;
    }

    public static class SelectSongViewHolder extends RecyclerView.ViewHolder {

        private TextView artist;

        public SelectSongViewHolder(@NonNull View itemView) {
            super(itemView);
            artist = itemView.findViewById(R.id.item_song);
        }
    }

}
