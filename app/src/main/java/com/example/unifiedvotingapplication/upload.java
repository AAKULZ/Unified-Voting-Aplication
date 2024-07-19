package com.example.unifiedvotingapplication;

import static com.example.unifiedvotingapplication.R.drawable;
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
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class upload extends AppCompatActivity {

    Uri uri;
    String imageURL;
    private FirebaseAuth firebaseAuth;


    //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://unified-voting-application-default-rtdb.firebaseio.com/");
    boolean regElectionTitle=false,regConductor=false,regDescription=false,regConductorEmail=false,regConductorMobile=false,regAccessPassword=false,regConPassword=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.onRestoreInstanceState(savedInstanceState);
        setContentView(layout.activity_upload);

        EditText electionTitle = findViewById(R.id.electionTitle);
        EditText conductor = findViewById(id.conductor);
        EditText description = findViewById(id.description);
        EditText conductorEmail = findViewById(id.conductorEmail);
        EditText conductorMobile = findViewById(id.conductorMobile);
        EditText accessPassword = findViewById(id.accessPassword);
        EditText conPassword = findViewById(id.conPassword);
        TextView nextBtn = findViewById(id.nextBtn);
        ProgressBar progressBar = findViewById(R.id.progressbar);

        ImageView uploadImage= findViewById(id.uploadImage);
        //final ImageView uploadImage = findViewById(id.uploadImage);



        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){

            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()== Activity.RESULT_OK){
                    Intent data = result.getData();
                    uri = data.getData();
                    uploadImage.setImageURI(uri);

                }else{
                    Toast.makeText(upload.this,"No Image Selected",Toast.LENGTH_SHORT).show();
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

        electionTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String n=electionTitle.getText().toString();
                    if(n.isEmpty()){
                        electionTitle.setError("Field cannot be empty");
                        electionTitle.setBackground(getDrawable(R.drawable.round_dark_grey));

                    }
                    else{
                        electionTitle.setError(null);
                        regElectionTitle=true;
                        electionTitle.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        electionTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String n=electionTitle.getText().toString();
                if(n.isEmpty()){
                    electionTitle.setError("Field cannot be empty");
                    electionTitle.setBackground(getDrawable(R.drawable.round_dark_grey));

                }
                else{
                    electionTitle.setError(null);
                    regElectionTitle=true;
                    electionTitle.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        conductor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String n=conductor.getText().toString();
                    if(n.isEmpty()){
                        conductor.setError("Field cannot be empty");
                        conductor.setBackground(getDrawable(R.drawable.round_dark_grey));

                    }
                    else{
                        conductor.setError(null);
                        regConductor=true;
                        conductor.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        conductor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String n=conductor.getText().toString();
                if(n.isEmpty()){
                    conductor.setError("Field cannot be empty");
                    conductor.setBackground(getDrawable(R.drawable.round_dark_grey));

                }
                else{
                    conductor.setError(null);
                    regConductor=true;
                    conductor.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        conductorEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String e=conductorEmail.getText().toString();
                    String ep="[a-z]+2[0-9](bcy|bcs|bcd|bec)(\\d{1}|\\d{2}|\\d{3})@iiitkottayam.ac.in";
                    if(e.isEmpty()){
                        conductorEmail.setError("Field cannot be empty");
                        conductorEmail.setBackground(getDrawable(R.drawable.round_dark_grey));

                    } else if (!e.matches(ep)) {
                        conductorEmail.setError("Invalid email address");
                        conductorEmail.setBackground(getDrawable(R.drawable.round_dark_grey));

                    }
                    else{
                        conductorEmail.setError(null);
                        regConductorEmail=true;
                        conductorEmail.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        conductorEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String e=conductorEmail.getText().toString();
                String ep="[a-z]+2[0-9](bcy|bcs|bcd|bec)(\\d{1}|\\d{2}|\\d{3})@iiitkottayam.ac.in";
                if(e.isEmpty()){
                    conductorEmail.setError("Field cannot be empty");
                    conductorEmail.setBackground(getDrawable(R.drawable.round_dark_grey));

                } else if (!e.matches(ep)) {
                    conductorEmail.setError("Invalid email address");
                    conductorEmail.setBackground(getDrawable(R.drawable.round_dark_grey));

                }
                else{
                    conductorEmail.setError(null);
                    regConductorEmail=true;
                    conductorEmail.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        conductorMobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String m=conductorMobile.getText().toString();
                    String mp="^(6|7|8|9)\\d{9}";
                    if(m.isEmpty()){
                        conductorMobile.setError("Field cannot be empty");
                        conductorMobile.setBackground(getDrawable(R.drawable.round_dark_grey));

                    } else if (!m.matches(mp)) {
                        conductorMobile.setError("Invalid mobile Number");
                        conductorMobile.setBackground(getDrawable(R.drawable.round_dark_grey));

                    }
                    else{
                        conductorMobile.setError(null);
                        regConductorMobile=true;
                        conductorMobile.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));}
                }
            }
        });

        conductorMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String n=conductorMobile.getText().toString();
                String m=conductorMobile.getText().toString();
                String mp="^(6|7|8|9)\\d{9}";
                if(m.isEmpty()){
                    conductorMobile.setError("Field cannot be empty");
                    conductorMobile.setBackground(getDrawable(R.drawable.round_dark_grey));

                } else if (!m.matches(mp)) {
                    conductorMobile.setError("Invalid mobile Number");
                    conductorMobile.setBackground(getDrawable(R.drawable.round_dark_grey));

                }
                else{
                    conductorMobile.setError(null);
                    regConductorMobile=true;
                    conductorMobile.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        description.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String n=description.getText().toString();
                    if(n.isEmpty()){
                        description.setError("Field cannot be empty");
                        description.setBackground(getDrawable(R.drawable.round_dark_grey));

                    }
                    else{
                        description.setError(null);
                        regDescription=true;
                        description.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String n=description.getText().toString();
                if(n.isEmpty()){
                    description.setError("Field cannot be empty");
                    description.setBackground(getDrawable(R.drawable.round_dark_grey));

                }
                else{
                    description.setError(null);
                    regDescription=true;
                    description.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        accessPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String p=accessPassword.getText().toString();
                    String pp="[A-Z]{2}\\d{2}[A-Z]{2}\\d{2}";
                    String pp1="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";
                    if(p.isEmpty()){
                        accessPassword.setError("Field cannot be empty");
                        accessPassword.setBackground(getDrawable(R.drawable.round_dark_grey));

                    } else if (!(p.matches(pp)||p.matches(pp1))) {
                        accessPassword.setError("Institue-ID Password\n----OR----\nAtleast:\n1 Digit\n1 Uppercase Character\n1 Lowercase Character\n1 Special Character\n6 Digits minimum");
                        accessPassword.setBackground(getDrawable(R.drawable.round_dark_grey));

                    }
                    else{
                        accessPassword.setError(null);
                        regAccessPassword=true;
                        accessPassword.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        accessPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String n=accessPassword.getText().toString();
                String p=accessPassword.getText().toString();
                String pp="[A-Z]{2}\\d{2}[A-Z]{2}\\d{2}";
                String pp1="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";
                if(p.isEmpty()){
                    accessPassword.setError("Field cannot be empty");
                    accessPassword.setBackground(getDrawable(R.drawable.round_dark_grey));

                } else if (!(p.matches(pp)||p.matches(pp1))) {
                    accessPassword.setError("Institue-ID Password\n----OR----\nAtleast:\n1 Digit\n1 Uppercase Character\n1 Lowercase Character\n1 Special Character\n6 Digits minimum");
                    accessPassword.setBackground(getDrawable(R.drawable.round_dark_grey));

                }
                else{
                    accessPassword.setError(null);
                    regAccessPassword=true;
                    accessPassword.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        conPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String cp=accessPassword.getText().toString();
                    String cpp=conPassword.getText().toString();
                    if(cp.isEmpty()){
                        conPassword.setError("Field cannot be empty");
                        conPassword.setBackground(getDrawable(R.drawable.round_dark_grey));

                    } else if (!cp.matches(cpp)) {
                        conPassword.setError("Password do not match");
                        conPassword.setBackground(getDrawable(R.drawable.round_dark_grey));

                    }
                    else{
                        conPassword.setError(null);
                        regConPassword=true;
                        conPassword.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        conPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String cp=accessPassword.getText().toString();
                String cpp=conPassword.getText().toString();
                if(cp.isEmpty()){
                    conPassword.setError("Field cannot be empty");
                    conPassword.setBackground(getDrawable(R.drawable.round_dark_grey));

                } else if (!cp.matches(cpp)) {
                    conPassword.setError("Password do not match");
                    conPassword.setBackground(getDrawable(R.drawable.round_dark_grey));

                }
                else{
                    conPassword.setError(null);
                    regConPassword=true;
                    conPassword.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {

                String title=electionTitle.getText().toString();
                String conduc=conductor.getText().toString();
                String des=description.getText().toString();
                String email=conductorEmail.getText().toString();
                String mobile=conductorMobile.getText().toString();
                String access=accessPassword.getText().toString();
                String conpass=conPassword.getText().toString();

                if(regElectionTitle&&regConductor&&regDescription&&regConductorMobile&&regConductorEmail&&regAccessPassword&&regConPassword){
                    progressBar.setVisibility(View.VISIBLE);
                    nextBtn.setVisibility(View.INVISIBLE);

                    //--------------------------------------------->
                    //saveData(title,conduc,des,email,mobile,access);

                    //------------------------------------------------------------------------->
                    Toast.makeText(upload.this,"Ok",Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(getApplicationContext(), seteli.class);
                    intent.putExtra("electionTitle", title);
                    intent.putExtra("conductor", conduc);
                    intent.putExtra("description",des);
                    intent.putExtra("conductorEmail",email);
                    intent.putExtra("conductorMobile",mobile);
                    intent.putExtra("accessPassword",access);
                    intent.putExtra("conPassword",conpass);
                    intent.putExtra("imageUri",uri.toString());
                    startActivity(intent);

                }
                else{
                    if(regElectionTitle)electionTitle.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(regDescription)description.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(regConductor)conductor.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(regConductorMobile)conductorMobile.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(regConductorEmail)conductorEmail.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(regAccessPassword)accessPassword.setBackground(getDrawable(drawable.round_dark_grey));
                    if(regConPassword)conPassword.setBackground(getDrawable(drawable.round_dark_grey));

                    Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
                    }
                    else{
                        vib.vibrate(500);
                    }

                    Toast.makeText(upload.this,"Fill all Fields Correctly",Toast.LENGTH_SHORT).show();
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