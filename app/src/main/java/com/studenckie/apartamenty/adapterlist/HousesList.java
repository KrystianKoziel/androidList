package com.studenckie.apartamenty.adapterlist;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Iterator;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class HousesList extends Fragment implements RecyclerAdapter.OnItemClickListener {

    private RecyclerView myRecyclerView;
    private List<House> houses;
    private RecyclerAdapter recyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View recipesListFragment = inflater.inflate(R.layout.houses_list, container, false);
        myRecyclerView = recipesListFragment.findViewById(R.id.houses_list);
        return recipesListFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAdapter = new RecyclerAdapter(getContext(), this);
        myRecyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setHouses(houses);
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    @Override
    public void onItemClick(House item) {
        if (getActivity().getFragmentManager()
                .findFragmentById(R.id.details_fragment) != null) {
            getActivity().getFragmentManager().popBackStack();
        }
        HouseFragment houseFragment = new HouseFragment();
        houseFragment.setTargetFragment(HouseFragment.this, getTargetRequestCode());
        Bundle args = new Bundle();
        args.putParcelable("recipe", item);
        houseFragment.setArguments(args);
        getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.details_fragment, houseFragment)
                .addToBackStack(null).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            House house = data.getParcelableExtra("recipe_to_remove");
            Iterator<House> iterator = houses.iterator();
            while (iterator.hasNext()) {
                House house1 = iterator.next();
                if (house1.getName().equals(house.getName())) {
                    iterator.remove();
                    break;
                }
            }
            recyclerAdapter.notifyDataSetChanged();
        }
    }
}
