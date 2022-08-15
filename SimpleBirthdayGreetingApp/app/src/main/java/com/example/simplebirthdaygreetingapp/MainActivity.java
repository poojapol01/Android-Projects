package com.example.simplebirthdaygreetingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText eText;
    Button birthdayGreetingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eText = (EditText)findViewById(R.id.nameInput);
        birthdayGreetingBtn = (Button)findViewById(R.id.createBirthdayCardButton);

        birthdayGreetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String str = eText.getText().toString();
//                Toast.makeText(getBaseContext(), "Button is clicked and Name is: " +str, Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(MainActivity.this, BirthdayGreetingActivity.class);
                  startActivity(intent);
            }
        });
    }
}