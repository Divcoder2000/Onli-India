package com.onli.india;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.onli.india.utils.Model;
import com.squareup.picasso.Picasso;


public class RiverInfoActivity extends AppCompatActivity {

    TextView nameText, originText, endText, lengthText;
    ImageView image, mapImage;
    Button knowMore;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_river_info);

        nameText = findViewById(R.id.river_name);
        originText = findViewById(R.id.river_origin);
        endText = findViewById(R.id.river_end);
        lengthText = findViewById(R.id.river_length);

        knowMore = findViewById(R.id.river_wikibtn);

        image = findViewById(R.id.river_image);
        mapImage = findViewById(R.id.river_mapImage);
        toolbar = findViewById(R.id.river_info_toolbar);

        Intent intent = getIntent();
        Model model = (Model) intent.getSerializableExtra("riverdata");

        String name = model.getName().substring(0, 1).toUpperCase() + model.getName().substring(1).toLowerCase();
        final String imageURL = model.getImage();

        nameText.setText(name);
        originText.setText(model.getOrigin());
        endText.setText(model.getEnd());
        lengthText.setText(model.getLengthKm());

        toolbar.setTitle(name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        Picasso.get().load(imageURL).into(image);

        Picasso.get().load(model.getMapimage()).into(mapImage);

        mapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/?api=1&query=River+" + name));
                startActivity(intent);
            }
        });

        knowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(model.getWikipedia()));
                startActivity(intent);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RiverInfoActivity.this, ImageViewActivity.class);
                i.putExtra("img", imageURL);
                startActivity(i);
            }
        });
    }
}