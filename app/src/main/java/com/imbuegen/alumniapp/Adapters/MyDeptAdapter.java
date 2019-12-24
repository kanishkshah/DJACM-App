package com.imbuegen.alumniapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imbuegen.alumniapp.Activity.CompanyActivity;
import com.imbuegen.alumniapp.R;
import com.imbuegen.alumniapp.Models.Department;
import java.util.ArrayList;

public class MyDeptAdapter extends RecyclerView.Adapter<MyDeptAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Department> deptList;

    public MyDeptAdapter(Context context, ArrayList<Department> deptList) {
        this.context = context;
        this.deptList = deptList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new MyDeptAdapter.ViewHolder(inflater.inflate(R.layout.dept_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Department dp = deptList.get(i);
        TextView nameView = viewHolder.itemView.findViewById(R.id.txt_name);
        ImageView icnView = viewHolder.itemView.findViewById(R.id.icon_dept);

        nameView.setText(dp.getName());
        icnView.setImageResource(dp.getIconPath());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDeptName = deptList.get(i).getName();
                Intent companyActivityIntent = new Intent(context,CompanyActivity.class);
                companyActivityIntent.putExtra("deptName",selectedDeptName);
                context.startActivity(companyActivityIntent);
            }
        });

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return deptList.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
