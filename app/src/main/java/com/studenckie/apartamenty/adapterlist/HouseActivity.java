package com.studenckie.apartamenty.adapterlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


public class HouseActivity extends AppCompatActivity {

    ImageView houseImage;
    TextView housePrice;
    TextView houseDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_page);

        houseImage = findViewById(R.id.house_view);
        housePrice = findViewById(R.id.house_price);
        houseDescription = findViewById(R.id.house_description);

        Intent intent = getIntent();
        House house = intent.getParcelableExtra("house");
        setTitle(house.getName());
        houseImage.setImageResource(house.getPictureId());
        housePrice.setText(Integer.toString(house.getPrice()));
        houseDescription.setText(house.getDescription());
    }


}
