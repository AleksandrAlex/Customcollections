package com.suslovalex.view.contracts;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SelectView extends MvpView {
        void showChoseSongs();
    }

