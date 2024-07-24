package com.example.tictactoe;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ttt extends AppCompatActivity {

    private String player1Choice, player2Choice;
    private String currentPlayer;
    private int[] gameState = new int[9];
    private ImageView[] blocks = new ImageView[9];
    private TextView player1TextView, player2TextView, winner1;
    private int[][] gamewin = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttt);

        // Initialize views
        player1TextView = findViewById(R.id.player1);
        player2TextView = findViewById(R.id.player2);

        blocks[0] = findViewById(R.id.block1);
        blocks[1] = findViewById(R.id.block2);
        blocks[2] = findViewById(R.id.block3);
        blocks[3] = findViewById(R.id.block4);
        blocks[4] = findViewById(R.id.block5);
        blocks[5] = findViewById(R.id.block6);
        blocks[6] = findViewById(R.id.block7);
        blocks[7] = findViewById(R.id.block8);
        blocks[8] = findViewById(R.id.block9);

        // Load player choices from SharedPreferences
        player1Choice = PreferenceUtil.getPlayer1Choice(this);
        player2Choice = PreferenceUtil.getPlayer2Choice(this);

        // Set the initial player
        currentPlayer = player1Choice;

        // Update the UI
        player1TextView.setText("Player 1");
        player2TextView.setText("Player 2");
    }

    public void playerTap(View view) {
        ImageView tappedBlock = (ImageView) view;
        int tappedTag = Integer.parseInt(tappedBlock.getTag().toString());

        // Ensure the tapped block is empty
        if (gameState[tappedTag] == 0) {
            gameState[tappedTag] = currentPlayer.equals(player1Choice) ? 1 : 2;
            tappedBlock.setImageResource(currentPlayer.equals(player1Choice) ? R.drawable.outline_cancel_24 : R.drawable.baseline_circle_24);

            // Check for a winner
            checkForWinner();

            // Switch players
            currentPlayer = currentPlayer.equals(player1Choice) ? player2Choice : player1Choice;
        }
    }

    public void resetGame(View view) {
        // Reset the game state
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 0;
            blocks[i].setImageResource(0);
        }

        // Set the initial player
        currentPlayer = player1Choice;
    }

    private void checkForWinner() {
        boolean winnerFound = false;

        // Check all winning combinations
        for (int[] winPosition : gamewin) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 0) {

                // We have a winner
                winnerFound = true;

                String winner = currentPlayer.equals(player1Choice) ? "Player 2" : "Player 1";
                String winnerSymbol = currentPlayer.equals(player1Choice) ? player2Choice : player1Choice;

                // Display winner
                if (winnerSymbol.equals(player1Choice)) {
                    player1TextView.setText("Winner: Player 1 (" + player1Choice + ")");
                } else {
                    player2TextView.setText("Winner: Player 2 (" + player2Choice + ")");
                }

                // Disable further taps
                disableAllBlocks();
                break;
            }
        }

        if (!winnerFound) {
            // Check for a draw
            boolean draw = true;
            for (int state : gameState) {
                if (state == 0) {
                    draw = false;
                    break;
                }
            }

            if (draw) {
                player1TextView.setText("Draw");
                player2TextView.setText("Draw");
            }
        }
    }

    private void disableAllBlocks() {
        for (ImageView block : blocks) {
            block.setEnabled(false);
        }
    }

}
