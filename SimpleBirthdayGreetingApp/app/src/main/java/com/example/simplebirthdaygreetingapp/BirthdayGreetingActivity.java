package com.example.simplebirthdaygreetingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BirthdayGreetingActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_birthday_greeting);

        textView = findViewById(R.id.birthdayGreetingTextView);
        String receivedData = getIntent().getStringExtra("message_key");    //receive the value by getStringExtra() method and key must be same which is send by first activity
        textView.setText("Happy Birthday \n" +receivedData);
    }
}