package com.suslovalex.view;

import com.suslovalex.model.Article;

import java.util.List;

public interface IBreakingNewsView {
    void showNews(List<Article> articles);
    void showDialogLoading();
    void showDialogError();
    void hideDialog();
}
