package com.ahmmedalmzini783.progectfinish.adoapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmmedalmzini783.progectfinish.R;
import com.ahmmedalmzini783.progectfinish.classt.Subject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerAdapterSupject extends RecyclerView.Adapter<CustomRecyclerAdapterSupject.MyHolder> {

    Context context;
    ArrayList<Subject> data;
    onItemClickListener listener;

    public CustomRecyclerAdapterSupject(Context context, ArrayList<Subject> data,onItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_proj,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int i) {
        holder.subjectName.setText(data.get(i).getSubjectName());

        holder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemDelete(data.get(holder.getAdapterPosition()).getId(),holder.getAdapterPosition());
            }
        });
        holder.recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(data.get(holder.getAdapterPosition()),holder.getAdapterPosition());

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView subjectName;

        ConstraintLayout recyclerView;

        ImageView imageDelete;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.t_supName);
            recyclerView=itemView.findViewById(R.id.recyclerView);
            imageDelete=itemView.findViewById(R.id.imageDelete);

        }
    }

    public interface onItemClickListener{
        void onItemDelete(int id,int position);
        void onItemClick(Subject subject,int position);
    }
}
