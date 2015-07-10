package com.example.mow.doctorinfo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddDoctor extends ActionBarActivity implements View.OnClickListener {
        private EditText editTextName;
        private EditText editTextDesignation;
        private EditText editTextDetails;
        private EditText editTextMobile;
        private EditText editTextEmail;
        private EditText editTextPlace;
        private EditText editTextTime;
        private EditText editTextDay;


        private String name;
        private String designation;
        private String details;
        private String mobile;
        private String email;
        private String place;
        private String time;
        private String day;
        private Button btnSave;


        public Doctor aDoctor;
        private DoctorDBHandler dbHandler;
        private Intent intent;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_doctor);

            initialize();
            btnSave.setOnClickListener(this);

        }

        private void initialize() {
            aDoctor = new Doctor();
            dbHandler = new DoctorDBHandler(AddDoctor.this);

            editTextName = (EditText) findViewById(R.id.etName);
            editTextDesignation = (EditText)findViewById(R.id.etDesignation);
            editTextDetails = (EditText) findViewById(R.id.etDetails);
            editTextMobile = (EditText) findViewById(R.id.etMobile);
            editTextEmail = (EditText)findViewById(R.id.etEmail);
            editTextPlace = (EditText) findViewById(R.id.etPlace);
            editTextTime = (EditText)findViewById(R.id.etTime);
            editTextDay = (EditText) findViewById(R.id.etDay);

            btnSave = (Button) findViewById(R.id.btnSave);
        }

        private void getValue() {
            name = editTextName.getText().toString();
            designation = editTextDesignation.getText().toString();
            details = editTextDetails.getText().toString();
            mobile = editTextMobile.getText().toString();
            email = editTextEmail.getText().toString();
            place = editTextPlace.getText().toString();
            time = editTextTime.getText().toString();
            day = editTextDay.getText().toString();


            aDoctor.setName(name);
            aDoctor.setDesignation(designation);
            aDoctor.setDetails(details);
            aDoctor.setMobile(mobile);
            aDoctor.setEmail(email);
            aDoctor.setPlace(place);
            aDoctor.setTime(time);
            aDoctor.setDay(day);

            dbHandler.addDoctorInfo(aDoctor);
        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_add_doctor, menu);
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
                case R.id.btnSave:
                    getValue();

                    showMessage("Saved Successfully");
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

