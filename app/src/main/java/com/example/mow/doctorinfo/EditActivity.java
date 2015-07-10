package com.example.mow.doctorinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditActivity extends ActionBarActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etDesignation;
    private EditText etDetails;
    private EditText etMobile;
    private EditText etEmail;
    private EditText etPlace;
    private EditText etTime;
    private EditText etDay;

    private String name;
    private String designation;
    private String details;
    private String mobile;
    private String email;
    private String place;
    private String time;
    private String day;

    private Button btnUpdateProfile;
    private Doctor aDoctor;
    private DoctorDBHandler dbHandler;
    private Intent intent;
    private static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initialize();
        Bundle b = getIntent().getExtras();
        aDoctor = b.getParcelable("doctor");
        setValue(aDoctor);
        btnUpdateProfile.setOnClickListener(this);
    }
    private void initialize() {
        dbHandler = new DoctorDBHandler(EditActivity.this);

        etName=(EditText)findViewById(R.id.etName);
        etDesignation =(EditText)findViewById(R.id.etDesignation);
        etDetails = (EditText)findViewById(R.id.etDetails);
        etMobile=(EditText)findViewById(R.id.etMobile);
        etEmail =(EditText)findViewById(R.id.etEmail);
        etPlace = (EditText)findViewById(R.id.etPlace);
        etTime =(EditText)findViewById(R.id.etTime);
        etDay = (EditText)findViewById(R.id.etDay);
;


        btnUpdateProfile = (Button) findViewById(R.id.btnUpdate);
    }

    private void setValue(Doctor doctor) {
        id = doctor.getDoctorID();
        showMessage(String.valueOf(id));
        etName.setText(doctor.getName());
        showMessage(doctor.getName());
        etDesignation.setText(doctor.getDesignation());
        etDetails.setText(doctor.getDetails());
        etMobile.setText(doctor.getMobile());
        etEmail.setText(doctor.getEmail());

        etPlace.setText(doctor.getPlace());
        etTime.setText(doctor.getTime());
        etDay.setText(doctor.getDay());

    }

    private void getValue() {
        Doctor upDoctor = new Doctor();
        name = etName.getText().toString();
        designation = etDesignation.getText().toString();
        details = etDetails.getText().toString();
        mobile = etMobile.getText().toString();
        email = etEmail.getText().toString();
        place = etPlace.getText().toString();
        time = etTime.getText().toString();
        day = etDay.getText().toString();

        upDoctor.setDoctorID(id);
        upDoctor.setName(name);
        upDoctor.setDesignation(designation);
        upDoctor.setDetails(details);
        upDoctor.setMobile(mobile);
        upDoctor.setEmail(email);
        upDoctor.setPlace(place);
        upDoctor.setTime(time);
        upDoctor.setDay(day);


        dbHandler.updateProfile(upDoctor);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUpdate:
                getValue();
                showMessage("Updated Successfully");
                finish();
                break;
            default:
                break;
        }
    }
    private void showMessage(String str){
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}


