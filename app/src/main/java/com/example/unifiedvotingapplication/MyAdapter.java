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

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recTitle.setText(dataList.get(position).getTitle());
        holder.recDes.setText(dataList.get(position).getDes());
        holder.recLang.setText(dataList.get(position).getConduc());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("Image",dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Description",dataList.get(holder.getAdapterPosition()).getDes());
                intent.putExtra("Title",dataList.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("conductor",dataList.get(holder.getAdapterPosition()).getConduc());
                intent.putExtra("description",dataList.get(holder.getAdapterPosition()).getDes());
                intent.putExtra("conductorMobile",dataList.get(holder.getAdapterPosition()).getConducMobile());
                intent.putExtra("cbr",dataList.get(holder.getAdapterPosition()).getCbr());
                intent.putExtra("cba",dataList.get(holder.getAdapterPosition()).getCba());
                intent.putExtra("cyr",dataList.get(holder.getAdapterPosition()).getCyr());
                intent.putExtra("vbr",dataList.get(holder.getAdapterPosition()).getVbr());
                intent.putExtra("vba",dataList.get(holder.getAdapterPosition()).getVba());
                intent.putExtra("vyr",dataList.get(holder.getAdapterPosition()).getVyr());
                intent.putExtra("frd",dataList.get(holder.getAdapterPosition()).getFrd());
                intent.putExtra("psd",dataList.get(holder.getAdapterPosition()).getPsd());
                intent.putExtra("ped",dataList.get(holder.getAdapterPosition()).getPed());
                intent.putExtra("fcd",dataList.get(holder.getAdapterPosition()).getFcd());
                intent.putExtra("frt",dataList.get(holder.getAdapterPosition()).getFrt());
                intent.putExtra("pst",dataList.get(holder.getAdapterPosition()).getPst());
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

class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recTitle, recDes, recLang;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.recImage);
        recCard = itemView.findViewById(R.id.recCard);
        recDes = itemView.findViewById(R.id.recDesc);
        recLang = itemView.findViewById(R.id.recLang);
        recTitle = itemView.findViewById(R.id.recTitle);
    }
}
