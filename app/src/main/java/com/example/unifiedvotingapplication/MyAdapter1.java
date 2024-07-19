package com.example.unifiedvotingapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter1 extends RecyclerView.Adapter<MyViewHolder1> {

    private Context context;
    private List<DataClass1> dataList;

    public MyAdapter1(Context context, List<DataClass1> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getUser());
        holder.recDes.setText(dataList.get(position).getId());
        holder.recLang.setText(dataList.get(position).getId());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity1.class);
                intent.putExtra("Image",dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Description",dataList.get(holder.getAdapterPosition()).getId().toUpperCase());
                intent.putExtra("Title",dataList.get(holder.getAdapterPosition()).getUser());
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("name",dataList.get(holder.getAdapterPosition()).getUser());
                intent.putExtra("id",dataList.get(holder.getAdapterPosition()).getId().toUpperCase());
                intent.putExtra("dob",dataList.get(holder.getAdapterPosition()).getDob());
                intent.putExtra("gender",dataList.get(holder.getAdapterPosition()).getGender());
                intent.putExtra("batch",dataList.get(holder.getAdapterPosition()).getBatch());
                intent.putExtra("year",dataList.get(holder.getAdapterPosition()).getId().substring(0,4));
                intent.putExtra("branch",dataList.get(holder.getAdapterPosition()).getId().substring(4,7).toUpperCase());
                intent.putExtra("mobile",dataList.get(holder.getAdapterPosition()).getMobile());
                intent.putExtra("email",dataList.get(holder.getAdapterPosition()).getEmail());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DataClass1> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder1 extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recTitle, recDes, recLang;
    CardView recCard;

    public MyViewHolder1(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recDes = itemView.findViewById(R.id.recDesc);
        recLang = itemView.findViewById(R.id.recLang);
        recTitle = itemView.findViewById(R.id.recTitle);
    }
}
