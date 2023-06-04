package com.ahmmedalmzini783.progectfinish;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.ahmmedalmzini783.progectfinish.adapter.CustomRecyclerAdapterSupjectAllStudent;
import com.ahmmedalmzini783.progectfinish.models.Students;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllStudentsActivity extends AppCompatActivity {
    CustomRecyclerAdapterSupjectAllStudent adapter;
    DpHelper dpHelper;
    RecyclerView recyclerView;
    TextInputEditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);
        searchEditText = findViewById(R.id.te_serch_name);
        TextInputLayout searchTextInputLayout = findViewById(R.id.textInputLayout_search);

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });

        searchTextInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        recyclerView = findViewById(R.id.list_item_all_student);

        dpHelper = new DpHelper(this);

        ArrayList<Students> data = dpHelper.getAllDataStudents();
        adapter = new CustomRecyclerAdapterSupjectAllStudent(this, data, new CustomRecyclerAdapterSupjectAllStudent.onItemClickListener() {

            @Override
            public void onItemDelete(int id, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AllStudentsActivity.this);
                View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dilog_delet, null);
                builder.setView(dialogView);

                TextView textViewMessage = dialogView.findViewById(R.id.textMassege);
                TextView textViewPositive = dialogView.findViewById(R.id.textBosetiv);
                TextView textViewNegative = dialogView.findViewById(R.id.textNegativ);
                TextView textViewTitle = dialogView.findViewById(R.id.textViewTitle);
                textViewTitle.setText("حذف طالب");
                textViewMessage.setText("هل تريد تأكيد حذف الطالب؟");
                textViewPositive.setText("نعم");
                textViewNegative.setText("لا");

                AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(false);

                textViewPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dpHelper.deleteStudents(String.valueOf(id))) {
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
            public void onItemClick(Students student, int position) {
                int studentId = student.getId();

                Intent intent = new Intent(getApplicationContext(), InformationStudentActivity2.class);

                intent.putExtra("studentId", studentId);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
    }

    public void searchStudents(String view) {
        String searchText = searchEditText.getText().toString().trim();

        ArrayList<Students> searchedData = dpHelper.getAllStudentsByName(searchText);
        adapter.setData(searchedData);
        adapter.notifyDataSetChanged();
    }
    private void performSearch() {
        String searchText = searchEditText.getText().toString().trim();

        searchStudents(searchText);
    }

}
