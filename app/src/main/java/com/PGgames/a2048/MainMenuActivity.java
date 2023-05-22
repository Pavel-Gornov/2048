package com.PGgames.a2048;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import com.PGgames.a2048.databinding.MainMenuBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainMenuActivity extends AppCompatActivity {
    private static final String TAG = "MainMenuActivity";
    private MainMenuBinding binding;
    protected Intent g;
    protected SharedPreferences sharedPref;
    @SuppressLint("HardwareIds")
    private final String ANDROID_ID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPref = getSharedPreferences(Keys.PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
        myRef.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e(TAG, "Error getting data", task.getException());
            } else {
                DataSnapshot res = (DataSnapshot) task.getResult().getValue();
                Log.d(TAG, String.valueOf(res));
            }
        });
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