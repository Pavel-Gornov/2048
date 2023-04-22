package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    protected Switch switch_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        switch_1 = findViewById(R.id.switch_1);
    }

    public void switch_1_click(View view) {
        System.out.println(switch_1.isChecked());
        System.out.println("Что-то произошло!!!!");
    }
}