package com.imbuegen.alumniapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.imbuegen.alumniapp.R;

public class AlumniLoginpg extends AppCompatActivity {
    private static Button btn;
    private static EditText mail;
    private static EditText pass;
    private static FirebaseAuth firebaseAuth;
    private static ProgressDialog progressDialog1;
    public static final String SH_PRF="shared_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_loginpg);
        views();
        progressDialog1 = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            finish();
            startActivity(new Intent(AlumniLoginpg.this,DepartmentsActivity.class));
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mail.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
                    Toast.makeText(AlumniLoginpg.this,"Enter all the details",Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences sharedPreferences=getSharedPreferences(SH_PRF,MODE_PRIVATE);
                    if(sharedPreferences.getString("Reg_as","STUDENT")=="ALUMNI")
                    {
                        validate(mail.getText().toString(),pass.getText().toString());
                    }else{

                        Toast.makeText(AlumniLoginpg.this,"NOT REGISTERED AS ALUMNI",Toast.LENGTH_SHORT).show();
                    }




                }
            }
        });

    }

    public void views(){
        btn = (Button)findViewById(R.id.logins);
        mail=findViewById(R.id.etEmails);
        pass = (EditText)findViewById(R.id.passwords);
    }

    private void checkemailverification(){
        FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag=((FirebaseUser) firebaseUser).isEmailVerified();
        if(emailflag){
            finish();
            startActivity(new Intent(AlumniLoginpg.this, DepartmentsActivity.class));
        }else{
            Toast.makeText(AlumniLoginpg.this,"Verify Your Email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }

    }
    private void validate(String userName,String userPassword){

        progressDialog1.setMessage("Verifying your account");
        progressDialog1.show();

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog1.dismiss();

                    checkemailverification();

                }else{

                    Toast.makeText(AlumniLoginpg.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    progressDialog1.dismiss();

                }


            }
        });

    }
}
