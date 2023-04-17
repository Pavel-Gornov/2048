package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.io.IOException;

public class MainMenuActivity extends AppCompatActivity {
    protected ImageView logo;
    public Bitmap main_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
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