package com.example.myapplication5;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private EditText editTextNumberOfPlayers;
    private EditText editTextSkipTurns;
    private EditText editTextReloads;
    private Button buttonStartGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        editTextNumberOfPlayers = findViewById(R.id.editTextNumberOfPlayers);
        editTextSkipTurns = findViewById(R.id.editTextSkipTurns);
        editTextReloads = findViewById(R.id.editTextReloads);
        buttonStartGame = findViewById(R.id.buttonStartGame);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOfPlayers = Integer.parseInt(editTextNumberOfPlayers.getText().toString());
                int skipTurns = Integer.parseInt(editTextSkipTurns.getText().toString());
                int reloads = Integer.parseInt(editTextReloads.getText().toString());
                GameSettings settings = new GameSettings(numberOfPlayers, skipTurns, reloads);
                Intent intent = new Intent(SettingsActivity.this, GameActivity.class);
                intent.putExtra("gameSettings", settings);
                startActivity(intent);
            }
        });
    }
}
