package com.example.sakshi.dont_panic1;

import android.widget.Button;


public class FriendlyHelper {

    public String NumberBedsLeft;
    public String Hospitalname;

    public String BloodBank;


    public FriendlyHelper() {

    }

    public FriendlyHelper(String NumberBedsLeft, String Hospitalname, String  BloodBank) {
        this.NumberBedsLeft = NumberBedsLeft;
        this.Hospitalname = Hospitalname;
        this.BloodBank=BloodBank;
    }

    public String getText() {
        return NumberBedsLeft;
    }

    public void setText(String Beds) {
        this.NumberBedsLeft = Beds;
    }

    public String getName() {
        return Hospitalname;
    }

    public void setName(String hospitalname) {
        this.Hospitalname = hospitalname;
    }
    public String getText1() {
        return BloodBank;
    }

    public void setText1(String BloodBank) {
        this.BloodBank = BloodBank;
    }


}