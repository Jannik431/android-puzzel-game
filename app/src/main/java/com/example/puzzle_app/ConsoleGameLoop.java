package com.example.puzzle_app;

import java.util.Scanner;

public class ConsoleGameLoop implements Runnable {
    Scanner scan = new Scanner(System.in);
    String eingabe = "";
    int reihe;
    int spalte;
    int id;
    private final GameBoard gameBoard;
    private final Form form;
    private final GameBoardPrinter printer;

    public ConsoleGameLoop(GameBoard gameBoard, Form form, GameBoardPrinter printer) {
        this.gameBoard = gameBoard;
        this.form = form;
        this.printer = printer;
    }

    public void run() {
        /*
        System.out.println("Willkommen zum Puzzle Spiel");
        System.out.println("Drücke e um das spiel zu verlassen");

        while(!eingabe.equalsIgnoreCase("E")){
            System.out.println("E - Exit P - Place R - Remove");
            System.out.print("Mache eine Eingabe > ");
            eingabe = scan.next();
            if(eingabe.equalsIgnoreCase("E")){
                break;
            } else if (eingabe.equalsIgnoreCase("P")){
                System.out.println("Wähle die Koordinaten für deine Form (das wird natürlich später durch klicken ersetzt)");
                System.out.print("Zeile >");
                reihe = scan.nextInt();
                System.out.println("Spalte >");
                spalte = scan.nextInt();
                printer.placeForm(gameBoard,form, reihe, spalte);
            } else if (eingabe.equalsIgnoreCase("R")) {
                System.out.println("Wähle die ID der Form, die du entfernen möchtest");
                System.out.print("ID >");
                id = scan.nextInt();
                printer.removeForm(gameBoard, id);
            }

        }
        */
        System.out.println("Starte automatisierten Testlauf...");
        int versuche = 0;
        while (versuche <= 7) {
            try {
                // Zufällige Form auswählen
                int formTyp = (int) (Math.random() * 3);
                Form form;
                switch (formTyp) {
                    case 0:
                        form = FormFactory.getIForm();
                        break;
                    case 1:
                        form = FormFactory.getGrossesL();
                        break;
                    case 2:
                        form = FormFactory.getTForm();
                        break;
                    default:
                        form = FormFactory.getIForm();
                }

                // Zufällige Koordinaten generieren
                int reihe = (int) (Math.random() * gameBoard.getSpielfeld().length);
                int spalte = (int) (Math.random() * gameBoard.getSpielfeld()[0].length);

                System.out.println("Versuche Form an (" + reihe + "," + spalte + ") zu platzieren");
                printer.placeForm(gameBoard, form, reihe, spalte);
                printer.printGameBoard(gameBoard);

                Thread.sleep(3000); // 3 Sekunden Pause
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            versuche++;
        }
        try {
            System.out.println("Entferne alle L");
            printer.removeForm(gameBoard, 2);
            Thread.sleep(3000);
            System.out.println("Entferne alle I");
            printer.removeForm(gameBoard,1);
            Thread.sleep(3000);
            System.out.println("Entferne alle T");
            printer.removeForm(gameBoard,3);
            Thread.sleep(3000);
            System.out.println("Ende");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
