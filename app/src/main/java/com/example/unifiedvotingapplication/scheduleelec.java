package com.example.unifiedvotingapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class scheduleelec extends AppCompatActivity {

    FirebaseAuth mAuth =FirebaseAuth.getInstance();
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://unified-voting-application-default-rtdb.firebaseio.com/");

    boolean fcd=false,frd=false,frt=false,fct=false,psd=false,ped=false,pst=false,pet=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduleelec);

        final EditText formReleaseDate = findViewById(R.id.formReleaseDate);
        final EditText formReleaseTime = findViewById(R.id.formReleaseTime);
        final EditText formCloseDate = findViewById(R.id.formCloseDate);
        final EditText formCloseTime = findViewById(R.id.formCloseTime);
        final EditText pollStartDate = findViewById(R.id.pollStartDate);
        final EditText pollStartTime = findViewById(R.id.pollStartTime);
        final EditText pollEndDate = findViewById(R.id.pollEndDate);
        final EditText pollEndTime = findViewById(R.id.pollEndTime);
        final ProgressBar progressBar = findViewById(R.id.progressbar);

        final ImageView calIcon1 = findViewById(R.id.calIcon1);
        final ImageView calIcon2 = findViewById(R.id.calIcon2);
        final ImageView calIcon3 = findViewById(R.id.calIcon3);
        final ImageView calIcon4 = findViewById(R.id.calIcon4);

        final ImageView clockIcon1 = findViewById(R.id.clockIcon1);
        final ImageView clockIcon2 = findViewById(R.id.clockIcon2);
        final ImageView clockIcon3 = findViewById(R.id.clockIcon3);
        final ImageView clockIcon4 = findViewById(R.id.clockIcon4);

        final String title = getIntent().getStringExtra("electionTitle");
        final String conduc = getIntent().getStringExtra("conductor");
        final String des = getIntent().getStringExtra("description");
        final String email = getIntent().getStringExtra("conductorEmail");
        final String mobile = getIntent().getStringExtra("conductorMobile");
        final String access = getIntent().getStringExtra("accessPassword");
        final String conpass = getIntent().getStringExtra("conPassword");
        final String imageUri = getIntent().getStringExtra("imageUri");
        ArrayList<String> cbranch = (ArrayList<String>) getIntent().getSerializableExtra("cbranch");
        ArrayList<String> cyear = (ArrayList<String>) getIntent().getSerializableExtra("cyear");
        ArrayList<String> cgender = (ArrayList<String>) getIntent().getSerializableExtra("cgender");
        ArrayList<String> cbatch = (ArrayList<String>) getIntent().getSerializableExtra("cbatch");

        ArrayList<String> vbranch = (ArrayList<String>) getIntent().getSerializableExtra("vbranch");
        ArrayList<String> vyear = (ArrayList<String>) getIntent().getSerializableExtra("vyear");
        ArrayList<String> vgender = (ArrayList<String>) getIntent().getSerializableExtra("vgender");
        ArrayList<String> vbatch = (ArrayList<String>) getIntent().getSerializableExtra("vbatch");


        final TextView createElectionBtn =findViewById(R.id.createElectionBtn);

        formReleaseDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Boolean flag=false;
                    Date currentDate = new Date();
                    String d=formReleaseDate.getText().toString();
                    String dp="^([1-9]|1[0-9]|2[0-9]|3[01])-([1-9]|1[0-2])-\\d{4}";
                    if(d.isEmpty()){
                        formReleaseDate.setError("Field cannot be empty");
                        formReleaseDate.setBackground(getDrawable(R.drawable.round_dark_grey));frd=false;

                    } else if (!d.matches(dp)) {
                        formReleaseDate.setError("Invalid Date Format");
                        formReleaseDate.setBackground(getDrawable(R.drawable.round_dark_grey));frd=false;

                    }
                    else{

                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        String currentDateStr = sdf.format(currentDate);

                        if(d.compareTo(currentDateStr) >= 0)flag=true;
                        if(flag){
                            formReleaseDate.setError(null);
                            formReleaseDate.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                            frd=true;
                        }else{
                            formReleaseDate.setError("Date Selected from Past");
                            formReleaseDate.setBackground(getDrawable(R.drawable.round_dark_grey));frd=false;
                        }
                    }
                }
            }
        });

        formReleaseDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Boolean flag=false;
                Date currentDate = new Date();
                String d=formReleaseDate.getText().toString();
                String dp="^([1-9]|1[0-9]|2[0-9]|3[0-1])-([1-9]|1[0-2])-\\d{4}";
                if(d.isEmpty()){
                    formReleaseDate.setError("Field cannot be empty");
                    formReleaseDate.setBackground(getDrawable(R.drawable.round_dark_grey));frd=false;

                } else if (!d.matches(dp)) {
                    formReleaseDate.setError("Invalid Date Format");
                    formReleaseDate.setBackground(getDrawable(R.drawable.round_dark_grey));frd=false;

                }
                else{
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String currentDateStr = sdf.format(currentDate);

                    if(d.compareTo(currentDateStr) >= 0)flag=true;
                    if(flag){
                        formReleaseDate.setError(null);
                        formReleaseDate.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                        frd=true;
                    }else{
                        formReleaseDate.setError("Date Selected from Past");
                        formReleaseDate.setBackground(getDrawable(R.drawable.round_dark_grey));frd=false;
                    }

                }}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        formCloseDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Boolean flag=false;
                    String d=formCloseDate.getText().toString();
                    String dp="^([1-9]|1[0-9]|2[0-9]|3[01])-([1-9]|1[0-2])-\\d{4}";
                    if(d.isEmpty()){
                        formCloseDate.setError("Field cannot be empty");
                        formCloseDate.setBackground(getDrawable(R.drawable.round_dark_grey));fcd=false;

                    } else if (!d.matches(dp)) {
                        formCloseDate.setError("Invalid Date Format");
                        formCloseDate.setBackground(getDrawable(R.drawable.round_dark_grey));fcd=false;

                    }
                    else{

                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                        try {
                            Date parsedDateA = sdf.parse(d);
                            Date parsedDateB = sdf.parse(formReleaseDate.getText().toString());

                            if(parsedDateA.compareTo(parsedDateB) >= 0)flag=true;
                        } catch (ParseException e) {
                            e.printStackTrace();

                        }
                        if(flag){
                            formCloseDate.setError(null);
                            formCloseDate.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                            fcd=true;}else{
                            formCloseDate.setError("Form Cannot be Closed before Released");
                            formCloseDate.setBackground(getDrawable(R.drawable.round_dark_grey));fcd=false;
                        }
                    }
                }
            }
        });

        formCloseDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Boolean flag=false;
                String d=formCloseDate.getText().toString();
                String dp="^([1-9]|1[0-9]|2[0-9]|3[0-1])-([1-9]|1[0-2])-\\d{4}";
                if(d.isEmpty()){
                    formCloseDate.setError("Field cannot be empty");
                    formCloseDate.setBackground(getDrawable(R.drawable.round_dark_grey));fcd=false;

                } else if (!d.matches(dp)) {
                    formCloseDate.setError("Invalid Date Format");
                    formCloseDate.setBackground(getDrawable(R.drawable.round_dark_grey));fcd=false;

                }
                else{
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                    try {
                        Date parsedDateA = sdf.parse(d);
                        Date parsedDateB = sdf.parse(formReleaseDate.getText().toString());

                        if(parsedDateA.compareTo(parsedDateB) >= 0)flag=true;
                    } catch (ParseException e) {
                        e.printStackTrace();

                    }
                    if(flag){
                    formCloseDate.setError(null);
                    formCloseDate.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    fcd=true;}else{
                        formCloseDate.setError("Form Cannot be Closed before Released");
                        formCloseDate.setBackground(getDrawable(R.drawable.round_dark_grey));fcd=false;
                    }

                }}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pollStartDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Boolean flag=false;
                    Date currentDate = new Date();
                    String d=pollStartDate.getText().toString();
                    String dp="^([1-9]|1[0-9]|2[0-9]|3[01])-([1-9]|1[0-2])-\\d{4}";
                    if(d.isEmpty()){
                        pollStartDate.setError("Field cannot be empty");
                        pollStartDate.setBackground(getDrawable(R.drawable.round_dark_grey));psd=false;

                    } else if (!d.matches(dp)) {
                        pollStartDate.setError("Invalid Date Format");
                        pollStartDate.setBackground(getDrawable(R.drawable.round_dark_grey));psd=false;

                    }
                    else{
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        String currentDateStr = sdf.format(currentDate);

                        if(d.compareTo(currentDateStr) >= 0)flag=true;
                        if(flag){
                            pollStartDate.setError(null);
                            pollStartDate.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                            psd=true;
                        }else{
                            pollStartDate.setError("Date Selected from Past");
                            pollStartDate.setBackground(getDrawable(R.drawable.round_dark_grey));psd=false;
                        }
                    }
                }
            }
        });

        pollStartDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Boolean flag=false;
                Date currentDate = new Date();
                String d=pollStartDate.getText().toString();
                String dp="^([1-9]|1[0-9]|2[0-9]|3[0-1])-([1-9]|1[0-2])-\\d{4}";
                if(d.isEmpty()){
                    pollStartDate.setError("Field cannot be empty");
                    pollStartDate.setBackground(getDrawable(R.drawable.round_dark_grey));psd=false;

                } else if (!d.matches(dp)) {
                    pollStartDate.setError("Invalid Date Format");
                    pollStartDate.setBackground(getDrawable(R.drawable.round_dark_grey));psd=false;

                }
                else{
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String currentDateStr = sdf.format(currentDate);

                    if(d.compareTo(currentDateStr) >= 0)flag=true;
                    if(flag){
                        pollStartDate.setError(null);
                        pollStartDate.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                        psd=true;
                    }else{
                        pollStartDate.setError("Date Selected from Past");
                        pollStartDate.setBackground(getDrawable(R.drawable.round_dark_grey));psd=false;}

                }}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pollEndDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Boolean flag=false;
                    String d= pollEndDate.getText().toString();
                    String dp="^([1-9]|1[0-9]|2[0-9]|3[01])-([1-9]|1[0-2])-\\d{4}";
                    if(d.isEmpty()){
                        pollEndDate.setError("Field cannot be empty");
                        pollEndDate.setBackground(getDrawable(R.drawable.round_dark_grey));ped=false;

                    } else if (!d.matches(dp)) {
                        pollEndDate.setError("Invalid Date Format");
                        pollEndDate.setBackground(getDrawable(R.drawable.round_dark_grey));ped=false;

                    }
                    else{


                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                        try {
                            Date parsedDateA = sdf.parse(d);
                            Date parsedDateB = sdf.parse(pollStartDate.getText().toString());

                            if(parsedDateA.compareTo(parsedDateB) >= 0)flag=true;
                        } catch (ParseException e) {
                            e.printStackTrace();

                        }
                        if(flag){
                            pollEndDate.setError(null);
                            pollEndDate.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                            ped=true;}else{
                            pollEndDate.setError("Poll cannot be Ended before Started");
                            pollEndDate.setBackground(getDrawable(R.drawable.round_dark_grey));ped=false;
                        }
                    }
                }
            }
        });

        pollEndDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Boolean flag=false;
                String d= pollEndDate.getText().toString();
                String dp="^([1-9]|1[0-9]|2[0-9]|3[0-1])-([1-9]|1[0-2])-\\d{4}";
                if(d.isEmpty()){
                    pollEndDate.setError("Field cannot be empty");
                    pollEndDate.setBackground(getDrawable(R.drawable.round_dark_grey));ped=false;

                } else if (!d.matches(dp)) {
                    pollEndDate.setError("Invalid Date Format");
                    pollEndDate.setBackground(getDrawable(R.drawable.round_dark_grey));ped=false;

                }
                else{


                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                    try {
                        Date parsedDateA = sdf.parse(d);
                        Date parsedDateB = sdf.parse(pollStartDate.getText().toString());

                        if(parsedDateA.compareTo(parsedDateB) >= 0)flag=true;
                    } catch (ParseException e) {
                        e.printStackTrace();

                    }
                    if(flag){
                        pollEndDate.setError(null);
                        pollEndDate.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                        ped=true;}else{
                        pollEndDate.setError("Poll cannot be Ended before Started");
                        pollEndDate.setBackground(getDrawable(R.drawable.round_dark_grey));ped=false;}

                }}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        formReleaseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(scheduleelec.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        formReleaseDate.setText(dayOfMonth +"-"+ (month+1) +"-"+year);
                    }
                },2023,7,7);
                dialog.show();
            }
        });
        formCloseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(scheduleelec.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        formCloseDate.setText(dayOfMonth +"-"+ (month+1) +"-"+year);
                    }
                },2023,7,7);
                dialog.show();
            }
        });
        pollStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(scheduleelec.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        pollStartDate.setText(dayOfMonth +"-"+ (month+1) +"-"+year);
                    }
                },2023,7,7);
                dialog.show();
            }
        });
        pollEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(scheduleelec.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        pollEndDate.setText(dayOfMonth +"-"+ (month+1) +"-"+year);
                    }
                },2023,7,7);
                dialog.show();
            }
        });

        calIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(scheduleelec.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        formReleaseDate.setText(dayOfMonth +"-"+ (month+1) +"-"+year);
                    }
                },2023,7,7);
                dialog.show();
            }
        });
        calIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(scheduleelec.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        formCloseDate.setText(dayOfMonth +"-"+ (month+1) +"-"+year);
                    }
                },2023,7,7);
                dialog.show();
            }
        });
        calIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(scheduleelec.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        pollStartDate.setText(dayOfMonth +"-"+ (month+1) +"-"+year);
                    }
                },2023,7,7);
                dialog.show();
            }
        });
        calIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(scheduleelec.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        pollEndDate.setText(dayOfMonth +"-"+ (month+1) +"-"+year);
                    }
                },2023,7,7);
                dialog.show();
            }
        });


        formReleaseTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String d=formReleaseTime.getText().toString();
                    String dp="^([0-9]|[0-1][0-9]|2[0-3]):[0-5][0-9]";
                    if(d.isEmpty()){
                        formReleaseTime.setError("Field cannot be empty");
                        formReleaseTime.setBackground(getDrawable(R.drawable.round_dark_grey));frt=false;

                    } else if (!d.matches(dp)) {
                        formReleaseTime.setError("Invalid Time Format");
                        formReleaseTime.setBackground(getDrawable(R.drawable.round_dark_grey));frt=false;

                    }
                    else{
                        formReleaseTime.setError(null);
                        formReleaseTime.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                        frt=true;
                    }
                }
            }
        });

        formReleaseTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String d=formReleaseTime.getText().toString();
                String dp="^([01][0-9]|2[0-3]):[0-5][0-9]";
                if(d.isEmpty()){
                    formReleaseTime.setError("Field cannot be empty");
                    formReleaseTime.setBackground(getDrawable(R.drawable.round_dark_grey));frt=false;

                } else if (!d.matches(dp)) {
                    formReleaseTime.setError("Invalid Time Format");
                    formReleaseTime.setBackground(getDrawable(R.drawable.round_dark_grey));frt=false;

                }
                else{
                    formReleaseTime.setError(null);
                    formReleaseTime.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                    frt=true;
                }}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        formCloseTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String d=formCloseTime.getText().toString();
                    String dp="^([01][0-9]|2[0-3]):[0-5][0-9]";
                    if(d.isEmpty()){
                        formCloseTime.setError("Field cannot be empty");
                        formCloseTime.setBackground(getDrawable(R.drawable.round_dark_grey));fct=false;

                    } else if (!d.matches(dp)) {
                        formCloseTime.setError("Invalid Time Format");
                        formCloseTime.setBackground(getDrawable(R.drawable.round_dark_grey));fct=false;

                    }
                    else{
                        if (compareDateTime(formReleaseDate.getText().toString(), formReleaseTime.getText().toString(), formCloseDate.getText().toString(), formCloseTime.getText().toString())) {
                            fct=false;
                            formCloseTime.setError("Form cannot be closed before Opened");
                        }else{
                            formCloseTime.setError(null);
                            formCloseTime.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                            fct=true;

                        }
                    }
                }
            }
        });

        formCloseTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String d=formCloseTime.getText().toString();

                String dp="^([01][0-9]|2[0-3]):[0-5][0-9]";
                if(d.isEmpty()){
                    formCloseTime.setError("Field cannot be empty");
                    formCloseTime.setBackground(getDrawable(R.drawable.round_dark_grey));fct=false;

                } else if (!d.matches(dp)) {
                    formCloseTime.setError("Invalid Time Format");
                    formCloseTime.setBackground(getDrawable(R.drawable.round_dark_grey));fct=false;

                }
                else{
                    if (compareDateTime(formReleaseDate.getText().toString(), formReleaseTime.getText().toString(), formCloseDate.getText().toString(), formCloseTime.getText().toString())) {
                        fct=false;
                        formCloseTime.setError("Form cannot be closed before Opened");
                    }else{
                        formCloseTime.setError(null);
                        formCloseTime.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                        fct=true;

                    }

                }}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pollStartTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String d=pollStartTime.getText().toString();
                    String dp="^([01][0-9]|2[0-3]):[0-5][0-9]";
                    if(d.isEmpty()){
                        pollStartTime.setError("Field cannot be empty");
                        pollStartTime.setBackground(getDrawable(R.drawable.round_dark_grey));pst=false;

                    } else if (!d.matches(dp)) {
                        pollStartTime.setError("Invalid Time Format");
                        pollStartTime.setBackground(getDrawable(R.drawable.round_dark_grey));pst=false;

                    }
                    else{
                        if (compareDateTime(formCloseDate.getText().toString(), formCloseTime.getText().toString(), pollStartDate.getText().toString(), pollStartTime.getText().toString())) {
                            pst=false;
                            pollStartTime.setBackground(getDrawable(R.drawable.round_dark_grey));
                            pollStartTime.setError("Poll cannot be Started before closure of Forms");
                        }else{
                            pollStartTime.setError(null);
                            pollStartTime.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                            pst=true;

                        }

                    }
                }
            }
        });

        pollStartTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String d=pollStartTime.getText().toString();
                String dp="^([01][0-9]|2[0-3]):[0-5][0-9]";
                if(d.isEmpty()){
                    pollStartTime.setError("Field cannot be empty");
                    pollStartTime.setBackground(getDrawable(R.drawable.round_dark_grey));pst=false;

                } else if (!d.matches(dp)) {
                    pollStartTime.setError("Invalid Time Format");
                    pollStartTime.setBackground(getDrawable(R.drawable.round_dark_grey));pst=false;

                }
                else{
                    if (compareDateTime(formCloseDate.getText().toString(), formCloseTime.getText().toString(), pollStartDate.getText().toString(), pollStartTime.getText().toString())) {
                        pst=false;
                        pollStartTime.setBackground(getDrawable(R.drawable.round_dark_grey));
                        pollStartTime.setError("Poll cannot be Started before closure of Forms");
                    }else{
                        pollStartTime.setError(null);
                        pollStartTime.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                        pst=true;

                    }
                }}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pollEndTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String d=pollEndTime.getText().toString();
                    String dp="^([01][0-9]|2[0-3]):[0-5][0-9]";
                    if(d.isEmpty()){
                        pollEndTime.setError("Field cannot be empty");
                        pollEndTime.setBackground(getDrawable(R.drawable.round_dark_grey));pet=false;

                    } else if (!d.matches(dp)) {
                        pollEndTime.setError("Invalid Time Format");
                        pollEndTime.setBackground(getDrawable(R.drawable.round_dark_grey));pet=false;

                    }
                    else{
                        if (compareDateTime(pollStartDate.getText().toString(), pollStartTime.getText().toString(), pollEndDate.getText().toString(), pollEndTime.getText().toString())) {
                            pet=false;pollEndTime.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                            pollEndTime.setError("Poll cannot be Ended before Started");
                        }else{
                            pollEndTime.setError(null);
                            pollEndTime.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                            pet=true;

                        }

                    }
                }
            }
        });

        pollEndTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String d=pollEndTime.getText().toString();
                String dp="^([01][0-9]|2[0-3]):[0-5][0-9]";
                if(d.isEmpty()){
                    pollEndTime.setError("Field cannot be empty");
                    pollEndTime.setBackground(getDrawable(R.drawable.round_dark_grey));pet=false;

                } else if (!d.matches(dp)) {
                    pollEndTime.setError("Invalid Time Format");
                    pollEndTime.setBackground(getDrawable(R.drawable.round_dark_grey));pet=false;

                }
                else{
                    if (compareDateTime(pollStartDate.getText().toString(), pollStartTime.getText().toString(), pollEndDate.getText().toString(), pollEndTime.getText().toString())) {
                        pet=false;pollEndTime.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                        pollEndTime.setError("Poll cannot be Ended before Started");
                    }else{
                        pollEndTime.setError(null);
                        pollEndTime.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
                        pet=true;

                    }
                }}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        clockIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(scheduleelec.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        formReleaseTime.setText(hourOfDay+":"+minute);
                    }
                },13,50,true);
                dialog.show();
            }
        });
        clockIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(scheduleelec.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        formCloseTime.setText(hourOfDay+":"+minute);
                    }
                },13,50,true);
                dialog.show();
            }
        });
        clockIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(scheduleelec.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        pollStartTime.setText(hourOfDay+":"+minute);
                    }
                },13,50,true);
                dialog.show();
            }
        });
        clockIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(scheduleelec.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        pollEndTime.setText(hourOfDay+":"+minute);
                    }
                },13,50,true);
                dialog.show();
            }
        });

        createElectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(frd&&fcd&&frt&&fct&&psd&&ped&&pst&&pet){

                    progressBar.setVisibility(View.VISIBLE);
                    createElectionBtn.setVisibility(View.INVISIBLE);

                    PhoneAuthOptions.Builder builder =
                            PhoneAuthOptions.newBuilder(mAuth)
                                    .setPhoneNumber("+91"+mobile)
                                    .setTimeout(60L, TimeUnit.SECONDS)
                                    .setActivity(scheduleelec.this)
                                    .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                            progressBar.setVisibility(View.GONE);
                                            createElectionBtn.setVisibility(View.VISIBLE);
                                            Toast.makeText(scheduleelec.this,"OTP Sucess",Toast.LENGTH_SHORT).show();
                                        }
                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {
                                            Toast.makeText(getApplicationContext(),"Verification Failed",Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                            createElectionBtn.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                            super.onCodeSent(s, forceResendingToken);

                                            Toast.makeText(getApplicationContext(),"OTP Sent Sucessfully",Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                            createElectionBtn.setVisibility(View.VISIBLE);
                                            Intent intent = new Intent(getApplicationContext(), etp.class);
                                            String frd = formReleaseDate.getText().toString();
                                            String fcd = formCloseDate.getText().toString();
                                            String psd = pollStartDate.getText().toString();
                                            String ped = pollEndDate.getText().toString();

                                            String frt = formReleaseTime.getText().toString();
                                            String fct = formCloseTime.getText().toString();
                                            String pst = pollStartTime.getText().toString();
                                            String pet = pollEndTime.getText().toString();

                                            intent.putExtra("electionTitle", title);
                                            intent.putExtra("conductor", conduc);
                                            intent.putExtra("description",des);
                                            intent.putExtra("conductorEmail",email);
                                            intent.putExtra("conductorMobile",mobile);
                                            intent.putExtra("accessPassword",access);
                                            intent.putExtra("conPassword",conpass);

                                            intent.putExtra("frd",frd);
                                            intent.putExtra("fcd",fcd);
                                            intent.putExtra("psd",psd);
                                            intent.putExtra("ped",ped);

                                            intent.putExtra("frt",frt);
                                            intent.putExtra("fct",fct);
                                            intent.putExtra("pst",pst);
                                            intent.putExtra("pet",pet);

                                            intent.putExtra("cbranch",cbranch);
                                            intent.putExtra("cyear",cyear);
                                            intent.putExtra("cgender",cgender);
                                            intent.putExtra("cbatch",cbatch);

                                            intent.putExtra("vbranch",vbranch);
                                            intent.putExtra("vyear",vyear);
                                            intent.putExtra("vgender",vgender);
                                            intent.putExtra("vbatch",vbatch);

                                            intent.putExtra("imageUri",imageUri);

                                            //startActivity(intent);

                                            intent.putExtra("backotp",s);
                                            startActivity(intent);



                                        }
                                    });

                    PhoneAuthProvider.verifyPhoneNumber(builder.build());

                }
                else{
                    if(!frd)formReleaseDate.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!fcd)formCloseDate.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!psd)pollStartDate.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!ped)pollEndDate.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!frt)formReleaseTime.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!fct)formCloseTime.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!pst)pollStartTime.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!pet)pollEndTime.setBackground(getDrawable(R.drawable.round_dark_grey));

                    Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
                    }
                    else{
                        vib.vibrate(500);
                    }

                    Toast.makeText(scheduleelec.this,"Fill all Fields Correctly",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private boolean compareDateTime(String givenDate, String givenTime, String currentDate, String currentTime) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date givenDateTime = dateFormat.parse(givenDate + " " + givenTime);
            Date currentDateTime = dateFormat.parse(currentDate + " " + currentTime);
            return givenDateTime.compareTo(currentDateTime) > 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}