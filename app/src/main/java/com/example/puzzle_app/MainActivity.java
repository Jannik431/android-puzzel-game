package com.example.puzzle_app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Gravity;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    private GameBoard spielfeld;
    private Form form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Spielfeld und Beispiel-Form erzeugen
        this.spielfeld = new GameBoard(10,10);
        this.form = FormFactory.getGrossesL();

        // GameView initialiseren
        this.gameView = new GameView(this, this.spielfeld, this.form);

        // LinearLayout als Container für die GameView
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(20,20,20,20);

        // Layout-Parameter für GameView
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, // Breite nach Inhalt
                LinearLayout.LayoutParams.WRAP_CONTENT); // Höhe nach Inhalt
        params.gravity = Gravity.CENTER; // GameView mittig im Layout positionieren

        // GameView dem Layout hinzufügen mit den Parametern
        layout.addView(gameView, params);

        // Layout als Haupt-View der Activity setzen
        setContentView(layout);
    }
}