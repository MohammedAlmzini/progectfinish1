package com.ahmmedalmzini783.progectfinish.adoapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmmedalmzini783.progectfinish.classt.Presence;
import com.ahmmedalmzini783.progectfinish.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerAdapterDayes extends RecyclerView.Adapter<CustomRecyclerAdapterDayes.MyHolder> {

    Context context;
    ArrayList<Presence> data;
    onItemClickListener listener;

    public CustomRecyclerAdapterDayes(Context context, ArrayList<Presence> data, onItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_days,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        holder.name.setText(data.get(i).getDay()+"");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView name;
        ConstraintLayout recyclerView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_dayes);
            recyclerView=itemView.findViewById(R.id.recyclerView);

        }
    }

    public interface onItemClickListener{
        void onItemClick(Presence student, int adapterPosition);
    }
}
