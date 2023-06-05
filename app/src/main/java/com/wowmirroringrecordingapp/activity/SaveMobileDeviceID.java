package com.wowmirroringrecordingapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wowmirroringrecordingapp.R;

import java.util.Objects;

public class SaveMobileDeviceID extends AppCompatActivity {

    private AppCompatButton device_btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_mobile_device_id);
        Objects.requireNonNull(getSupportActionBar()).hide();


        device_btn_save = (AppCompatButton) findViewById(R.id.device_btn_save);
        device_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveMobileDeviceID.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
}