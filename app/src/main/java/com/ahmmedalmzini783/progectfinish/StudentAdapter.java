package com.ahmmedalmzini783.progectfinish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmmedalmzini783.progectfinish.classt.Students;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Students> studentsList;

    public StudentAdapter(Context context, ArrayList<Students> studentsList) {
        this.context = context;
        this.studentsList = studentsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_supject_choice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Students student = studentsList.get(position);
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tv_last_name;
        private TextView tvAge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tv_last_name = itemView.findViewById(R.id.tv_last_name);
            tvAge = itemView.findViewById(R.id.tv_age);
        }

        public void bind(Students students) {
            tvName.setText(students.getFirstName());
            tv_last_name.setText(students.getLastName());
            tvAge.setText(String.valueOf(students.getAge()));
        }
    }
}

