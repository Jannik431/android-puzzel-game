package com.example.puzzle_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    private GameBoard spielfeld;
    private Form form;
    private GameBoardPrinter gameBoardPrinter;
    private FormPaletteView paletteView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("highscore", MODE_PRIVATE);
        long bestTime = prefs.getLong("level1_time", Long.MAX_VALUE);


        // Einfaches vertikales Layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(50,50,50,50);
        layout.setBackgroundResource(R.drawable.wood_background);


        // Highscore TextView ganz oben einfügen
        TextView highscoreText = new TextView(this);
        if (bestTime != Long.MAX_VALUE) {
            highscoreText.setText("Highscore: " + (bestTime / 1000) + " Sekunden");
        } else {
            highscoreText.setText("Noch kein Highscore");
        }
//        highscoreText.setText("Highscore: 120"); // Beispielwert
        highscoreText.setTextSize(22);
        highscoreText.setTextColor(Color.WHITE);
        highscoreText.setPadding(0, 0, 0, 50);
        highscoreText.setGravity(Gravity.CENTER);
        layout.addView(highscoreText);

        // Button für unterschiedliche Level erstellen
        // Lambda-Funktion: kompakt Klick-Listener schreiben
        Button level1 = new Button(this);
        level1.setText("Level 1");
        level1.setOnClickListener(v -> {
            Intent intent = new Intent(this,Level1Activity.class);
            startActivity(intent);
        });
        level1.setTextSize(18);
        level1.setPadding(30,20,30,20);


        Button level2 = new Button(this);
        level2.setText("Level 2");
        level2.setOnClickListener(v -> {
            Intent intent = new Intent(this,Level2Activity.class);
            startActivity(intent);
        });
        level2.setTextSize(18);
        level2.setPadding(30,20,30,20);

        Button level3 = new Button(this);
        level3.setText("Level 3");
        level3.setOnClickListener(v -> {
            Intent intent = new Intent(this,Level3Activity.class);
            startActivity(intent);
        });
        level3.setTextSize(18);
        level3.setPadding(30,20,30,20);

        layout.addView(level1);
        layout.addView(level2);
        layout.addView(level3);

        setContentView(layout);

//        // Spielfeld vorbereiten
//        this.spielfeld = new GameBoard(10,10);
//
//        // GameView zeigt nur das Spielfeld (aktuelle Form = null)
//        this.gameView = new GameView(this, this.spielfeld, null);
//
//        // Liste von Formen für die Miniaturanzeige
//        List<Form> formenListe = new ArrayList<>();
//        formenListe.add(FormFactory.getGrossesL());
//        formenListe.add(FormFactory.getGrossesRechteck());
//        formenListe.add(FormFactory.getTForm());
//        formenListe.add(FormFactory.getFalschesL());
//        formenListe.add(FormFactory.getGrossesT());
//        formenListe.add(FormFactory.getIForm());
//
//        // FormPalette zeigt diesen Formen
//        this.paletteView = new FormPaletteView (this, formenListe);
//        // Neu -> GameView bekommt die Referenz auf die Palette
//        this.gameView.setFormPaletteView(this.paletteView);
//
//        // Hauptlayout vertikal ausrichten
//        LinearLayout hauptLayout = new LinearLayout(this);
//        hauptLayout.setOrientation(LinearLayout.VERTICAL);
//        hauptLayout.setGravity(Gravity.CENTER);
//        hauptLayout.setPadding(20,20,20,20);
//
//        // GameView zum Layout hinzufügen
//        LinearLayout.LayoutParams gameParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        hauptLayout.addView(gameView, gameParams);
//
//        // PaletteView darunter einfügen
//        LinearLayout.LayoutParams paletteParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        paletteParams.topMargin = 50;
//        hauptLayout.addView(paletteView, paletteParams);
//
//        // Layout setzen
//        setContentView(hauptLayout);




//        new Thread(new ConsoleGameLoop(gameBoardPrinted, form, gameBoardPrinter).start();
//        new Thread(new ConsoleGameLoop(gameBoardPrinted, form, gameBoardPrinter)).start();

    }

}