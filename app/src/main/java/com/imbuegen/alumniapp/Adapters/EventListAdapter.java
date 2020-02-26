package com.imbuegen.alumniapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.imbuegen.alumniapp.NestedFragmentListener;
import com.imbuegen.alumniapp.R;

import com.imbuegen.alumniapp.Models.EventModel;

import java.util.List;

public class EventListAdapter extends ArrayAdapter<EventModel> {

    private Activity context;
    private List<EventModel> data;
    NestedFragmentListener listener;
    SharedPreferences.Editor editor;
    public EventListAdapter(Activity context, List<EventModel> alumniList, NestedFragmentListener listener) {
        super(context,R.layout.event_list,alumniList);
        this.context = context;
        this.data = alumniList;
        this.listener=listener;
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {


        LayoutInflater inflater = context.getLayoutInflater();

        View listItemView = inflater.inflate(R.layout.event_list,null,true);
        TextView title_tv = (TextView) listItemView.findViewById(R.id.title_tv);
        title_tv.setText(data.get(position).title);
        TextView date_tv = (TextView) listItemView.findViewById(R.id.date_tv);
        date_tv.setText(data.get(position).date);


        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eventName = data.get(position).getTitle();
                if(!(eventName.equalsIgnoreCase("Industrial Visit (I-Medita)") ||
                        eventName.equalsIgnoreCase("Placements 101") ||
                        eventName.equalsIgnoreCase("Raspberry Pi Workshop"))) {

                    Toast.makeText(context, "No Pictures Available", Toast.LENGTH_SHORT).show();
                    return;
                }


                editor=context.getSharedPreferences("SwitchTo", Context.MODE_PRIVATE).edit();
                editor.putString("goto","DetEvent");
                editor.commit();
                editor=context.getSharedPreferences("EventInfo", Context.MODE_PRIVATE).edit();
                editor.putString("name",eventName);
                editor.putString("body", data.get(position).getBody());
                editor.commit();
                listener.onSwitchToNextFragment();

//                Intent i = new Intent(context,DetailedEventActivity.class);
//                i.putExtra("name",eventName);
//                i.putExtra("body", data.get(position).getBody());
//                context.startActivity(i);
            }
        });

        return listItemView;
    }
}
