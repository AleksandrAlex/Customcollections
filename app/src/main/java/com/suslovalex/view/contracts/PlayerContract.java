package com.suslovalex.view.contracts;

import android.content.Context;

public interface PlayerContract {

    interface View{
        Context getViewContext();
    }

    interface Presenter{
        void prepareSong();
        void prepareIntentToService();
        void bindPlayService();
        void unbindPlayerService();
        void saveMusic();
        void playMusic();
        void pauseMusic();
        void stopMusic();
        void sendIntentToSelectActivity();
    }
}
