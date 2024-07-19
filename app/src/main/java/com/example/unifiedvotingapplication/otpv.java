package com.example.unifiedvotingapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.concurrent.TimeUnit;

public class otpv extends AppCompatActivity {

    /*PhoneAuthProvider.ForceResendingToken resendtoken;
    String VerificationCode;*/
    FirebaseAuth mAuth =FirebaseAuth.getInstance();
    String imageURL;
    //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://unified-voting-application-default-rtdb.firebaseio.com/");
    //FirebaseFirestore firestore;
    //CollectionReference reference;
    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s,int start, int before, int count) {

        }
        @Override
        public void afterTextChanged(Editable s) {
            if(s.length()>0){
                if(selectedPosition==0){
                    selectedPosition=1;
                    showKeyboard(otpEt2);
                }
                else if(selectedPosition==1){
                    selectedPosition=2;
                    showKeyboard(otpEt3);
                }
                else if(selectedPosition==2){
                    selectedPosition=3;
                    showKeyboard(otpEt4);
                }
                else if(selectedPosition==3) {
                    selectedPosition = 4;
                    showKeyboard(otpEt5);
                }
                else if(selectedPosition==4) {
                    selectedPosition = 5;
                    showKeyboard(otpEt6);
                }
            }
        }
    };
    private EditText otpEt1, otpEt2, otpEt3, otpEt4, otpEt5, otpEt6;
    private TextView resendBtn;
    private boolean resendEnabled = false;
    private int resendTime = 60;
    private int selectedPosition = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpv);
        otpEt1=findViewById(R.id.otpET1);
        otpEt2=findViewById(R.id.otpET2);
        otpEt3=findViewById(R.id.otpET3);
        otpEt4=findViewById(R.id.otpET4);
        otpEt5=findViewById(R.id.otpET5);
        otpEt6=findViewById(R.id.otpET6);


        final  Button verifyBtn = findViewById(R.id.verifyBtn);
        resendBtn = findViewById(R.id.resendBtn);
        final  TextView otpEmail = findViewById(R.id.otpEmail);
        final  TextView otpMobile = findViewById(R.id.otpMobile);

        final  String getEmail = getIntent().getStringExtra("email");
        final String getMobile = getIntent().getStringExtra("mobile");
        final String getotpbackend = getIntent().getStringExtra("backotp");
        final String getUser=getIntent().getStringExtra("user");
        final String getID=getIntent().getStringExtra("Id");
        final String getDOB=getIntent().getStringExtra("DOB");
        final String batchyear= getID.substring(0,4);
        final String branch= getID.substring(4,7).toUpperCase();
        final String getPass=getIntent().getStringExtra("Pass");
        final String getGender=getIntent().getStringExtra("gender");
        final String getBatch=getIntent().getStringExtra("batch");
        final String imageUri = getIntent().getStringExtra("imageUri");
        final Uri uri=Uri.parse(imageUri);
        otpEmail.setText(getEmail);
        otpMobile.setText(getMobile);

        final ProgressBar progressbar = findViewById(R.id.progressbar);

        otpEt1.addTextChangedListener(textWatcher);
        otpEt2.addTextChangedListener(textWatcher);
        otpEt3.addTextChangedListener(textWatcher);
        otpEt4.addTextChangedListener(textWatcher);
        otpEt5.addTextChangedListener(textWatcher);
        otpEt6.addTextChangedListener(textWatcher);
        showKeyboard(otpEt1);

        startCountDownTimer();
        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resendEnabled){
                    startCountDownTimer();
                }
            }
        });
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String generateOtp=otpEt1.getText().toString().trim()+otpEt2.getText().toString().trim()+otpEt3.getText().toString().trim()+otpEt4.getText().toString().trim()+otpEt5.getText().toString().trim()+otpEt6.getText().toString().trim();



                /*databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(getID)){
                            Toast.makeText(otpv.this,"Already Registered User",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            databaseReference.child("Users").child(getID).child("User Name").setValue(getUser);
                            databaseReference.child("Users").child(getID).child("Institute Id").setValue(getID);
                            databaseReference.child("Users").child(getID).child("Gender").setValue(getGender);
                            databaseReference.child("Users").child(getID).child("Date of Birth").setValue(getDOB);
                            databaseReference.child("Users").child(getID).child("Batch Year").setValue(batchyear);
                            databaseReference.child("Users").child(getID).child("Batch Divison").setValue(getBatch);
                            databaseReference.child("Users").child(getID).child("Branch").setValue(branch);
                            databaseReference.child("Users").child(getID).child("Mobile no").setValue(getMobile);
                            databaseReference.child("Users").child(getID).child("Password").setValue(getPass);
                            Toast.makeText(otpv.this,"Registered User Success",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/

                if(generateOtp.length()==6){
                    //Toast.makeText(otpv.this,"OTP Verify",Toast.LENGTH_SHORT).show();
                    if(getotpbackend!=null){
                        //progressbar.setVisibility(View.VISIBLE);
                        //verifyBtn.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getotpbackend,generateOtp);

                        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    /*Toast.makeText(otpv.this,"successful",Toast.LENGTH_SHORT).show();
                                    databaseReference.child("Users").child(getID).child("User Name").setValue(getUser);
                                    databaseReference.child("Users").child(getID).child("Institute Id").setValue(getID);
                                    databaseReference.child("Users").child(getID).child("Gender").setValue(getGender);
                                    databaseReference.child("Users").child(getID).child("Date of Birth").setValue(getDOB);
                                    databaseReference.child("Users").child(getID).child("Batch Year").setValue(batchyear);
                                    databaseReference.child("Users").child(getID).child("Batch Divison").setValue(getBatch);
                                    databaseReference.child("Users").child(getID).child("Branch").setValue(branch);
                                    databaseReference.child("Users").child(getID).child("Mobile no").setValue(getMobile);
                                    databaseReference.child("Users").child(getID).child("Password").setValue(getPass);
                                    Toast.makeText(otpv.this,"Registered User Success",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);*/
                                    Toast.makeText(otpv.this, "successful", Toast.LENGTH_SHORT).show();
                                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Election Posters").child(uri.getLastPathSegment());

                                    AlertDialog.Builder builder = new AlertDialog.Builder(otpv.this);
                                    builder.setCancelable(false);
                                    builder.setView(R.layout.progress_layout);
                                    AlertDialog dialog = builder.create();
                                    dialog.show();

                                    storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                                            while(!uriTask.isComplete());
                                            Uri urlImage = uriTask.getResult();
                                            imageURL = urlImage.toString();
                                            Toast.makeText(otpv.this,imageURL,Toast.LENGTH_SHORT).show();
                                            uploadData(getUser,getID,getDOB,getGender,getBatch,getMobile,getEmail,getPass);
                                            dialog.dismiss();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            dialog.dismiss();
                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(otpv.this,"Sorry",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        /*PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getotpbackend, generateOtp);
                        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressbar.setVisibility(View.GONE);
                                verifyBtn.setVisibility(View.VISIBLE);

                                if(task.isSuccessful()){
                                    //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    //startActivity(intent);
                                    Toast.makeText(otpv.this,"Successful",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(otpv.this, "Enter Correct OTP", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });*/
                        //Toast.makeText(otpv.this,"Successfull",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(otpv.this,"PLease Check Your Internet Connectivity!",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(otpv.this,"Please Enter All Digits",Toast.LENGTH_SHORT).show();
                }
            }
        });

        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PhoneAuthOptions.Builder builder =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber("+91"+getMobile)
                                .setTimeout(60L,TimeUnit.SECONDS)
                                .setActivity(otpv.this)
                                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                    }
                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        Toast.makeText(getApplicationContext(),"Incorrect PIN",Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(s, forceResendingToken);
                                        //getotpbackend=s;
                                        Toast.makeText(getApplicationContext(),"OTP Sent Sucessfully",Toast.LENGTH_SHORT).show();

                                    }
                                });

                PhoneAuthProvider.verifyPhoneNumber(builder.build());

            }


        });
    }

    private void showKeyboard(EditText otpET){
        otpET.requestFocus();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otpET,InputMethodManager.SHOW_IMPLICIT);
    }

    private void startCountDownTimer(){
        resendEnabled = false;
        resendBtn.setTextColor(Color.parseColor("#99000000"));
        new CountDownTimer(resendTime* 1000L, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                resendBtn.setText("Resend Code ("+(millisUntilFinished/1000)+")");
            }

            @Override
            public void onFinish() {
                resendEnabled=true;
                resendBtn.setText("Resend Code");
                resendBtn.setTextColor(getResources().getColor(R.color.primary));
            }
        }.start();
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_DEL){
            if(selectedPosition==5){
                selectedPosition=4;
                showKeyboard(otpEt5);
            }
            else if(selectedPosition==4){
                selectedPosition=3;
                showKeyboard(otpEt4);
            }
            else if(selectedPosition==3){
                selectedPosition=2;
                showKeyboard(otpEt3);
            }
            else if(selectedPosition==2){
                selectedPosition=1;
                showKeyboard(otpEt2);
            }
            else if (selectedPosition == 1) {
                selectedPosition=0;
                showKeyboard(otpEt1);
            }
            return true;
        }
        else{
            return super.onKeyUp(keyCode,event);
        }

    }

    public void uploadData(String user, String id, String dob,String gender,String batch,String mobile,String email, String pass) {


        DataClass1 dataClass = new DataClass1(user,id,dob,gender,batch,mobile,email,pass, imageURL.toString());

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        String key = databaseReference.push().getKey();
        databaseReference.child(id).setValue(dataClass).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(otpv.this,"Data Uploaded Sucessfuly",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(otpv.this,"Issues",Toast.LENGTH_SHORT).show();
            }
        });
    }

}