package com.ahmmedalmzini783.progectfinish;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        TextView te_Date = findViewById(R.id.te_Date);

        TextInputLayout textInputLayout = findViewById(R.id.text_input_layout);
        textInputLayout.setStartIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // الحصول على التاريخ المحدد في حالة تم اختيار التاريخ
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // إنشاء DatePickerDialog وتعيين مستمع الحدث له
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddStudentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        // يتم تحديث النص في TextInputEditText بالتاريخ المختار
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        te_Date.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

                // عرض DatePickerDialog
                datePickerDialog.show();
            }
        });
    }
}