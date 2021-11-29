package com.onli.india;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.onli.india.Adapters.WarsAdapter;

public class WarsActivity extends AppCompatActivity
{
    RecyclerView rev;
    MaterialToolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wars);

        toolbar = findViewById(R.id.wars_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        rev=(RecyclerView)findViewById(R.id.rclview);
        rev.setLayoutManager(new LinearLayoutManager(this));

        String[] arr ={
                "Indo-Pakistani War of 1947–1948",
                "Sino-Indian War",
                "Indo-Pakistani War of 1965",
                "Indo-Pakistani War of 1971",
                "Kargil War"};
        String[] url_arr = {
                "https://en.wikipedia.org/wiki/Indo-Pakistani_War_of_1947%E2%80%931948",
                "https://en.wikipedia.org/wiki/Sino-Indian_War",
                "https://en.wikipedia.org/wiki/Indo-Pakistani_War_of_1965",
                "https://en.wikipedia.org/wiki/Indo-Pakistani_War_of_1971",
                "https://en.wikipedia.org/wiki/Kargil_War"};
        rev.setAdapter(new WarsAdapter(this, arr, url_arr));
    }
}