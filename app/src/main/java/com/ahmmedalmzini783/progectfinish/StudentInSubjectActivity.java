package com.ahmmedalmzini783.progectfinish;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmmedalmzini783.progectfinish.adapter.StudentAdapter;
import com.ahmmedalmzini783.progectfinish.models.Presence;
import com.ahmmedalmzini783.progectfinish.models.Students;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StudentInSubjectActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private ArrayList<Students> studentsList;
    private DpHelper dpHelper;
    private SparseBooleanArray attendanceList = new SparseBooleanArray();
    private int subjectId;
    private String selectedMonth;
    private String selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_in_subject);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentInSubjectActivity.this);
                View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dilog_delet, null);
                builder.setView(dialogView);

                TextView textViewMessage = dialogView.findViewById(R.id.textMassege);
                TextView textViewPositive = dialogView.findViewById(R.id.textBosetiv);
                TextView textViewNegative = dialogView.findViewById(R.id.textNegativ);
                TextView textViewTitle = dialogView.findViewById(R.id.textViewTitle);
                ImageView img=dialogView.findViewById(R.id.img);
                img.setImageResource(R.drawable.baseline_cloud);
                textViewTitle.setText("تأكيد الحضور");
                textViewMessage.setText("هل تريد تأكيد الحضور للطلاب الذين تم اختيارهم؟");
                textViewPositive.setText("نعم");
                textViewNegative.setText("لا");

                AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(false);

                textViewPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveAttendance();
                        alertDialog.dismiss();

                    }
                });

                textViewNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();


            }
        });


        recyclerView = findViewById(R.id.recyclerview_students);
        studentsList = new ArrayList<>();

        subjectId = getIntent().getIntExtra("subjectId", 0);
        selectedMonth = getIntent().getStringExtra("selectedMonth");
        selectedDay = getIntent().getStringExtra("selectedDay");


        dpHelper = new DpHelper(getApplicationContext());
        studentsList = dpHelper.getStudentsBySubject(subjectId);

        adapter = new StudentAdapter(this, studentsList, attendanceList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
    }





    private void saveAttendance() {
        String selectedMonth = getIntent().getStringExtra("selectedMonth");
        String selectedDay = getIntent().getStringExtra("selectedDay");
        int subjectId = getIntent().getIntExtra("subjectId", 0);

        dpHelper = new DpHelper(getApplicationContext());

        StringBuilder attendanceData = new StringBuilder();

        for (int i = 0; i < studentsList.size(); i++) {
            Students student = studentsList.get(i);
            boolean isPresent = attendanceList.get(i, false);

            if (isPresent) {
                Presence presence = new Presence();
                presence.setMonth(selectedMonth);
                presence.setDay(Integer.parseInt(selectedDay));
                presence.setStudent_id(student.getId());
                presence.setSubject_id(subjectId);
                presence.setPresent(true);

                boolean result = dpHelper.insertPresence(presence);
                if (result) {
                    attendanceData.append("تم التخزي ").append(student.getFirstName()).append("\n");
                } else {
                    attendanceData.append("حدث خطأ ").append(student.getFirstName()).append("\n");
                }
            }
        }

    }




}