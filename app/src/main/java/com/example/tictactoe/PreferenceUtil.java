package com.example.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {

    public static final String PREFS_NAME = "TicTacToePrefs";

    public static void savePlayerChoice(Context context, String player1Choice, String player2Choice) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Player1Choice", player1Choice);
        editor.putString("Player2Choice", player2Choice);
        editor.apply();
    }

    public static String getPlayer1Choice(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("Player1Choice", "X");
    }

    public static String getPlayer2Choice(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("Player2Choice", "O");
    }
}
