package com.example.sakshi.dont_panic1;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button ButtonView;
    CardView gethospital,updateinfo,getroute,hospital_login,emergency;

    public static ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side_bar);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        gethospital=  findViewById(R.id.headphone_card);
        updateinfo=findViewById(R.id.location_card);
        getroute=findViewById(R.id.traffic_card);
        hospital_login=findViewById(R.id.hosp_login);
        emergency=findViewById(R.id.emergency_card);

        if (!Utils.hasLocationPermission(this))
            Utils.requestLocationPermission(this, 1002);

        gethospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hospitalLocations();
            }
        });

       /** updateinfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){

            }
        });**/
        updateinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this,UpdateInfo.class);
                startActivity(i);
            }
        });
        hospital_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Home.this,Update_Availability.class);
                startActivity(i);
            }
        });
        emergency.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent(Home.this,Emergency.class);
                        startActivity(i);

                    }
                });

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    void hospitalLocations() {
        if(Utils.isNetworkAvailable(this)) {
            loading("Scanning Location...");
            Intent intent = new Intent(Home.this, NearestHospital.class);
            startActivity(intent);
        }else
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
    }

    void loading(String message){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage(message);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }



    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Share");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        else if (id == R.id.nav_rate) {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                startActivity(myAppLinkToMarket);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, " Unable to find market app", Toast.LENGTH_LONG).show();
            }
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        return true;
    }


}
