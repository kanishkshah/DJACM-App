package com.imbuegen.alumniapp.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imbuegen.alumniapp.ProfileFragment;
import com.imbuegen.alumniapp.AnswerFragment;
import com.imbuegen.alumniapp.R;
public class RegistrationActivity extends BaseActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mAlumniReference;
    private static final String TAG = "XYZ";

    BottomNavigationView navigation ;

    private TextView uidTv;
    String alumniPath;
    String dbPath;
    String uid;
    String xyz = "";


    public String getDbPath() {
        return dbPath;
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeration);

         navigation = (BottomNavigationView) findViewById(R.id.navigation);



        init();

        final String uid = mAuth.getUid();
        DatabaseReference userIndexRef = mDatabase.getReference("userIndex").child(uid);
        userIndexRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                xyz = alumniPath = (String) dataSnapshot.getValue();
                Bundle bundle = new Bundle();
                bundle.putString("path", xyz);
                bundle.putString("uid",uid);
                Fragment fragment = null;

                // set Fragmentclass Arguments
                fragment = new ProfileFragment();
                fragment.setArguments(bundle);
                loadFragment(fragment);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("path", xyz);
        bundle.putString("uid",uid);

        Fragment profileFragment =  new ProfileFragment();
        profileFragment.setArguments(bundle);
        loadFragment(profileFragment);


        getTheAlumni();


    }

    private void getTheAlumni() {
        final String uid = mAuth.getUid();

        DatabaseReference userIndexRef = mDatabase.getReference("userIndex").child(uid);
        userIndexRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               xyz = alumniPath = (String) dataSnapshot.getValue();


                 navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                     @Override
                     public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                         Fragment fragment = null;
                         if (menuItem.getItemId() == R.id.navigation_profile)
                         {
                             Bundle bundle = new Bundle();
                             bundle.putString("path", xyz);
                             bundle.putString("uid",uid);

                             // set Fragmentclass Arguments
                             fragment = new ProfileFragment();
                             fragment.setArguments(bundle);


                         }

                         if (menuItem.getItemId() == R.id.navigation_answer)
                         {
                             Bundle bundle = new Bundle();
                             bundle.putString("path", xyz);
                             bundle.putString("uid",uid);
                             fragment = new AnswerFragment();
                             fragment.setArguments(bundle);
                         }

                         return loadFragment(fragment);
                     }
                 });



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
    }



    private boolean loadFragment(Fragment fragment)
    {
        if (fragment != null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();

            return true;
        }
        return false;
    }
}
