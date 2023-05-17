package com.ahmmedalmzini783.progectfinish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CustomRecyclerAdapterSupject adapter;
    LinearLayout myLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                Animation popOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop_out);
                myLayout.startAnimation(popOut);
                myLayout.setVisibility(View.GONE);
                RippleDrawable rippleDrawable = (RippleDrawable) myImageButton.getBackground();
                Animation rotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
                myImageButton.startAnimation(rotation);
            }
        });

        RecyclerView recyclerView=findViewById(R.id.recvSupject);
        ArrayList<Subject> data =new ArrayList<>();
        data.add(new Subject("جافا"));
        data.add(new Subject("قواعد بيانات"));
        data.add(new Subject("هندسة برمجبات"));
        data.add(new Subject("تطبيقات برمجية"));
        data.add(new Subject("تطوير أندرويد "));
        data.add(new Subject("ورش"));
        data.add(new Subject("جافا"));

        adapter = new CustomRecyclerAdapterSupject(this, data, new CustomRecyclerAdapterSupject.onItemClickListener() {
            @Override
            public void onItemClick(Subject subject, int adapterPosition) {

            }
        });
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
    }
}