package com.imbuegen.alumniapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

public class IfFragment extends Fragment {
    NestedFragmentListener listener;
    Button screen1,screen2;
    SharedPreferences.Editor editor;
    public IfFragment()
    {}
    @SuppressLint("ValidFragment")
    public IfFragment(NestedFragmentListener listener)
    {this.listener=listener;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.if_screen1,null);
        screen1=v.findViewById(R.id.screen1);
        screen2=v.findViewById(R.id.screen2);
        editor=getActivity().getSharedPreferences("SwitchTo",MODE_PRIVATE).edit();

        screen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Applications");
                final String user = FirebaseAuth.getInstance().getCurrentUser().getUid();


                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for(DataSnapshot snap:snapshot.getChildren())
                        {
                            Log.d("NAME",snap.getKey());
                        }
                        if (snapshot.hasChild(user)) {
                            editor.putString("goto","IfComp");
                            editor.commit();
                             listener.onSwitchToNextFragment();

                        }
                        else{
                            editor.putString("goto","screen1");
                            editor.commit();
                            listener.onSwitchToNextFragment();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }

        });
        screen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("goto","screen2");
                editor.commit();
                listener.onSwitchToNextFragment();
            }
        });
        return v;
    }
}
