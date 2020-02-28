package com.imbuegen.alumniapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.imbuegen.alumniapp.R;

import java.util.HashSet;
import java.util.Set;

public class ApplicationForm extends AppCompatActivity implements OnItemSelectedListener {

    public String ap_year;
    private static EditText name,sap,email;
    public  static Button send_d,pdf;
    DatabaseReference reference;
    StorageReference storageReference;


    public String ap_name,ap_sap,ap_email,ap_dept;
    public TextView urll;
    public Uri url1,url2;
    public int c=0;
    Set<String> hash_Set = new HashSet<String>();
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_form);
        Spinner year=findViewById(R.id.Appli_Year);
        Spinner dept=findViewById(R.id.departments);


        hash_Set.add("tejasghone73@gmail.com");
        hash_Set.add("rajshah@gmail.com");
        hash_Set.add("Geeks");
        hash_Set.add("Example");
        hash_Set.add("Set");

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(
                this,
                R.array.Applicant_Dept,
                android.R.layout.simple_spinner_item

        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dept.setAdapter(adapter);
        dept.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ap_dept=adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        year.setOnItemSelectedListener(this);
        name=findViewById(R.id.Appli_Name);
        sap=findViewById(R.id.Appli_Sap);
        email=findViewById(R.id.Appli_Email);
        send_d=findViewById(R.id.Appli_Details);
        urll=findViewById(R.id.Pdf_url);
        pdf=findViewById(R.id.pdf_btn);
        storageReference= FirebaseStorage.getInstance().getReference();

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPDF();
            }
        });



        send_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            ap_name=name.getText().toString();
            ap_sap=sap.getText().toString();
            ap_email=email.getText().toString();
           uploadPDFfile(url1);



            }
        });

    }
    private void selectPDF(){

        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"SELECT PDF FILE"),1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
//            uploadPDFfile(data.getData());
            urll.setText(data.getDataString());
            url1=data.getData();
        }
    }

    private void uploadPDFfile(Uri data) {


        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uploading your Application");
        progressDialog.show();
        StorageReference refer=storageReference.child("Applicant_CVs/"+ap_dept+"/"+ap_year+"/"+ap_name);
        refer.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        boolean f=hash_Set.contains(ap_email);
                        if(f)
                        {c=3;}
                        Applicant_Details details=new Applicant_Details(ap_name,ap_email,ap_dept,ap_year,ap_sap,0,c);
                        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        reference= FirebaseDatabase.getInstance().getReference().child("Applications");
                        reference.child(user).setValue(details);

                        Toast.makeText(ApplicationForm.this,"Application Uploaded",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(ApplicationForm.this,InternshipCompany.class));

                    }


                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.setMessage("Uploading..");


            }
        });


    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ap_year=adapterView.getSelectedItem().toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
