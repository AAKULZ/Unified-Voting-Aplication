
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
import android.widget.CheckBox;
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

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class etp extends AppCompatActivity {

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
        setContentView(R.layout.activity_etp);
        otpEt1=findViewById(R.id.otpET1);
        otpEt2=findViewById(R.id.otpET2);
        otpEt3=findViewById(R.id.otpET3);
        otpEt4=findViewById(R.id.otpET4);
        otpEt5=findViewById(R.id.otpET5);
        otpEt6=findViewById(R.id.otpET6);


        final Button verifyBtn = findViewById(R.id.verifyBtn);
        resendBtn = findViewById(R.id.resendBtn);
        final TextView otpEmail = findViewById(R.id.otpEmail);
        final TextView otpMobile = findViewById(R.id.otpMobile);
        final CheckBox checkBox= findViewById(R.id.checkbox);
        final TextView electionTitle=findViewById(R.id.electionTitle);
        final TextView conductorName=findViewById(R.id.conductorName);

        //-------------------------------------------------
        final String title = getIntent().getStringExtra("electionTitle");
        final String conduc = getIntent().getStringExtra("conductor");
        final String des = getIntent().getStringExtra("description");
        final String email = getIntent().getStringExtra("conductorEmail");
        final String mobile = getIntent().getStringExtra("conductorMobile");
        final String access = getIntent().getStringExtra("accessPassword");
        final String conpass = getIntent().getStringExtra("conPassword");
        final String getotpbackend = getIntent().getStringExtra("backotp");
        final String imageUri=getIntent().getStringExtra("imageUri");
        ArrayList<String> cbranch = (ArrayList<String>) getIntent().getSerializableExtra("cbranch");
        ArrayList<String> cyear = (ArrayList<String>) getIntent().getSerializableExtra("cyear");
        ArrayList<String> cgender = (ArrayList<String>) getIntent().getSerializableExtra("cgender");
        ArrayList<String> cbatch = (ArrayList<String>) getIntent().getSerializableExtra("cbatch");

        ArrayList<String> vbranch = (ArrayList<String>) getIntent().getSerializableExtra("vbranch");
        ArrayList<String> vyear = (ArrayList<String>) getIntent().getSerializableExtra("vyear");
        ArrayList<String> vgender = (ArrayList<String>) getIntent().getSerializableExtra("vgender");
        ArrayList<String> vbatch = (ArrayList<String>) getIntent().getSerializableExtra("vbatch");


        final Uri uri=Uri.parse(imageUri);
        final String frd = getIntent().getStringExtra("frd");
        final String fcd = getIntent().getStringExtra("fcd");
        final String psd = getIntent().getStringExtra("psd");
        final String ped = getIntent().getStringExtra("ped");

        final String frt = getIntent().getStringExtra("frt");
        final String fct = getIntent().getStringExtra("fct");
        final String pst = getIntent().getStringExtra("pst");
        final String pet = getIntent().getStringExtra("pet");
        //-------------------------------------------------

        otpEmail.setText(email);
        otpMobile.setText(mobile);
        electionTitle.setText(title);
        conductorName.setText(conduc);


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



                if(checkBox.isChecked()&&generateOtp.length()==6){
                //Toast.makeText(otpv.this,"OTP Verify",Toast.LENGTH_SHORT).show();
                if(getotpbackend!=null) {
                    //progressbar.setVisibility(View.VISIBLE);
                    //verifyBtn.setVisibility(View.INVISIBLE);

                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getotpbackend, generateOtp);

                    mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(etp.this, "successful", Toast.LENGTH_SHORT).show();
                                StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Election Posters").child(uri.getLastPathSegment());

                                AlertDialog.Builder builder = new AlertDialog.Builder(etp.this);
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
                                        Toast.makeText(etp.this,imageURL,Toast.LENGTH_SHORT).show();
                                        uploadData(title,conduc,des,email,mobile,access,frd,fcd,frt,fct,psd,ped,pst,pet);
                                        dialog.dismiss();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialog.dismiss();
                                    }
                                });
                                /*databaseReference.child("Elections").child(title).child("Election Poster").setValue(imageURL);
                                databaseReference.child("Elections").child(title).child("Election Title").setValue(title);
                                databaseReference.child("Elections").child(title).child("Conductor").setValue(conduc);
                                databaseReference.child("Elections").child(title).child("Conductor-Mobile").setValue(mobile);
                                databaseReference.child("Elections").child(title).child("Conductor-Email").setValue(email);
                                databaseReference.child("Elections").child(title).child("Description").setValue(des);
                                databaseReference.child("Elections").child(title).child("Password").setValue(access);
                                databaseReference.child("Elections").child(title).child("Form Release Date").setValue(frd);
                                databaseReference.child("Elections").child(title).child("Poll Start Date").setValue(psd);
                                databaseReference.child("Elections").child(title).child("Form Close Time").setValue(fct);
                                databaseReference.child("Elections").child(title).child("Poll Start Time").setValue(pst);
                                databaseReference.child("Elections").child(title).child("Form Close Date").setValue(fcd);*/
                                //databaseReference.child("Elections").child(title).child("Poll End Date").setValue(ped);
                                //databaseReference.child("Elections").child(title).child("Form Close Time").setValue(fct);
                                //databaseReference.child("Elections").child(title).child("Poll End Time").setValue(pet);
                                /*StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Election Posters").child(uri.getLastPathSegment());
                                AlertDialog.Builder builder = new AlertDialog.Builder(etp.this);
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
                                        //--------------------------------------------------

                                        DataClass dataClass = new DataClass(title,des,conduc,email,mobile,access,frd,fcd,psd,ped,frt,fct,pst,pet,imageURL);
                                        FirebaseDatabase.getInstance().getReference().child("Elections").child(title).setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(etp.this,"Election Created Sucessfull",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(etp.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        //--------------------------------------------------
                                        dialog.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialog.dismiss();
                                    }
                                });*/


                            } else {
                                Toast.makeText(etp.this, "Sorry", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                    else{
                        Toast.makeText(etp.this,"PLease Check Your Internet Connectivity!",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(etp.this,"Please Enter All Digits",Toast.LENGTH_SHORT).show();
                }
            }
        });

        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PhoneAuthOptions.Builder builder =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber("+91"+mobile)
                                .setTimeout(60L, TimeUnit.SECONDS)
                                .setActivity(etp.this)
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

    public void uploadData(String title, String conduc, String des,String email,String mobile,String access,String frd,String fcd,String frt,String fct,String psd,String ped,String pst,String pet) {




        ArrayList<String> cbranch = (ArrayList<String>) getIntent().getSerializableExtra("cbranch");
        ArrayList<String> cyear = (ArrayList<String>) getIntent().getSerializableExtra("cyear");
        ArrayList<String> cgender = (ArrayList<String>) getIntent().getSerializableExtra("cgender");
        ArrayList<String> cbatch = (ArrayList<String>) getIntent().getSerializableExtra("cbatch");

        ArrayList<String> vbranch = (ArrayList<String>) getIntent().getSerializableExtra("vbranch");
        ArrayList<String> vyear = (ArrayList<String>) getIntent().getSerializableExtra("vyear");
        ArrayList<String> vgender = (ArrayList<String>) getIntent().getSerializableExtra("vgender");
        ArrayList<String> vbatch = (ArrayList<String>) getIntent().getSerializableExtra("vbatch");
        String cbr="";
        for (String item : cbranch) {
            cbr=cbr.concat(":").concat(item);
            Toast.makeText(etp.this,item.toString(),Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(etp.this,cbr.toString(),Toast.LENGTH_SHORT).show();

        String cyr="";
        for (String item : cyear) {
            cyr=cyr.concat(":").concat(item);
        }

        String cg="";
        for (String item : cgender) {
            cg=cg.concat(":").concat(item);
        }

        String cba="";
        for (String item : cbatch) {
            cba=cba.concat(":").concat(item);
        }

        String vbr="";
        for (String item : vbranch) {
            vbr=vbr.concat(":").concat(item);
        }

        String vyr="";
        for (String item : vyear) {
            vyr=vyr.concat(":").concat(item);
        }

        String vg="";
        for (String item : vgender) {
            vg=vg.concat(":").concat(item);
        }
        String vba="";
        for (String item : vbatch) {
            vba=vba.concat(":");
            vba=vba.concat(item);
        }

        DataClass dataClass = new DataClass(title, des, conduc,mobile, email,  access, imageURL.toString(),frd,fcd,frt,fct,psd,ped,pst,pet,cbr,cyr,cg,cba,vbr,vyr,vg,vba);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Elections");
        String key = databaseReference.push().getKey();
        databaseReference.child(title).setValue(dataClass).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(etp.this,"Data Uploaded Sucessfuly",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(etp.this,"Issues",Toast.LENGTH_SHORT).show();
            }
        });



    }

}