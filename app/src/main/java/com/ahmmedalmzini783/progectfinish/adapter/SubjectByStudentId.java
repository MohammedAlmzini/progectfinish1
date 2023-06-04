package com.ahmmedalmzini783.progectfinish.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmmedalmzini783.progectfinish.DpHelper;
import com.ahmmedalmzini783.progectfinish.R;
import com.ahmmedalmzini783.progectfinish.models.Students;
import com.ahmmedalmzini783.progectfinish.models.Subject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubjectByStudentId extends RecyclerView.Adapter<SubjectByStudentId.MyHolder> {

    Context context;
    ArrayList<Subject> data;
    DpHelper dpHelper;
    private int studentId;

    public SubjectByStudentId(Context context, ArrayList<Subject> data, int studentId) {
        this.context = context;
        this.data = data;
        this.studentId = studentId;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stubject_by_student_id, parent, false);
        return new MyHolder(view, studentId);
    }

    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Subject subject = data.get(position);
        holder.subjectName.setText(subject.getSubjectName());

        Log.e("TAG", "onBindViewHolder: "+subject );
        dpHelper = new DpHelper(context);
        String attendancePercentage = dpHelper.getAttendancePercentage(subject.getId(), studentId);
        String attendanceText = " " + attendancePercentage + "%";
        holder.attendancePercentage.setText(attendanceText);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView subjectName;
        TextView attendancePercentage;

        public MyHolder(@NonNull View itemView, int studentId) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.SubjectName);
            attendancePercentage = itemView.findViewById(R.id.percentage_of_student_attendance);

            // استخدم studentId هنا حسب الحاجة
        }
    }
}
