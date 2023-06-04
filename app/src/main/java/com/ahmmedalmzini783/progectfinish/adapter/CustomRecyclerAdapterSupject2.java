package com.ahmmedalmzini783.progectfinish.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.ahmmedalmzini783.progectfinish.DpHelper;
import com.ahmmedalmzini783.progectfinish.R;
import com.ahmmedalmzini783.progectfinish.models.Subject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRecyclerAdapterSupject2 extends RecyclerView.Adapter<CustomRecyclerAdapterSupject2.MyHolder> {

    Context context;
    ArrayList<Subject> data;
    private ArrayList<Subject> subjectsList;
    DpHelper dpHelper;


    public CustomRecyclerAdapterSupject2(Context context, ArrayList<Subject> data, ArrayList<Subject> subjectsList) {
        this.context = context;
        this.data = data;
        this.subjectsList = subjectsList;
        dpHelper = new DpHelper(context);
        this.subjectsList.clear();
        this.subjectsList.addAll(dpHelper.getAllDataSubject());
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_supject_choice, parent, false);
        return new MyHolder(view, subjectsList);
    }



    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int i) {
        Subject subject = subjectsList.get(i);
        holder.supjectNameCheck.setText(subject.getSubjectName());

        // تعيين حالة المادة المحددة
        holder.supjectNameCheck.setChecked(subject.isSelected());

        // تعيين الحدث لتغيير حالة المادة المحددة
        holder.supjectNameCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = !subject.isSelected();
                subject.setSelected(isSelected);
                holder.supjectNameCheck.setSelected(isSelected);

                // تحديث حالة المادة المحددة في قائمة البيانات (data)
                data.get(i).setSelected(isSelected);
            }
        });

    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CheckBox supjectNameCheck;
        ArrayList<Subject> subjectsList;

        public MyHolder(@NonNull View itemView, ArrayList<Subject> subjectsList) {
            super(itemView);
            supjectNameCheck = itemView.findViewById(R.id.supjectName);
            this.subjectsList = subjectsList;
        }
    }

    public interface onItemClickListener {

    }


    public ArrayList<Subject> getSelectedSubjects() {
        ArrayList<Subject>selectedSubjects = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelected()) {
                selectedSubjects.add(data.get(i));
            }
        }

        return selectedSubjects;
    }

}
