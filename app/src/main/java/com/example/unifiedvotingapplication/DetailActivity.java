package com.example.unifiedvotingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc,detailTitle,conductor,conductorMobile,description,psd,frd,candidateBranch,candidateBatch,voterBranch,voterBatch,pst,frt;
    ImageView detailImage;
    FloatingActionButton deleteButton;
    String key="";
    String imageUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDesc=findViewById(R.id.detailDesc);
        detailImage=findViewById(R.id.detailImage);
        detailTitle=findViewById(R.id.detailTitle);
        deleteButton=findViewById(R.id.deleteButton);
        description=findViewById(R.id.description);
        conductor=findViewById(R.id.conductor);
        conductorMobile=findViewById(R.id.conductorMobile);
        frd=findViewById(R.id.frd);
        psd=findViewById(R.id.psd);
        pst=findViewById(R.id.pst);
        frt=findViewById(R.id.frt);
        voterBranch=findViewById(R.id.voterBranch);
        voterBatch=findViewById(R.id.voterBatch);
        candidateBatch=findViewById(R.id.candidateBatch);
        candidateBranch=findViewById(R.id.candidateBranch);

        Bundle bundle = getIntent().getExtras();    
        if(bundle!=null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            key=bundle.getString("Key");
            imageUrl=bundle.getString("Image");
            conductor.setText(bundle.getString("conductor"));
            conductorMobile.setText(bundle.getString("conductorMobile"));
            description.setText(bundle.getString("description"));
            frd.setText(bundle.getString("frd"));
            psd.setText(bundle.getString("psd"));
            pst.setText(bundle.getString("pst"));
            frt.setText(bundle.getString("frt"));
            voterBranch.setText(bundle.getString("vbr"));
            voterBatch.setText(bundle.getString("vba"));
            candidateBranch.setText(bundle.getString("cbr"));
            candidateBatch.setText(bundle.getString("cba"));
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Elections");
                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference storageReference=storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),electionList.class));
                        finish();
                    }
                });
            }
        });
    }
}