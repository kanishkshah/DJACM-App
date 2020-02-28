package com.imbuegen.alumniapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imbuegen.alumniapp.R;

public class InternshipDetails extends BaseActivity {

    TextView companyTitle,companyDesc;
    ImageView companyImage;
    Button applyBtn;
    String name,sap,branch,yr;
    int coun,amnt;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1,databaseReference2,databaseReference3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship_details);
        companyTitle=findViewById(R.id.company_title);
        companyDesc=findViewById(R.id.company_desc);
        companyImage=findViewById(R.id.logo);
        applyBtn = findViewById(R.id.apply_btn);



        ActionBar actionBar=getSupportActionBar();


        Intent intent=getIntent();

        final String mTitle=intent.getStringExtra("iTitle");
        String mDesc=intent.getStringExtra("iDesc");
        actionBar.setTitle(mTitle);
        companyTitle.setText(mTitle);
        companyDesc.setText(mDesc);
        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference1=FirebaseDatabase.getInstance().getReference("Applications").child(user);
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               name=dataSnapshot.child("name").getValue().toString();
                sap=dataSnapshot.child("sap").getValue().toString();
                branch=dataSnapshot.child("department").getValue().toString();
                yr=dataSnapshot.child("year").getValue().toString();
                coun=Integer.parseInt(dataSnapshot.child("count").getValue().toString());
                amnt=Integer.parseInt(dataSnapshot.child("amount").getValue().toString());
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
                String user2 = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if(coun==0)
                {
                    databaseReference3.child("Applications").child(user2).child("amount").setValue(amnt+100);
                    databaseReference2.child("Applied_To").child(user2).child(mTitle).child("name").setValue(mTitle);
                    databaseReference2.child("Applied_To").child(user2).child(mTitle).child("skills").setValue(mTitle);
                    databaseReference.child("APC").child(mTitle).child("name").setValue(name);
                    databaseReference.child("APC").child(mTitle).child("sap").setValue(sap);
                    databaseReference.child("APC").child(mTitle).child("dept").setValue(branch);
                    databaseReference.child("APC").child(mTitle).child("year").setValue(yr);
                }
                else{
                    databaseReference2.child("Applied_To").child(user2).child(mTitle).child("name").setValue(mTitle);
                    databaseReference2.child("Applied_To").child(user2).child(mTitle).child("skills").setValue(mTitle);
                    databaseReference3.child("Applications").child(user2).child("count").setValue(coun-1);
                    databaseReference.child("APC").child(mTitle).child("name").setValue(name);
                    databaseReference.child("APC").child(mTitle).child("sap").setValue(sap);
                    databaseReference.child("APC").child(mTitle).child("dept").setValue(branch);
                    databaseReference.child("APC").child(mTitle).child("year").setValue(yr);

                }

            }
        });


    }
}
