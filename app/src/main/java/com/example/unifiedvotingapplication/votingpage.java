package com.example.unifiedvotingapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class votingpage extends AppCompatActivity {

    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private List<DataClass3> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votingpage);

        final String title = getIntent().getStringExtra("election");
        final String user = getIntent().getStringExtra("user");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(votingpage.this,1);
        recyclerView = findViewById(R.id.vrec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        AlertDialog.Builder builder = new AlertDialog.Builder(votingpage.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        cardList=new ArrayList<>();

        cardAdapter = new CardAdapter(votingpage.this, cardList,user);
        recyclerView.setAdapter(cardAdapter);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Candidates").child(title);
        dialog.show();

        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cardList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataClass3 dataClass = itemSnapshot.getValue(DataClass3.class);
                    dataClass.setKey(itemSnapshot.getKey());
                    cardList.add(dataClass);
                }
                cardAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });


        // Set a listener to handle card focus changes
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();

                int centerPosition = (firstVisiblePosition + lastVisiblePosition) / 2;
                cardAdapter.setFocusedPosition(centerPosition);
            }
        });


    }
}
