package com.example.helloworld;
import android.os.Parcel;
import android.os.Parcelable;

public class Stocker implements Parcelable {
    private String Name;
    private String Firstname;
    private String Birthday;
    private String BirthCity;
    public Stocker(){
    }
    public String getName(){
        return Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public String getFirstname(){
        return Firstname;
    }
    public void setFirstname(String Firstname){
        this.Firstname = Firstname;
    }
    public String getBirthday(){
        return Birthday;
    }
    public void setBirthday(String Birthday){
        this.Birthday = Birthday;
    }
    public String getBirthCity(){
        return BirthCity;
    }
    public void setBirthCity(String BirthCity){
        this.BirthCity = BirthCity;
    }


    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(Firstname);
        parcel.writeString(Birthday);
        parcel.writeString(BirthCity);
    }


    public static final Parcelable.Creator<Stocker> CREATOR = new Creator<Stocker>(){
        @Override
        public Stocker[] newArray(int size){
            return new Stocker[size];
        }
        @Override
        public Stocker createFromParcel(Parcel in){
            return new Stocker(in);
        }
    };
    public Stocker(Parcel in){
        Name = in.readString();
        Firstname = in.readString();
        Birthday = in.readString();
        BirthCity = in.readString();
    }
}