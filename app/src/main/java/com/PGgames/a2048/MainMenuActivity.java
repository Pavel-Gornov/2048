package com.PGgames.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.PGgames.a2048.databinding.MainMenuBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Scanner;

public class MainMenuActivity extends AppCompatActivity {
    private static final String TAG = "MainMenuActivity";
    private MainMenuBinding binding;
    protected Intent g;
    protected SharedPreferences sharedPref;
    protected String user_id;
    protected int high_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPref = getSharedPreferences(Keys.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        user_id = get_user_id();
        high_score = sharedPref.getInt(Keys.HIGH_SCORE_KEY, 0);
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
        myRef.child(user_id).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e(TAG, "Error getting data", task.getException());
            } else {
                com.google.firebase.database.DataSnapshot res = task.getResult();
                if (res.getValue() != null) {
                    String record_string = String.valueOf(res.child("record").getValue());
                    Scanner in = new Scanner(record_string);
                    int record = in.nextInt();
                    int high_score = sharedPref.getInt(Keys.HIGH_SCORE_KEY, 0);
                    if (record > high_score) {
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt(Keys.HIGH_SCORE_KEY, record);
                        editor.apply();
                    }
                }
                else {
                    update_db();
                }
                Log.d(TAG, String.valueOf(res));
                update_ui();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        update_ui();

    }

    @Override
    protected void onStop() {
        super.onStop();
        update_db();
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

    public String generate_hardware_id() {
        return "000" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 + Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 + Build.USER.length() % 10;
    }

    public String get_user_id() {
        String id = sharedPref.getString(Keys.USER_ID_KEY, null);
        if (id == null) {
            id = generate_hardware_id();
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(Keys.USER_ID_KEY, id);
            editor.apply();
        }
        return id;
    }

    public void update_db() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
        myRef.child(user_id).child("record").setValue(high_score);
        myRef.push();
    }
    @SuppressLint("SetTextI18n")
    public void update_ui(){
        high_score = sharedPref.getInt(Keys.HIGH_SCORE_KEY, 0);
        binding.highScoreTxt.setText(getResources().getString(R.string.your_high_score) + " " + high_score);
    }
}