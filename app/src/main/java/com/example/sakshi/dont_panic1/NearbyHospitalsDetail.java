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

import android.util.Log;

import java.util.Arrays;

/**
 * Created by Sakshi on 26-Feb-18.
 */

public class NearbyHospitalsDetail {

    /** hospitalName variable */
    private String hospitalName;
    /** ratting variable */
    private String rating;
    /** openingHours variable */
    private String openingHours;
    /** address variable */
    private String address;
    /** geometry - latitude and longitude array */
    private double[] geometry;


    public String getHospitalName() {
        return hospitalName;
    }


    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }


    public String getRating() {
        return rating;
    }


    public void setRating(String rating) {
        this.rating = rating;
    }


    public String getOpeningHours() {
        return openingHours;
    }


    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public double[] getGeometry() {
        return geometry;
    }


    public void setGeometry(double[] geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "NearbyHospitalsDetail{" +
                ", hospitalName='" + hospitalName + '\'' +
                ", rating=" + rating +
                ", openingHours='" + openingHours + '\'' +
                ", address='" + address + '\'' +
                ", geometry=" + Arrays.toString(geometry) +
                '}';
    }

    public  double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
