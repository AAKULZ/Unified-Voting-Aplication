package com.example.unifiedvotingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailActivity3 extends AppCompatActivity {

    TextView detailDesc,detailTitle,contest,resendBtn,vrdBtn,vrtBtn,vcdBtn,vctBtn;
    ImageView detailImage;
    String key="";
    String user,vrd,vcd,vrt,vct;
    String imageUrl="";
    private CountDownTimer countDownTimer;
    private long timeRemainingMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail3);


        detailDesc=findViewById(R.id.detailDesc);
        detailImage=findViewById(R.id.detailImage);
        detailTitle=findViewById(R.id.detailTitle);
        contest=findViewById(R.id.contest);
        resendBtn=findViewById(R.id.resendBtn);
        vrdBtn=findViewById(R.id.vrdBtn);
        vcdBtn=findViewById(R.id.vcdBtn);
        vctBtn=findViewById(R.id.vctBtn);
        vrtBtn=findViewById(R.id.vrtBtn);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            key=bundle.getString("Key");
            imageUrl=bundle.getString("Image");
            user=bundle.getString("user");
            vrd=bundle.getString("vrd");
            vrt=bundle.getString("vrt");
            vcd=bundle.getString("vcd");
            vct=bundle.getString("vct");
            vrdBtn.setText(vrd);vcdBtn.setText(vcd);vrtBtn.setText(vrt);vctBtn.setText(vct);

            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
            startCountdown(vct);

        }
        contest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Date currentDate = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                String currentDateString = dateFormat.format(currentDate);
                String currentTimeString = timeFormat.format(currentDate);

                // Compare the given date and time with the current date and time
                if (compareDateTime(vrd, vrt, currentDateString, currentTimeString)) {
                    Toast.makeText(getApplicationContext(),"This Activity is not yet Available",Toast.LENGTH_SHORT).show();
                } else {
                    if (compareDateTime(vcd,vct,currentDateString,currentTimeString)){
                        Intent intent = new Intent(getApplicationContext(), votingpage.class);
                        intent.putExtra("user",user);
                        intent.putExtra("election",bundle.getString("Title"));
                        startActivity(intent);
                        //
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"This Activity is not Available Anymore",Toast.LENGTH_SHORT).show();
                    }

                }
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