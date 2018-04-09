package com.example.sakshi.dont_panic1;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;


import com.example.sakshi.dont_panic1.FriendlyHelper;
import com.example.sakshi.dont_panic1.R;

import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpdateAdapter extends ArrayAdapter<FriendlyHelper> {

    public UpdateAdapter(Context context, int resource, List<FriendlyHelper> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }


        TextView hospital =  convertView.findViewById(R.id.messageTextView);
        TextView beds =  convertView.findViewById(R.id.bedsview);
        TextView bloodbank =  convertView.findViewById(R.id.BloodView);

        FriendlyHelper message = getItem(position);


        hospital.setVisibility(View.VISIBLE);
        hospital.setText(message.getText());

        beds.setText(message.getName());
        //NearestHospital.result.put(message.getName(), Integer.valueOf(message.getText()));

        bloodbank.setText(message.getText1());


        return convertView;
    }
}