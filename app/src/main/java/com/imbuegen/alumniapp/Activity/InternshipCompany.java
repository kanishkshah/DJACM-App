package com.imbuegen.alumniapp.Activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.imbuegen.alumniapp.Adapters.InternshipCompanyAdapter;
import com.imbuegen.alumniapp.Models.InternshipCompanyModel;
import com.imbuegen.alumniapp.NestedFragmentListener;
import com.imbuegen.alumniapp.R;

import java.util.ArrayList;

import java.util.concurrent.TimeUnit;


public class InternshipCompany extends Fragment {

    RecyclerView mRecyclerView;
    InternshipCompanyAdapter internshipCompanyAdapter;
    DatabaseReference databaseReference;
NestedFragmentListener listener;
SharedPreferences.Editor editor;
public InternshipCompany(){}
    @SuppressLint("ValidFragment")
    public InternshipCompany(NestedFragmentListener listener){
    this.listener=listener;
    }
    public void backPressed() {
        editor=getContext().getSharedPreferences("SwitchTo", Context.MODE_PRIVATE).edit();
        editor.putString("goto","IF");
        editor.commit();

        listener.onSwitchToNextFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_internship_company,null);

        mRecyclerView=v.findViewById(R.id.internship_recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        try {
            internshipCompanyAdapter=new InternshipCompanyAdapter(getContext(),getInternshipCompanyList(),listener);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        mRecyclerView.setAdapter(internshipCompanyAdapter);
return v;
    }

    private ArrayList<InternshipCompanyModel> getInternshipCompanyList() throws InterruptedException {

        final ArrayList<InternshipCompanyModel> internshipCompanyModels=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Companies");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {


                for(DataSnapshot companysnapshot:dataSnapshot.getChildren())
                {
                    Company company=companysnapshot.getValue(Company.class);
                    InternshipCompanyModel m =new InternshipCompanyModel();
                    Log.d("NAME",company.getName());
                    m.setTitle(company.getName());
                    m.setDescription(company.getDescription());
                    m.setLogo(R.drawable.ic_acm);

                    internshipCompanyModels.add(m);


                }}

                else
                {
                    Log.d("NAME","Broooooooooooooooooooooooo");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d("NAME","Broooooooooooooooooooooooo");
        InternshipCompanyModel m =new InternshipCompanyModel();
//        Log.d("NAME",company.getName());
        m.setTitle("Google");
        m.setDescription("HEY BABY");
        m.setLogo(R.drawable.ic_acm);
        internshipCompanyModels.add(m);
        return internshipCompanyModels;



    }

}
