package com.imbuegen.alumniapp.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imbuegen.alumniapp.InternshipCompanyClickListener;
import com.imbuegen.alumniapp.InternshipHolder;
import com.imbuegen.alumniapp.Models.InternshipCompanyModel;
import com.imbuegen.alumniapp.NestedFragmentListener;
import com.imbuegen.alumniapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InternshipCompanyAdapter extends RecyclerView.Adapter<InternshipHolder> {
SharedPreferences.Editor editor;
    Context mContext;
    int count;
    String code;
    ArrayList<InternshipCompanyModel> models;
NestedFragmentListener listener;
    public InternshipCompanyAdapter(Context mContext, ArrayList<InternshipCompanyModel> models,NestedFragmentListener listener,String code) {
        this.mContext = mContext;
        this.models = models;
        this.listener=listener;
        this.code=code;
    }

    @NonNull
    @Override
    public InternshipHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.if_company_list,null);

        InternshipHolder holder = new InternshipHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final InternshipHolder internshipHolder, int i) {
    if((models.get(i).getCode()).equals(code))
    {
    Log.d("getcode", "onBindViewHolder: "+models.get(i).getCode());
    internshipHolder.mTitle.setText(models.get(i).getName());
    internshipHolder.mDescription.setText(models.get(i).getSkills());
    Picasso.get().load(models.get(i).getUrl()).into(internshipHolder.mLogo);
    internshipHolder.setInternshipCompanyClickListener(new InternshipCompanyClickListener() {
        @Override
        public void onInternshipCompanyClickListener(View v, int position) {

            String gTitle = models.get(position).getName();
            String gSkills = models.get(position).getSkills();
            String gLogo = models.get(position).getUrl();

            editor = mContext.getSharedPreferences("IntDet", Context.MODE_PRIVATE).edit();
            editor.putString("iTitle", gTitle);
            editor.putString("iSkills", gSkills);
            editor.putString("iLogo", gLogo);
            editor.commit();
            editor = mContext.getSharedPreferences("SwitchTo", Context.MODE_PRIVATE).edit();
            editor.putString("goto", "IntDet");
            editor.commit();
            listener.onSwitchToNextFragment();
        }
    });

}
    }

    @Override
    public int getItemCount() {
       // return models.size();
        for (int i=0;i<models.size();i++)
        {
            if((models.get(i).getCode()).equals(code))
                count++;
            else {
                models.remove(models.get(i));
                i--;
            }
        }
        return models.size();
    }
}
