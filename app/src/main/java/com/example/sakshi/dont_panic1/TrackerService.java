package com.example.sakshi.dont_panic1;

import android.*;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.core.GeoHash;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sakshi on 21/3/18.
 */


    public class TrackerService extends Service {

        private static final String TAG = TrackerService.class.getSimpleName();
        GeoFire geoFire;
        double latitude;
        double longitude;
       String token;

    @Override
        public IBinder onBind(Intent intent) {return null;}

        @Override
        public void onCreate() {
            super.onCreate();
            token = FirebaseInstanceId.getInstance().getToken();
            buildNotification();
            requestLocationUpdates();

        }

        private void buildNotification() {
            String stop = "stop";
            registerReceiver(stopReceiver, new IntentFilter(stop));

            PendingIntent broadcastIntent = PendingIntent.getBroadcast(
                    this, 0, new Intent(stop), PendingIntent.FLAG_UPDATE_CURRENT);
            // Create the persistent notification
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(getString(R.string.notification_text))
                    .setOngoing(true)
                    .setContentIntent(broadcastIntent)
                    .setSmallIcon(R.drawable.ic_launcher_background);
            startForeground(1, builder.build());
        }

        protected BroadcastReceiver stopReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "received stop broadcast");

                unregisterReceiver(stopReceiver);
                stopSelf();
            }
        };


        private void requestLocationUpdates() {

            LocationRequest request = new LocationRequest();

            request.setInterval(10000);
            request.setFastestInterval(5000);
            request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);

            int permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
            if (permission == PackageManager.PERMISSION_GRANTED) {
                // Request location updates and when an update is

                client.requestLocationUpdates(request, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        geoFire = new GeoFire(ref);
                        Location location = locationResult.getLastLocation();
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        if (location != null) {

                            Log.d(TAG, "loc update " + location);


                            geoFire.setLocation(token,new GeoLocation(latitude,longitude),new GeoFire.CompletionListener() {
                                @Override
                                public void onComplete(String key, DatabaseError error) {
                                    if (error != null) {
                                        System.err.println("There was an error saving the location to GeoFire: " + error);

                                    } else {
                                        System.out.println("Location saved on server successfully!");
                                        Log.v("ff","fgfg");
                                    }
                                }
                            });

                        }
                        else{
                            throw new IllegalArgumentException("Can't trace location");
                        }
                    }
                }, null);
            }
        }

    }

