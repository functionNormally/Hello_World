package com.example.helloworld;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Stocker implements Parcelable {
    private String Name;
    private String Firstname;
    private String Birthday;
    private String BirthCity;
    private String birthDepartment;
    private ArrayList<String> teleNumbers;

    public Stocker() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String Birthday) {
        this.Birthday = Birthday;
    }

    public String getBirthCity() {
        return BirthCity;
    }

    public void setBirthCity(String BirthCity) {
        this.BirthCity = BirthCity;
    }

    public String getBirthDepartment() {
        return birthDepartment;
    }

    public void setBirthDepartment(String birthDepartment) {
        this.birthDepartment = birthDepartment;
    }

    public void setTeleNumbers(ArrayList<String> teleNumbers) {
        this.teleNumbers = teleNumbers;
    }

    public ArrayList<String> getTeleNumbers() {
        return teleNumbers;
    }

    public void addTeleNumber(String teleNumber) {
        this.teleNumbers.add(teleNumber);
    }

    public void deleteTeleNumber(String teleNumber) {
        this.teleNumbers.remove(teleNumber);
    }

    public void clearTeleNumbers() {
        this.teleNumbers.clear();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(Firstname);
        parcel.writeString(Birthday);
        parcel.writeString(BirthCity);
        parcel.writeList(teleNumbers);
        parcel.writeString(birthDepartment);
    }


    public static final Parcelable.Creator<Stocker> CREATOR = new Creator<Stocker>() {
        @Override
        public Stocker[] newArray(int size) {
            return new Stocker[size];
        }

        @Override
        public Stocker createFromParcel(Parcel in) {
            return new Stocker(in);
        }
    };

    public Stocker(Parcel in) {
        Name = in.readString();
        Firstname = in.readString();
        Birthday = in.readString();
        BirthCity = in.readString();
        teleNumbers = in.readArrayList(null);
        birthDepartment = in.readString();
    }
}