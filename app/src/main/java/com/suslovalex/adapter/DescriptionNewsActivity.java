package com.suslovalex.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.suslovalex.customcollections.R;
import com.suslovalex.model.Article;


public class DescriptionNewsActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView nameTextView;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.describe_news);

        titleTextView = findViewById(R.id.title_text);
        nameTextView = findViewById(R.id.name_text);
        descriptionTextView = findViewById(R.id.description_text);

        Intent intent = getIntent();
        Article article = (Article)intent.getSerializableExtra("article");

        titleTextView.setText(article.getTitle());
        nameTextView.setText(article.getSource().getName());
        descriptionTextView.setText(article.getDescription());



    }
}
