package com.ahmmedalmzini783.progectfinish;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.primer));
        }
        statusbarcolor();
        int dayNumber = (int)(System.currentTimeMillis() / (1000 * 60 * 60 * 24));
        Thread welvom2=new Thread(){
            public void run(){
                try {
                    sleep(4000);
                    SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
                    boolean policyAccepted = sharedPref.getBoolean("policy_accepted", false);

                    if (policyAccepted) {
                        Intent main=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(main);
                        finish();
                    } else {
                        Intent main = new Intent(getApplicationContext(), LoginActivity2.class);
                        startActivity(main);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }


        };

        welvom2.start();

    }
    private void statusbarcolor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.primer,this.getTheme()));
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.primer));

        }

    }
}