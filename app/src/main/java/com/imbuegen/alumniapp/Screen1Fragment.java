package com.imbuegen.alumniapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imbuegen.alumniapp.Adapters.CompanyListAdapter;
import com.imbuegen.alumniapp.Adapters.CompanyViewpager;

public class Screen1Fragment extends Fragment implements TabLayout.OnTabSelectedListener {
    NestedFragmentListener listener;
    SharedPreferences.Editor editor;
     private TabLayout tabLayout;
   private ViewPager viewPager;
    public void backPressed() {
        editor=getContext().getSharedPreferences("SwitchTo", Context.MODE_PRIVATE).edit();
        editor.putString("goto","IF");
        editor.commit();

        listener.onSwitchToNextFragment();
    }
    public Screen1Fragment()
    {}
    @SuppressLint("ValidFragment")
    public Screen1Fragment(NestedFragmentListener listener){this.listener=listener;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
View v=inflater.inflate(R.layout.company_list_viewpager,null);
        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("start ups"));
        tabLayout.addTab(tabLayout.newTab().setText("Dream"));
        tabLayout.addTab(tabLayout.newTab().setText("Super Dream"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) v.findViewById(R.id.pager);

        //Creating our pager adapter
        CompanyViewpager adapter = new CompanyViewpager(getChildFragmentManager(), tabLayout.getTabCount(),listener);

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(this);
return v;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
