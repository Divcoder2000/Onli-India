package com.onli.india;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.onli.india.Adapters.NewsAdapter;
import com.onli.india.newsapi.Article;
import com.onli.india.newsapi.NewsApi;
import com.onli.india.newsapi.NewsClient;
import com.onli.india.newsapi.NewsResponse;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsActivity extends AppCompatActivity {

    final String BASE_URL = "https://newsapi.org/v2/";
    NewsApi newsApi;

    RecyclerView recyclerView;
    NewsAdapter newsAdapter;

    MaterialToolbar toolbar;
    LinearLayout layout;

    ArrayList<Article> articles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsApi = NewsClient.getClient(BASE_URL).create(NewsApi.class);

        layout = findViewById(R.id.news_layout);
        toolbar = findViewById(R.id.news_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        recyclerView = findViewById(R.id.news_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getNews();

    }

    public void getNews()
    {
        Call<NewsResponse> call = newsApi.getNews("in", getString(R.string.new_api_key));

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                progressDialog.dismiss();
                if(response.isSuccessful())
                {
                    if(Objects.requireNonNull(response.body()).getStatus().equals("ok"))
                    {
                        articles = (ArrayList<Article>) response.body().getArticles();
                        newsAdapter = new NewsAdapter(NewsActivity.this, articles);
                        recyclerView.setAdapter(newsAdapter);
                    }
                    else
                    {
                        Snackbar.make(layout, "Error! Please try again later.", BaseTransientBottomBar.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Snackbar.make(layout, "Error! Can't connect to the server.", BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(layout, "Error! Connection failed.", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
    }

}