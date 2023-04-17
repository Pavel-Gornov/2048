package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void btn_new_game_click(View view) {
        Intent g = new Intent(this, GameActivity.class);
        startActivity(g);
    }

    public void btn_continue_click(View view) {
    }

    public void btn_settings_click(View view) {
    }
}