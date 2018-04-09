package com.example.sakshi.dont_panic1;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Payal on 26-03-2018.
 */

public class send_notifications extends AsyncTask<String ,Void ,String> {

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url=new URL("https://minorapp.000webhostapp.com/send_notifications.php");
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            String line,response=" ";
            StringBuilder stringBuilder=new StringBuilder();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            while((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line);
                response=stringBuilder.toString();
            }
            bufferedReader.close();
            Log.d("httpresponse","xxx"+response);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
