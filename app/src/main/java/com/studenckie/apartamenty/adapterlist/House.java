package com.studenckie.apartamenty.adapterlist;

import android.os.Parcel;
import android.os.Parcelable;

public class House implements Parcelable {
    private String name;
    private int pictureId;
    private int price;
    private String description;

    public House(String name, int pictureId, int price, String description) {
        this.name = name;
        this.pictureId = pictureId;
        this.price = price;
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(pictureId);
        parcel.writeDouble(price);
        parcel.writeString(description);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public static final Parcelable.Creator<House> CREATOR = new Parcelable.Creator<House>() {

        @Override
        public House createFromParcel(Parcel parcel) {
            return new House(parcel);
        }

        @Override
        public House[] newArray(int size) {
            return new House[size];
        }
    };

    private House(Parcel parcel) {
        name = parcel.readString();
        pictureId = parcel.readInt();
        price = parcel.readInt();
        description = parcel.readString();
    }
}