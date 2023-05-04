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
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    protected Switch switch_2;
    protected boolean switch_1_state;
    protected boolean switch_2_state;
    protected SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        switch_1 = findViewById(R.id.switch_1);
        switch_2 = findViewById(R.id.switch_2);
        sharedPref = getSharedPreferences(Keys.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        switch_1_state = sharedPref.getBoolean(Keys.SWITCH_1_KEY, false);
        switch_2_state = sharedPref.getBoolean(Keys.SWITCH_2_KEY, false);
        switch_1.setChecked(switch_1_state);
        switch_2.setChecked(switch_2_state);
    }

    public void switch_1_click(View view) {
        switch_1_state = switch_1.isChecked();
    }
    public void switch_2_click(View view) {
        switch_2_state = switch_2.isChecked();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(Keys.SWITCH_1_KEY, switch_1_state);
        editor.putBoolean(Keys.SWITCH_2_KEY, switch_2_state);
        editor.apply();
    }
}