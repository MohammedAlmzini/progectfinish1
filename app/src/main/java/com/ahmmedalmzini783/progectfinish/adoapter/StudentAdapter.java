package com.ahmmedalmzini783.progectfinish.adoapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ahmmedalmzini783.progectfinish.AddStudentActivity;
import com.ahmmedalmzini783.progectfinish.R;
import com.ahmmedalmzini783.progectfinish.classt.Students;
import com.ahmmedalmzini783.progectfinish.classt.Subject;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends BaseAdapter {

    private Context context;
    private List<Students> studentList;
    private List<Subject> allDataSubject;

    public StudentAdapter(Context context, List<Students> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    public StudentAdapter(Context context, ArrayList<Subject> allDataSubject) {
        this.context = context;
        this.allDataSubject = allDataSubject;
    }

    @Override
    public int getCount() {
        return allDataSubject.size();
    }

    @Override
    public Object getItem(int position) {
        return allDataSubject.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_supject_choice, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.supjectName);

        Subject student = allDataSubject.get(position);

        return convertView;
    }

    public void updateStudents(List<Students> updatedList) {
        studentList = updatedList;
        notifyDataSetChanged();
    }
}
