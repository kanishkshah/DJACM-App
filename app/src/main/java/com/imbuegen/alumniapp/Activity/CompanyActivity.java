package com.imbuegen.alumniapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.List;import com.imbuegen.alumniapp.R;import com.imbuegen.alumniapp.R;

public class CompanyActivity extends BaseActivity {

    //Displaying List of Companies

    ListView listViewComapny;
    List<CompanyModel> companyModelList;

    String fbDeptKey ;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference deptRef ;//= database.getReference("Departments").child(fbDeptKey);
    //DatabaseReference companyRef = deptRef.child("Companies");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);



        Bundle gt=getIntent().getExtras();
        String str=gt.getString("deptName");


        listViewComapny = (ListView) findViewById(R.id.listViewCompany);

        companyModelList = new ArrayList<>();

        if(str.equals("Computer and IT"))
        {
            fbDeptKey = "Computers";
        }

        if(str.equals("EXTC"))
        {
            fbDeptKey = "EXTC";
        }
        if(str.equals("Electronics"))
        {
            fbDeptKey = "Electronics";
        }
        if(str.equals("Mechanical"))
        {
            fbDeptKey = "Mechanical";
        }
        if(str.equals("Production"))
        {
            fbDeptKey = "Production";
        }
        if(str.equals("Chemical"))
        {
            fbDeptKey = "Chemical";
        }

        deptRef = database.getReference("Departments").child(fbDeptKey);
        DatabaseReference companyRef = deptRef.child("Companies");
        setTitle(str);



        companyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                companyModelList.clear();

                for (DataSnapshot companySnapshot : dataSnapshot.getChildren())
                {
                    String key = companySnapshot.getKey();
                    CompanyModel companyModel = new CompanyModel(key);
                    //companyList.add(key);
                    companyModelList.add(companyModel);
                }


                CompanyListAdapter adapter = new CompanyListAdapter(CompanyActivity.this,companyModelList);


                listViewComapny.setAdapter(adapter);
                listViewComapny.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        CompanyModel selectedCompany = (CompanyModel) listViewComapny.getItemAtPosition(i);

                        String selectedCompName = selectedCompany.getCompanyName();
                        Intent AlumniActivityIntent = new Intent(CompanyActivity.this,AlumniActivity.class);
                        AlumniActivityIntent.putExtra("CompName",selectedCompName);
                        AlumniActivityIntent.putExtra("DeptName",fbDeptKey);
                        startActivity(AlumniActivityIntent);

                    }
                });

                //company1TextView.setText(companyList.get(0));
               //company2TextView.setText(companyList.get(1));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
