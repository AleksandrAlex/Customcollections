package com.suslovalex.view.contracts;



import android.content.Context;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface SelectContract {

    @StateStrategyType(AddToEndSingleStrategy.class)
    interface SelectView extends MvpView {
        Context getViewContext();
        void showChoseSongs();

    }

    interface SelectPresenter {
        void addDataToDB();
        void initialize();
        void prepareSongs();
        void setSelectArtistField(String artist);
        void setSelectGenreField(String genre);
        String[] getArtistArray();
        String [] getGenreArray();
    }
}
