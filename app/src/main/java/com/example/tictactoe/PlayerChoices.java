package com.example.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;

public class PlayerChoices {
    private static final String PREFS_NAME = "TicTacToePrefs";
    private static final String PLAYER1_CHOICE = "Player1Choice";
    private static final String PLAYER2_CHOICE = "Player2Choice";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public PlayerChoices(Context context, int choice){
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(choice == 0){
            setPlayerChoice("X", "O");
        }
        else if(choice == 1){
            setPlayerChoice("O", "X");
        }

    }

    private void setPlayerChoice(String player1choice, String player2choice){
        editor.putString(PLAYER1_CHOICE, player1choice);
        editor.putString(PLAYER2_CHOICE, player2choice);
        editor.apply();
    }

    // Method to get player 1 choice
    public String getPlayer1Choice() {
        return sharedPreferences.getString(PLAYER1_CHOICE, "");
    }

    // Method to get player 2 choice
    public String getPlayer2Choice() {
        return sharedPreferences.getString(PLAYER2_CHOICE, "");
    }
}
