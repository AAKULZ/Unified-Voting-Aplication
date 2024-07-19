package com.example.unifiedvotingapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.unifiedvotingapplication.R.id;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import com.example.bottomnavigationdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference databaseReference,dbr;

    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    @SuppressLint({"NonConstantResourceId", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String user = getIntent().getStringExtra("id");
        final String gender = getIntent().getStringExtra("gender");
        final String batch = getIntent().getStringExtra("batch");

        bottomNavigationView = findViewById(id.bottomNavigationView);
        fab = findViewById(id.fab);
        drawerLayout = findViewById(id.drawer_layout);
        NavigationView navigationView = findViewById(id.nav_view);
        Toolbar toolbar = findViewById(id.toolbar);
        //TextView profile=findViewById(id.profile);

        dbr = FirebaseDatabase.getInstance().getReference("Users").child(user);
        dbr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //----------------------

                        if(snapshot.getValue(DataClass1.class).getAdmin().contains("true")){
                            //profile.setEnabled(true);

                            Toast.makeText(getApplicationContext(),"Admin Access Mode",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            findViewById(id.profile).setEnabled(false);
                            findViewById(id.profile).setEnabled(false);
                            findViewById(id.profile).setActivated(false);

                            Toast.makeText(getApplicationContext(),"Non-Admin Access Mode",Toast.LENGTH_SHORT).show();
                        }}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(id.frame_layout, new HomeFragment(user,gender,batch)).commit();
            navigationView.setCheckedItem(id.nav_home);
        }

        replaceFragment(new HomeFragment());

        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            int Itemid=item.getItemId();
                if(Itemid==R.id.home) {
                    Intent intent=new Intent(MainActivity.this,elielection.class);
                    intent.putExtra("id",user);
                    intent.putExtra("gender",gender);
                    intent.putExtra("batch",batch);
                    startActivity(intent);
                    //replaceFragment(new HomeFragment());
                   }
                else if(Itemid==R.id.shorts){
                    Intent intent=new Intent(MainActivity.this,votelection.class);
                    intent.putExtra("id",user);
                    intent.putExtra("gender",gender);
                    intent.putExtra("batch",batch);
                    startActivity(intent);
                    //replaceFragment(new ShortsFragment());
                   }
                else if(Itemid== R.id.subscriptions){
                    Intent intent=new Intent(MainActivity.this,electionList.class);
                    intent.putExtra("id",user);
                    intent.putExtra("gender",gender);
                    intent.putExtra("batch",batch);
                    startActivity(intent);
                    //replaceFragment(new SubsriptionsFragment());
                   }
                else if(Itemid==R.id.library) {
                    Intent intent=new Intent(MainActivity.this,resultpage.class);
                    intent.putExtra("id",user);
                    intent.putExtra("gender",gender);
                    intent.putExtra("batch",batch);
                    startActivity(intent);
                    replaceFragment(new LibraryFragment());
                   }
                else if(Itemid==R.id.profile) {
                    showBottomDialog();
                }


            return true;
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog();
            }
        });

    }

    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomshettlayout);

        LinearLayout aE = dialog.findViewById(id.addElection);

        LinearLayout rE = dialog.findViewById(id.removeElection);
        LinearLayout mE = dialog.findViewById(id.modifyElection);
        //ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        aE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Intent intent = new Intent(MainActivity.this, upload.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"Directing to Election section",Toast.LENGTH_SHORT).show();

            }
        });

        rE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Intent intent = new Intent(MainActivity.this, electionList.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"Directing to Election section",Toast.LENGTH_SHORT).show();

            }
        });

        mE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this,"Directing to User List",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), userList.class);

                startActivity(intent);
                //
                finish();

            }
        });

        /*cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });*/

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}