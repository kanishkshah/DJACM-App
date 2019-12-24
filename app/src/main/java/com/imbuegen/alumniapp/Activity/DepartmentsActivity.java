package com.imbuegen.alumniapp.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.imbuegen.alumniapp.Activity.BaseActivity;
import com.imbuegen.alumniapp.Adapters.MyDeptAdapter;
import com.imbuegen.alumniapp.Models.Department;

import java.util.ArrayList;import com.imbuegen.alumniapp.R;

public class DepartmentsActivity extends BaseActivity {

    ArrayList<Department> deptList;
    RecyclerView deptListView;
    MyDeptAdapter myDeptAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        instantiateDeptList();
        loadData();
    }

    private void init(){
        deptList = new ArrayList<>();
        deptListView = findViewById(R.id.list_depts);
    }
    private void loadData() {
        deptList.clear();
        deptList.add(new Department("Computer and IT", R.mipmap.comps));
        deptList.add(new Department("EXTC", R.mipmap.extc));
        deptList.add(new Department("Electronics", R.mipmap.electronics));
        deptList.add(new Department("Mechanical", R.mipmap.mechanical));
        deptList.add(new Department("Production", R.mipmap.prod));
        deptList.add(new Department("Chemical", R.mipmap.chem));
        myDeptAdapter.notifyDataSetChanged();
    }

    private void instantiateDeptList() {

        myDeptAdapter = new MyDeptAdapter(this, deptList);


        deptListView.setAdapter(myDeptAdapter);
        deptListView.setLayoutManager(new LinearLayoutManager(this));
    }
}
