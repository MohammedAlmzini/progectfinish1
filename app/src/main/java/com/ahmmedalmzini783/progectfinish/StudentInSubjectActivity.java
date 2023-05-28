package com.ahmmedalmzini783.progectfinish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ahmmedalmzini783.progectfinish.classt.Students;

import java.util.ArrayList;

public class StudentInSubjectActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private ArrayList<Students> studentsList;
    private DpHelper dpHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_in_subject);
        recyclerView = findViewById(R.id.recyclerview_students);
        studentsList = new ArrayList<>();

        // استلم المعرف المرتبط بالمادة المحددة من الـ Intent
        int subjectId = getIntent().getIntExtra("subjectId", 0);

        // استخدم المعرف لاستعلام قاعدة البيانات لاسترداد بيانات الطلاب
        dpHelper = new DpHelper(getApplicationContext());
        studentsList = dpHelper.getStudentsBySubject(subjectId);

        // عرض بيانات الطلاب في واجهة المستخدم باستخدام محول العرض (Adapter)
        adapter = new StudentAdapter(this, studentsList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
    }}