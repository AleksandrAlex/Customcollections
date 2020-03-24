package com.suslovalex.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;
import com.suslovalex.customcollections.R;
import com.suslovalex.model.Article;


public class DescriptionNewsActivity extends AppCompatActivity {

    private Article article;
    private TextView descriptionTextView;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.describe_news);
        initViewById();
        getArticles();
        setValues();
    }

    private void setValues() {
        descriptionTextView.setText(article.getDescription());
        Picasso.get()
                .load(article.getUrlToImage())
                .placeholder(R.drawable.ic_file_download_black_24dp)
                .error(R.drawable.ic_error_black_24dp)
                .into(imageView);

    }

    private void getArticles() {
        Intent intent = getIntent();
        article = (Article)intent.getSerializableExtra("article");
    }

    private void initViewById() {
        descriptionTextView = findViewById(R.id.description_text);
        imageView = findViewById(R.id.loaded_image);
    }
}
