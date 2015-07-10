package com.example.mow.doctorinfo;

import android.os.Parcel;
import android.os.Parcelable;

public class Doctor implements Parcelable {


    private String name;
    private String designation;
    private String details;
    private String mobile;
    private String email;
    private String place;
    private String time;
    private String day;

    private int doctorID;

    public Doctor(Parcel source) {

        doctorID = source.readInt();
        name = source.readString();
        designation = source.readString();
        details = source.readString();
        mobile = source.readString();
        email = source.readString();
        place = source.readString();
        time = source.readString();
        day = source.readString();
    }

    public Doctor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(doctorID);
        dest.writeString(name);
        dest.writeString(designation);
        dest.writeString(details);
        dest.writeString(mobile);
        dest.writeString(email);
        dest.writeString(place);
        dest.writeString(time);
        dest.writeString(day);

    }


    public static final Creator<Doctor> CREATOR
            = new Parcelable.Creator<Doctor>() {
        @Override
        public Doctor createFromParcel(Parcel source) {
            return new Doctor(source);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };


}