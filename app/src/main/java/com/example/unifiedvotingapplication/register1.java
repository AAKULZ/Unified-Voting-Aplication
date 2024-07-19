package com.example.unifiedvotingapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class register1 extends AppCompatActivity{
    FirebaseAuth mAuth =FirebaseAuth.getInstance();

    private otpv binding;

    Uri uri;
    String imageURL;

    Boolean regUser=false,regId=false,regDOB=false,regEmail=false,regMobile=false,regPass=false,regCon=false,regRG1=false,regRG2=false;

    private boolean passwordShowing = false;
    private boolean conPasswordShowing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register1);


        final EditText name =findViewById(R.id.fullNameET);
        final EditText instituteid =findViewById(R.id.instituteIdET);
        final EditText email =findViewById(R.id.emailET);
        final EditText mobile = findViewById(R.id.mobileET);

        final Calendar calendar = Calendar.getInstance();
        final RadioButton r1=findViewById(R.id.r1);
        final RadioButton r2=findViewById(R.id.r2);
        final RadioButton r3=findViewById(R.id.r3);
        final RadioButton rr1=findViewById(R.id.rr1);
        final RadioButton rr2=findViewById(R.id.rr2);

        final EditText password = findViewById(R.id.passwordET);
        final EditText conPassword = findViewById(R.id.conpasswordET);
        final ImageView passwordIcon = findViewById(R.id.passwordIcon);
        final ImageView conPasswordIcon = findViewById(R.id.conpasswordIcon);
        final ImageView calIcon = findViewById(R.id.calIcon);
        final RadioGroup rg1=findViewById(R.id.rg1);
        final RadioGroup rg2=findViewById(R.id.rg2);
        final EditText dateformat = findViewById(R.id.DOBET);

        final AppCompatButton signUpBtn = findViewById(R.id.signUpBtn);
        final TextView signInBtn = findViewById(R.id.signInBtn);
        final ProgressBar progressBar = findViewById(R.id.progressbar);
        final TextView rgt1=findViewById(R.id.rgt1);
        final TextView rgt2=findViewById(R.id.rgt2);
        ImageView uploadImage= findViewById(R.id.uploadImage);

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
                            Toast.makeText(register1.this,"No Image Selected",Toast.LENGTH_SHORT).show();
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


        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String n=name.getText().toString();
                    if(n.isEmpty()){
                        name.setError("Field cannot be empty");
                        name.setBackground(getDrawable(R.drawable.round_dark_grey));

                    }
                    else{
                        name.setError(null);
                        regUser=true;
                        if(regUser&&regId&&regDOB&&regEmail&&regMobile&&regPass&&regCon) signUpBtn.setEnabled(true);
                        name.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String n=name.getText().toString();
                if(n.isEmpty()){
                    name.setError("Field cannot be empty");
                    name.setBackground(getDrawable(R.drawable.round_dark_grey));

                }
                else{
                    name.setError(null);
                    regUser=true;
                    if(regUser&&regId&&regDOB&&regEmail&&regMobile&&regPass&&regCon) signUpBtn.setEnabled(true);
                    name.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        instituteid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String i=instituteid.getText().toString();
                    String ip="^20\\d{2}(bcs|bcy|bcd|bec|BCS|BCY|BCD|BEC)\\d{4}";
                    if(i.isEmpty()){
                        instituteid.setError("Field cannot be empty");
                        instituteid.setBackground(getDrawable(R.drawable.round_dark_grey));

                    } else if (!i.matches(ip)) {
                        instituteid.setError("Invalid Institute ID");
                        instituteid.setBackground(getDrawable(R.drawable.round_dark_grey));

                    }
                    else{
                        instituteid.setError(null);
                        regId=true;
                        if(regUser&&regId&&regDOB&&regEmail&&regMobile&&regPass&&regCon)signUpBtn.setEnabled(true);
                        instituteid.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        instituteid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String i=instituteid.getText().toString();
                String ip="^20\\d{2}(bcs|bcy|bcd|bec|BCS|BCY|BCD|BEC)\\d{4}";
                if(i.isEmpty()){
                    instituteid.setError("Field cannot be empty");
                    instituteid.setBackground(getDrawable(R.drawable.round_dark_grey));

                } else if (!i.matches(ip)) {
                    instituteid.setError("Invalid Institute ID");
                    instituteid.setBackground(getDrawable(R.drawable.round_dark_grey));

                }
                else{
                    instituteid.setError(null);
                    regId=true;
                    if(regUser&&regId&&regDOB&&regEmail&&regMobile&&regPass&&regCon)signUpBtn.setEnabled(true);
                    instituteid.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String e=email.getText().toString();
                    String ep="[a-z]+2[0-9](bcy|bcs|bcd|bec)(\\d{1}|\\d{2}|\\d{3})@iiitkottayam.ac.in";
                    if(e.isEmpty()){
                        email.setError("Field cannot be empty");
                        email.setBackground(getDrawable(R.drawable.round_dark_grey));

                    } else if (!e.matches(ep)) {
                        email.setError("Invalid email address");
                        email.setBackground(getDrawable(R.drawable.round_dark_grey));

                    }
                    else{
                        email.setError(null);
                        regEmail=true;
                        if(regUser&&regId&&regDOB&&regEmail&&regMobile&&regPass&&regCon)signUpBtn.setEnabled(true);
                        email.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String e=email.getText().toString();
                String ep="[a-z]+2[0-9](bcy|bcs|bcd|bec)(\\d{1}|\\d{2}|\\d{3})@iiitkottayam.ac.in";
                if(e.isEmpty()){
                    email.setError("Field cannot be empty");
                    email.setBackground(getDrawable(R.drawable.round_dark_grey));

                } else if (!e.matches(ep)) {
                    email.setError("Invalid email address");
                    email.setBackground(getDrawable(R.drawable.round_dark_grey));

                }
                else{
                    email.setError(null);
                    regEmail=true;
                    if(regUser&&regId&&regDOB&&regEmail&&regMobile&&regPass&&regCon)signUpBtn.setEnabled(true);
                    email.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String m=mobile.getText().toString();
                    String mp="^(6|7|8|9)\\d{9}";
                    if(m.isEmpty()){
                        mobile.setError("Field cannot be empty");
                        mobile.setBackground(getDrawable(R.drawable.round_dark_grey));

                    } else if (!m.matches(mp)) {
                        mobile.setError("Invalid mobile Number");
                        mobile.setBackground(getDrawable(R.drawable.round_dark_grey));

                    }
                    else{
                        mobile.setError(null);
                        regMobile=true;
                        if(regUser&&regId&&regDOB&&regEmail&&regMobile&&regPass&&regCon)signUpBtn.setEnabled(true);
                        mobile.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String m=mobile.getText().toString();
                String mp="^(6|7|8|9)\\d{9}";
                if(m.isEmpty()){
                    mobile.setError("Field cannot be empty");
                    mobile.setBackground(getDrawable(R.drawable.round_dark_grey));

                } else if (!m.matches(mp)) {
                    mobile.setError("Invalid mobile Number");
                    mobile.setBackground(getDrawable(R.drawable.round_dark_grey));

                }
                else{
                    mobile.setError(null);
                    regMobile=true;
                    if(regUser&&regId&&regDOB&&regEmail&&regMobile&&regPass&&regCon)signUpBtn.setEnabled(true);
                    mobile.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dateformat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String d=dateformat.getText().toString();
                    String dp="^([1-9]|1[0-9]|2[0-9]|3[01])-([1-9]|1[0-2])-\\d{4}";
                    if(d.isEmpty()){
                        dateformat.setError("Field cannot be empty");
                        dateformat.setBackground(getDrawable(R.drawable.round_dark_grey));

                    } else if (!d.matches(dp)) {
                        dateformat.setError("Invalid Date Format");
                        dateformat.setBackground(getDrawable(R.drawable.round_dark_grey));

                    }
                    else{
                        dateformat.setError(null);
                        regDOB=true;
                        if(regUser&&regId&&regDOB&&regEmail&&regMobile&&regPass&&regCon)signUpBtn.setEnabled(true);
                        dateformat.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        dateformat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String d=dateformat.getText().toString();
                String dp="^([1-9]|1[0-9]|2[0-9]|3[0-1])-([1-9]|1[0-2])-\\d{4}";
                if(d.isEmpty()){
                    dateformat.setError("Field cannot be empty");
                    dateformat.setBackground(getDrawable(R.drawable.round_dark_grey));

                } else if (!d.matches(dp)) {
                    dateformat.setError("Invalid Date Format");
                    dateformat.setBackground(getDrawable(R.drawable.round_dark_grey));

                }
                else{
                    dateformat.setError(null);
                    regDOB=true;
                    if(regUser&&regId&&regDOB&&regEmail&&regMobile&&regPass&&regCon)signUpBtn.setEnabled(true);
                    dateformat.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));

                }}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String p=password.getText().toString();
                    String pp="[A-Z]{2}\\d{2}[A-Z]{2}\\d{2}";
                    String pp1="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";
                    if(p.isEmpty()){
                        password.setError("Field cannot be empty");
                        password.setBackground(getDrawable(R.drawable.round_dark_grey));

                    } else if (!(p.matches(pp)||p.matches(pp1))) {
                        password.setError("Institue-ID Password\n----OR----\nAtleast:\n1 Digit\n1 Uppercase Character\n1 Lowercase Character\n1 Special Character\n6 Digits minimum");
                        password.setBackground(getDrawable(R.drawable.round_dark_grey));

                    }
                    else{
                        password.setError(null);
                        regPass=true;
                        if(regUser&&regId&&regDOB&&regEmail&&regMobile&&regPass&&regCon)signUpBtn.setEnabled(true);
                        password.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    }
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String p=password.getText().toString();
                String pp="[A-Z]{2}\\d{2}[A-Z]{2}\\d{2}";
                String pp1="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";
                if(p.isEmpty()){
                    password.setError("Field cannot be empty");
                    password.setBackground(getDrawable(R.drawable.round_dark_grey));

                } else if (!(p.matches(pp)||p.matches(pp1))) {
                    password.setError("Institue-ID Password\n----OR----\nAtleast:\n1 Digit\n1 Uppercase Character\n1 Lowercase Character\n1 Special Character\n6 Digits minimum");
                    password.setBackground(getDrawable(R.drawable.round_dark_grey));

                }
                else{
                    password.setError(null);
                    regPass=true;
                    if(regUser&&regId&&regDOB&&regEmail&&regMobile&&regPass&&regCon)signUpBtn.setEnabled(true);
                    password.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        rg1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(rg1.getCheckedRadioButtonId()!=-1)rgt1.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    regRG1=true;
                }
            }
        });

        rg2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(rg2.getCheckedRadioButtonId()!=-1)rgt2.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    regRG2=true;
                }
            }
        });

        conPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String cp=password.getText().toString();
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
                        regCon=true;
                        if(regUser&&regId&&regDOB&&regEmail&&regMobile&&regPass&&regCon)signUpBtn.setEnabled(true);
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
                String cp=password.getText().toString();
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
                    regCon=true;
                    if(regUser&&regId&&regDOB&&regEmail&&regMobile&&regPass&&regCon)signUpBtn.setEnabled(true);
                    conPassword.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgt2.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgt2.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });

        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgt2.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });

        rr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rgt1.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });

        rr2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                rgt1.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });

        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordShowing){
                    passwordShowing=false;

                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    //passwordIcon.setImageResource(R.drawable.op);
                }
                else{
                    passwordShowing=true;

                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    //passwordIcon.setImageResource(R.drawable.oc);
                }
                password.setSelection(password.length());
            }
        });

        conPasswordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conPasswordShowing){
                    conPasswordShowing=false;

                    conPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    //conPasswordIcon.setImageResource(R.drawable.op);
                }
                else{
                    conPasswordShowing=true;

                    conPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    //conPasswordIcon.setImageResource(R.drawable.oc);
                }
                conPassword.setSelection(conPassword.length());
            }
        });




        dateformat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(register1.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateformat.setText(dayOfMonth +"-"+ (month+1) +"-"+year);
                    }
                },2023,7,7);
                dialog.show();
            }
        });

        calIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(register1.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateformat.setText(dayOfMonth +"-"+ (month+1) +"-"+year);
                    }
                },2023,7,7);
                dialog.show();
            }
        });




        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                final String getMobileTxt = mobile.getText().toString();
                final String getEmailTxt = email.getText().toString();
                final String getUser = name.getText().toString();
                final String getId = instituteid.getText().toString();
                final String getDOB = dateformat.getText().toString();
                final String getPass = password.getText().toString();



                if(regEmail&&regPass&&regCon&&regUser&&regMobile&&regId&&regDOB) {
                    if ((rg1.getCheckedRadioButtonId()!=-1&&rg2.getCheckedRadioButtonId()!=-1)) {
                        rgt1.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                        rgt2.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));


                        progressBar.setVisibility(View.VISIBLE);
                        signUpBtn.setVisibility(View.INVISIBLE);

                        PhoneAuthOptions.Builder builder =
                                PhoneAuthOptions.newBuilder(mAuth)
                                        .setPhoneNumber("+91"+getMobileTxt)
                                        .setTimeout(60L,TimeUnit.SECONDS)
                                        .setActivity(register1.this)
                                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                            @Override
                                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                                progressBar.setVisibility(View.GONE);
                                                signUpBtn.setVisibility(View.VISIBLE);
                                                Toast.makeText(register1.this,"OTP Sucess",Toast.LENGTH_SHORT).show();
                                            }
                                            @Override
                                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                                Toast.makeText(getApplicationContext(),"Verification Failed",Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                                signUpBtn.setVisibility(View.VISIBLE);
                                            }

                                            @Override
                                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                                super.onCodeSent(s, forceResendingToken);

                                                String batch="1",gender="M";
                                                if(r1.isChecked())batch="1";
                                                else if(r2.isChecked())batch="2";
                                                else if(r3.isChecked())batch="3";
                                                if(rr1.isChecked())gender="M";
                                                else if(rr2.isChecked())gender="F";

                                                Toast.makeText(getApplicationContext(),"OTP Sent Sucessfully",Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                                signUpBtn.setVisibility(View.VISIBLE);
                                                Intent intent = new Intent(getApplicationContext(), otpv.class);
                                                intent.putExtra("mobile", getMobileTxt);
                                                intent.putExtra("email", getEmailTxt);
                                                intent.putExtra("user",getUser);
                                                intent.putExtra("Id",getId);
                                                intent.putExtra("DOB",getDOB);
                                                intent.putExtra("Pass",getPass);
                                                intent.putExtra("gender",gender);
                                                intent.putExtra("batch",batch);
                                                intent.putExtra("imageUri",uri.toString());
                                                intent.putExtra("backotp",s);
                                                startActivity(intent);


                                            }
                                        });

                        PhoneAuthProvider.verifyPhoneNumber(builder.build());

                    } else {
                        if(rg1.getCheckedRadioButtonId()==-1){
                            rgt1.setBackground(getDrawable(R.drawable.round_dark_grey));

                        }
                        if(rg2.getCheckedRadioButtonId()==-1){
                            rgt2.setBackground(getDrawable(R.drawable.round_dark_grey));
                        }
                    }
                }
                else{
                    if(!regUser)name.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!regEmail)email.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!regDOB)dateformat.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!regCon)conPassword.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!regPass)password.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!regId)instituteid.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!regMobile)mobile.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!regRG1)rgt1.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!regRG2)rgt2.setBackground(getDrawable(R.drawable.round_dark_grey));

                    Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            vib.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
                        }
                    else{
                        vib.vibrate(500);
                    }

                    Toast.makeText(register1.this,"Fill all Fields Correctly",Toast.LENGTH_SHORT).show();
                }

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}