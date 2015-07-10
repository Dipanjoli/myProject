package com.example.mow.doctorinfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class DeleteActivity extends ActionBarActivity {

    private ListView lvDoctorDlt;
    private CustomAdapterSelect adapter;
    private List<Doctor> doctorList;
    private DoctorDBHandler dbHandler;
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        initialize();
    }

    private void initialize() {

        lvDoctorDlt = (ListView) findViewById(R.id.lvDelete);
        dbHandler = new DoctorDBHandler(this);
        doctorList = dbHandler.getAllProfile();
        adapter = new CustomAdapterSelect(this, doctorList);
        lvDoctorDlt.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.delete:
                new AlertDialog.Builder(DeleteActivity.this)
                        .setTitle("Delete Log")
                        .setMessage("Item deleted")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i : CustomAdapterSelect.idList.keySet()) {
                                    int id = CustomAdapterSelect.idList.get(i);
                                    dbHandler.deleteProfile(id);
                                }
                                showMessage("Deleted Successfully");
                                CustomAdapterSelect.idList.clear();
                                CustomAdapterSelect.itemChecked.clear();
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Do nothing
                            }
                        })
                        .show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        clear();

    }

    private void clear(){
        CustomAdapterSelect.idList.clear();
        CustomAdapterSelect.itemChecked.clear();
    }
    private void showMessage(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}

