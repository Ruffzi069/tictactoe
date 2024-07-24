package com.example.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    private Button button1, button2;
    private TextView playerChoiceTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        button1 = findViewById(R.id.bt1);
        button2 = findViewById(R.id.bt2);
        playerChoiceTextView = findViewById(R.id.playerChoiceTextView);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerChoices playerchoice = new PlayerChoices(Home.this, 0);
                UpdatePlayerChoice(playerchoice);
                Navigatettt();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerChoices playerchoice = new PlayerChoices(Home.this, 1);
                UpdatePlayerChoice(playerchoice);
                Navigatettt();
            }
        });
        PlayerChoices playerchoice = new PlayerChoices(Home.this, 2);
        UpdatePlayerChoice(playerchoice);
    }

    private void UpdatePlayerChoice(PlayerChoices playerc){
        String player1c = playerc.getPlayer1Choice();
        String player2c = playerc.getPlayer2Choice();
        Toast.makeText(this, "Player 1 : " + player1c + "\nPlayer 2 : " + player2c, Toast.LENGTH_SHORT).show();
    }

    private void Navigatettt(){
        Intent in = new Intent(Home.this, ttt.class);
        startActivity(in);
    }
}
