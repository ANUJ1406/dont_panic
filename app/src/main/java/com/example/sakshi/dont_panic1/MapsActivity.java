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
package com.example.sakshi.dont_panic1;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    /** variables for longitude and latitude */
    double longitude, latitude;
    DatabaseReference databaseReference;
    GeoFire geoFire;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);


        findViewById(R.id.zoomInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });


        findViewById(R.id.zoomOutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });

        /** initializing latitude and longitude */
        latitude = getIntent().getDoubleExtra("latitude", 0);
        longitude = getIntent().getDoubleExtra("longitude", 0);

      /*databaseReference= FirebaseDatabase.getInstance().getReference();
      geoFire = new GeoFire(databaseReference);

        geoFire.setLocation("firebase-hq", new GeoLocation(latitude,longitude), new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {
                if (error != null) {
                    System.err.println("There was an error saving the location to GeoFire: " + error);
                } else {
                    System.out.println("Location saved on server successfully!");
                    mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).title("user"));
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
                }
            }
        });

       //*/

        findViewById(R.id.refreshButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
            }
        });

        /** initializing checkBox for switching satelliteView */
        final CheckBox checkBox = findViewById(R.id.satelliteViewCheckBox);

        /**
         * setting Up setOnClickListener
         * @param OnClickListener
         * */
        checkBox.setOnClickListener(new View.OnClickListener() {
            /**
             * override onClick method
             * @param view
             * */
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()) mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                else mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(latitude,longitude))
                .radius(500)
                .strokeColor(Color.BLUE)
                .fillColor(0x220000FF)
                .strokeWidth(5.0f)
        );



        mMap.setMyLocationEnabled(true);

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setUpCameraAndMarkers();
            }
        });
    }




    void setUpCameraAndMarkers(){

        LatLng latLng;
        for(int i=0; i<GeometryController.detailArrayList.size(); i++) {
            latLng = new LatLng(GeometryController.detailArrayList.get(i).getGeometry()[0], GeometryController.detailArrayList.get(i).getGeometry()[1]);
            mMap.addMarker(new MarkerOptions().position(latLng).title(GeometryController.detailArrayList.get(i).getHospitalName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }


}
