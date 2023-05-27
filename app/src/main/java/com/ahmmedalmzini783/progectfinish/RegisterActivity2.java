package com.ahmmedalmzini783.progectfinish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity2 extends AppCompatActivity {
    EditText mNameEditText;
    EditText mEmailEditText;
    EditText mPasswordEditText;
    DpHelper dpHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);


        // تعيين المراجع لعناصر واجهة المستخدم
        mNameEditText = findViewById(R.id.te_name);
        mEmailEditText = findViewById(R.id.te_email);
        mPasswordEditText = findViewById(R.id.te_password);

        Button btn_register = findViewById(R.id.btn_register);
        dpHelper=new DpHelper(this);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mNameEditText1=mNameEditText.getText().toString();
                String mEmailEditText1= mEmailEditText.getText().toString();
                String mPasswordEditText1= mPasswordEditText.getText().toString();

                boolean isInserted = dpHelper.insertAdmin(mNameEditText1,mEmailEditText1,mPasswordEditText1);

                if (isInserted) {
                    Toast.makeText(getApplicationContext(), "تمت إضافة الحساب بنجاح", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "حدث خطأ أثناء إضافة الحساب", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }
