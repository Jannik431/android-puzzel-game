package com.example.puzzle_app;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Level3Activity extends AppCompatActivity {
    private GameView gameView;
    private GameBoard spielfeld;
    private Form form;
    private GameBoardPrinter gameBoardPrinter;
    private FormPaletteView paletteView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Spielfeld vorbereiten
        this.spielfeld = new GameBoard(10,10);

        // GameView zeigt nur das Spielfeld (aktuelle Form = null)
        this.gameView = new GameView(this, this.spielfeld, null);

        // Liste von Formen für die Miniaturanzeige
        List<Form> formenListe = new ArrayList<>();
        formenListe.add(FormFactory.getGrossesL());
        formenListe.add(FormFactory.getGrossesRechteck());
        formenListe.add(FormFactory.getTForm());
        formenListe.add(FormFactory.getFalschesL());
        formenListe.add(FormFactory.getGrossesT());
        formenListe.add(FormFactory.getIForm());

        // FormPalette zeigt diesen Formen
        this.paletteView = new FormPaletteView (this, formenListe);
        // Neu -> GameView bekommt die Referenz auf die Palette
        this.gameView.setFormPaletteView(this.paletteView);

        // Hauptlayout vertikal ausrichten
        LinearLayout hauptLayout = new LinearLayout(this);
        hauptLayout.setOrientation(LinearLayout.VERTICAL);
        hauptLayout.setGravity(Gravity.CENTER);
        hauptLayout.setPadding(20,20,20,20);

        // GameView zum Layout hinzufügen
        LinearLayout.LayoutParams gameParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        hauptLayout.addView(gameView, gameParams);

        // PaletteView darunter einfügen
        LinearLayout.LayoutParams paletteParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        paletteParams.topMargin = 50;
        hauptLayout.addView(paletteView, paletteParams);

        // Layout setzen
        setContentView(hauptLayout);
    }
}
