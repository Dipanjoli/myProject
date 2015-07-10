package com.example.mow.doctorinfo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class DoctorDetails extends ActionBarActivity {

    private TextView tvName;
    private TextView tvDesignation;
    private TextView tvDetails;
    private TextView tvMobile;
    private TextView tvEmail;
    private TextView tvPlace;
    private TextView tvTime;
    private TextView tvDay;




    private String id;
    private String name;
    private String designation;
    private String details;
    private String mobile;
    private String email;
    private String place;
    private String time;
    private String day;


    private Intent intent;
    private Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        initialize();
    }

    private void initialize() {
        tvName = (TextView) findViewById(R.id.tvName);
        tvDesignation = (TextView) findViewById(R.id.tvDesignation);
        tvDetails = (TextView) findViewById(R.id.tvDetails);
        tvMobile = (TextView)findViewById(R.id.tvMobile);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPlace = (TextView) findViewById(R.id.tvPlace);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvDay = (TextView)findViewById(R.id.tvDay);


        Bundle b = getIntent().getExtras();
        doctor = b.getParcelable("doctor");
        setValue(doctor);
    }


    private void setValue(Doctor aDoctor) {
        id = String.valueOf(aDoctor.getDoctorID());
        name = aDoctor.getName();
        designation = aDoctor.getDesignation();
        details = aDoctor.getDetails();
        mobile = aDoctor.getMobile();
        email = aDoctor.getEmail();
        place = aDoctor.getPlace();
        time= aDoctor.getTime();
        day= aDoctor.getDay();



        tvName.setText(name);
        tvDesignation.setText(designation);
        tvDetails.setText(details);
        tvMobile.setText(mobile);
        tvEmail.setText(email);
        tvPlace.setText(place);
        tvTime.setText(time);
        tvDay.setText(day);



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctor_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){
            case R.id.edit:
                intent = new Intent(DoctorDetails.this, EditActivity.class);
                intent.putExtra("doctor", doctor);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }

    private void showMessage(String str){
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}





































































