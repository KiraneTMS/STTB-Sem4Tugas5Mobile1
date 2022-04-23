package com.example.aruapuri.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PeopleData implements Parcelable {
    public String nik;
    public String name;
    public String birthPlace;
    public long birthDay;
    public String address;
    public String gender;
    public String occupation;
    public String marriageStatus;

    public PeopleData(Parcel in) {
        nik = in.readString();
        name = in.readString();
        birthPlace = in.readString();
        birthDay = in.readLong();
        address = in.readString();
        gender = in.readString();
        occupation = in.readString();
        marriageStatus = in.readString();
    }

    public static final Creator<PeopleData> CREATOR = new Creator<PeopleData>() {
        @Override
        public PeopleData createFromParcel(Parcel in) {
            return new PeopleData(in);
        }

        @Override
        public PeopleData[] newArray(int size) {
            return new PeopleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nik);
        parcel.writeString(name);
        parcel.writeString(birthPlace);
        parcel.writeLong(birthDay);
        parcel.writeString(address);
        parcel.writeString(gender);
        parcel.writeString(occupation);
        parcel.writeString(marriageStatus);
    }
}
