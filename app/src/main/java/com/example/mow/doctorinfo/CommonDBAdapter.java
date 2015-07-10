package com.example.mow.doctorinfo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CommonDBAdapter {
    public static final String DATABASE_NAME = "iCareDoctorDB";
    public static final int DATABASE_VERSION = 1;

    /*========================== Doctor Table ==========================*/

    public static final String DOCTOR_TABLE_NAME = "tbl_doctor";

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String DESIGNATION = "designation";
    public static final String DETAILS = "details";
    public static final String MOBILE = "mobile";
    public static final String EMAIL = "email";
    public static final String PLACE = "place";
    public static final String TIME = "time";
    public static final String DAY = "day";


    String SQL_CREATE_DOCTOR_TABLE = "CREATE TABLE " + DOCTOR_TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME + " TEXT," + DESIGNATION + " TEXT, " + DETAILS + " TEXT, " + MOBILE + " TEXT, " + EMAIL + " TEXT, "
            + PLACE + " TEXT, " + TIME + " TEXT, "
            + DAY + " TEXT)";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public CommonDBAdapter(Context context) {
        this.context = context;
        this.DBHelper = new DatabaseHelper(this.context);
    }

    public class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_DOCTOR_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DOCTOR_TABLE_NAME);
            // Create tables again
            onCreate(db);
        }
    }

    public SQLiteDatabase open(){
        this.db = this.DBHelper.getWritableDatabase();
        return db;
    }

    public void close(){
        this.DBHelper.close();
    }
}
