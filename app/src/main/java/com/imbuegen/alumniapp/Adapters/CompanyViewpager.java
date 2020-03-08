package com.imbuegen.alumniapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.imbuegen.alumniapp.Activity.InternshipCompany;
import com.imbuegen.alumniapp.NestedFragmentListener;

public class CompanyViewpager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;
    NestedFragmentListener listener;
    //Constructor to the class
    public CompanyViewpager(FragmentManager fm, int tabCount, NestedFragmentListener listener) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        this.listener=listener;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:return new InternshipCompany(listener,"Red");


            case 1:return new InternshipCompany(listener,"Green");

            case 2:return new InternshipCompany(listener,"Blue");


            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}