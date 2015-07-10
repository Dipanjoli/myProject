package com.example.mow.doctorinfo;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ListAdapter;

import java.util.List;


public class DoctorMainActivity extends ActionBarActivity {

    private Intent intent;
    private ListView listViewDoctor;
    private DoctorDBHandler dbHandler;
    private List<Doctor> doctorList;
    private CustomDoctorAdapter adapter;
    private Doctor doctor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();


    }

    private void initialize() {
        listViewDoctor = (ListView) findViewById(R.id.lvDoctor);
        dbHandler = new DoctorDBHandler(DoctorMainActivity.this);
        updateDatabase();

        listViewDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               doctor = (Doctor) parent.getItemAtPosition(position);
                intent = new Intent(DoctorMainActivity.this, DoctorDetails.class);
                intent.putExtra("doctor", doctor);
                startActivity(intent);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        switch(id) {
            case R.id.new_profile:
                intent = new Intent(DoctorMainActivity.this, AddDoctor.class);
                startActivity(intent);
                return true;
            case R.id.delete:
                intent = new Intent(DoctorMainActivity.this, DeleteActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }




    private void updateDatabase() {
        doctorList = dbHandler.getAllProfile();
        if (!doctorList.isEmpty()) {
            adapter = new CustomDoctorAdapter(this, doctorList);
            listViewDoctor.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDatabase();
    }


}


