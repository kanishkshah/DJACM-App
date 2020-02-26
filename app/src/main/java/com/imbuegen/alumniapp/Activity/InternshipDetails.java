package com.imbuegen.alumniapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.imbuegen.alumniapp.R;

public class InternshipDetails extends BaseActivity {

    TextView companyTitle,companyDesc;
    ImageView companyImage;
    Button applyBtn;
    DatabaseReference databaseReference;

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

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference= FirebaseDatabase.getInstance().getReference();
                Application_Details application_details;
               // application_details=

            }
        });

        actionBar.setTitle(mTitle);
        companyTitle.setText(mTitle);
        companyDesc.setText(mDesc);


    }
}
