package com.example.unifiedvotingapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://unified-voting-application-default-rtdb.firebaseio.com/");
    Boolean name=false,pass1=false;
    private boolean passwordShowing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        final EditText usernameET =findViewById(R.id.usernameET);
        final EditText passwordET =findViewById(R.id.passwordET);
        final ImageView passwordIcon =findViewById(R.id.passwordIcon);
        final TextView signUpBtn =findViewById(R.id.signUpBtn);
        final TextView signInBtn =findViewById(R.id.signInBtn);

        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordShowing){
                    passwordShowing=false;

                    passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    //passwordIcon.setImageResource(R.drawable.op);
                }
                else{
                    passwordShowing=true;

                    passwordET.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    //passwordIcon.setImageResource(R.drawable.oc);
                }
                passwordET.setSelection(passwordET.length());
            }
        });
        usernameET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String n=usernameET.getText().toString();
                    if(n.isEmpty()){
                        usernameET.setError("Field cannot be empty");
                        usernameET.setBackground(getDrawable(R.drawable.round_dark_grey));
                        name=false;

                    }
                    else{
                        usernameET.setError(null);
                        usernameET.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                        name=true;
                    }
                }
            }
        });

        passwordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String n=passwordET.getText().toString();
                    if(n.isEmpty()){
                        passwordET.setError("Field cannot be empty");
                        passwordET.setBackground(getDrawable(R.drawable.round_dark_grey));
                        pass1=false;

                    }
                    else{
                        passwordET.setError(null);
                        passwordET.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                        pass1=true;
                    }
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, register1.class));
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user=usernameET.getText().toString();
                final String pass=passwordET.getText().toString();

                if(!user.isEmpty() && !pass.isEmpty()) {

                    databaseReference.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(user)){
                                final String getPassword = snapshot.child(user).child("pass").getValue(String.class);
                                final String gender = snapshot.child(user).child("gender").getValue(String.class);
                                final String batch = snapshot.child(user).child("batch").getValue(String.class);
                                if(getPassword.toLowerCase().equals(pass.toLowerCase())){
                                    //startActivity(new Intent(Login.this, MainActivity.class));
                                    Intent intent=new Intent(Login.this,MainActivity.class);
                                    intent.putExtra("id",user);
                                    intent.putExtra("gender",gender);
                                    intent.putExtra("batch",batch);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(Login.this,"Wrong Credentials",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(Login.this,"Wrong Credentials",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
                else{
                    if(!name)usernameET.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!pass1)passwordET.setBackground(getDrawable(R.drawable.round_dark_grey));
                    Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
                    }
                    else{
                        vib.vibrate(500);
                    }

                    Toast.makeText(Login.this,"Fill all Fields Correctly",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}