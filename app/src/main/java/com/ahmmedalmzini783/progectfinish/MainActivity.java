package com.ahmmedalmzini783.progectfinish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmmedalmzini783.progectfinish.adoapter.CustomRecyclerAdapterSupject;
import com.ahmmedalmzini783.progectfinish.classt.Subject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CustomRecyclerAdapterSupject adapter;
    LinearLayout myLayout;
    DpHelper dpHelper;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tv_name_home=findViewById(R.id.tv_name_home);
        TextView tv_email_home=findViewById(R.id.tv_email_home);

        String email = getIntent().getStringExtra("email"); // استرداد البريد الإلكتروني
        String name = getIntent().getStringExtra("name"); // استرداد اسم المستخدم

        tv_name_home.setText(email);
        tv_email_home.setText(name);





         recyclerView=findViewById(R.id.recvSupject);
        ArrayList<Subject> data =new ArrayList<>();



        recyclerView.setAdapter(adapter);
        GridLayoutManager manager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);























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
        dpHelper=new DpHelper(this);
        ArrayList<Subject> data=dpHelper.getAllDataSubject();
        adapter=new CustomRecyclerAdapterSupject(this, data, new CustomRecyclerAdapterSupject.onItemClickListener() {
            @Override
            public void onItemDelete(int id, int position) {
                if (dpHelper.deleteSubject(String.valueOf(id))){
                    data.remove(position);
                    adapter.notifyItemRemoved(position);
                }
            }
        }) {



        };
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);

    }
}