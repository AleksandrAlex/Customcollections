package com.suslovalex.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.suslovalex.customcollections.R;
import com.suslovalex.model.Article;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private List<Article> mArticles;

    public List<Article> getArticles() {
        return mArticles;
    }

    public void setArticles(List<Article> articles) {
        mArticles = articles;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = mArticles.get(position);
        holder.author.setText("author: "+article.getAuthor());
        holder.title.setText("Title: "+article.getTitle());
        holder.publishedAt.setText("Published at: "+article.getPublishedAt());

    //  holder.author.setOnClickListener(new View.OnClickListener() {
    //      @Override
    //      public void onClick(View v) {
    //          Intent newsDescription = new Intent(v.getContext(), DescriptionNewsActivity.class);
    //          v.getContext().startActivity(newsDescription);
    //      }
    //  });

    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        private TextView author;
        private TextView title;
        private TextView publishedAt;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.authorTextView);
            title = itemView.findViewById(R.id.titleTextView);
            publishedAt = itemView.findViewById(R.id.publishedTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newsDescription = new Intent(v.getContext(), DescriptionNewsActivity.class);
                    v.getContext().startActivity(newsDescription);
                }
            });

        }

    }
}
