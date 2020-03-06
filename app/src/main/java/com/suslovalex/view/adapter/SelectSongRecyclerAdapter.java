package com.suslovalex.view.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.suslovalex.customcollections.R;
import com.suslovalex.model.Song;
import com.suslovalex.view.activity.PlayerActivity;
import java.util.List;

public class SelectSongRecyclerAdapter extends RecyclerView.Adapter<SelectSongRecyclerAdapter.SelectSongViewHolder> {
    public static final String INTENT_KEY_SONG_ID = "intent_key_song_id";
    private List<Song> mSongs;
    public static final String BROADCAST_ACTION = "com.suslovalex.action.PLAYSONG";

    public SelectSongRecyclerAdapter(List<Song> songs) {
        mSongs = songs;
    }

    @NonNull
    @Override
    public SelectSongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_item_recycler_view, parent, false);

        Log.d(PlayerActivity.MY_LOGS, "SelectSongRecyclerAdapter onCreateViewHolder()");

        return new SelectSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectSongViewHolder holder, int position) {
        final Song song = mSongs.get(position);
        String textValue = song.getArtist() + " - " + song.getTitle() + " - " + song.getGenre();
        holder.artist.setText(textValue);
        Log.d(PlayerActivity.MY_LOGS, "SelectSongRecyclerAdapter onBindViewHolder()");
        holder.artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(v.getContext(), PlayerActivity.class);
                //intent.putExtra(KEY_SONG_ID, song.getId());
                //v.getContext().startActivity(intent);

                Intent intent = new Intent();
                intent.setAction(BROADCAST_ACTION);
                intent.putExtra(INTENT_KEY_SONG_ID, song.getId());
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                v.getContext().sendBroadcast(intent);

            }
        });

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
            Log.d(PlayerActivity.MY_LOGS, "SelectSongViewHolder");
        }
    }

}
