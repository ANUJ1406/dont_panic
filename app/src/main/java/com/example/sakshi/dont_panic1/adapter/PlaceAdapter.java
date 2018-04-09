/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.sakshi.dont_panic1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sakshi.dont_panic1.R;

import java.util.ArrayList;


public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.NumberViewHolder> {

    private static final String TAG = PlaceAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<String> placeName;
    private ArrayList<String> ratingText;
    private ArrayList<String> openNowText;



    private static int viewHolderCount;

    private int mNumberItems;


    public PlaceAdapter(Context context, ArrayList<String> placeName, ArrayList<String> ratingText, ArrayList<String> openNowText) {
        this.context = context;
        this.placeName = placeName;
        this.ratingText = ratingText;
        this.openNowText = openNowText;
    }
    public class NumberViewHolder extends RecyclerView.ViewHolder {

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView listItemNumberView;
        // COMPLETED (10) Add a TextView variable to display the ViewHolder index
        // Will display which ViewHolder is displaying this data
        TextView viewHolderIndex;
        TextView placeTextView;
        TextView ratingTextView;
        TextView openNowTextView;

        public NumberViewHolder(View itemView) {
            super(itemView);

            TextView placeTextView =  itemView.findViewById(R.id.placeNameTextView);
            TextView ratingTextView =  itemView.findViewById(R.id.ratingTextView);
            TextView openNowTextView = itemView.findViewById(R.id.openingTime);

        }


        void bind(int listIndex) {
            listItemNumberView.setText(String.valueOf(listIndex));
        }
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.health_centers_result_view;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        viewHolder.viewHolderIndex.setText("ViewHolder index: " + viewHolderCount);


        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + viewHolderCount);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.placeTextView.setText(placeName.get(position));
        holder.ratingTextView.setText(ratingText.get(position));
        holder.openNowTextView.setText("Open now: " + openNowText.get(position));

        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return mNumberItems;
    }



}
