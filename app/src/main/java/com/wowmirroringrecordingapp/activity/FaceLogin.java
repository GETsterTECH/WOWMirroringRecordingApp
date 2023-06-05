package com.wowmirroringrecordingapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wowmirroringrecordingapp.R;

import java.util.Objects;

public class FaceLogin extends AppCompatActivity {

    private AppCompatButton face_login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        face_login_btn = (AppCompatButton) findViewById(R.id.face_login_btn);
        face_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FaceLogin.this, SaveMobileDeviceID.class);
                startActivity(intent);
            }
        });
    }
}