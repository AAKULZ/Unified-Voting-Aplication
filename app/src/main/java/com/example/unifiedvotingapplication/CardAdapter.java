package com.example.unifiedvotingapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private DatabaseReference databaseReference,dbr;
    private Context context;
    private List<DataClass3> dataList;
    private String user;
    private int focusedPosition = 0;

    public CardAdapter(Context context,List<DataClass3> datalist,String user) {
        this.dataList = datalist;
        this.context=context;
        this.user=user;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.imageView);
        holder.textView.setText(dataList.get(position).getUser());

        // Set the size of the focused card
        if (position == focusedPosition) {
            holder.textView.setTextSize(20);// Increase font size
        } else {
            holder.textView.setTextSize(16); // Default font size
        }

        //------------------------------------------
        databaseReference = FirebaseDatabase.getInstance().getReference("Candidates").child(dataList.get(position).getElection()).child(dataList.get(position).getUser()).child("vote");
        dbr = FirebaseDatabase.getInstance().getReference("Elections").child(dataList.get(position).getElection());

        //------------------------------------------

        holder.vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dbr.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(!snapshot.getValue(DataClass.class).getVoted().contains(user)){
                        Toast.makeText(context.getApplicationContext(), "Successful",Toast.LENGTH_SHORT).show();
                        int i=Integer.valueOf(dataList.get(position).getVote());
                        i++;
                        dataList.get(position).setVote(Integer.toString(i));
                        databaseReference.setValue(Integer.toString(i));
                        dbr.child("voted").setValue(snapshot.getValue(DataClass.class).getVoted().concat(":").concat(user));

                    }
                    else{

                        Toast.makeText(context.getApplicationContext(),"Already Voted",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
                //--------------------------------

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setFocusedPosition(int position) {
        focusedPosition = position;
        notifyDataSetChanged();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView textView,vote;
        ImageView imageView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cardText);
            imageView= itemView.findViewById(R.id.ci);
            vote=itemView.findViewById(R.id.vote);
        }

    }
}
