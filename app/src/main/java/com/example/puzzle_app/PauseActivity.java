package com.example.puzzle_app;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class PauseActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(50, 50, 50, 50);
        layout.setBackgroundResource(R.drawable.wood_background);

        Button removeAllButton = new Button(this);
        removeAllButton.setText("\uD83D\uDD04");
        removeAllButton.setTextSize(30);
        removeAllButton.setTypeface(null, Typeface.BOLD);
        removeAllButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("removeAll", true);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        Button startButton = new Button(this);
        startButton.setText("▶");
        startButton.setTextSize(30);
        startButton.setOnClickListener(v -> {
            setResult(RESULT_CANCELED); // Pause schließen, nichts ändern
            finish();
        });

        Button hausButton = new Button(this);
        hausButton.setText("\uD83C\uDFE0");
        hausButton.setTextSize(30);
        hausButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("goHome", true);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        layout.addView(startButton);
        layout.addView(removeAllButton);
        layout.addView(hausButton);

        setContentView(layout);
    }
}
