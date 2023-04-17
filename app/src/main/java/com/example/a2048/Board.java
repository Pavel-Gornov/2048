package com.example.a2048;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Board {
    public ArrayList<ArrayList<Integer>> map;
    public Random random = new Random();

    public Board() {
        map = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            map.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        }
    }

    public Board(ArrayList<ArrayList<Integer>> m) {
        map = m;
    }

    public void add_tile() {
        boolean is_full = true;
        for (int i = 0; i < 4; i++) {
            if (map.get(i).contains(0)) {
                is_full = false;
                break;
            }
        }
        if (!is_full) {
            int x = random.nextInt(4);
            int y = random.nextInt(4);
            int n = random.nextInt(10);
            if (n == 9)
                n = 4;
            else
                n = 2;
            while (map.get(x).get(y) != 0) {
                x = random.nextInt(4);
                y = random.nextInt(4);
            }
            map.get(x).set(y, n);
        }
    }

    public void reverse() {
        for (int i = 0; i < 4; i++) {
            Collections.reverse(map.get(i));
        }
    }

    public void transpose() {
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            temp.add(new ArrayList<>());
            for (int j = 0; j < 4; j++) {
                temp.get(i).add(map.get(j).get(i));
            }
        }
        map = temp;
    }

    public void cover_up() {
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        int count;
        int item;
        for (int i = 0; i < 4; i++) {
            temp.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        }
        for (int i = 0; i < 4; i++) {
            count = 0;
            for (int j = 0; j < 4; j++) {
                item = map.get(i).get(j);
                if (item != 0) {
                    temp.get(i).set(count, item);
                    count++;
                }
            }
        }
        map = temp;
    }

    public void merge() {
        int item;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                item = map.get(i).get(j);
                if (map.get(i).get(j + 1) == item & item != 0) {
                    map.get(i).set(j, item * 2);
                    map.get(i).set(j + 1, 0);
                }
            }
        }
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            s.append(map.get(i).toString()).append("\n");
        }
        return s.toString();
    }
}