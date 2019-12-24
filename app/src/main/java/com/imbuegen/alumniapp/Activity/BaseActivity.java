package com.imbuegen.alumniapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.imbuegen.alumniapp.Models.CommitteeMember;import com.imbuegen.alumniapp.R;

abstract class BaseActivity extends AppCompatActivity {

    public static int itemID = R.id.navigation_alumni;

    Activity activity;
    public void setActivity(Activity a) {
        activity =a;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpNavigation();
    }

    void setUpNavigation() {


        BottomNavigationView navigation = findViewById(R.id.navigation);

        if(navigation == null) {
            //Toast.makeText(activity, "NULL", Toast.LENGTH_SHORT).show();
            Log.d("TAG", "setUpNavigation: NULLL");
            return;
        }

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if(itemID == menuItem.getItemId())
                    return false;

                if(menuItem.getItemId() == R.id.navigation_events) {
                    startActivity(new Intent(activity,EventsActivity.class));
                    finish();
                } else if(menuItem.getItemId() == R.id.navigation_committee) {
                    startActivity(new Intent(activity, CommiteeActivity.class));
                    finish();
                }
                else {startActivity(new Intent(activity,DepartmentsActivity.class));finish();}

                itemID = menuItem.getItemId();
                return true;
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        return true;
    }
}
