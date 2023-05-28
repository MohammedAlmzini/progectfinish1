package com.ahmmedalmzini783.progectfinish.adoapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmmedalmzini783.progectfinish.DpHelper;
import com.ahmmedalmzini783.progectfinish.R;
import com.ahmmedalmzini783.progectfinish.classt.Subject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerAdapterSupject2 extends RecyclerView.Adapter<CustomRecyclerAdapterSupject2.MyHolder> {

    Context context;
    ArrayList<Subject> data;
    onItemClickListener listener;
    private ArrayList<Subject> subjectsList;


    public CustomRecyclerAdapterSupject2(Context context, ArrayList<Subject> data, ArrayList<Subject> subjectsList) {
        this.context = context;
        this.data = data;
        this.subjectsList = subjectsList;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_supject_choice, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int i) {
        Subject subject = data.get(i);
        holder.supjectName.setText(subject.getSubjectName());

        // تعيين حالة المادة المحددة



        // تعيين الحدث لتغيير حالة المادة المحددة
        holder.supjectName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = !subject.isSelected();
                subject.setSelected(isSelected);
                holder.supjectName.setSelected(isSelected);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CheckBox supjectName;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            supjectName = itemView.findViewById(R.id.supjectName);
        }
    }

    public interface onItemClickListener {
    }

//    public ArrayList<Subject> getSelectedSubjects() {
//        ArrayList<Subject> selectedSubjects = new ArrayList<>();
//        DpHelper dpHelper=new DpHelper(context.getApplicationContext());
//        for (Subject subject : dpHelper.getAllDataSubject()) {
//            if (subject.isSelected()) {
//                selectedSubjects.add(subject);
//            }
//        }
//        return selectedSubjects;
//    }

    public ArrayList<Subject> getSelectedSubjects() {
        ArrayList<Subject> selectedSubjects = new ArrayList<>();

        for (Subject subject : subjectsList) {
            if (subject.isSelected()) {
                selectedSubjects.add(subject);
            }
        }

        return selectedSubjects;
    }


}
