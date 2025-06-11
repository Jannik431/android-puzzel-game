package com.example.puzzle_app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Gravity;
import android.widget.LinearLayout;

import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    private GameBoard spielfeld;
    private Form form;
    private GameBoardPrinter gameBoardPrinter;


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

        gameBoardPrinter = new GameBoardPrinter();
        GameBoard gameBoardPrinted = new GameBoard(10,10);
        FormFactory formFactory = new FormFactory();
        gameBoardPrinter.printGameBoard(gameBoardPrinted);
        System.out.println("\n");
        System.out.println("-- Platziere L an Koordinate 1,1 --");
        gameBoardPrinter.placeForm(gameBoardPrinted,form,1,1);
        System.out.println("\n");
        System.out.println("-- Platziere L an Koordinate 1,1 (schon belegt) --");
        gameBoardPrinter.placeForm(gameBoardPrinted,form,1,1);
        System.out.println("\n");
        System.out.println("-- Platziere L an Koordinate 0,3 (funktioniert) -- ");
        gameBoardPrinter.placeForm(gameBoardPrinted,form,0,3);
        System.out.println("\n");
        System.out.println("-- Platziere L an Koordinate 8,8 (teilweise außerhalb des Spielfelds) -- ");
        gameBoardPrinter.placeForm(gameBoardPrinted,form,8,8);
        System.out.println("\n");
        System.out.println("-- Platziere L an Koordinate 20,20 (komplett außerhalb des Spielfelds) -- ");
        gameBoardPrinter.placeForm(gameBoardPrinted,form,20,20);
        System.out.println("\n");
        System.out.println("I einfügen");
        this.form = FormFactory.getIForm();
        gameBoardPrinter.placeForm(gameBoardPrinted,form,0,4);
        System.out.println("\n");
        System.out.println("-- Alle L entfernen");
        gameBoardPrinter.removeForm(gameBoardPrinted,2);

//        Scanner scan = new Scanner(System.in);
//        String eingabe = "";
//        int reihe;
//        int spalte;
//        int id;
//        System.out.println("Willkommen zum Puzzle Spiel");
//        System.out.println("Drücke e um das spiel zu verlassen");
//
//        while(!eingabe.equalsIgnoreCase("E")){
//            System.out.println("E - Exit P - Place R - Remove");
//            System.out.print("Mache eine Eingabe > ");
//            eingabe = scan.next();
//            if(eingabe.equalsIgnoreCase("E")){
//                break;
//            } else if (eingabe.equalsIgnoreCase("P")){
//                System.out.println("Wähle die Koordinaten für deine Form (das wird natürlich später durch klicken ersetzt)");
//                System.out.print("Zeile >");
//                reihe = scan.nextInt();
//                System.out.println("Spalte >");
//                spalte = scan.nextInt();
//                gameBoardPrinter.placeForm(gameBoardPrinted,form, reihe, spalte);
//            } else if (eingabe.equalsIgnoreCase("R")) {
//                System.out.println("Wähle die ID der Form, die du entfernen möchtest");
//                System.out.print("ID >");
//                id = scan.nextInt();
//                gameBoardPrinter.removeForm(gameBoardPrinted, id);
//            }
//
//        }
//        new Thread(new ConsoleGameLoop(gameBoardPrinted, form, gameBoardPrinter).start();
        new Thread(new ConsoleGameLoop(gameBoardPrinted, form, gameBoardPrinter)).start();

    }

}