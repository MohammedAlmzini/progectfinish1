package com.ahmmedalmzini783.progectfinish;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmmedalmzini783.progectfinish.adapter.CustomRecyclerAdapterDayes;
import com.ahmmedalmzini783.progectfinish.models.Presence;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DAyesActivity extends AppCompatActivity {
    ArrayList<Presence> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dayes);

        int subjectId = getIntent().getIntExtra("subjectId", 0);
        String selectedMonth = getIntent().getStringExtra("selectedMonth");

        TextView textPesint=findViewById(R.id.textPesint);
        DpHelper dpHelper=new DpHelper(this);
        String persint= String.valueOf(dpHelper.getAttendanceRateByMonthAndSubject(selectedMonth,subjectId));
        textPesint.setText(persint);

        RecyclerView list_item_all_dayes = findViewById(R.id.list_item_all_dayes);

        data = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            data.add(new Presence(i));
        }


        Toast.makeText(this, selectedMonth, Toast.LENGTH_SHORT).show();

        CustomRecyclerAdapterDayes adapter = new CustomRecyclerAdapterDayes(this, data, new CustomRecyclerAdapterDayes.onItemClickListener() {
            @Override
            public void onItemClick(String selectedDay, int position) {
                Intent intent = new Intent(getApplicationContext(), StudentInSubjectActivity.class);
                intent.putExtra("selectedMonth", selectedMonth);
                intent.putExtra("subjectId", subjectId);
                intent.putExtra("selectedDay", selectedDay);
                startActivity(intent);
            }
        });

        list_item_all_dayes.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(this, 3);
        list_item_all_dayes.setLayoutManager(manager);
    }
}
