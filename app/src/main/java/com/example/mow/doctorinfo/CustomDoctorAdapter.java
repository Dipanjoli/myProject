package com.example.mow.doctorinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class  CustomDoctorAdapter extends ArrayAdapter<Doctor> {
    LayoutInflater inflater;

    public CustomDoctorAdapter(Context context, List<Doctor> objects) {
        super(context, 0, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_doctorinfo, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Doctor list = getItem(position);
        holder.tvName.setText(list.getName() + "");
        return convertView;
    }

    public class ViewHolder {
        TextView tvName;
    }
}