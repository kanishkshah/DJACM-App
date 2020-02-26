package com.imbuegen.alumniapp.Activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.imbuegen.alumniapp.Adapters.InternshipCompanyAdapter;
import com.imbuegen.alumniapp.Models.InternshipCompanyModel;
import com.imbuegen.alumniapp.R;

import java.util.ArrayList;

public class InternshipCompany extends BaseActivity {

    RecyclerView mRecyclerView;
    InternshipCompanyAdapter internshipCompanyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_internship_company);

        mRecyclerView=findViewById(R.id.internship_recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        internshipCompanyAdapter=new InternshipCompanyAdapter(this,getInternshipCompanyList());

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.navigation_internship).setCheckable(true).setChecked(true);

        mRecyclerView.setAdapter(internshipCompanyAdapter);

    }

    private ArrayList<InternshipCompanyModel> getInternshipCompanyList(){

        ArrayList<InternshipCompanyModel> internshipCompanyModels=new ArrayList<>();

        InternshipCompanyModel m =new InternshipCompanyModel();

        m.setTitle("Company 1");
        m.setDescription("This is company 1");
        m.setLogo(R.drawable.ic_acm);

        internshipCompanyModels.add(m);

         m =new InternshipCompanyModel();

        m.setTitle("Company 2");
        m.setDescription("This is company 2");
        m.setLogo(R.drawable.ic_acm);

        internshipCompanyModels.add(m);

        m =new InternshipCompanyModel();

        m.setTitle("Company 3");
        m.setDescription("This is company 3");
        m.setLogo(R.drawable.ic_acm);

        internshipCompanyModels.add(m);

        m =new InternshipCompanyModel();

        m.setTitle("Company 4");
        m.setDescription("This is company 4");
        m.setLogo(R.drawable.ic_acm);

        internshipCompanyModels.add(m);

        return internshipCompanyModels;

    }

}
