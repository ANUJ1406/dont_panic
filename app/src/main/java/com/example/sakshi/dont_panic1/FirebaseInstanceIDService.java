package com.example.sakshi.dont_panic1;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Warrior on 8/8/2016.
 */
public class FirebaseInstanceIDService extends com.google.firebase.iid.FirebaseInstanceIdService
{
     String token;
    double latitude,longitude;
    @Override
    public void onTokenRefresh() {
        token= FirebaseInstanceId.getInstance().getToken();

        Log.v("ABCDE",token);
        registerToken(token);
    }

    private void registerToken(String token) {
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Devices").child(token).child("token_num").setValue(token);
        databaseReference.child("Devices").child(token).child("present").setValue("0");
    }
}