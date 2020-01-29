package com.imbuegen.alumniapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.imbuegen.alumniapp.Models.EventMember;
import com.imbuegen.alumniapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailedEventAdapter extends RecyclerView.Adapter<DetailedEventAdapter.ViewHolder>{
    List<EventMember> list;
    Context context;
    public DetailedEventAdapter(List<EventMember> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new ViewHolder(inflater.inflate(R.layout.detailed_item,viewGroup,false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        EventMember m = list.get(i);

        //ImageView iv = viewHolder.itemView.findViewById(R.id.Eventmember_image);
        PhotoView iv = (PhotoView) viewHolder.itemView.findViewById(R.id.Eventmember_image);

        if(m.getPhotoUrl() != null && !m.getPhotoUrl().isEmpty())
            Picasso.get().load(m.getPhotoUrl()).into(iv);


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
