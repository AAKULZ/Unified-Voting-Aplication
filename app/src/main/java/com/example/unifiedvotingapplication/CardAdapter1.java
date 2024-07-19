package com.example.unifiedvotingapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CardAdapter1 extends RecyclerView.Adapter<CardAdapter1.CardViewHolder> {

    private DatabaseReference databaseReference,dbr;
    private Context context;
    private List<DataClass3> dataList;
    private String user;
    private int focusedPosition = 0;

    public CardAdapter1(Context context,List<DataClass3> datalist,String user) {
        this.dataList = datalist;
        this.context=context;
        this.user=user;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.itemcard2, parent, false);
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

        holder.resendBtn.setText(dataList.get(position).getVote().concat(" Votes"));
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

        TextView textView,resendBtn;
        ImageView imageView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cardText);
            imageView= itemView.findViewById(R.id.ci);
            resendBtn=itemView.findViewById(R.id.resendBtn);
        }

    }
}
