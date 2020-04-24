package com.clem.mealonwheels;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

public class Truck implements Parcelable {

    final String image;
    final String name;
    final String location;
    final String time;
    final String number;
    final String link;


    public Truck(String image, String name, String location, String time, String number, String link) {
        this.image = image;
        this.name = name;
        this.location = location;
        this.time = time;
        this.number = number;
        this.link = link;
    }

    protected Truck(Parcel in) {
        image = in.readString();
        name = in.readString();
        location = in.readString();
        time = in.readString();
        number = in.readString();
        link = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(time);
        dest.writeString(number);
        dest.writeString(link);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Truck> CREATOR = new Creator<Truck>() {
        @Override
        public Truck createFromParcel(Parcel in) {
            return new Truck(in);
        }

        @Override
        public Truck[] newArray(int size) {
            return new Truck[size];
        }
    };

    public String getImage() { return image; }

    public String getName() { return name; }

    public String getLocation() { return location; }

    public String getTime() { return time; }

    public String getNumber() { return number; }

    public String getLink() { return link; }
}
