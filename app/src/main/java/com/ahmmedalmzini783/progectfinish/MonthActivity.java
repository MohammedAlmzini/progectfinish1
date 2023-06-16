package com.ahmmedalmzini783.progectfinish;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ahmmedalmzini783.progectfinish.adapter.CustomAdapterMonth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MonthActivity extends AppCompatActivity {

    private ListView listViewMonths;
    private List<String> monthsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        listViewMonths = findViewById(R.id.list_item_all_month);
        int subjectId = getIntent().getIntExtra("subjectId", 0);

        // إنشاء قائمة بأسماء الشهور
        monthsList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 12; i++) {
            calendar.set(Calendar.MONTH, i);
            String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            monthsList.add(monthName);
        }

        CustomAdapterMonth adapter = new CustomAdapterMonth(this, (ArrayList<String>) monthsList);
        listViewMonths.setAdapter(adapter);

        listViewMonths.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedMonth = monthsList.get(position);

                // انتقال إلى صفحة الأيام وتمرير الشهر المحدد
                Intent intent = new Intent(MonthActivity.this, DAyesActivity.class);
                intent.putExtra("selectedMonth", selectedMonth);
                intent.putExtra("subjectId", subjectId);
                startActivity(intent);
            }
        });
    }
}
