package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.io.IOException;

public class MainMenuActivity extends AppCompatActivity {
    protected ImageView logo;
    public Bitmap main_menu;

    @SuppressLint("ObsoleteSdkInt")
    protected void hide_navigation() {
        View v = this.getWindow().getDecorView();
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            Window window = this.getWindow();
            WindowCompat.setDecorFitsSystemWindows(window, false);
            WindowInsetsControllerCompat controllerCompat = new WindowInsetsControllerCompat(window, v);
            controllerCompat.hide(WindowInsetsCompat.Type.navigationBars());
            controllerCompat.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        hide_navigation();
        logo = findViewById(R.id.menu_logo);
        try {
            main_menu = BitmapFactory.decodeStream(this.getAssets().open("main_menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logo.setImageBitmap(main_menu);

    }

    public void btn_new_game_click(View view) {
        Intent g = new Intent(this, GameActivity.class);
        startActivity(g);
    }
}