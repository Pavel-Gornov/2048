package com.PGgames.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.PGgames.a2048.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {
    private ActivitySettingsBinding binding;
    protected SharedPreferences sharedPref;
    protected boolean switch_1_state;
    protected boolean switch_2_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPref = getSharedPreferences(Keys.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        switch_1_state = sharedPref.getBoolean(Keys.SWITCH_1_KEY, false);
        switch_2_state = sharedPref.getBoolean(Keys.SWITCH_2_KEY, false);
        binding.switch1.setChecked(switch_1_state);
        binding.switch2.setChecked(switch_2_state);
    }

    public void switch_1_click(View view) {
        switch_1_state = binding.switch1.isChecked();
    }

    public void switch_2_click(View view) {
        switch_2_state = binding.switch2.isChecked();
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