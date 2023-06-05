package com.wowmirroringrecordingapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wowmirroringrecordingapp.R;

import java.util.Objects;

public class LoginPage extends AppCompatActivity {
    private AppCompatButton login_btn_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Objects.requireNonNull(getSupportActionBar()).hide();

        login_btn_two = (AppCompatButton) findViewById(R.id.login_btn_two);
        login_btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, FaceLogin.class);
                startActivity(intent);
            }
        });

    }
}