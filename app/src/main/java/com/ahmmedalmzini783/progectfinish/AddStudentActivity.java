package com.ahmmedalmzini783.progectfinish;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmmedalmzini783.progectfinish.adoapter.StudentAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class AddStudentActivity extends AppCompatActivity {

    private TextInputEditText te_name;
    private TextInputEditText te_email;
    private TextInputEditText te_Date;
    private Button addButton;
    private ListView listView_student;
    private StudentAdapter studentAdapter;
    private DpHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        te_name = findViewById(R.id.te_name);
        te_email = findViewById(R.id.te_email);
        te_Date = findViewById(R.id.te_Date);
        addButton = findViewById(R.id.addButton);
        listView_student = findViewById(R.id.listView_student);

        dbHelper = new DpHelper(getApplicationContext());
        studentAdapter = new StudentAdapter(this, dbHelper.getAllDataSubject());
        listView_student.setAdapter(studentAdapter);

        te_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
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

    private void addStudent() {
        String firstName = te_name.getText().toString().trim();
        String lastName = te_email.getText().toString().trim();
        String ageString = te_Date.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || ageString.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }else {


            int age = Integer.parseInt(ageString);

            boolean isInserted = dbHelper.insertStudent(firstName, lastName, age);

            if (isInserted) {
                Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
                te_name.setText("");
                te_email.setText("");
                te_Date.setText("");

                // Refresh student list
                studentAdapter.updateStudents(dbHelper.getAllDataStudents());
            } else {
                Toast.makeText(this, "Failed to add student", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
