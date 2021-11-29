package com.onli.india;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.onli.india.utils.TouchImageView;
import com.squareup.picasso.Picasso;

public class ImageViewActivity extends AppCompatActivity {

    TouchImageView touchImageView;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        Intent intent = getIntent();

        toolbar = findViewById(R.id.imageDisplay_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        touchImageView = findViewById(R.id.image_display);

        Picasso.get()
                .load(intent.getStringExtra("img"))
                .into(touchImageView);
    }
}