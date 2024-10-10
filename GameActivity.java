package com.example.myapplication5;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private GameSettings gameSettings;
    private int currentPlayerIndex;
    private int[] revolver;
    private boolean[] playersAlive;
    private int[] skipCount;
    private static int totalShots = 0;
    private int currentChamber;
    private TextView textViewStatus;
    private TextView textViewEliminated;
    private Button buttonShoot;
    private Button buttonSkip;
    private Button buttonReload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewStatus = findViewById(R.id.textViewStatus);
        textViewEliminated = findViewById(R.id.textViewEliminated);
        buttonShoot = findViewById(R.id.buttonShoot);
        buttonSkip = findViewById(R.id.buttonSkip);
        buttonReload = findViewById(R.id.buttonReload);

        gameSettings = (GameSettings) getIntent().getSerializableExtra("gameSettings");

        currentPlayerIndex = 0;

        revolver = new int[6];
        playersAlive = new boolean[gameSettings.getNumberOfPlayers()];
        skipCount = new int[gameSettings.getNumberOfPlayers()];

        for (int i = 0; i < gameSettings.getNumberOfPlayers(); i++) {
            playersAlive[i] = true;
        }

        startNewRound();

        buttonShoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoot();
            }
        });

        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipTurn();
            }
        });

        buttonReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
            }
        });
    }

    private void startNewRound() {
        Random random = new Random();
        int bulletPosition = random.nextInt(6);
        for (int i = 0; i < revolver.length; i++) {
            revolver[i] = 0;
        }
        revolver[bulletPosition] = 1;
        currentChamber = 0;
        updatePlayerStatus();
        textViewEliminated.setText("");
    }

    private void updatePlayerStatus() {
        while (!playersAlive[currentPlayerIndex]) {
            currentPlayerIndex = (currentPlayerIndex + 1) % gameSettings.getNumberOfPlayers();
        }
        textViewStatus.setText("Игрок " + (currentPlayerIndex + 1) + ", ваш ход!");
    }

    private void shoot() {
        if (!playersAlive[currentPlayerIndex]) {
            textViewStatus.setText("Игрок " + (currentPlayerIndex + 1) + " уже выбыл!");
            return;
        }

        currentChamber = (currentChamber + 1) % 6;

        if (revolver[currentChamber] == 1) {
            textViewStatus.setText("БАХ! Игрок " + (currentPlayerIndex + 1) + " выбывает!");
            playersAlive[currentPlayerIndex] = false; // Игрок выбывает
            textViewEliminated.append("Игрок " + (currentPlayerIndex + 1) + " выбывает!\n");
            checkWinner();
        } else {
            textViewStatus.setText("Промах! Игрок " + (currentPlayerIndex + 1) + " остается в игре.");
            currentPlayerIndex = (currentPlayerIndex + 1) % gameSettings.getNumberOfPlayers();
            updatePlayerStatus();
        }
    }

    private void skipTurn() {
        textViewStatus.setText("Игрок " + (currentPlayerIndex + 1) + " пропустил ход.");
        currentPlayerIndex = (currentPlayerIndex + 1) % gameSettings.getNumberOfPlayers();
        updatePlayerStatus();
    }

    private void reload() {
        textViewStatus.setText("Игрок " + (currentPlayerIndex + 1) + " покрутил барабан.");
        startNewRound();
    }

    private void checkWinner() {
        int aliveCount = 0;
        int winnerIndex = -1;

        for (int i = 0; i < playersAlive.length; i++) {
            if (playersAlive[i]) {
                aliveCount++;
                winnerIndex = i;
            }
        }

        if (aliveCount == 1) {
            textViewStatus.setText("Игрок " + (winnerIndex + 1) + " победил!");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 3000);
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % gameSettings.getNumberOfPlayers();
            updatePlayerStatus();
        }
    }
}
