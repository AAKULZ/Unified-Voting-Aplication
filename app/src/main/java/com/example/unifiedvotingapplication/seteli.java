package com.example.unifiedvotingapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class seteli extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seteli);

        final CheckBox cb1 =findViewById(R.id.cb1);
        final CheckBox cb2 =findViewById(R.id.cb2);
        final CheckBox cb3 =findViewById(R.id.cb3);
        final CheckBox cb4 =findViewById(R.id.cb4);
        final CheckBox cg1 =findViewById(R.id.cg1);
        final CheckBox cg2 =findViewById(R.id.cg2);
        final CheckBox cy1 =findViewById(R.id.cy1);
        final CheckBox cy2 =findViewById(R.id.cy2);
        final CheckBox cy3 =findViewById(R.id.cy3);
        final CheckBox cy4 =findViewById(R.id.cy4);
        final CheckBox cba1 =findViewById(R.id.cba1);
        final CheckBox cba2 =findViewById(R.id.cba2);
        final CheckBox cba3 =findViewById(R.id.cba3);

        final CheckBox vb1 =findViewById(R.id.vb1);
        final CheckBox vb2 =findViewById(R.id.vb2);
        final CheckBox vb3 =findViewById(R.id.vb3);
        final CheckBox vb4 =findViewById(R.id.vb4);
        final CheckBox vg1 =findViewById(R.id.vg1);
        final CheckBox vg2 =findViewById(R.id.vg2);
        final CheckBox vy1 =findViewById(R.id.vy1);
        final CheckBox vy2 =findViewById(R.id.vy2);
        final CheckBox vy3 =findViewById(R.id.vy3);
        final CheckBox vy4 =findViewById(R.id.vy4);
        final CheckBox vba1 =findViewById(R.id.vba1);
        final CheckBox vba2 =findViewById(R.id.vba2);
        final CheckBox vba3 =findViewById(R.id.vba3);

        final TextView cbranch = findViewById(R.id.cbranch);
        final TextView cyear = findViewById(R.id.cyear);
        final TextView cbatch = findViewById(R.id.cbatch);
        final TextView cgender = findViewById(R.id.cgender);

        final TextView vbranch = findViewById(R.id.vbranch);
        final TextView vyear = findViewById(R.id.vyear);
        final TextView vbatch = findViewById(R.id.vbatch);
        final TextView vgender = findViewById(R.id.vgender);


        final String title = getIntent().getStringExtra("electionTitle");
        final String conduc = getIntent().getStringExtra("conductor");
        final String des = getIntent().getStringExtra("description");
        final String email = getIntent().getStringExtra("conductorEmail");
        final String mobile = getIntent().getStringExtra("conductorMobile");
        final String access = getIntent().getStringExtra("accessPassword");
        final String conpass = getIntent().getStringExtra("conPassword");
        final String imageUri = getIntent().getStringExtra("imageUri");


        final TextView createBtn = findViewById(R.id.createBtn);

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbranch.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbranch.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbranch.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbranch.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        cg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cgender.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        cg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cgender.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        cy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cyear.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        cy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cyear.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        cy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cyear.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        cy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cyear.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        cba1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbatch.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        cba2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbatch.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        cba3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbatch.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });

        //----------------------------------------------------//

        vb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vbranch.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        vb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vbranch.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        vb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vbranch.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        vb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vbranch.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        vg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vgender.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        vg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vgender.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        vy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vyear.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        vy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vyear.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        vy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vyear.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        vy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vyear.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        vba1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vbatch.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        vba2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vbatch.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });
        vba3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vbatch.setBackground(getDrawable(R.drawable.round_back_dark_blue5_15));
            }
        });




        createBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                if((cb1.isChecked()||cb2.isChecked()||cb3.isChecked()||cb4.isChecked())&&(cy1.isChecked()||cy2.isChecked()||cy3.isChecked()||cy4.isChecked())&&(cg1.isChecked()||cg2.isChecked())&&(cba1.isChecked()||cba2.isChecked()||cba3.isChecked())&&(vb1.isChecked()||vb2.isChecked()||vb3.isChecked()||vb4.isChecked())&&(vy1.isChecked()||vy2.isChecked()||vy3.isChecked()||vy4.isChecked())&&(vg1.isChecked()||vg2.isChecked())&&(vba1.isChecked()||vba2.isChecked()||vba3.isChecked())){

                    ArrayList<String> cbatch = new ArrayList<String>();
                    ArrayList<String> cgender = new ArrayList<String>();
                    ArrayList<String> cyear = new ArrayList<String>();
                    ArrayList<String> cbranch = new ArrayList<String>();

                    ArrayList<String> vbatch = new ArrayList<String>();
                    ArrayList<String> vgender = new ArrayList<String>();
                    ArrayList<String> vyear = new ArrayList<String>();
                    ArrayList<String> vbranch = new ArrayList<String>();

                    if(cb1.isChecked())cbranch.add("BCS");
                    if(cb2.isChecked())cbranch.add("BCY");
                    if(cb3.isChecked())cbranch.add("BEC");
                    if(cb4.isChecked())cbranch.add("BCD");
                    if(cg1.isChecked())cgender.add("M");
                    if(cg2.isChecked())cgender.add("F");
                    if(cy1.isChecked())cyear.add("2019");
                    if(cy2.isChecked())cyear.add("2020");
                    if(cy3.isChecked())cyear.add("2021");
                    if(cy4.isChecked())cyear.add("2022");
                    if(cba1.isChecked())cbatch.add("1");
                    if(cba2.isChecked())cbatch.add("2");
                    if(cba3.isChecked())cbatch.add("3");

                    if(vb1.isChecked())vbranch.add("BCS");
                    if(vb2.isChecked())vbranch.add("BCY");
                    if(vb3.isChecked())vbranch.add("BEC");
                    if(vb4.isChecked())vbranch.add("BCD");
                    if(vg1.isChecked())vgender.add("M");
                    if(vg2.isChecked())vgender.add("F");
                    if(vy1.isChecked())vyear.add("2019");
                    if(vy2.isChecked())vyear.add("2020");
                    if(vy3.isChecked())vyear.add("2021");
                    if(vy4.isChecked())vyear.add("2022");
                    if(vba1.isChecked())vbatch.add("1");
                    if(vba2.isChecked())vbatch.add("2");
                    if(vba3.isChecked())vbatch.add("3");


                    Intent intent = new Intent(getApplicationContext(), scheduleelec.class);
                    intent.putExtra("electionTitle", title);
                    intent.putExtra("conductor", conduc);
                    intent.putExtra("description",des);
                    intent.putExtra("conductorEmail",email);
                    intent.putExtra("conductorMobile",mobile);
                    intent.putExtra("accessPassword",access);
                    intent.putExtra("conPassword",conpass);

                    intent.putExtra("cbranch",cbranch);
                    intent.putExtra("cyear",cyear);
                    intent.putExtra("cgender",cgender);
                    intent.putExtra("cbatch",cbatch);

                    intent.putExtra("vbranch",vbranch);
                    intent.putExtra("vyear",vyear);
                    intent.putExtra("vgender",vgender);
                    intent.putExtra("vbatch",vbatch);

                    intent.putExtra("imageUri",imageUri);

                    startActivity(intent);
                }
                else{
                    if(!(cb1.isChecked()||cb2.isChecked()||cb3.isChecked()||cb4.isChecked()))cbranch.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!(cg1.isChecked()||cg2.isChecked()))cgender.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!(cba1.isChecked()||cba2.isChecked()||cba3.isChecked()))cbatch.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!(cy1.isChecked()||cy2.isChecked()||cy3.isChecked()||cy4.isChecked()))cyear.setBackground(getDrawable(R.drawable.round_dark_grey));

                    if(!(vb1.isChecked()||vb2.isChecked()||vb3.isChecked()||vb4.isChecked()))vbranch.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!(vg1.isChecked()||vg2.isChecked()))vgender.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!(vba1.isChecked()||vba2.isChecked()||vba3.isChecked()))vbatch.setBackground(getDrawable(R.drawable.round_dark_grey));
                    if(!(vy1.isChecked()||vy2.isChecked()||vy3.isChecked()||vy4.isChecked()))vyear.setBackground(getDrawable(R.drawable.round_dark_grey));

                    Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
                    }
                    else{
                        vib.vibrate(500);
                    }

                    Toast.makeText(seteli.this,"Fill all Fields Correctly",Toast.LENGTH_SHORT).show();



                }
            }
        });

    }
}