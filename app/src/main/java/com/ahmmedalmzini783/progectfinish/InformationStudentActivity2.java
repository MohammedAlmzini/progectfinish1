package com.ahmmedalmzini783.progectfinish;

import android.os.Bundle;
import android.widget.TextView;

import com.ahmmedalmzini783.progectfinish.adapter.SubjectByStudentId;
import com.ahmmedalmzini783.progectfinish.models.Students;
import com.ahmmedalmzini783.progectfinish.models.Subject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InformationStudentActivity2 extends AppCompatActivity {
    TextView tvFirstNameAndLastName, tvBarthDay, tvAge;
    SubjectByStudentId adapter;
    DpHelper dpHelper;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_student2);

        int studentId = getIntent().getIntExtra("studentId", 0);

        tvFirstNameAndLastName = findViewById(R.id.tvFirstNameAndLateName);
        tvBarthDay = findViewById(R.id.tvBarthDay);
        tvAge = findViewById(R.id.tvAge);

         dpHelper = new DpHelper(getApplicationContext());
        Students student = dpHelper.getStudentById(studentId);

        if (student != null) {
            tvFirstNameAndLastName.setText(student.getFirstName() + " " + student.getLastName());
            tvBarthDay.setText(student.getParthDay());
            tvAge.setText(String.valueOf(student.getAge()));
        }

        recyclerView = findViewById(R.id.subjectRecyclerView);

        dpHelper = new DpHelper(this);
        ArrayList<Subject> data2 = dpHelper.getSubjectsByStudentId(studentId);



        adapter = new SubjectByStudentId(this, data2, studentId);



        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }




}
