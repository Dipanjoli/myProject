package com.example.mow.doctorinfo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DoctorDBHandler {
    // All Static variables
    public static final String DOCTOR_TABLE = CommonDBAdapter.DOCTOR_TABLE_NAME;
    public static final String ROW_ID = CommonDBAdapter.ID;
    public static final String NAME = CommonDBAdapter.NAME;
    public static final String DESIGNATION = CommonDBAdapter.DESIGNATION;
    public static final String DETAILS = CommonDBAdapter.DETAILS;
    public static final String MOBILE = CommonDBAdapter.MOBILE;
    public static final String EMAIL = CommonDBAdapter.EMAIL;
    public static final String PLACE = CommonDBAdapter.PLACE;
    public static final String TIME = CommonDBAdapter.TIME;
    public static final String DAY = CommonDBAdapter.DAY;



    public static final String[] COLUMNS = new String[]{
            ROW_ID,
            NAME,
            DESIGNATION,
            DETAILS,
            MOBILE,
            EMAIL,
            PLACE,
            TIME,
            DAY

    };


    private CommonDBAdapter mDbHelper;
    private Context mContext;
    private SQLiteDatabase db;
    public DoctorDBHandler(Context context) {
        this.mContext = context;
        mDbHelper = new CommonDBAdapter(context);
    }


    void addDoctorInfo(Doctor aDoctor) {

        SQLiteDatabase db = mDbHelper.open();

        ContentValues values = new ContentValues();
        values.put(NAME, aDoctor.getName());
        values.put(DESIGNATION, aDoctor.getDesignation());
        values.put(DETAILS, aDoctor.getDetails()== null ? "" : aDoctor.getDetails());
        values.put(MOBILE, aDoctor.getMobile()== null ? "" : aDoctor.getMobile());
        values.put(EMAIL, aDoctor.getEmail());
        values.put(PLACE, aDoctor.getPlace());
        values.put(TIME, aDoctor.getTime());
        values.put(DAY, aDoctor.getDay());


        // Inserting Row
        long result = db.insert(DOCTOR_TABLE, null, values);


        db.close(); // Closing database connection
    }

    public Cursor getAllCursorProfile(){
        open();
        Cursor cursor;

        cursor = db.query(DOCTOR_TABLE, COLUMNS, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Doctor> getAllProfile() {
        List<Doctor> doctorList = new ArrayList<Doctor>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DOCTOR_TABLE;

        open();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Doctor aDoctor = new Doctor();
                aDoctor.setDoctorID(cursor.getInt(0));
                aDoctor.setName(cursor.getString(1));
                aDoctor.setDesignation(cursor.getString(2));
                aDoctor.setDetails(cursor.getString(3));
                aDoctor.setMobile(cursor.getString(4));
                aDoctor.setEmail(cursor.getString(5));
                aDoctor.setPlace(cursor.getString(6));
                aDoctor.setTime(cursor.getString(7));
                aDoctor.setDay(cursor.getString(8));

                doctorList.add(aDoctor);
            } while (cursor.moveToNext());
        }
        db.close();
        return doctorList;
    }

    public Doctor getProfileByID(int id) {
        String selectQuery = "SELECT  * FROM " + DOCTOR_TABLE + " where _id = " + id;

        open();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Doctor aDoctor = new Doctor();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            aDoctor.setDoctorID(cursor.getInt(0));
            aDoctor.setName(cursor.getString(1));
            aDoctor.setDesignation(cursor.getString(2));
            aDoctor.setDetails(cursor.getString(3));
            aDoctor.setMobile(cursor.getString(4));
            aDoctor.setEmail(cursor.getString(5));
            aDoctor.setPlace(cursor.getString(6));
            aDoctor.setTime(cursor.getString(7));
            aDoctor.setDay(cursor.getString(8));



        }
        db.close();
        return aDoctor;
    }


    public int updateProfile(Doctor aDoctor) {
        SQLiteDatabase db = mDbHelper.open();

        ContentValues values = new ContentValues();

        values.put(ROW_ID, aDoctor.getDoctorID());
        values.put(NAME, aDoctor.getName());
        values.put(DESIGNATION, aDoctor.getDesignation());
        values.put(DETAILS, aDoctor.getDetails());
        values.put(MOBILE, aDoctor.getMobile());
        values.put(EMAIL, aDoctor.getEmail());
        values.put(PLACE, aDoctor.getPlace());
        values.put(TIME, aDoctor.getTime());
        values.put(DAY, aDoctor.getDay());


        return db.update(DOCTOR_TABLE, values, ROW_ID + " = ?",
                new String[]{String.valueOf(aDoctor.getDoctorID())});
    }

    void deleteProfile(int id){
        SQLiteDatabase db = mDbHelper.open();
        db.delete(DOCTOR_TABLE, ROW_ID +"= ?", new String[] {String.valueOf(id)});
        db.close();
    }
    public void open() throws SQLException {
        db = mDbHelper.open();
    }
}


