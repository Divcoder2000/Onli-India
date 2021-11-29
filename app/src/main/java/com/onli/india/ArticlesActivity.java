package com.onli.india;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class ArticlesActivity extends AppCompatActivity {

    WebView webView;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        toolbar = findViewById(R.id.articles_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        webView = findViewById(R.id.articles_webview);

        webView.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);

        webView.setWebViewClient(new ArticlesWebviewClient(progressDialog));

        webView.loadUrl(getString(R.string.articles_pdf_url));

    }

    public static class ArticlesWebviewClient extends WebViewClient {
        ProgressDialog progressDialog;

        public ArticlesWebviewClient(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
            progressDialog.show();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressDialog.dismiss();
        }
    }

}