package com.onli.india.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.onli.india.NewsInfoActivity;
import com.onli.india.R;
import com.onli.india.newsapi.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    ArrayList<Article> articles;
    Context context;

    public NewsAdapter(Context context, ArrayList<Article> articles) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.title.setText(articles.get(position).getTitle());
        holder.published.setText(articles.get(position).getSource().getName());
        holder.date.setText(articles.get(position).getPublishedAt().replace("T", " ").replace("Z", ""));

        Picasso.get()
                .load(articles.get(position).getUrlToImage())
                .into(holder.imageView);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewsInfoActivity.class);
                intent.putExtra("article", articles.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView title, published, date;
        ImageView imageView;
        ConstraintLayout layout;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.news_title);
            published = itemView.findViewById(R.id.news_published);
            date = itemView.findViewById(R.id.news_date);

            imageView = itemView.findViewById(R.id.news_imageView);

            layout = itemView.findViewById(R.id.news_recycler_layout);

        }
    }
}
