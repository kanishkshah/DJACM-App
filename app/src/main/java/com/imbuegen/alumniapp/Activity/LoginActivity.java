package com.imbuegen.alumniapp.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.imbuegen.alumniapp.Service.SFHandler;

import java.util.Arrays;
import java.util.List;import com.imbuegen.alumniapp.R;
import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static Button sgnup;
    private static Button b1;
    private static Button b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sgnup=findViewById(R.id.signupbtn);
        b1=findViewById(R.id.studentLogin);
        b2=findViewById(R.id.AL);
        sgnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,Register.class);
                startActivity(intent);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,StudentLog.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,AlumniLoginpg.class);
                startActivity(intent);
            }
        });

        ImageView img=(ImageView)findViewById(R.id.logo);
        Drawable myDrawable = getResources().getDrawable(R.drawable.ic_acm);
        img.setImageDrawable(myDrawable);

    }

}