package com.PGgames.a2048;

import androidx.annotation.NonNull;

public class Keys {
    public static final String HIGH_SCORE_KEY = "HIGH_SCORE";
    public static final String SWITCH_1_KEY = "SWITCH_1_KEY";
    public static final String SWITCH_2_KEY = "SWITCH_2_KEY";
    public static final String SAVE_KEY = "SAVE_KEY";
    public static final String PREFERENCE_FILE_KEY = "com.PGgames.a2048.pref";
    public static final String DEFLATE_BOARD_STATE = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
    public static final String USER_ID_KEY = "USER_ID_KEY";
}

class User implements Comparable<User> {
    public String id;
    public int record;

    public User(String id, int record) {
        this.id = id;
        this.record = record;
    }

    @NonNull
    @Override
    public String toString() {
        return id + ": " + record;
    }

    @Override
    public int compareTo(User other) {
        return other.record - record;
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object other) {
        User other_u = (User) other;
        return id.equals(other_u.id);
    }
}
