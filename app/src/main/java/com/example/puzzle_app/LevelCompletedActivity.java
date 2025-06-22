package com.example.puzzle_app;

import android.content.Intent;
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
        layout.addView(hausButton);

        setContentView(layout);
    }
}
