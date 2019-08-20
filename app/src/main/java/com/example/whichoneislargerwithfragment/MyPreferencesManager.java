package com.example.whichoneislargerwithfragment;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class MyPreferencesManager {

    static MyPreferencesManager instance = null;
    private SharedPreferences sharedPreference;
    private SharedPreferences.Editor editor;

    public static MyPreferencesManager setInstance(Context context) {
        if (instance == null) {
            instance = new MyPreferencesManager(context);
        }
        return instance;
    }

    private MyPreferencesManager(Context context) {
        sharedPreference = context.getSharedPreferences("my_file", Context.MODE_PRIVATE);
        editor = sharedPreference.edit();
    }
    public void putPlayerListManager(PlayerRankedList playerList){
        Gson gson = new Gson();
        String playerRankedList = gson.toJson(playerList);
        editor.putString("player_list", playerRankedList);
        editor.apply();

    }
    public PlayerRankedList getPlayerListManager(){
        String playerRankedList = sharedPreference.getString("player_list", null);
        Gson gson = new Gson();
        return gson.fromJson(playerRankedList, PlayerRankedList.class);
    }
}
