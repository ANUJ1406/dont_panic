package com.example.sakshi.dont_panic1.receiver;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.example.sakshi.dont_panic1.MapsActivity;
import com.example.sakshi.dont_panic1.R;
import com.example.sakshi.dont_panic1.TrackerService;
import com.example.sakshi.dont_panic1.Utils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by sakshi on 20/3/18.
 */

public class location_receiver extends BroadcastReceiver {

    Context mContext;
    private static final int PERMISSIONS_REQUEST = 1;

    public void onReceive(Context context, Intent intent) {

        this.mContext = context;

        NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if (info != null && info.isConnected()) {

            int permission = ContextCompat.checkSelfPermission(mContext,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            if (permission == PackageManager.PERMISSION_GRANTED) {
                startTrackerService();
            } else {
                /*ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST);*/
            }


        }
        else {
                Intent i = new Intent(mContext, TrackerService.class);
                context.stopService(i);
            }
        }




    private void startTrackerService() {
        mContext.startService(new Intent(mContext, TrackerService.class));
        //finish();
    }

}

