package com.imbuegen.alumniapp.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.imbuegen.alumniapp.Adapters.DetailedEventAdapter;
import com.imbuegen.alumniapp.Models.EventMember;
import com.imbuegen.alumniapp.NestedFragmentListener;
import com.imbuegen.alumniapp.R;

import java.util.ArrayList;
import java.util.List;

import su.j2e.rvjoiner.RvJoiner;

public class DetailedEventFragment extends Fragment {
    SharedPreferences gt;
    String eventName ="";
    DetailedEventAdapter adapter;
    RecyclerView recyclerView;
    NestedFragmentListener listener;
    public DetailedEventFragment(){}
    @SuppressLint("ValidFragment")
    public DetailedEventFragment(NestedFragmentListener listener){
        this.listener=listener;


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_detailed_event,null);


         gt=getActivity().getSharedPreferences("EventInfo", Context.MODE_PRIVATE);


        eventName =gt.getString("name","Placements 101");
        getActivity().setTitle(" GALLERY");


        TextView tv = v.findViewById(R.id.et_name);
        tv.setText(eventName);
        TextView tv1 = v.findViewById(R.id.et_body);
        tv1.setText(gt.getString("body","body"));

        recyclerView = v.findViewById(R.id.member_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        init();
        return v;
    }

    private void init() {

        List<EventMember> event  = new ArrayList<>();
        if(eventName.equalsIgnoreCase("Industrial Visit (I-Medita)"))  {
            event.add(new EventMember("https://firebasestorage.googleapis.com/v0/b/alumniapp-21db1.appspot.com/o/Industrial%20Visit%20(I-Medita)%2Fiv1.jpeg?alt=media&token=d09778eb-d0b2-4695-b152-72c426b70864"));
            event.add(new EventMember("https://firebasestorage.googleapis.com/v0/b/alumniapp-21db1.appspot.com/o/Industrial%20Visit%20(I-Medita)%2Fiv2.jpeg?alt=media&token=db26b652-210d-425a-a1ca-ac314b49819b"));
        }
        else if(eventName.equalsIgnoreCase("Placements 101"))  {
            event.add(new EventMember("https://firebasestorage.googleapis.com/v0/b/alumniapp-21db1.appspot.com/o/Placements%20101%2Fplace1.jpeg?alt=media&token=c1cd8d8d-4e62-49ef-a2a9-8ca75e0ed7c1"));
        }
        else if(eventName.equalsIgnoreCase("Raspberry Pi Workshop")) {
            event.add(new EventMember("https://firebasestorage.googleapis.com/v0/b/alumniapp-21db1.appspot.com/o/Raspbery%20Pi%20Workshop%2Frpi1.jpeg?alt=media&token=5f6f5367-b838-483a-8a7b-bbd3ad6b67a5"));
        } else {

        }
        adapter = new DetailedEventAdapter(event,getContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
