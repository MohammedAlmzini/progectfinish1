package com.ahmmedalmzini783.progectfinish;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmmedalmzini783.progectfinish.models.Admin;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity2 extends AppCompatActivity {
    EditText mNameEditText;
    EditText mEmailEditText;
    EditText mPasswordEditText;
    DpHelper dpHelper;
    Admin admin;
    EditText te_name,te_email,te_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        te_name=findViewById(R.id.te_nameAdd);
        te_email=findViewById(R.id.te_emailAdd);
        te_password=findViewById(R.id.te_passwordAdd);
        Button btn_register = findViewById(R.id.btn_register);
        TextView tv_update=findViewById(R.id.tv_update);



        dpHelper=new DpHelper(this);



        if (getIntent().getExtras()!=null) {


             admin= (Admin) getIntent().getExtras().getSerializable("admin");
            if (admin != null) {
                te_name.setText(admin.getUsername());
                te_email.setText(admin.getEmail());
                te_password.setText(admin.getPassword());
                btn_register.setText("Update");
                tv_update.setText("تحديث الحساب");
            }
        }


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String te_nameUpdate=te_name.getText().toString();
                String te_emailUpdate=te_email.getText().toString();
                String te_passwordUpdate=te_password.getText().toString();
                if (admin!=null){
                    String id= String.valueOf(admin.getId());
                    boolean isSuccesfulyUpdate= dpHelper.updateAdmin(id,te_nameUpdate,te_emailUpdate,te_passwordUpdate);
                    if (isSuccesfulyUpdate){

                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "يرجى إدخال قيم صحيحة", Toast.LENGTH_SHORT).show();
                    }
                }else {


                    boolean isSucssefly = dpHelper.insertAdmin(te_nameUpdate,te_emailUpdate,te_passwordUpdate);
                    if (isSucssefly){
                        Toast.makeText(getApplicationContext(), "تمت إضافة الحساب بنجاح", Toast.LENGTH_SHORT).show();

                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "حدث خطأ أثناء إضافة الحساب", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



//        btn_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String mNameEditText1=te_name.getText().toString();
//                String mEmailEditText1= te_email.getText().toString();
//                String mPasswordEditText1= te_password.getText().toString();
//
//                boolean isInserted = dpHelper.insertAdmin(mNameEditText1,mEmailEditText1,mPasswordEditText1);
//
//                if (isInserted) {
//                    Toast.makeText(getApplicationContext(), "تمت إضافة الحساب بنجاح", Toast.LENGTH_SHORT).show();
//                    finish();
//                } else {
//                    Toast.makeText(getApplicationContext(), "حدث خطأ أثناء إضافة الحساب", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
    }
