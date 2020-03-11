package com.imbuegen.alumniapp.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imbuegen.alumniapp.Models.InternshipCompanyModel;
import com.imbuegen.alumniapp.NestedFragmentListener;
import com.imbuegen.alumniapp.R;
import com.squareup.picasso.Picasso;

public class InternshipDetails extends Fragment {

    TextView companyTitle,companyDesc,companySkills;
    ImageView companyImage;
    Button applyBtn,submitBtn,backBtn;
    Boolean has_applied;
    String name,sap,branch,yr,code,mTitle,user2;
    int amnt,r_count,g_count,b_count,saving;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1,databaseReference2,databaseReference3,databaseReference4,databaseReference5;
    InternshipCompanyModel ICMcopy,companies[];
    NestedFragmentListener listener;
    SharedPreferences.Editor editor;
    SharedPreferences shpref;

    public InternshipDetails(){}
    @SuppressLint("ValidFragment")
    public InternshipDetails(NestedFragmentListener listener){
        this.listener=listener;
    }
    public void backPressed() {
        editor=getContext().getSharedPreferences("SwitchTo", Context.MODE_PRIVATE).edit();
        editor.putString("goto","IfComp");
        editor.commit();

        listener.onSwitchToNextFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_internship_details,null);

        companyTitle=v.findViewById(R.id.company_title);
        companyDesc=v.findViewById(R.id.company_desc);
        companySkills=v.findViewById(R.id.company_skill);
        companyImage=v.findViewById(R.id.logo);
        applyBtn = v.findViewById(R.id.apply_btn);
        submitBtn=v.findViewById(R.id.submit_btn);
        backBtn=v.findViewById(R.id.exit_btn);

        applyBtn.setVisibility(View.VISIBLE);
        submitBtn.setVisibility(View.INVISIBLE);
        backBtn.setVisibility(View.INVISIBLE);

         user2 = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //ActionBar actionBar=getSupportActionBar();
        companies=new InternshipCompanyModel[20];
        has_applied=false;
        //Intent intent=getIntent();
        shpref=getContext().getSharedPreferences("IntDet", Context.MODE_PRIVATE);

         mTitle=shpref.getString("iTitle","");
        String mSkills=shpref.getString("iSkills","");
        String CompUrl=shpref.getString("iLogo","");
        //getActivity().setTitle(mTitle);
        companyTitle.setText(mTitle);
        companySkills.setText(mSkills);
        Picasso.get().load(CompUrl).into(companyImage);
        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference4=FirebaseDatabase.getInstance().getReference("Companies").child(mTitle);
        databaseReference1=FirebaseDatabase.getInstance().getReference("Applications").child(user);
        databaseReference5=FirebaseDatabase.getInstance().getReference("Applied_To").child(user);
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               name=dataSnapshot.child("name").getValue().toString();
                sap=dataSnapshot.child("sap").getValue().toString();
                branch=dataSnapshot.child("department").getValue().toString();
                yr=dataSnapshot.child("year").getValue().toString();
                r_count=Integer.parseInt(dataSnapshot.child("r_count").getValue().toString());
                b_count=Integer.parseInt(dataSnapshot.child("b_count").getValue().toString());
                g_count=Integer.parseInt(dataSnapshot.child("g_count").getValue().toString());
                amnt=Integer.parseInt(dataSnapshot.child("amount").getValue().toString());
                saving=Integer.parseInt(dataSnapshot.child("saving").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                InternshipCompanyModel internshipCompanyModel=dataSnapshot.getValue(InternshipCompanyModel.class);
                ICMcopy=internshipCompanyModel;
                companyDesc.setText(internshipCompanyModel.getDescription());
                code=internshipCompanyModel.getCode();
                if(code.equals("Red"))
                {Log.d("CODEEEEEEEEEEE",code);}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
        databaseReference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot company_list:dataSnapshot.getChildren()) {
                    InternshipCompanyModel company1_list=company_list.getValue(InternshipCompanyModel.class);
                    companies[i]=company1_list;
                    i++;

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference2=FirebaseDatabase.getInstance().getReference();
        databaseReference3=FirebaseDatabase.getInstance().getReference();
        databaseReference=FirebaseDatabase.getInstance().getReference();

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),companies[0].getName()+"",Toast.LENGTH_SHORT).show();
                applyBtn.setVisibility(View.INVISIBLE);
                backBtn.setVisibility(View.VISIBLE);
                submitBtn.setVisibility(View.VISIBLE);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitBtn.setVisibility(View.INVISIBLE);
                applyBtn.setVisibility(View.VISIBLE);
                backBtn.setVisibility(View.INVISIBLE);

            }
        });




        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.e("InternshipDetails",companies[0].getName());
                //Log.e("InternshipDetails",ICMcopy.getName());
                //int n=companies.length;
                //Log.e("InternshipDetails",n+"");
                int i=0;
                while(companies[i]!=null)
                {
                    Log.e("InternshipDetails",companies[i].getSkills());
                    //Log.e("InternshipDetails",ICMcopy.getName());
                    if(companies[i].getName().equals(ICMcopy.getName()))
                    {
                        has_applied=true;
                        break;
                    }
                    i++;
                }
                if(!(has_applied)){
                    if (code.equals("Red") && r_count == 0) {
                        databaseReference3.child("Applications").child(user2).child("amount").setValue(amnt + 100);
                        addCompanyToUser();
                        addUSerToCompany();
                    }
                    if (code.equals("Red") && r_count != 0) {

                        databaseReference3.child("Applications").child(user2).child("r_count").setValue(r_count - 1);
                        databaseReference3.child("Applications").child(user2).child("saving").setValue(saving + 100);
                        addUSerToCompany();
                        addCompanyToUser();

                    }
                    if (code.equals("Blue") && b_count == 0) {
                        databaseReference3.child("Applications").child(user2).child("amount").setValue(amnt + 100);
                        addCompanyToUser();
                        addUSerToCompany();
                    }
                    if (code.equals("Blue") && b_count != 0) {

                        databaseReference3.child("Applications").child(user2).child("b_count").setValue(b_count - 1);
                        databaseReference3.child("Applications").child(user2).child("saving").setValue(saving + 100);
                        addUSerToCompany();
                        addCompanyToUser();

                    }
                    if (code.equals("Green") && g_count == 0) {
                        databaseReference3.child("Applications").child(user2).child("amount").setValue(amnt + 100);
                        addCompanyToUser();
                        addUSerToCompany();
                    }
                    if (code.equals("Green") && g_count != 0) {

                        databaseReference3.child("Applications").child(user2).child("g_count").setValue(g_count - 1);
                        databaseReference3.child("Applications").child(user2).child("saving").setValue(saving + 100);
                        addUSerToCompany();
                        addCompanyToUser();

                    }

//                databaseReference= FirebaseDatabase.getInstance().getReference();
//                Application_Details application_details;
//               // application_details=
                    Toast.makeText(getContext(), "Applied Successfully", Toast.LENGTH_SHORT).show();
                    submitBtn.setVisibility(View.INVISIBLE);
                    applyBtn.setVisibility(View.INVISIBLE);
                    backBtn.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Toast.makeText(getContext(),"Already Applied",Toast.LENGTH_SHORT).show();
                }
            }
        });




        //      actionBar.setTitle(mTitle);
//        companyTitle.setText(mTitle);
//        companyDesc.setText(mDesc);
        return v;
    }
    public void addUSerToCompany(){
        databaseReference.child("APC").child(mTitle).child(sap).child("name").setValue(name);
        databaseReference.child("APC").child(mTitle).child(sap).child("sap").setValue(sap);
        databaseReference.child("APC").child(mTitle).child(sap).child("dept").setValue(branch);
        databaseReference.child("APC").child(mTitle).child(sap).child("year").setValue(yr);
    }
    public void addCompanyToUser(){
        databaseReference2.child("Applied_To").child(user2).child(mTitle).child("name").setValue(mTitle);
        databaseReference2.child("Applied_To").child(user2).child(mTitle).child("skills").setValue(mTitle);
    }


}
