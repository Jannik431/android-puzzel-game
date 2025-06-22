package com.example.puzzle_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LevelCompletedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("LevelCompletedActivity wird ausgeführt (debug)");
        SharedPreferences prefs = getSharedPreferences("highscore", MODE_PRIVATE);
        long bestTime = prefs.getLong("level1_time", Long.MAX_VALUE);
        long timePassed = getIntent().getLongExtra("timePassed", 0);
        int dummyHighscore = 15;

        // Layout erstellen
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setBackgroundColor(Color.WHITE);
        layout.setPadding(50, 50, 50, 50);

        // Nachricht
        TextView message = new TextView(this);
        message.setText("Level Completed!");
        message.setTextSize(24);
        message.setGravity(Gravity.CENTER);
        message.setTextColor(Color.BLACK);

        // Zeit anzeigen
        TextView timeView = new TextView(this);
        int seconds = (int) (timePassed / 1000);
        timeView.setText("In nur " + seconds + " Sekunden :O");
        timeView.setTextSize(18);
        timeView.setGravity(Gravity.CENTER);
        timeView.setTextColor(Color.DKGRAY);

        // HighScore anzeigen
        TextView highscoreView = new TextView(this);
//        if(seconds<dummyHighscore)
        if(timePassed < bestTime){
            highscoreView.setText("Damit schlägst du den bisherigen Highscore von " + bestTime/100 + " Sekunden");
            prefs.edit().putLong("level1_time", timePassed).apply();

        }
        else
            highscoreView.setText("Den Highscore von " + bestTime/1000 + " Sekunden schlägst du damit aber nicht");
        highscoreView.setTextSize(18);
        highscoreView.setGravity(Gravity.CENTER);
        highscoreView.setTextColor(Color.DKGRAY);


        // Haus-Button
        Button hausButton = new Button(this);
        hausButton.setText("\uD83C\uDFE0");
        hausButton.setTextSize(30);
        hausButton.setOnClickListener(v -> {
            Intent intent = new Intent(LevelCompletedActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Hinzufügen zur Ansicht
        layout.addView(message);
        layout.addView(timeView);
        layout.addView(highscoreView);
        layout.addView(hausButton);

        setContentView(layout);
    }
}
