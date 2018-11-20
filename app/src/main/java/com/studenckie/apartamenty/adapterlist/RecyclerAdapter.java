package com.studenckie.apartamenty.adapterlist;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<House> houses;
    private final OnItemClickListener listener;

    public RecyclerAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        houses = new ArrayList<>();
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(House house);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView image;
        public House house;

        public ViewHolder(View v) {
            super(v);
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            image = v.findViewById(R.id.icon);
        }

        void bind(final House house, final OnItemClickListener listener) {
            this.house = house;
            String price = Double.toString(house.getPrice());

            txtHeader.setText(house.getName());
            txtFooter.setText(price);
            image.setImageResource(house.getPictureId());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(house);
                }
            });
        }

    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.house_for_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(houses.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }


}
