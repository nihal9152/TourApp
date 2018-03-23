package com.example.chiranjivrajput.tourapp;

/**
 * Created by Chiranjiv Rajput on 17-01-2018.
 */

public class data {
    private String name,desc;
    private int imageresourceid,audioresourseid;
    private float rating;
    public data(String n,String d,int i,int a,float r)
    {
        name=n;
        desc=d;
        imageresourceid=i;
        audioresourseid=a;
        rating=r;
    }
    public String showname(){return name;}
    public String showdesc(){return desc;}
    public int showimageresourceid(){return imageresourceid;}
    public int showaudioresourceid(){return audioresourseid;}
    public float showrating(){return rating;}
}
