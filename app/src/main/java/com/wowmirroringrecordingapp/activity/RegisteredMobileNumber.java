package com.wowmirroringrecordingapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wowmirroringrecordingapp.R;

import java.util.Objects;

public class RegisteredMobileNumber extends AppCompatActivity {
    AppCompatButton login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_mobile_number);
        Objects.requireNonNull(getSupportActionBar()).hide();

        login_btn = (AppCompatButton) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisteredMobileNumber.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }
}