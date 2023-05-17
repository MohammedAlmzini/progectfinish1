package com.ahmmedalmzini783.progectfinish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MonthActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        // Initialize the ListView
        listView = findViewById(R.id.list_item_all_month);

        // Create an array of month names
        String[] months = {"يناير", "فبراير", "مارس", "أبريل", "مايو", "يونيو", "يوليو", "أغسطس", "سبتمبر", "أكتوبر", "نوفمبر", "ديسمبر"};

        // Create an ArrayAdapter and set it to the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_month, R.id.tv_month, months);
        listView.setAdapter(adapter);
    }
}