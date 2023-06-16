package com.ahmmedalmzini783.progectfinish;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.ahmmedalmzini783.progectfinish.adapter.CustomRecyclerAdapterSupject2;
import com.ahmmedalmzini783.progectfinish.models.Subject;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddStudentActivity extends AppCompatActivity {
    private TextInputEditText te_first_name;
    private TextInputEditText te_last_name;
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

        te_first_name = findViewById(R.id.te_first_name);
        te_last_name = findViewById(R.id.te_last_name);
        te_Date = findViewById(R.id.te_Date);
        addButton = findViewById(R.id.addButton);



        recyclerview_subject_to_Add = findViewById(R.id.recyclerview_subject_to_Add);

        dbHelper = new DpHelper(getApplicationContext());

        subjectsList = new ArrayList<>();
        adapter = new CustomRecyclerAdapterSupject2(this, dbHelper.getAllDataSubject(), subjectsList);

        recyclerview_subject_to_Add.setAdapter(adapter);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        recyclerview_subject_to_Add.setLayoutManager(manager);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = te_first_name.getText().toString();
                String lastName = te_last_name.getText().toString();
                String ageString = te_Date.getText().toString();

                if (firstName.isEmpty() ||lastName.isEmpty() || ageString.isEmpty()) {
                    Toast.makeText(AddStudentActivity.this, "يرجى إدخال جميع الحقول", Toast.LENGTH_SHORT).show();
                } else {
                    int year, monthOfYear, dayOfMonth;
                    Calendar dob = Calendar.getInstance();
                    dob.setTimeInMillis(0);
                    String[] dateParts = ageString.split("/");
                    if (dateParts.length == 3) {
                        dayOfMonth = Integer.parseInt(dateParts[0]);
                        monthOfYear = Integer.parseInt(dateParts[1]) - 1;
                        year = Integer.parseInt(dateParts[2]);
                        dob.set(year, monthOfYear, dayOfMonth);
                        Calendar today = Calendar.getInstance();
                        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
                        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                            age--;
                        }
                        int studentAge = age;

                        ArrayList<Subject> selectedSubjects = adapter.getSelectedSubjects();

                        dbHelper = new DpHelper(getApplicationContext());
                        boolean inserted = dbHelper.insertStudent(firstName, lastName, studentAge, adapter.getSelectedSubjects(),ageString);
                        if (inserted) {
                            Toast.makeText(getApplicationContext(), "تم إضافة الطالب. عمره "+ studentAge + " سنة", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "تاريخ الميلاد "+ ageString + " سنة", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "تم إضافة المواد التالية: " + selectedSubjects, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "حدث خطأ أثناء إضافة الطالب", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddStudentActivity.this, "يرجى إدخال التاريخ بالتنسيق الصحيح (DD/MM/YYYY)", Toast.LENGTH_SHORT).show();
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