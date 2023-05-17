package com.ahmmedalmzini783.progectfinish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;

public class DAyesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dayes);

        RecyclerView list_item_all_dayes = findViewById(R.id.list_item_all_dayes);


        ArrayList<Presence> data = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            data.add(new Presence(i));
        }



        CustomRecyclerAdapterDayes adapter = new CustomRecyclerAdapterDayes(this, data, new CustomRecyclerAdapterDayes.onItemClickListener() {
            @Override
            public void onItemClick(Presence student, int adapterPosition) {

            }
        });
        list_item_all_dayes.setAdapter(adapter);

        //  LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        GridLayoutManager manager = new GridLayoutManager(this,3);
        list_item_all_dayes.setLayoutManager(manager);    }
}