package com.ahmmedalmzini783.progectfinish.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ahmmedalmzini783.progectfinish.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView choicesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        choicesTextView = findViewById(R.id.choicesTextView);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            List<Integer> choices = extras.getIntegerArrayList("choices");
            String choicesText = "Selected Choices: ";
            for (int i = 0; i < choices.size(); i++) {
                choicesText += choices.get(i) + " ";
            }
            choicesTextView.setText(choicesText);
        }
    }
}