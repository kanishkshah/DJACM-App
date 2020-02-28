package com.imbuegen.alumniapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.imbuegen.alumniapp.AnswerFragment;
import com.imbuegen.alumniapp.ProfileFragment;

public class AluminiViewPagerAdaptor extends FragmentPagerAdapter {
    public AluminiViewPagerAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
       switch (i)
       {
           case 0:
               return new ProfileFragment();

           case 1:
               return new AnswerFragment();
           default:return null;
       }

    }

    @Override
    public int getCount() {
        return 2;
    }



}
