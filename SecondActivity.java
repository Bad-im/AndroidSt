package com.example.myapplication55;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private String firstName;
    private String lastName;
    private String displayedText;

    private static final String NAME_VARIABLE_KEY = "NAME_VARIABLE";
    private static final String LAST_NAME_VARIABLE_KEY = "LAST_NAME_VARIABLE";
    private static final String TEXT_VIEW_TEXT_KEY = "TEXTVIEW_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textViewDisplay = findViewById(R.id.textViewDisplay);
        Button buttonGreet = findViewById(R.id.buttonGreet);
        Button buttonFarewell = findViewById(R.id.buttonFarewell);
        Button buttonReturn = findViewById(R.id.buttonReturn);

        if (savedInstanceState != null) {
            firstName = savedInstanceState.getString(NAME_VARIABLE_KEY);
            lastName = savedInstanceState.getString(LAST_NAME_VARIABLE_KEY);
            displayedText = savedInstanceState.getString(TEXT_VIEW_TEXT_KEY);
            textViewDisplay.setText(displayedText);
        } else {
            firstName = getIntent().getStringExtra("firstName");
            lastName = getIntent().getStringExtra("lastName");
            displayedText = "Имя: " + firstName + "\nФамилия: " + lastName;
            textViewDisplay.setText(displayedText);
        }

        buttonGreet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "Привет, " + firstName + " " + lastName + "!";
                returnResult(result);
            }
        });

        buttonFarewell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "До свидания, " + firstName + " " + lastName + "!";
                returnResult(result);
            }
        });

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "Данные отправлены: " + firstName + " " + lastName);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NAME_VARIABLE_KEY, firstName);
        outState.putString(LAST_NAME_VARIABLE_KEY, lastName);
        outState.putString(TEXT_VIEW_TEXT_KEY, displayedText);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        firstName = savedInstanceState.getString(NAME_VARIABLE_KEY);
        lastName = savedInstanceState.getString(LAST_NAME_VARIABLE_KEY);
        displayedText = savedInstanceState.getString(TEXT_VIEW_TEXT_KEY);
        TextView textViewDisplay = findViewById(R.id.textViewDisplay);
        textViewDisplay.setText(displayedText);
    }

    private void returnResult(String result) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", result);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
