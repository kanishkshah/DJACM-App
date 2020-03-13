package com.imbuegen.alumniapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.imbuegen.alumniapp.Models.CommitteeMember;
import com.squareup.picasso.Picasso;
import com.imbuegen.alumniapp.R;
import java.util.List;

public class CommitteeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<CommitteeMember> list;
    Context context;


    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_COMMITTEE = 0;
    public CommitteeAdapter(List<CommitteeMember> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return TYPE_FOOTER;
        } else
        return TYPE_COMMITTEE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        if (i == TYPE_COMMITTEE) {
            //Inflating recycle view item layout
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comittee_member_item, viewGroup, false);
            return new CommitteeViewHolder(itemView);
        }
        if(i == TYPE_FOOTER)
        {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.commitee_toggle_button, viewGroup, false);
            return new ButtonViewHolder(itemView);
        }
        return null;
    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
////        super.onBindViewHolder(holder, position, payloads);
//        CommitteeMember m = list.get(position);
//
//        ImageView iv = holder.itemView.findViewById(R.id.member_image);
//        TextView  nameTv = holder.itemView.findViewById(R.id.name_tv);
//        TextView  positionTv = holder.itemView.findViewById(R.id.position_tv);
//
//        if(m.getPhotoUrl() != null && !m.getPhotoUrl().isEmpty())
//            Picasso.get().load(m.getPhotoUrl()).into(iv);
//        else iv.setImageResource(m.getPhotoId());
//
//        nameTv.setText(m.getName());
//        positionTv.setText(m.getPosition());
//    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if(holder instanceof CommitteeViewHolder) {
            try {
                CommitteeMember m = list.get(i);

                ImageView iv = holder.itemView.findViewById(R.id.member_image);
                TextView nameTv = holder.itemView.findViewById(R.id.name_tv);
                TextView positionTv = holder.itemView.findViewById(R.id.position_tv);

                if (m.getPhotoUrl() != null && !m.getPhotoUrl().isEmpty())
                    Picasso.get().load(m.getPhotoUrl()).into(iv);
                else iv.setImageResource(m.getPhotoId());

                nameTv.setText(m.getName());
                positionTv.setText(m.getPosition());
            }catch(Exception e)
            {
                Log.e("PLLLLLLZZZZZZZZZZZZ",""+holder.getItemViewType());
            }
        }
        if(holder instanceof ButtonViewHolder)
        {
            Log.e("Chalo","Button Che");
        }


    }

    @Override
    public int getItemCount() {
        return this.list.size()+1;
    }


    public class ViewHolder  extends  RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public class CommitteeViewHolder  extends  RecyclerView.ViewHolder{

        public CommitteeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public class ButtonViewHolder  extends  RecyclerView.ViewHolder{
        Button b;
        public ButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            b=itemView.findViewById(R.id.toggle_committee);
            Log.e("PLSSS WORKKK",""+b.getText());
        }
        public Button getB()
        {
            return b;
        }
    }
}
