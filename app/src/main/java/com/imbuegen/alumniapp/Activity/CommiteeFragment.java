package com.imbuegen.alumniapp.Activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.imbuegen.alumniapp.Adapters.CommitteeAdapter;
import com.imbuegen.alumniapp.Models.CommitteeMember;

import java.util.ArrayList;
import java.util.List;

import su.j2e.rvjoiner.JoinableAdapter;
import su.j2e.rvjoiner.JoinableLayout;
import su.j2e.rvjoiner.RvJoiner;import com.imbuegen.alumniapp.R;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;

public class CommiteeFragment extends Fragment {

    //private DatabaseReference mDatabase;
    //CommitteeMember committeeMember;
    RecyclerView recyclerView;
    List<CommitteeMember> core = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v=inflater.inflate(R.layout.activity_commitee, null);
    recyclerView = v.findViewById(R.id.member_rv);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        init();


        return v;

    }

    private void init() {



        DividerItemDecoration itemDecor = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL);
//        mDatabase = FirebaseDatabase.getInstance().getReference();
        //core.add(new CommitteeMember(R.mipmap.hod,"Dr. Prof. Narendra Shekokar","ACM Convenor"));
        //core.add(new CommitteeMember(R.mipmap.aruna_maam,"Prof. Aruna Gawde","ACM Co-ordinator"));
        //core.add(new CommitteeMember(R.mipmap.pankaj_sir,"Prof. Pankaj Sonawane","ACM Co-ordinator"));
        //core.add(new CommitteeMember(R.mipmap.jinesh,"Jinesh Marfatia","Chairperson"));
//        int i=1;
//        Query query1=mDatabase.child("Committee").child("2018-19");
//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Committee member object and use the values to update the UI
//                for(DataSnapshot core_member:dataSnapshot.getChildren())
//                {
//                    committeeMember=core_member.getValue(CommitteeMember.class);
//                    core.add(committeeMember);
//                    Log.e("CommitteeFragment",""+core.get(0).getPhotoUrl());
//                    Toast.makeText(getContext(), committeeMember.getPhotoUrl(), Toast.LENGTH_SHORT).show();
//                }
//                //committeeMember = dataSnapshot.getValue(CommitteeMember.class);
//                //Toast.makeText(getContext(), committeeMember.getName(), Toast.LENGTH_SHORT).show();
//                // ...
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w("CommiteeFragment", "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        };
//        query1.addValueEventListener(postListener);
        core.add(new CommitteeMember("https://firebasestorage.googleapis.com/v0/b/alumniapp-21db1.appspot.com/o/Committee%2F2018-19%2Fjinesh.jpeg?alt=media&token=7d972d7d-c0f0-466a-ab1c-9e4a6a125b31","Jinesh","Chairperson"));
        core.add(new CommitteeMember("https://firebasestorage.googleapis.com/v0/b/alumniapp-21db1.appspot.com/o/Committee%2F2018-19%2Fanirudh.jpeg?alt=media&token=468de273-5497-4549-8a38-e0c268b38a37","Aayush Kharwal","Secretary"));
        core.add(new CommitteeMember("https://firebasestorage.googleapis.com/v0/b/alumniapp-21db1.appspot.com/o/Committee%2F2018-19%2Freny.jpeg?alt=media&token=6552f0ef-3661-4553-82f1-b6b498aab336","Reny Shah","Vice Chairperson\n(Sponsorship and Publicity)"));
        core.add(new CommitteeMember("https://firebasestorage.googleapis.com/v0/b/alumniapp-21db1.appspot.com/o/Committee%2F2018-19%2Fsifat.jpeg?alt=media&token=92915dd5-a9ca-44ab-9042-364b24909653","Sifat Sheikh","Vice Chairperson\n(Admin and Creatives)"));
        core.add(new CommitteeMember("https://firebasestorage.googleapis.com/v0/b/alumniapp-21db1.appspot.com/o/Committee%2F2018-19%2Fsagar.jpeg?alt=media&token=801bb42d-93fa-40d0-9312-7938a39932ca","Sagar Patani","Vice Chairperson\n(Logistics)"));
        core.add(new CommitteeMember("https://firebasestorage.googleapis.com/v0/b/alumniapp-21db1.appspot.com/o/Committee%2F2018-19%2Fpranjal.jpeg?alt=media&token=cccb1667-f441-456c-9d15-a6b41dc27472","Pranjal Naringrekar","Vice Chairperson\n(Technical)"));
        core.add(new CommitteeMember("https://firebasestorage.googleapis.com/v0/b/alumniapp-21db1.appspot.com/o/Committee%2F2018-19%2Farjun.jpeg?alt=media&token=f2acfff4-d81d-4a0a-8739-247b8d2bf5cf","Arjun Dubey","Vice Chairperson\n(Technical)"));
        core.add(new CommitteeMember("https://firebasestorage.googleapis.com/v0/b/alumniapp-21db1.appspot.com/o/Committee%2F2018-19%2Fanirudh.jpeg?alt=media&token=468de273-5497-4549-8a38-e0c268b38a37","Anirudh Mukherjee","Vice Chairperson\n(Editorial)"));
        core.add(new CommitteeMember("https://firebasestorage.googleapis.com/v0/b/alumniapp-21db1.appspot.com/o/Committee%2F2018-19%2Fxyz.jpeg?alt=media&token=329c6a65-9818-4790-972a-a7722337add6","Maitri Gohil","Vice Chairperson\n(Finance)"));
        core.add(new CommitteeMember("https://firebasestorage.googleapis.com/v0/b/alumniapp-21db1.appspot.com/o/Committee%2F2018-19%2Fcommittee.jpg?alt=media&token=4e2ac195-e334-4ee7-9767-4df5fc585489","ACM TEAM",""));
        //Log.e("CommitteeFragment",""+core.get(0).getName());
        //Log.e("CommitteeFragment","FAiled");
        List<CommitteeMember> co = new ArrayList<>();

        //co.add(new CommitteeMember("https://duckduckgo.com/i/9791d2e0.jpg","Nishay Madhani","69"));

        co.add(new CommitteeMember(R.mipmap.hod,"Dr. Prof. Narendra Shekokar","ACM Convenor"));
        co.add(new CommitteeMember(R.mipmap.aruna_maam,"Prof. Aruna Gawde","ACM Co-ordinator"));
        co.add(new CommitteeMember(R.mipmap.pankaj_sir,"Prof. Pankaj Sonawane","ACM Co-ordinator"));

        RvJoiner rvJoiner = new RvJoiner();
//        List<CommitteeMember> coComm = new ArrayList<>();
//        coComm.add(new CommitteeMember(R.mipmap.yash_javeri, "Yash Javeri", " "));
//        coComm.add(new CommitteeMember(R.mipmap.harsh_mehta, "Harsh Mehta", " "));
//        coComm.add(new CommitteeMember(R.mipmap.jash_amin, "Jash Amin", " "));
        //coComm.add(new CommitteeMember(R.mipmap.jash_amin, "Nishay Madhani", " "));
        //rvJoiner.add(new JoinableLayout(R.layout.header));

        rvJoiner.add(new JoinableAdapter(new CommitteeAdapter(co,getContext())));

        rvJoiner.add(new JoinableLayout(R.layout.divider));

        rvJoiner.add(new JoinableAdapter(new CommitteeAdapter(core,getContext())));

//        rvJoiner.add(new JoinableLayout(R.layout.divider_co));
//        rvJoiner.add(new JoinableAdapter(new CommitteeAdapter(coComm,this)));


        recyclerView.setAdapter(rvJoiner.getAdapter());
        recyclerView.addItemDecoration(itemDecor);






    }


}
