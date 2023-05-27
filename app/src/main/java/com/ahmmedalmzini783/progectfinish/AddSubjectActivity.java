package com.ahmmedalmzini783.progectfinish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmmedalmzini783.progectfinish.R;

public class AddSubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        Button btn_add=findViewById(R.id.btn_add_subject);
        EditText addTitel=findViewById(R.id.te_name);
        DpHelper dbHelper=new DpHelper(getApplicationContext());


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addTitel1=addTitel.getText().toString();

                    boolean isSucssefly=dbHelper.insertSubject(addTitel1);
                    if (isSucssefly){
                        Toast.makeText(AddSubjectActivity.this, "تم إضافتها بنجاح", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "يرجى إدخال قيم صحيحة", Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }
}