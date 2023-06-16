package com.ahmmedalmzini783.progectfinish.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ahmmedalmzini783.progectfinish.R;
import com.ahmmedalmzini783.progectfinish.models.Students;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyHolder> {
    private Context context;
    private ArrayList<Students> studentsList;
    private SparseBooleanArray attendanceList;

    public StudentAdapter(Context context, ArrayList<Students> studentsList, SparseBooleanArray attendanceList) {
        this.context = context;
        this.studentsList = studentsList;
        this.attendanceList = attendanceList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student_hodore, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Students student = studentsList.get(position);
        holder.tvName.setText(student.getFirstName());
        holder.cbPresent.setChecked(attendanceList.get(position, false));

        holder.cbPresent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                attendanceList.put(position, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvId;
        CheckBox cbPresent;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_student_name);
            cbPresent = itemView.findViewById(R.id.cb_attendance);
        }
    }
}