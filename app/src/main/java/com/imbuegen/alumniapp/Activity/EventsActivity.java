package com.imbuegen.alumniapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imbuegen.alumniapp.Adapters.CompanyListAdapter;
import com.imbuegen.alumniapp.Adapters.CompanyModel;
import com.imbuegen.alumniapp.Adapters.EventListAdapter;
import com.imbuegen.alumniapp.Models.EventModel;

import java.util.ArrayList;import com.imbuegen.alumniapp.R;
import java.util.List;

public class EventsActivity extends BaseActivity {


    ListView eventsListView;
    List<EventModel> eventModelList;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference eventsRef ;//= database.getReference("Departments").child(fbDeptKey);
    //DatabaseReference companyRef = deptRef.child("Companies");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        setTitle("Events");

        eventsRef = database.getReference("notifications");

        eventsListView  = findViewById(R.id.list_events);

        eventModelList = new ArrayList<>();

        eventsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventModelList.clear();
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    eventModelList.add(s.getValue(EventModel.class));
                }
                EventListAdapter adapter = new EventListAdapter(EventsActivity.this,eventModelList);
                eventsListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.navigation_events).setCheckable(true).setChecked(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    //putextra me key eventName variable se store karna ,mene getextra me eventName key use kiya hai

}
