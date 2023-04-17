package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    protected ImageView iv1_1;
    protected ImageView iv1_2;
    protected ImageView iv1_3;
    protected ImageView iv1_4;
    protected ImageView iv2_1;
    protected ImageView iv2_2;
    protected ImageView iv2_3;
    protected ImageView iv2_4;
    protected ImageView iv3_1;
    protected ImageView iv3_2;
    protected ImageView iv3_3;
    protected ImageView iv3_4;
    protected ImageView iv4_1;
    protected ImageView iv4_2;
    protected ImageView iv4_3;
    protected ImageView iv4_4;

    protected Button btn_up;
    protected Button btn_down;
    protected Button btn_right;
    protected Button btn_left;
    protected TextView tv_score;
    protected Board TileMap;
    public Bitmap temp;
    public Bitmap n0;
    public Bitmap n2;
    public Bitmap n4;
    public Bitmap n8;
    public Bitmap n16;
    public Bitmap n32;
    public Bitmap n64;
    public Bitmap n128;
    public Bitmap n256;
    public Bitmap n512;
    public Bitmap n1024;
    public Bitmap n2048;
    public String score_text;

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
        setContentView(R.layout.main_game);
        hide_navigation();
        TileMap = new Board();
        iv1_1 = findViewById(R.id.cell_1_1);
        iv1_2 = findViewById(R.id.cell_1_2);
        iv1_3 = findViewById(R.id.cell_1_3);
        iv1_4 = findViewById(R.id.cell_1_4);
        iv2_1 = findViewById(R.id.cell_2_1);
        iv2_2 = findViewById(R.id.cell_2_2);
        iv2_3 = findViewById(R.id.cell_2_3);
        iv2_4 = findViewById(R.id.cell_2_4);
        iv3_1 = findViewById(R.id.cell_3_1);
        iv3_2 = findViewById(R.id.cell_3_2);
        iv3_3 = findViewById(R.id.cell_3_3);
        iv3_4 = findViewById(R.id.cell_3_4);
        iv4_1 = findViewById(R.id.cell_4_1);
        iv4_2 = findViewById(R.id.cell_4_2);
        iv4_3 = findViewById(R.id.cell_4_3);
        iv4_4 = findViewById(R.id.cell_4_4);

        btn_up = findViewById(R.id.btn_u);
        btn_down = findViewById(R.id.btn_d);
        btn_right = findViewById(R.id.btn_r);
        btn_left = findViewById(R.id.btn_l);

        tv_score = findViewById(R.id.score);
        score_text = getResources().getString(R.string.score);
        try {
            temp = BitmapFactory.decodeStream(this.getAssets().open("tiles/temp.png"));
            n0 = BitmapFactory.decodeStream(this.getAssets().open("tiles/0.png"));
            n2 = BitmapFactory.decodeStream(this.getAssets().open("tiles/2.png"));
            n4 = BitmapFactory.decodeStream(this.getAssets().open("tiles/4.png"));
            n8 = BitmapFactory.decodeStream(this.getAssets().open("tiles/8.png"));
            n16 = BitmapFactory.decodeStream(this.getAssets().open("tiles/16.png"));
            n32 = BitmapFactory.decodeStream(this.getAssets().open("tiles/32.png"));
            n64 = BitmapFactory.decodeStream(this.getAssets().open("tiles/64.png"));
            n128 = BitmapFactory.decodeStream(this.getAssets().open("tiles/128.png"));
            n256 = BitmapFactory.decodeStream(this.getAssets().open("tiles/256.png"));
            n512 = BitmapFactory.decodeStream(this.getAssets().open("tiles/512.png"));
            n1024 = BitmapFactory.decodeStream(this.getAssets().open("tiles/1024.png"));
            n2048 = BitmapFactory.decodeStream(this.getAssets().open("tiles/2048.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        update();
    }

    public Bitmap get_texture(int n) {
        if (n == 0) {
            return n0;
        } else if (n == 2) {
            return n2;
        } else if (n == 4) {
            return n4;
        } else if (n == 8) {
            return n8;
        } else if (n == 16) {
            return n16;
        } else if (n == 32) {
            return n32;
        } else if (n == 64) {
            return n64;
        } else if (n == 128) {
            return n128;
        } else if (n == 256) {
            return n256;
        } else if (n == 512) {
            return n512;
        } else if (n == 1024) {
            return n1024;
        } else if (n == 2048) {
            return n2048;
        } else {
            return temp;
        }
    }

    @SuppressLint("SetTextI18n")
    public void update() {
        iv1_1.setImageBitmap(get_texture(TileMap.map.get(0).get(0)));
        iv1_2.setImageBitmap(get_texture(TileMap.map.get(0).get(1)));
        iv1_3.setImageBitmap(get_texture(TileMap.map.get(0).get(2)));
        iv1_4.setImageBitmap(get_texture(TileMap.map.get(0).get(3)));
        iv2_1.setImageBitmap(get_texture(TileMap.map.get(1).get(0)));
        iv2_2.setImageBitmap(get_texture(TileMap.map.get(1).get(1)));
        iv2_3.setImageBitmap(get_texture(TileMap.map.get(1).get(2)));
        iv2_4.setImageBitmap(get_texture(TileMap.map.get(1).get(3)));
        iv3_1.setImageBitmap(get_texture(TileMap.map.get(2).get(0)));
        iv3_2.setImageBitmap(get_texture(TileMap.map.get(2).get(1)));
        iv3_3.setImageBitmap(get_texture(TileMap.map.get(2).get(2)));
        iv3_4.setImageBitmap(get_texture(TileMap.map.get(2).get(3)));
        iv4_1.setImageBitmap(get_texture(TileMap.map.get(3).get(0)));
        iv4_2.setImageBitmap(get_texture(TileMap.map.get(3).get(1)));
        iv4_3.setImageBitmap(get_texture(TileMap.map.get(3).get(2)));
        iv4_4.setImageBitmap(get_texture(TileMap.map.get(3).get(3)));
        int score = 0;
        for (ArrayList<Integer> i : TileMap.map) {
            for (int j : i) {
                score += j;
            }
        }
        tv_score.setText(score_text + ": " + score);
    }

    public void btn_up_click(View view) {
        TileMap.transpose();
        TileMap.cover_up();
        TileMap.merge();
        TileMap.cover_up();
        TileMap.transpose();
        TileMap.add_tile();
        update();
    }

    public void btn_left_click(View view) {
        TileMap.cover_up();
        TileMap.merge();
        TileMap.cover_up();
        TileMap.add_tile();
        update();
    }

    public void btn_right_click(View view) {
        TileMap.reverse();
        TileMap.cover_up();
        TileMap.merge();
        TileMap.cover_up();
        TileMap.reverse();
        TileMap.add_tile();
        update();
    }

    public void btn_down_click(View view) {
        TileMap.transpose();
        TileMap.reverse();
        TileMap.cover_up();
        TileMap.merge();
        TileMap.cover_up();
        TileMap.reverse();
        TileMap.transpose();
        TileMap.add_tile();
        update();
    }
}