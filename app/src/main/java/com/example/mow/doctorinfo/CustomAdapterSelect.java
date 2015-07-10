package com.example.mow.doctorinfo;


import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomAdapterSelect extends ArrayAdapter<Doctor> {

    LayoutInflater inflater;
    ArrayList<Doctor> doctorList;
    public static Map<Integer, Integer> idList = new ConcurrentHashMap<>();
    int id;
    String number;
    public static SparseBooleanArray itemChecked = new SparseBooleanArray();
    public static int pos;

    public CustomAdapterSelect(Context context, List<Doctor> objects) {
        super(context, 0, objects);
        this.doctorList = (ArrayList<Doctor>) objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null){
            convertView = inflater.inflate(R.layout.custom_delete, null);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkboxDelete);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Doctor doctor = doctorList.get(position);
        holder.tvName.setText(doctor.getName() + "");
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox box = (CheckBox) buttonView.findViewById(R.id.checkboxDelete);
                id = doctor.getDoctorID();
                if (box.isChecked()){
                    itemChecked.delete(position);
                    itemChecked.put(position, true);
                    pos = position;
                    idList.put(position, id);
                }
                if (!box.isChecked()){
                    if (itemChecked.get(position)){
                        itemChecked.delete(position);
                        itemChecked.put(position, false);
                        idList.remove(position);
                    }
                }
            }
        });
        boolean bool = itemChecked.get(position);
        holder.checkBox.setChecked(bool);
        return convertView;
    }

    private class ViewHolder{
        private TextView tvName;
        private CheckBox checkBox;
    }

}

