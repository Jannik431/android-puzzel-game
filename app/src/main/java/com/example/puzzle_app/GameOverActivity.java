package com.example.puzzle_app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Einfaches Layout mit Text "Game Over"
        TextView text = new TextView(this);
        text.setText("Game Over!");
        text.setTextSize(32);
        text.setTypeface(Typeface.MONOSPACE);
        text.setTextColor(Color.parseColor("#FF5722"));
        text.setGravity(Gravity.CENTER);

        // Button zurück
        Button zurueck = new Button(this);
        zurueck.setText("Zurück");
        zurueck.setOnClickListener(v -> {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(20,20,20,20);

        layout.addView(text);
        layout.addView(zurueck);

        setContentView(layout);
    }
}
