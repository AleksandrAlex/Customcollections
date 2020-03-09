package com.suslovalex.view.contracts;



import android.content.Context;

import com.arellomobile.mvp.MvpView;

public interface SelectContract {

    interface SelectView extends MvpView {
        Context getViewContext();

    }

    interface SelectPresenter {
        void addDataToDB();


    }
}
