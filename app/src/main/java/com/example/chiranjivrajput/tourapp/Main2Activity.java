package com.example.chiranjivrajput.tourapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ViewPager v=(ViewPager) findViewById(R.id.v);
        spadapter s=new spadapter(this,getSupportFragmentManager());
        v.setAdapter(s);
        TabLayout t=(TabLayout)findViewById(R.id.t);
        t.setupWithViewPager(v);
    }
}
