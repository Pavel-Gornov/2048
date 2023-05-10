package com.PGgames.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.PGgames.a2048.databinding.MainMenuBinding;

public class MainMenuActivity extends AppCompatActivity {
    private MainMenuBinding binding;
    protected Intent g;
    protected SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPref = getSharedPreferences(Keys.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        int high_score = sharedPref.getInt(Keys.HIGH_SCORE_KEY, 0);
        binding.highScoreTxt.setText(getResources().getString(R.string.your_high_score) + " " + high_score);
    }

    public void btn_new_game_click(View view) {
        g = new Intent(this, GameActivity.class);
        startActivity(g);
    }

    public void btn_continue_click(View view) {
        g = new Intent(this, GameActivity.class);
        g.putExtra(Keys.SAVE_KEY, true);
        startActivity(g);
    }

    public void btn_settings_click(View view) {
        g = new Intent(this, SettingsActivity.class);
        startActivity(g);
    }
}