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

public class MyAdapter3 extends RecyclerView.Adapter<MyViewHolder3> {

    private Context context;
    private List<DataClass> dataList;
    private String user;

    public MyAdapter3(Context context, List<DataClass> dataList,String user) {
        this.context = context;
        this.dataList = dataList;
        this.user=user;
    }

    @NonNull
    @Override
    public MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new MyViewHolder3(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder3 holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getTitle());
        holder.recDes.setText(dataList.get(position).getDes());
        holder.recLang.setText(dataList.get(position).getConduc());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity3.class);
                intent.putExtra("Image",dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Description",dataList.get(holder.getAdapterPosition()).getDes());
                intent.putExtra("Title",dataList.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("frd",dataList.get(holder.getAdapterPosition()).getFrd());
                intent.putExtra("fcd",dataList.get(holder.getAdapterPosition()).getFcd());
                intent.putExtra("frt",dataList.get(holder.getAdapterPosition()).getFrt());
                intent.putExtra("fct",dataList.get(holder.getAdapterPosition()).getFct());
                intent.putExtra("vrd",dataList.get(holder.getAdapterPosition()).getPsd());
                intent.putExtra("vcd",dataList.get(holder.getAdapterPosition()).getPed());
                intent.putExtra("vrt",dataList.get(holder.getAdapterPosition()).getPst());
                intent.putExtra("vct",dataList.get(holder.getAdapterPosition()).getPet());
                intent.putExtra("user",user);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder3 extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recTitle, recDes, recLang,vote;
    CardView recCard;

    public MyViewHolder3(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recDes = itemView.findViewById(R.id.recDesc);
        recLang = itemView.findViewById(R.id.recLang);
        recTitle = itemView.findViewById(R.id.recTitle);
        vote=itemView.findViewById(R.id.vote);
    }
}
