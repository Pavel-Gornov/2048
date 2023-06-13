package com.PGgames.a2048;

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
import android.widget.Toast;

import com.PGgames.a2048.databinding.MainGameBinding;

import java.io.IOException;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private MainGameBinding binding;
    protected SharedPreferences sharedPref;
    protected int score;
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
    protected boolean is_2048 = false;

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
        binding = MainGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
        }
        if (sharedPref.getBoolean(Keys.SWITCH_1_KEY, false)) {
            binding.btnU.setVisibility(View.GONE);
            binding.btnD.setVisibility(View.GONE);
            binding.btnR.setVisibility(View.GONE);
            binding.btnL.setVisibility(View.GONE);
            binding.imgNoBtn.setVisibility(View.VISIBLE);
        }
        if (sharedPref.getBoolean(Keys.SWITCH_2_KEY, false))
            load_textures("tiles_old");
        else
            load_textures("tiles");
        score_text = getString(R.string.score);
        update();
    }

    public void load_textures(String folder) {
        try {
            temp = BitmapFactory.decodeStream(this.getAssets().open(folder + "/temp.png"));
            n0 = BitmapFactory.decodeStream(this.getAssets().open(folder + "/0.png"));
            n2 = BitmapFactory.decodeStream(this.getAssets().open(folder + "/2.png"));
            n4 = BitmapFactory.decodeStream(this.getAssets().open(folder + "/4.png"));
            n8 = BitmapFactory.decodeStream(this.getAssets().open(folder + "/8.png"));
            n16 = BitmapFactory.decodeStream(this.getAssets().open(folder + "/16.png"));
            n32 = BitmapFactory.decodeStream(this.getAssets().open(folder + "/32.png"));
            n64 = BitmapFactory.decodeStream(this.getAssets().open(folder + "/64.png"));
            n128 = BitmapFactory.decodeStream(this.getAssets().open(folder + "/128.png"));
            n256 = BitmapFactory.decodeStream(this.getAssets().open(folder + "/256.png"));
            n512 = BitmapFactory.decodeStream(this.getAssets().open(folder + "/512.png"));
            n1024 = BitmapFactory.decodeStream(this.getAssets().open(folder + "/1024.png"));
            n2048 = BitmapFactory.decodeStream(this.getAssets().open(folder + "/2048.png"));
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
        binding.cell11.setImageBitmap(get_texture(TileMap.map.get(0).get(0)));
        binding.cell12.setImageBitmap(get_texture(TileMap.map.get(0).get(1)));
        binding.cell13.setImageBitmap(get_texture(TileMap.map.get(0).get(2)));
        binding.cell14.setImageBitmap(get_texture(TileMap.map.get(0).get(3)));
        binding.cell21.setImageBitmap(get_texture(TileMap.map.get(1).get(0)));
        binding.cell22.setImageBitmap(get_texture(TileMap.map.get(1).get(1)));
        binding.cell23.setImageBitmap(get_texture(TileMap.map.get(1).get(2)));
        binding.cell24.setImageBitmap(get_texture(TileMap.map.get(1).get(3)));
        binding.cell31.setImageBitmap(get_texture(TileMap.map.get(2).get(0)));
        binding.cell32.setImageBitmap(get_texture(TileMap.map.get(2).get(1)));
        binding.cell33.setImageBitmap(get_texture(TileMap.map.get(2).get(2)));
        binding.cell34.setImageBitmap(get_texture(TileMap.map.get(2).get(3)));
        binding.cell41.setImageBitmap(get_texture(TileMap.map.get(3).get(0)));
        binding.cell42.setImageBitmap(get_texture(TileMap.map.get(3).get(1)));
        binding.cell43.setImageBitmap(get_texture(TileMap.map.get(3).get(2)));
        binding.cell44.setImageBitmap(get_texture(TileMap.map.get(3).get(3)));
        score = 0;
        for (ArrayList<Integer> i : TileMap.map) {
            for (int j : i) {
                score += j;
            }
        }
        binding.score.setText(score_text + ": " + score);
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

    public void check_lose_or_win() {
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
                if (TileMap.map.get(i).get(j).equals(TileMap.map.get(i + 1).get(j)) || TileMap.map.get(i).get(j).equals(TileMap.map.get(i).get(j + 1))) {
                    b = false;
                    break;
                }
            }
            if (!b)
                break;
        }
        for (int i = 0; i < 3; i++) {
            if (TileMap.map.get(3).get(i).equals(TileMap.map.get(3).get(i + 1))) {
                c = false;
                break;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (TileMap.map.get(i).get(3).equals(TileMap.map.get(i + 1).get(3))) {
                d = false;
                break;
            }
        }
        if (a & b & c & d) {
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

    @Override
    protected void onPause() {
        super.onPause();
        save_high_score();
    }

    public void save_high_score() {
        int high_score = sharedPref.getInt(Keys.HIGH_SCORE_KEY, 0);
        if (score > high_score) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(Keys.HIGH_SCORE_KEY, score);
            editor.apply();
        }
    }

    public void createOn2048Dialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("2048!").
                setMessage(getString(R.string.on_2048_message)).
                setPositiveButton(getString(R.string.continue_btn),
                        (dialog, id) -> Toast.makeText(activity, getString(R.string.continue_toast), Toast.LENGTH_SHORT).show()).
                setNegativeButton(getString(R.string.finish),
                        (dialog, id) -> startActivity(new Intent(this, MainMenuActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)));
        builder.create().show();
    }

    public void createFinalDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(getString(R.string.game_over)).
                setMessage(getString(R.string.game_over_message)).
                setNeutralButton(getString(R.string.main_menu), (dialog, id) -> {
                    startActivity(new Intent(this, MainMenuActivity.class).
                            setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    TileMap = new Board();
                }).
                setPositiveButton(getString(R.string.continue_btn),
                        (dialog, id) -> Toast.makeText(activity, getString(R.string.continue_toast), Toast.LENGTH_SHORT).show()).
                setNegativeButton(getString(R.string.new_game), (dialog, id) -> {
                    save_high_score();
                    TileMap = new Board();
                    is_2048 = false;
                    update();
                });
        builder.create().show();
    }
}