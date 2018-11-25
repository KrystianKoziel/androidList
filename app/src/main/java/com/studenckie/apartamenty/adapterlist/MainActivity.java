package com.studenckie.apartamenty.adapterlist;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.StyleableRes;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<House> houses;

    @StyleableRes
            int houseNameI = 0;
    @StyleableRes
            int houseImageI = 1;
    @StyleableRes
            int housePriceI = 2;
    @StyleableRes
            int houseDescriptionI = 3;

    String[] names;
    TypedArray pics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null && savedInstanceState.containsKey("houses")) {
            House[] housesArray = (House[]) savedInstanceState.getParcelableArray("houses");
            startHousesList(new ArrayList(Arrays.asList(housesArray)));
            return;
        }
        startHousesList(null);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable[] parcelables = houses.toArray(new House[0]);
        outState.putParcelableArray("houses", parcelables);
    }

    private void startHousesList(List<House> housesList) {
        if (housesList == null) {
            houses = new ArrayList<>();

            TypedArray housesData = getResources().obtainTypedArray(R.array.houses);
            for (int i = 0; i < housesData.length(); i++) {
                int id = housesData.getResourceId(i, 0);
                if (id > 0) {
                    TypedArray h = getResources().obtainTypedArray(id);
                    House house = new House(
                            h.getString(houseNameI),
                            h.getResourceId(houseImageI, -1),
                            Integer.parseInt(h.getString(housePriceI)),
                            h.getString(houseDescriptionI)
                    );
                    houses.add(house);
                }
            }
        } else {
            houses = housesList;
        }
        HousesList housesListPage = new HousesList();
        housesListPage.setHouses(houses);
        getFragmentManager().beginTransaction()
                .replace(R.id.list_fragment, housesListPage).commit();
    }

    protected void removeHouse(House house) {
        Iterator<House> iterator = houses.iterator();
        while (iterator.hasNext()) {
            House h = iterator.next();
            if (house.getName().equals(h.getName())) {
                iterator.remove();
                break;
            }
        }
    }
}
