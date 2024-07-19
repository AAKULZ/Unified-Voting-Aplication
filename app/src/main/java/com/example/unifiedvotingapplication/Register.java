package com.example.unifiedvotingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class Register extends AppCompatActivity {
    FirebaseAuth mAuth =FirebaseAuth.getInstance();

    PhoneAuthProvider.ForceResendingToken resendtoken;
    private boolean passwordShowing = false;
    private boolean conPasswordShowing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText email = findViewById(R.id.emailET);
        final EditText mobile = findViewById(R.id.mobileET);

        final EditText password = findViewById(R.id.passwordET);
        final EditText conPassword = findViewById(R.id.conpasswordET);
        final ImageView passwordIcon = findViewById(R.id.passwordIcon);
        final ImageView conPasswordIcon = findViewById(R.id.conpasswordIcon);

        final AppCompatButton signUpBtn = findViewById(R.id.signUpBtn);
        final TextView signInBtn = findViewById(R.id.signInBtn);
        final ProgressBar progressBar = findViewById(R.id.progressbar);

        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordShowing){
                    passwordShowing=false;

                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.op);
                }
                else{
                    passwordShowing=true;

                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.oc);
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
                    conPasswordIcon.setImageResource(R.drawable.op);
                }
                else{
                    conPasswordShowing=true;

                    conPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    conPasswordIcon.setImageResource(R.drawable.oc);
                }
                conPassword.setSelection(conPassword.length());
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final String getMobileTxt = mobile.getText().toString();
                final String getEmailTxt = email.getText().toString();

                if(!getMobileTxt.trim().isEmpty()) {
                    if ((getMobileTxt.trim()).length() == 10) {

                        progressBar.setVisibility(View.VISIBLE);
                        signUpBtn.setVisibility(View.INVISIBLE);

                        PhoneAuthOptions.Builder builder =
                                PhoneAuthOptions.newBuilder(mAuth)
                                        .setPhoneNumber("+91"+getMobileTxt)
                                        .setTimeout(60l,TimeUnit.SECONDS)
                                        .setActivity(Register.this)
                                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                            @Override
                                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                                progressBar.setVisibility(View.GONE);
                                                signUpBtn.setVisibility(View.VISIBLE);
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
                                                Toast.makeText(getApplicationContext(),"OTP Sent Sucessfully",Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                                signUpBtn.setVisibility(View.VISIBLE);
                                                Intent intent = new Intent(getApplicationContext(), otpv.class);
                                                intent.putExtra("mobile", getMobileTxt);
                                                intent.putExtra("email", getEmailTxt);
                                                intent.putExtra("backotp",s);
                                                startActivity(intent);


                                            }
                                        });

                        PhoneAuthProvider.verifyPhoneNumber(builder.build());

                    } else {
                        Toast.makeText(Register.this, "Please Enter Correct Number", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Register.this,"Enter Mobile Number",Toast.LENGTH_SHORT).show();
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