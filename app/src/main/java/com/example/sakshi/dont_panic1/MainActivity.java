package com.example.sakshi.dont_panic1;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
       Thread thread=new Thread(  new Runnable() {
           @Override
           public void run() {
               databaseReference.child("Availability").addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                       Notification.Builder builder=new Notification.Builder(MainActivity.this);
                       builder.setContentTitle("Value Changed");
                       Notification notification=builder.build();
                       nm.notify(1,notification);
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });
           }
       }
       );
       thread.start();
*/
    }

    }
