package com.ahmmedalmzini783.progectfinish;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ahmmedalmzini783.progectfinish.adapter.CustomRecyclerAdapterSupject;
import com.ahmmedalmzini783.progectfinish.models.Admin;
import com.ahmmedalmzini783.progectfinish.models.Subject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CustomRecyclerAdapterSupject adapter;
    LinearLayout myLayout;
    DpHelper dpHelper;
    RecyclerView recyclerView;
    TextView tv_name_home;
    TextView tv_email_home;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv_name_home=findViewById(R.id.tv_name_home);
        tv_email_home=findViewById(R.id.tv_email_home);

        String email = getIntent().getStringExtra("email");
        String name = getIntent().getStringExtra("name");
        String password=getIntent().getStringExtra("password");





        LinearLayout btnUpdate=findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Admin admin=new Admin(1,password,email,name);
                Intent intent=new Intent(getApplicationContext(),RegisterActivity2.class);
                intent.putExtra("admin",admin);

                startActivity(intent);
            }
        });


        recyclerView=findViewById(R.id.recvSupject);
        ArrayList<Subject> data =new ArrayList<>();



        recyclerView.setAdapter(adapter);
        GridLayoutManager manager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);


        Button btn_All_Student=findViewById(R.id.btn_All_Student);
        btn_All_Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AllStudentsActivity.class);
                startActivity(intent);
            }
        });


        ImageView myImageButton = (ImageView) findViewById(R.id.my_image_button);
        myImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // تغيير الصورة على الضغط
                myLayout = (LinearLayout) findViewById(R.id.lin_add_gone);
                myLayout.setVisibility(View.VISIBLE);
                Animation popUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop_up);
                myLayout.startAnimation(popUp);
                // تطبيق تأثير على الضغط
                RippleDrawable rippleDrawable = (RippleDrawable) myImageButton.getBackground();
                Animation rotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
                myImageButton.startAnimation(rotation);
            }
        });


        LinearLayout add_studentLayout = (LinearLayout) findViewById(R.id.add_student);
        add_studentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddStudentActivity.class);
                startActivity(intent);
                Animation popOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop_out);
                myLayout.startAnimation(popOut);
                myLayout.setVisibility(View.GONE);
                RippleDrawable rippleDrawable = (RippleDrawable) myImageButton.getBackground();
                Animation rotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
                myImageButton.startAnimation(rotation);
            }
        });

        LinearLayout add_subjectLayout = (LinearLayout) findViewById(R.id.add_subject);
        add_subjectLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddSubjectActivity.class);
                startActivity(intent);
                Animation popOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop_out);
                myLayout.startAnimation(popOut);
                myLayout.setVisibility(View.GONE);
                RippleDrawable rippleDrawable = (RippleDrawable) myImageButton.getBackground();
                Animation rotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
                myImageButton.startAnimation(rotation);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        dpHelper = new DpHelper(this);
        ArrayList<Subject> data = dpHelper.getAllDataSubject();


        dpHelper = new DpHelper(this);
        ArrayList<Admin> adminList = dpHelper.getAllDataAdmin();

        if (!adminList.isEmpty()) {
            Admin admin = adminList.get(0);
            tv_name_home.setText(admin.getUsername());
            tv_email_home.setText(admin.getEmail());
        }

        adapter = new CustomRecyclerAdapterSupject(this, data, new CustomRecyclerAdapterSupject.onItemClickListener() {
            @Override
            public void onItemDelete(int id, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dilog_delet, null);
                builder.setView(dialogView);

                TextView textViewMessage = dialogView.findViewById(R.id.textMassege);
                TextView textViewPositive = dialogView.findViewById(R.id.textBosetiv);
                TextView textViewNegative = dialogView.findViewById(R.id.textNegativ);
                TextView textViewTitle = dialogView.findViewById(R.id.textViewTitle);
                textViewTitle.setText("حذف مادة");
                textViewMessage.setText("هل تريد تأكيد حذف المادة؟");
                textViewPositive.setText("نعم");
                textViewNegative.setText("لا");

                AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(false);

                textViewPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dpHelper.deleteSubject(String.valueOf(id))) {
                            data.remove(position);
                            adapter.notifyItemRemoved(position);
                            alertDialog.dismiss();
                        }
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

            @Override
            public void onItemClick(Subject subject, int position) {
                Subject selectedSubject = data.get(position);

                // تمرير معرف المادة أو المعلومات اللازمة للنشاط الجديد
                Intent intent = new Intent(getApplicationContext(), MonthActivity.class);
                intent.putExtra("subjectId", selectedSubject.getId());
                startActivity(intent);
            }
        });




        recyclerView.setAdapter(adapter);
        GridLayoutManager manager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);

    }



}