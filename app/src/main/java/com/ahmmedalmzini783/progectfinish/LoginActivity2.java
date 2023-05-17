package com.ahmmedalmzini783.progectfinish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class LoginActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        TextView createAccountTextView = findViewById(R.id.create_account);
        String text = "هل تريد ";
        String clickableText = "إنشاء حساب";
        SpannableString ss = new SpannableString(text + clickableText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // نقل المستخدم إلى الصفحة الجديدة
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
    }
}