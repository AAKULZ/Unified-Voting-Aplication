package com.example.unifiedvotingapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity1 extends AppCompatActivity {

    TextView detailDesc,detailTitle,nameET,instituteIdET,mobileET,emailET,dobET,genderET,batchET,branchET,yearET;
    ImageView detailImage;
    private DatabaseReference databaseReference,dbr;
    FloatingActionButton deleteButton,editButton;
    String key="";
    String imageUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);

        detailDesc=findViewById(R.id.detailDesc);
        detailImage=findViewById(R.id.detailImage);
        detailTitle=findViewById(R.id.detailTitle);
        deleteButton=findViewById(R.id.deleteButton);
        editButton=findViewById(R.id.editButton);
        nameET=findViewById(R.id.nameET);
        instituteIdET=findViewById(R.id.instituteIdET);
        dobET=findViewById(R.id.dobET);
        genderET=findViewById(R.id.genderET);
        batchET=findViewById(R.id.batchET);
        branchET=findViewById(R.id.branchET);
        yearET=findViewById(R.id.yearET);
        mobileET=findViewById(R.id.mobileET);
        emailET=findViewById(R.id.emailET);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            key=bundle.getString("Key");
            imageUrl=bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
            nameET.setText(bundle.getString("name"));
            instituteIdET.setText(bundle.getString("id"));
            dobET.setText(bundle.getString("dob"));
            genderET.setText(bundle.getString("gender"));
            batchET.setText(bundle.getString("batch"));
            yearET.setText(bundle.getString("year"));
            branchET.setText(bundle.getString("branch"));
            mobileET.setText(bundle.getString("mobile"));
            emailET.setText(bundle.getString("email"));


        }
        dbr = FirebaseDatabase.getInstance().getReference("Users").child(bundle.getString("id").toLowerCase());
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbr.child("admin").setValue("false");
                Toast.makeText(getApplicationContext(),"Sub-Admin Access Denied",Toast.LENGTH_SHORT).show();
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbr.child("admin").setValue("true");
                Toast.makeText(getApplicationContext(),"Sub-Admin Access Granted",Toast.LENGTH_SHORT).show();
            }
        });
    }
}