package com.example.chiranjivrajput.tourapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Chiranjiv Rajput on 17-01-2018.
 */

public class spadapter extends FragmentPagerAdapter {
    private Context mcontext;
    public spadapter(Context context, FragmentManager f)
    {
        super(f);
        mcontext=context;

    }
    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new AttractionsFragment();
        else if(position == 1)
            return new RestaurantsFragment();
        else
            return new EventFragment();
    }



    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mcontext.getString(R.string.attr);
            case 1:
                return mcontext.getString(R.string.res);
            case 2:
                return mcontext.getString(R.string.eve);
            default:
                return null;
        }
    }
}

