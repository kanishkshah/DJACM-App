package com.imbuegen.alumniapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imbuegen.alumniapp.Activity.InternshipCompany;
import com.imbuegen.alumniapp.Activity.InternshipDetails;
import com.imbuegen.alumniapp.InternshipCompanyClickListener;
import com.imbuegen.alumniapp.InternshipHolder;
import com.imbuegen.alumniapp.Models.InternshipCompanyModel;
import com.imbuegen.alumniapp.NestedFragmentListener;
import com.imbuegen.alumniapp.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class InternshipCompanyAdapter extends RecyclerView.Adapter<InternshipHolder> {
SharedPreferences.Editor editor;
    Context mContext;
    ArrayList<InternshipCompanyModel> models;
NestedFragmentListener listener;
    public InternshipCompanyAdapter(Context mContext, ArrayList<InternshipCompanyModel> models,NestedFragmentListener listener) {
        this.mContext = mContext;
        this.models = models;
        this.listener=listener;
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

        internshipHolder.mTitle.setText(models.get(i).getTitle());
        internshipHolder.mDescription.setText(models.get(i).getDescription());
        internshipHolder.mLogo.setImageResource(models.get(i).getLogo());

        internshipHolder.setInternshipCompanyClickListener(new InternshipCompanyClickListener() {
            @Override
            public void onInternshipCompanyClickListener(View v, int position) {

                String gTitle = models.get(position).getTitle();
                String gDesc= models.get(position).getDescription();
            //    BitmapDrawable bitmapDrawable= (BitmapDrawable)internshipHolder.mLogo.getDrawable();

              //  Bitmap bitmap=bitmapDrawable.getBitmap();
                //ByteArrayOutputStream stream=new ByteArrayOutputStream();

                //bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);

                //byte[] bytes =stream.toByteArray();
                editor=mContext.getSharedPreferences("IntDet", Context.MODE_PRIVATE).edit();
editor.putString("iTitle",gTitle);
                editor.putString("iDesc",gTitle);
                editor.commit();
                editor=mContext.getSharedPreferences("SwitchTo", Context.MODE_PRIVATE).edit();
                editor.putString("goto","IntDet");
                editor.commit();
                listener.onSwitchToNextFragment();
//                Intent intent= new Intent(mContext, InternshipDetails.class);
//                intent.putExtra("iTitle",gTitle);
//                intent.putExtra("iDesc",gTitle);
                //intent.putExtra("iImage",bytes);

//                mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return models.size();

    }
}
