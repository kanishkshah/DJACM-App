package com.imbuegen.alumniapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

public class Screen2Fragment extends Fragment {
    NestedFragmentListener listener;
    SharedPreferences.Editor editor;
    public void backPressed() {
        editor=getContext().getSharedPreferences("SwitchTo", Context.MODE_PRIVATE).edit();
        editor.putString("goto","IF");
        editor.commit();

        listener.onSwitchToNextFragment();
    }
    public Screen2Fragment()
    {}
    @SuppressLint("ValidFragment")
    public Screen2Fragment(NestedFragmentListener listener){this.listener=listener;}
}
