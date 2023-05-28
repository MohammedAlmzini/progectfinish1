package com.ahmmedalmzini783.progectfinish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.ahmmedalmzini783.progectfinish.adoapter.CustomRecyclerAdapterSupject2;
import com.ahmmedalmzini783.progectfinish.classt.Subject;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;

public class AddStudentActivity extends AppCompatActivity  {

    private TextInputEditText te_name;
    private TextInputEditText te_email;
    private TextInputEditText te_Date;
    private Button addButton;
    private RecyclerView recyclerview_subject_to_Add;
    private DpHelper dbHelper;
    private CustomRecyclerAdapterSupject2 adapter;
    private ArrayList<Subject> subjectsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        te_name = findViewById(R.id.te_name);
        te_email = findViewById(R.id.te_email);
        te_Date = findViewById(R.id.te_Date);
        addButton = findViewById(R.id.addButton);
        recyclerview_subject_to_Add = findViewById(R.id.recyclerview_subject_to_Add);


        dbHelper = new DpHelper(getApplicationContext());

        subjectsList = new ArrayList<>(); // قائمة المواد المتاحة
        // قم بملء subjectsList بالمواد المتاحة من قاعدة البيانات أو أي مصدر آخر
        // subjectsList = dbHelper.getAllSubjects(); // مثال على استرجاع المواد من قاعدة البيانات

        // قم بتهيئة وتعيين محول العرض (Adapter) لعرض المواد
        adapter = new CustomRecyclerAdapterSupject2(this, dbHelper.getAllDataSubject(), subjectsList);
        recyclerview_subject_to_Add.setAdapter(adapter);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerview_subject_to_Add.setLayoutManager(manager);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // قراءة القيم من الحقول النصية
                String firstName = te_name.getText().toString();
                String lastName = te_email.getText().toString();
                String ageString = te_Date.getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || ageString.isEmpty()) {
                    Toast.makeText(AddStudentActivity.this, "يرجى إدخال جميع الحقول", Toast.LENGTH_SHORT).show();
                } else {
                    int age = Integer.parseInt(ageString);

                    // جمع المواد المحددة
                    ArrayList<Subject> selectedSubjects = adapter.getSelectedSubjects();

                    // إدراج الطالب في قاعدة البيانات
                    dbHelper = new DpHelper(getApplicationContext());
                    boolean inserted = dbHelper.insertStudent(firstName, lastName, age, selectedSubjects);
                    if (inserted) {
                        Toast.makeText(getApplicationContext(), "تم إضافة الطالب بنجاح", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "حدث خطأ أثناء إضافة الطالب", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




        te_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddStudentActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                te_Date.setText(selectedDate);
            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }


}
