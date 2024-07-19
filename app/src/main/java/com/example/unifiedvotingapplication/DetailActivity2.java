package com.example.unifiedvotingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailActivity2 extends AppCompatActivity {

    private DatabaseReference databaseReference,dbr;
    TextView detailDesc,detailTitle,contest,resendBtn,frdBtn,frtBtn,fcdBtn,fctBtn;
    ImageView detailImage;
    String key="";
    String user,frd,fcd,frt,fct;
    String imageUrl="";
    private CountDownTimer countDownTimer;
    private long timeRemainingMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);


        detailDesc=findViewById(R.id.detailDesc);
        detailImage=findViewById(R.id.detailImage);
        detailTitle=findViewById(R.id.detailTitle);
        contest=findViewById(R.id.contest);
        resendBtn=findViewById(R.id.resendBtn);
        frdBtn=findViewById(R.id.frdBtn);
        fcdBtn=findViewById(R.id.fcdBtn);
        fctBtn=findViewById(R.id.fctBtn);
        frtBtn=findViewById(R.id.frtBtn);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            key=bundle.getString("Key");
            imageUrl=bundle.getString("Image");
            user=bundle.getString("user");
            frd=bundle.getString("frd");
            frt=bundle.getString("frt");
            fcd=bundle.getString("fcd");
            fct=bundle.getString("fct");
            frdBtn.setText(frd);fcdBtn.setText(fcd);frtBtn.setText(frt);fctBtn.setText(fct);

            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
            startCountdown(fct);

        }
        dbr = FirebaseDatabase.getInstance().getReference("Candidates").child(bundle.getString("Title"));
        contest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbr.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //----------------------
                        Date currentDate = new Date();
                        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                        String currentDateString = dateFormat.format(currentDate);
                        String currentTimeString = timeFormat.format(currentDate);

                        // Compare the given date and time with the current date and time
                        if (compareDateTime(frd, frt, currentDateString, currentTimeString)) {
                            Toast.makeText(getApplicationContext(),"This Activity is not yet Available",Toast.LENGTH_SHORT).show();
                        } else {
                            if (compareDateTime(fcd,fct,currentDateString,currentTimeString)){
                                if(!snapshot.hasChild(user)){
                                    Intent intent = new Intent(getApplicationContext(), candidateForm.class);
                                    intent.putExtra("election",bundle.getString("Title"));
                                    intent.putExtra("user",user);
                                    startActivity(intent);
                                    //
                                    finish();

                                }
                                else{

                                    Toast.makeText(getApplicationContext(),"Already Contested",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"This Activity is not Available Anymore",Toast.LENGTH_SHORT).show();
                            }

                        }
                        //----------------------

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }
    private void startCountdown(String inputTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());

        try {
            Date date = sdf.parse(inputTime);
            long targetTimeMillis = date.getTime();
            long currentTimeMillis = System.currentTimeMillis();

            // Get the current time in milliseconds at 00:00:00
            long midnightMillis = getCurrentMidnightMillis();

            // Check if the target time is after the current time
            if (targetTimeMillis >= currentTimeMillis) {
                timeRemainingMillis = targetTimeMillis - currentTimeMillis;
            } else {
                // Target time is before the current time, so add 24 hours
                timeRemainingMillis = (targetTimeMillis + 24 * 60 * 60 * 1000) - currentTimeMillis;
            }

            if (timeRemainingMillis > 0) {
                countDownTimer = new CountDownTimer(timeRemainingMillis, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timeRemainingMillis = millisUntilFinished;
                        updateCountdownText();
                    }

                    @Override
                    public void onFinish() {
                        resendBtn.setText("Countdown Finished!");
                    }
                };

                countDownTimer.start();
            } else {
                resendBtn.setText("Invalid input time");
            }
        } catch (ParseException e) {
            resendBtn.setText("Invalid input time");
            e.printStackTrace();
        }
    }

    private long getCurrentMidnightMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    private void updateCountdownText() {
        long minutes = (timeRemainingMillis / 1000) / 60;
        long seconds = (timeRemainingMillis / 1000) % 60;

        String countdownText = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        resendBtn.setText(countdownText);
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