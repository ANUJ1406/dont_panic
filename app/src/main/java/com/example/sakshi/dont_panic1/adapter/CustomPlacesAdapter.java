/*
 * %W% %E% Zain-Ul-Abedin
 *
 * Copyright (c) 2017-2018. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of ZainMustafaaa.
 * You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * for learning purposes.
 *
 */
package com.example.sakshi.dont_panic1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.sakshi.dont_panic1.R;


public class CustomPlacesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> placeName;
    private ArrayList<String> ratingText;
    private ArrayList<String> openNowText;


    public CustomPlacesAdapter(Context context, ArrayList<String> placeName, ArrayList<String> ratingText, ArrayList<String> openNowText) {
        this.context = context;
        this.placeName = placeName;
        this.ratingText = ratingText;
        this.openNowText = openNowText;
    }


    @Override
    public int getCount() {
        return placeName.size();
    }


    @Override
    public Object getItem(int i) {
        return placeName.get(i);
    }


    @Override
    public long getItemId(int i) {
        return 3232;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) view = view.inflate(context, R.layout.health_centers_result_view, null);

        TextView placeTextView =  view.findViewById(R.id.placeNameTextView);
        TextView ratingTextView =  view.findViewById(R.id.ratingTextView);
        TextView openNowTextView =  view.findViewById(R.id.openingTime);

        placeTextView.setText(placeName.get(i));
        ratingTextView.setText(ratingText.get(i));
        openNowTextView.setText("Open now: " + openNowText.get(i));

        return view;
    }
}
