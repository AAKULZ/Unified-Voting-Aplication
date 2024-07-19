package com.example.unifiedvotingapplication;

import static com.example.unifiedvotingapplication.R.id;
import static com.example.unifiedvotingapplication.R.layout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class candidateForm extends AppCompatActivity {

    Uri uri;
    String imageURL;
    private FirebaseAuth firebaseAuth;
    FirebaseAuth mAuth =FirebaseAuth.getInstance();
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://unified-voting-application-default-rtdb.firebaseio.com/");
    String mobile;

    //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://unified-voting-application-default-rtdb.firebaseio.com/");
    boolean regobj=false,regpro=false,regach=false,regoth=false,regimg=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.onRestoreInstanceState(savedInstanceState);
        setContentView(layout.activity_candidate_form);


        EditText objectives = findViewById(id.objectives);
        EditText achivements = findViewById(id.achivements);
        EditText promises = findViewById(id.promises);
        EditText others = findViewById(id.others);
        final String user=getIntent().getStringExtra("user");
        final String election=getIntent().getStringExtra("election");

        TextView nextBtn = findViewById(id.nextBtn);
        ProgressBar progressBar=findViewById(id.progressbar);


        ImageView uploadImage= findViewById(id.uploadImage);
        //final ImageView uploadImage = findViewById(id.uploadImage);

        databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(user)){
                    mobile = snapshot.child(user).child("mobile").getValue(String.class);

                }
                else{
                    Toast.makeText(candidateForm.this,"Network Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){

                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadImage.setImageURI(uri);
                            regimg=true;

                        }else{
                            Toast.makeText(candidateForm.this,"No Image Selected",Toast.LENGTH_SHORT).show();
                            regimg=false;
                        }
                    }

                }
        );

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photopicker = new Intent(Intent.ACTION_PICK);
                photopicker.setType("image/*");
                activityResultLauncher.launch(photopicker);
            }
        });

        objectives.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String n=objectives.getText().toString();
                    if(n.isEmpty()){
                        objectives.setError("Field cannot be empty");
                        objectives.setBackground(getDrawable(R.drawable.round_dark_grey));regobj=false;

                    }
                    else{
                        objectives.setError(null);
                        regobj=true;
                        objectives.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        objectives.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String n=objectives.getText().toString();
                if(n.isEmpty()){
                    objectives.setError("Field cannot be empty");
                    objectives.setBackground(getDrawable(R.drawable.round_dark_grey));regobj=false;

                }
                else{
                    objectives.setError(null);
                    regobj=true;
                    objectives.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        achivements.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String n=achivements.getText().toString();
                    if(n.isEmpty()){
                        achivements.setError("Field cannot be empty");
                        achivements.setBackground(getDrawable(R.drawable.round_dark_grey));regach=false;

                    }
                    else{
                        achivements.setError(null);
                        regach=true;
                        achivements.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        achivements.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String n=achivements.getText().toString();
                if(n.isEmpty()){
                    achivements.setError("Field cannot be empty");
                    achivements.setBackground(getDrawable(R.drawable.round_dark_grey));regach=false;

                }
                else{
                    achivements.setError(null);
                    regach=true;
                    achivements.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        promises.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String n=promises.getText().toString();
                    if(n.isEmpty()){
                        promises.setError("Field cannot be empty");
                        promises.setBackground(getDrawable(R.drawable.round_dark_grey));regpro=false;

                    }
                    else{
                        promises.setError(null);
                        regpro=true;
                        promises.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        promises.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String n=promises.getText().toString();
                if(n.isEmpty()){
                    promises.setError("Field cannot be empty");
                    promises.setBackground(getDrawable(R.drawable.round_dark_grey));regpro=false;

                }
                else{
                    promises.setError(null);
                    regpro=true;
                    promises.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        others.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String n=others.getText().toString();
                    if(n.isEmpty()){
                        others.setError("Field cannot be empty");
                        others.setBackground(getDrawable(R.drawable.round_dark_grey));regoth=false;

                    }
                    else{
                        others.setError(null);
                        regoth=true;
                        others.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        others.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String n=others.getText().toString();
                if(n.isEmpty()){
                    others.setError("Field cannot be empty");
                    others.setBackground(getDrawable(R.drawable.round_dark_grey));regoth=false;

                }
                else{
                    others.setError(null);
                    regoth=true;
                    others.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {

                String obj=objectives.getText().toString();
                String ach=achivements.getText().toString();
                String pro=promises.getText().toString();
                String oth=others.getText().toString();


                if(regoth&&regach&&regpro&&regobj&&regimg){
                    progressBar.setVisibility(View.VISIBLE);
                    nextBtn.setVisibility(View.INVISIBLE);

                    PhoneAuthOptions.Builder builder =
                            PhoneAuthOptions.newBuilder(mAuth)
                                    .setPhoneNumber("+91"+mobile)
                                    .setTimeout(60L, TimeUnit.SECONDS)
                                    .setActivity(candidateForm.this)
                                    .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                            progressBar.setVisibility(View.GONE);
                                            nextBtn.setVisibility(View.VISIBLE);
                                            Toast.makeText(candidateForm.this,"OTP Sucess",Toast.LENGTH_SHORT).show();
                                        }
                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {
                                            Toast.makeText(getApplicationContext(),"Verification Failed",Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                            nextBtn.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                            super.onCodeSent(s, forceResendingToken);


                                            Toast.makeText(getApplicationContext(),"OTP Sent Sucessfully",Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                            nextBtn.setVisibility(View.VISIBLE);
                                            Intent intent = new Intent(getApplicationContext(), ctp.class);
                                            intent.putExtra("obj", obj);
                                            intent.putExtra("ach",ach);
                                            intent.putExtra("oth",oth);
                                            intent.putExtra("pro",pro);
                                            intent.putExtra("user",user);
                                            intent.putExtra("election",election);
                                            Toast.makeText(candidateForm.this,election,Toast.LENGTH_SHORT).show();
                                            intent.putExtra("mobile",mobile);
                                            intent.putExtra("backotp",s);
                                            intent.putExtra("imageUri",uri.toString());
                                            startActivity(intent);


                                        }
                                    });
                    PhoneAuthProvider.verifyPhoneNumber(builder.build());

                    //--------------------------------------------->
                    //saveData(title,conduc,des,email,mobile,access);

                    //------------------------------------------------------------------------->

                }
                else{
                    if(!regach)achivements.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!regobj)objectives.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!regpro)promises.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!regimg)uploadImage.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!regoth)others.setBackground(getDrawable(R.drawable.round_dark_grey));


                    Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
                    }
                    else{
                        vib.vibrate(500);
                    }

                    Toast.makeText(candidateForm.this,"Fill all Fields Correctly",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    /*public void saveData(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Election Posters").child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(upload.this);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

        StorageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL =urlImage.toString();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });

    }*/

}