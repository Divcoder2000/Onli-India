package com.onli.india;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.onli.india.newsapi.Article;
import com.squareup.picasso.Picasso;

public class NewsInfoActivity extends AppCompatActivity {

    TextView title, author, date, content, source;
    Button readMore;
    ImageView imageView;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);

        title = findViewById(R.id.newsInfo_title);
        author = findViewById(R.id.newsInfo_author);
        date = findViewById(R.id.newsInfo_date);
        content = findViewById(R.id.newsInfo_content);
        source = findViewById(R.id.newsInfo_source);

        readMore = findViewById(R.id.newsInfo_readBtn);

        imageView = findViewById(R.id.newsInfo_image);

        toolbar = findViewById(R.id.newsInfo_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        Intent intent = getIntent();
        Article article = (Article) intent.getSerializableExtra("article");

        title.setText(article.getTitle());
        author.setText("Written: "+article.getAuthor());
        date.setText("Published: "+article.getPublishedAt().replace("T", " ").replace("Z", ""));
        content.setText(article.getDescription());
        source.setText(article.getSource().getName());

        readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
                startActivity(i);
            }
        });

        Picasso.get()
                .load(article.getUrlToImage())
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewsInfoActivity.this, ImageViewActivity.class);
                i.putExtra("img", article.getUrlToImage());
                startActivity(i);
            }
        });

    }
}