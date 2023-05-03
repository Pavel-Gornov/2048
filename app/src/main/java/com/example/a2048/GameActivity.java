package com.example.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    protected SharedPreferences sharedPref;

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
    protected ImageView img_no_btn;
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

    public final float sensitivity = 100;
    protected boolean is_2048;

    protected GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            if ((e1.getX() - e2.getX()) > sensitivity) {
                left();
            } else if ((e2.getX() - e1.getX()) > sensitivity) {
                right();
            } else if ((e1.getY() - e2.getY()) > sensitivity) {
                up();
            } else if ((e2.getY() - e1.getY()) > sensitivity) {
                down();
            }
            return true;
        }
    };

    protected GestureDetector gestureDetector = new GestureDetector(getBaseContext(), simpleOnGestureListener);

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game);
        sharedPref = getSharedPreferences(Keys.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        boolean is_continue = getIntent().getBooleanExtra(Keys.SAVE_KEY, false);
        if (is_continue) {
            TileMap = new Board(sharedPref.getString(Keys.SAVE_KEY, Keys.DEFLATE_BOARD_STATE));
            for (int i = 0; i < 4; i++) {
                if (TileMap.map.get(i).contains(2048)) {
                    is_2048 = true;
                    break;
                }
            }
        } else {
            TileMap = new Board();
            is_2048 = false;
        }
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
        img_no_btn = findViewById(R.id.img_no_btn);

        if (sharedPref.getBoolean(Keys.SWITCH_1_KEY, false)) {
            btn_up.setVisibility(View.GONE);
            btn_down.setVisibility(View.GONE);
            btn_right.setVisibility(View.GONE);
            btn_left.setVisibility(View.GONE);
            img_no_btn.setVisibility(View.VISIBLE);
        }

        tv_score = findViewById(R.id.score);
        score_text = getResources().getString(R.string.score);
        load_textures();
        update();
    }

    public void load_textures() {
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

    public void up() {
        TileMap.transpose();
        TileMap.cover_up();
        TileMap.merge();
        TileMap.cover_up();
        TileMap.transpose();
        check_lose_or_win();
        TileMap.add_tile();
        update();
    }

    public void down() {
        TileMap.transpose();
        TileMap.reverse();
        TileMap.cover_up();
        TileMap.merge();
        TileMap.cover_up();
        TileMap.reverse();
        TileMap.transpose();
        check_lose_or_win();
        TileMap.add_tile();
        update();
    }

    public void left() {
        TileMap.cover_up();
        TileMap.merge();
        TileMap.cover_up();
        check_lose_or_win();
        TileMap.add_tile();
        update();
    }

    public void right() {
        TileMap.reverse();
        TileMap.cover_up();
        TileMap.merge();
        TileMap.cover_up();
        TileMap.reverse();
        check_lose_or_win();
        TileMap.add_tile();
        update();
    }

    public void btn_up_click(View view) {
        up();
    }

    public void btn_left_click(View view) {
        left();
    }

    public void btn_right_click(View view) {
        right();
    }

    public void btn_down_click(View view) {
        down();
    }

    public void check_lose_or_win(){
        boolean a = true;
        boolean b = true;
        boolean c = true;
        boolean d = true;
        if (!is_2048) {
            for (int i = 0; i < 4; i++) {
                if (TileMap.map.get(i).contains(2048)) {
                    is_2048 = true;
                    createOn2048Dialog(this);
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            if (TileMap.map.get(i).contains(0)) {
                a = false;
                break;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (TileMap.map.get(i).get(j).equals(TileMap.map.get(i + 1).get(j)) || TileMap.map.get(i).get(j).equals(TileMap.map.get(i).get(j + 1))){
                    b = false;
                    break;
                }
            }
            if (!b)
                break;
        }
        for (int i = 0; i < 3; i++){
            if (TileMap.map.get(3).get(i).equals(TileMap.map.get(3).get(i + 1))){
                c = false;
                break;
            }
        }
        for (int i = 0; i < 3; i++){
            if (TileMap.map.get(i).get(3).equals(TileMap.map.get(i + 1).get(3))){
                d = false;
                break;
            }
        }
        if (a & b & c & d){
            createFinalDialog(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Keys.SAVE_KEY, TileMap.toSaveString());
        editor.apply();
    }
    public void createOn2048Dialog(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("2048!")
                .setMessage("Цель в 2048 достигнута. Можете завершить игру или продолжать.")
                .setPositiveButton("Продолжить", (dialog, id) -> Toast.makeText(activity,"Продолжаем!",Toast.LENGTH_SHORT).show())
                .setNegativeButton("Завершить", (dialog, id) -> startActivity(new Intent(this, MainMenuActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)));
        builder.create().show();
    }

    public void createFinalDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Игра окончена!")
                .setMessage("Игра окончена, ходов не осталось.")
                .setNeutralButton( "Главное меню",(dialog, id) -> startActivity(new Intent(this, MainMenuActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)))
                .setPositiveButton("Продолжить", (dialog, id) -> Toast.makeText(activity,"Продолжаем!",Toast.LENGTH_SHORT).show())
                .setNegativeButton("Новая игра", (dialog, id) -> {TileMap = new Board(); is_2048 = false; update();});
        builder.create().show();
    }
}