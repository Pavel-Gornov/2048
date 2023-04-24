package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    protected Switch switch_1;
    protected boolean switch_state;
    protected SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        switch_1 = findViewById(R.id.switch_1);
        sharedPref = getSharedPreferences(Keys.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        switch_state = sharedPref.getBoolean(Keys.SWITCH_1_KEY, false);
        switch_1.setChecked(switch_state);
    }

    public void switch_1_click(View view) {
        switch_state = switch_1.isChecked();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(Keys.SWITCH_1_KEY, switch_state);
        editor.apply();
    }
}