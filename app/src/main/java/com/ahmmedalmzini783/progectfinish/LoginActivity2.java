package com.ahmmedalmzini783.progectfinish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity2 extends AppCompatActivity {
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);




        SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();


        TextView createAccountTextView = findViewById(R.id.create_account);
        String text = "هل تريد ";
        String clickableText = "إنشاء حساب";
        SpannableString ss = new SpannableString(text + clickableText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(LoginActivity2.this, RegisterActivity2.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, text.length(), text.length() + clickableText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        createAccountTextView.setText(ss);
        createAccountTextView.setMovementMethod(LinkMovementMethod.getInstance());
        createAccountTextView.setHighlightColor(Color.TRANSPARENT);


        CheckBox remember=findViewById(R.id.cb_remember);






        mUsernameEditText = findViewById(R.id.te_name);
        mPasswordEditText = findViewById(R.id.te_password);
        Button loginButton = findViewById(R.id.btn_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String username = mUsernameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();



                boolean usernameValid = true;
                boolean passwordValid = true;
                boolean policyAccepted = true;


                if (username.length() == 0) {
                    mUsernameEditText.setError("Empty name");
                    usernameValid = false;
                } else if (username.length() <= 3) {
                    mUsernameEditText.setError("name should be more than 3 characters");
                    usernameValid = false;
                }

                if (password.length() == 0) {
                    mPasswordEditText.setError("Empty password");
                    passwordValid = false;
                }
                boolean lsterr = false;
                boolean number = false;

                for (int i = 0; i < password.length(); i++) {
                    char c = password.charAt(i);
                    if (Character.isLetter(c)) {
                        lsterr = true;
                    } else if (Character.isDigit(c)) {
                        number = true;
                    }
                }

                if (lsterr && number) {
                } else {
                    mPasswordEditText.setError("يجب أن يحتوي النص على أحرف وأرقام");
                    passwordValid = false;

                }
                if (remember.isChecked()) {
                    remember.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    policyAccepted=true;
                }else {
                    remember.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    policyAccepted = false;


                }

                CheckBox remember = findViewById(R.id.cb_remember);
                SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                if (remember.isChecked()) {
                    policyAccepted = true;
                    editor.putBoolean("policy_accepted", true);
                    editor.putString("username", username);
                    editor.putString("password", password);
                } else {
                    policyAccepted = false;
                    editor.putBoolean("policy_accepted", false);
                }

                editor.apply();


                if ( passwordValid && usernameValid) {
                    DpHelper dbHelper = new DpHelper(getApplicationContext());
                    boolean isMatched = dbHelper.login(username, password);

                    if (isMatched) {
                        String[] userData = dbHelper.loginData(username, password);
                        String email = userData[0];
                        String name = userData[1];

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("email", email);
                        intent.putExtra("name", name);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }

                }  else {
                    Toast.makeText(LoginActivity2.this, "يرجى التأكد من القيم المدخلة", Toast.LENGTH_SHORT).show();

                }



            }
        });



    }
}