package com.suslovalex.view.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.suslovalex.customcollections.R;
import com.suslovalex.service.ServicePlayer;

import static com.suslovalex.view.activity.PlayerActivity.SONG;

public class PlayerFragment extends Fragment implements View.OnClickListener {

    private TextView mSong;
    private TextView mArtist;
    private TextView mGenre;
    private Button mPlay;
    private Button mPause;
    private Button mStop;
    private Intent mIntent;
    private ServicePlayer servicePlayer;
    private boolean mBound = false;
    private int mSongId;

    public void setSongId(int songId) {
        mSongId = songId;
    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ServicePlayer.PlayerBinder playerBinder = (ServicePlayer.PlayerBinder) service;
            servicePlayer = playerBinder.getPlayer();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;

        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment, container, false);
        bindViews(v);
        mIntent = new Intent(getContext(),ServicePlayer.class);
        mIntent.putExtra(SONG, R.raw.kassabian_fire);


        getContext().bindService(mIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
        return v;
    }
    private void bindViews(View view) {
        mSong = view.findViewById(R.id.song);
        mArtist = view.findViewById(R.id.artist);
        mGenre = view.findViewById(R.id.genre);
        mPlay = view.findViewById(R.id.playBtn);
        mPause = view.findViewById(R.id.pauseBtn);
        mStop = view.findViewById(R.id.stopBtn);

        mPlay.setOnClickListener(this);
        mStop.setOnClickListener(this);
        mPause.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playBtn:
             //  mPlay();

                servicePlayer.playMusic();
               //getContext().startForegroundService(new Intent(getContext(), ServicePlayer.class));
                break;

            case R.id.pauseBtn:
           //   mPause();
                //servicePlayer.pauseMusic();
                break;

            case R.id.stopBtn:
              //  mStop();
                servicePlayer.stopMusic();
                //servicePlayer.stopMusic();
               // getContext().stopService(new Intent(getContext(), ServicePlayer.class));
                break;
        }
    }

//   public void mPlay (){
//       if (mediaPlayer == null){
//           mediaPlayer = MediaPlayer.create(getActivity(),R.raw.kassabian_fire);
//       }
//       mediaPlayer.start();
//   }
//   public void mPause(){
//       if (mediaPlayer!=null) {
//           mediaPlayer.mPause();
//       }
//   }
//   public  void mStop(){
//       if (mediaPlayer!=null) {
//           mediaPlayer.release();
//           mediaPlayer=null;
//       }
  //  }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBound) {
            getContext().unbindService(mServiceConnection);
            mBound = false;
        }
    }
}
