package com.example.puzzle_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;

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

        // Einfaches vertikales Layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(50,50,50,50);

        // Button für unterschiedliche Level erstellen
        // Lambda-Funktion: kompakt Klick-Listener schreiben
        Button level1 = new Button(this);
        level1.setText("Level1");
        level1.setOnClickListener(v -> {
            Intent intent = new Intent(this,Level1Activity.class);
            startActivity(intent);
        });

        Button level2 = new Button(this);
        level2.setText("Level2");
        level2.setOnClickListener(v -> {
            Intent intent = new Intent(this,Level2Activity.class);
            startActivity(intent);
        });

        Button level3 = new Button(this);
        level3.setText("Level3");
        level3.setOnClickListener(v -> {
            Intent intent = new Intent(this,Level3Activity.class);
            startActivity(intent);
        });

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