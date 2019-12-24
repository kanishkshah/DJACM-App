package com.imbuegen.alumniapp.Activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.imbuegen.alumniapp.Adapters.CommitteeAdapter;
import com.imbuegen.alumniapp.Models.CommitteeMember;

import java.util.ArrayList;
import java.util.List;

import su.j2e.rvjoiner.JoinableAdapter;
import su.j2e.rvjoiner.JoinableLayout;
import su.j2e.rvjoiner.RvJoiner;import com.imbuegen.alumniapp.R;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;

public class CommiteeActivity extends BaseActivity {


    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActivity(this);
        setContentView(R.layout.activity_commitee);

        setTitle("Committee");

        recyclerView = findViewById(R.id.member_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        init();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.navigation_committee).setCheckable(true).setChecked(true);


    }

    private void init() {

        List<CommitteeMember> core = new ArrayList<>();

        DividerItemDecoration itemDecor = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);

        //core.add(new CommitteeMember(R.mipmap.hod,"Dr. Prof. Narendra Shekokar","ACM Convenor"));
        //core.add(new CommitteeMember(R.mipmap.aruna_maam,"Prof. Aruna Gawde","ACM Co-ordinator"));
        //core.add(new CommitteeMember(R.mipmap.pankaj_sir,"Prof. Pankaj Sonawane","ACM Co-ordinator"));
        core.add(new CommitteeMember(R.mipmap.jinesh,"Jinesh Marfatia","Chairperson"));
        core.add(new CommitteeMember(R.mipmap.aayush,"Aayush Kharwal","Secretary"));
        core.add(new CommitteeMember(R.mipmap.reny,"Reny Shah","Vice Chairperson\n(Sponsorship and Publicity)"));
        core.add(new CommitteeMember(R.mipmap.sifat,"Sifat Sheikh","Vice Chairperson\n(Admin and Creatives)"));
        core.add(new CommitteeMember(R.mipmap.sagar,"Sagar Patani","Vice Chairperson\n(Logistics)"));
        core.add(new CommitteeMember(R.mipmap.pranjal,"Pranjal Naringrekar","Vice Chairperson\n(Technical)"));
        core.add(new CommitteeMember(R.mipmap.arjun,"Arjun Dubey","Vice Chairperson\n(Technical)"));
        core.add(new CommitteeMember(R.mipmap.anirudh,"Anirudh Mukherjee","Vice Chairperson\n(Editorial)"));
        core.add(new CommitteeMember(R.mipmap.xyz,"Maitri Gohil","Vice Chairperson\n(Finance)"));
        core.add(new CommitteeMember(R.mipmap.committee,"ACM TEAM",""));

        List<CommitteeMember> co = new ArrayList<>();

        //co.add(new CommitteeMember("https://duckduckgo.com/i/9791d2e0.jpg","Nishay Madhani","69"));

        co.add(new CommitteeMember(R.mipmap.hod,"Dr. Prof. Narendra Shekokar","ACM Convenor"));
        co.add(new CommitteeMember(R.mipmap.aruna_maam,"Prof. Aruna Gawde","ACM Co-ordinator"));
        co.add(new CommitteeMember(R.mipmap.pankaj_sir,"Prof. Pankaj Sonawane","ACM Co-ordinator"));

        RvJoiner rvJoiner = new RvJoiner();
        List<CommitteeMember> coComm = new ArrayList<>();
        coComm.add(new CommitteeMember(R.mipmap.yash_javeri, "Yash Javeri", " "));
        coComm.add(new CommitteeMember(R.mipmap.harsh_mehta, "Harsh Mehta", " "));
        coComm.add(new CommitteeMember(R.mipmap.jash_amin, "Jash Amin", " "));
        //coComm.add(new CommitteeMember(R.mipmap.jash_amin, "Nishay Madhani", " "));
        //rvJoiner.add(new JoinableLayout(R.layout.header));

        rvJoiner.add(new JoinableAdapter(new CommitteeAdapter(co,this)));

        rvJoiner.add(new JoinableLayout(R.layout.divider));

        rvJoiner.add(new JoinableAdapter(new CommitteeAdapter(core,this)));

//        rvJoiner.add(new JoinableLayout(R.layout.divider_co));
//        rvJoiner.add(new JoinableAdapter(new CommitteeAdapter(coComm,this)));


        recyclerView.setAdapter(rvJoiner.getAdapter());
        recyclerView.addItemDecoration(itemDecor);






    }


}
