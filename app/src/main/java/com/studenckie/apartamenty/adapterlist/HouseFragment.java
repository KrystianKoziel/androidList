package com.studenckie.apartamenty.adapterlist;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Marek Noworolnik on 11.11.2018.
 */

public class HouseFragment extends Fragment {

    private House house;
    ImageView houseImage;
    TextView housePrice;
    TextView houseDescription;
    Button removeButton;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View houseFragment = inflater.inflate(R.layout.house_page, container, false);
        houseImage = houseFragment.findViewById(R.id.house_view);
        housePrice = houseFragment.findViewById(R.id.house_price);
        houseDescription = houseFragment.findViewById(R.id.house_description);
        removeButton = houseFragment.findViewById(R.id.remove_button);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HouseFragment.class);
                intent.putExtra("house_to_remove", house);
                getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
                getFragmentManager().popBackStack();
            }
        });
        return houseFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        setHouse();
    }

    private void setHouse() {
        if (getArguments() != null) {
            house = (House) getArguments().get("house");
            if (house != null) {
                houseImage.setImageResource(house.getPictureId());
                housePrice.setText(Double.toString(house.getPrice()));
                houseDescription.setText(house.getDescription());
            }
        }
    }
}
