package com.imbuegen.alumniapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imbuegen.alumniapp.Models.CommitteeMember;
import com.squareup.picasso.Picasso;
import com.imbuegen.alumniapp.R;
import java.util.List;

public class CommitteeAdapter extends RecyclerView.Adapter<CommitteeAdapter.ViewHolder> {


    List<CommitteeMember> list;
    Context context;

    public CommitteeAdapter(List<CommitteeMember> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new ViewHolder(inflater.inflate(R.layout.comittee_member_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        CommitteeMember m = list.get(i);

        ImageView iv = viewHolder.itemView.findViewById(R.id.member_image);
        TextView  nameTv = viewHolder.itemView.findViewById(R.id.name_tv);
        TextView  positionTv = viewHolder.itemView.findViewById(R.id.position_tv);

        if(m.getPhotoUrl() != null && !m.getPhotoUrl().isEmpty())
            Picasso.get().load(m.getPhotoUrl()).into(iv);
        else iv.setImageResource(m.getPhotoId());

        nameTv.setText(m.getName());
        positionTv.setText(m.getPosition());

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }


    public class ViewHolder  extends  RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
