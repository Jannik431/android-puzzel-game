//package com.example.puzzle_app;
//
//import android.os.Bundle;
//import android.view.Gravity;
//import android.widget.LinearLayout;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Level3Activity extends AppCompatActivity {
//    private GameView gameView;
//    private GameBoard spielfeld;
//    private Form form;
//    private GameBoardPrinter gameBoardPrinter;
//    private FormPaletteView paletteView;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // Spielfeld vorbereiten
//        this.spielfeld = new GameBoard(10, 10);
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
//        hauptLayout.setBackgroundResource(R.drawable.wood_background);
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
//    }
//}


package com.example.puzzle_app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Level3Activity extends AppCompatActivity {
    private GameView gameView;
    private GameBoard spielfeld;
    private Form form;
    private GameBoardPrinter gameBoardPrinter;
    private FormPaletteView paletteView;

    private boolean isRunning = false;

    private long remainingTime = 20000;

    private Button startStopButton;

    private CountDownTimer countDownTimer;

    private TextView timerText;

    private ActivityResultLauncher<Intent> pauseActivityLauncher;

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
        hauptLayout.setBackgroundResource(R.drawable.wood_background);


        // Obere Button-Zeile (Start/Stopp + Timer)
        LinearLayout topBar = new LinearLayout(this);
        topBar.setOrientation(LinearLayout.HORIZONTAL);
        topBar.setGravity(Gravity.CENTER);
        topBar.setPadding(0,0,0,30);

        // Start/Stopp-Button
        this.startStopButton = new Button(this);
        startStopButton.setText("▶");
        startStopButton.setOnClickListener(v -> {
            timerUmschalten();
            Intent intent = new Intent(this, PauseActivity.class);
            pauseActivityLauncher.launch(intent);
        });

        // Timer-Anzeige
        this.timerText = new TextView(this);
        timerText.setText("60s");
        timerText.setTextSize(24);
        timerText.setTypeface(Typeface.MONOSPACE);
        timerText.setTextColor(Color.parseColor("#FF5722"));
        timerText.setPadding(50,0,0,0);

        // Buttons zu TopBar hinzufügen
        topBar.addView(startStopButton);
        topBar.addView(timerText);
//        topBar.addView(removeAllButton);

        // GameView zum Layout hinzufügen
        LinearLayout.LayoutParams gameParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // PaletteView darunter einfügen
        LinearLayout.LayoutParams paletteParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        paletteParams.topMargin = 50;

        // Layout zusammensetzen
        hauptLayout.addView(topBar);
        hauptLayout.addView(gameView, gameParams);
        hauptLayout.addView(paletteView, paletteParams);

        pauseActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        boolean removeAll = data.getBooleanExtra("removeAll", false);
                        if (removeAll) {
                            spielfeld.removeAll();
                            gameView.invalidate();
                        }
                        boolean goHome = data.getBooleanExtra("goHome", false);
                        if (goHome) {
                            finish();
                            return;
                        }
                    }
                    // Nach Pause: Falls timer nicht läuft, neu starten
                    if(!this.isRunning)
                    {
                        this.startTimer();
                    }
                });

        // Layout setzen
        setContentView(hauptLayout);

        this.startTimer();
    }

    /**
     * Wechselt zwischen Timer starten und stoppen.
     * Wenn der Timer läuft, wird er gestoppt.
     * Wenn er nicht läuft, wird er gestartet.
     */
    private void timerUmschalten()
    {
        if(this.isRunning)
        {
            this.stopTimer();
        }else
        {
            this.startTimer();
        }
    }

    /**
     * Startet den CountDown-Timer und aktualisiert das Play/Stop-Symbol
     */
    private void startTimer()
    {
        this.isRunning = true;
        this.startStopButton.setText("■");

        // neuen CountDown timer
        this.countDownTimer = new CountDownTimer(this.remainingTime, 1000)
        {

            // onTick() und onFinish() müssen hier direkt überschrieben werden.
            // CountDownTimer ist eine anonyme Klasse, die ihr Verhalten direkt benötigt.
            /**
             * Wird jede Sekunde aufgerufen, solange der Timer läuft.
             * Aktulaisiert den angzeigten Text mit der verlbleibenden Zeit
             * @param millisUntilFinished The amount of time until finished.
             */
            @Override
            public void onTick(long millisUntilFinished)
            {
                remainingTime = millisUntilFinished;// Zeit merken
                timerText.setText((millisUntilFinished/1000) + "s"); // Anzeige aktualisieren
            }

            /**
             * Wird aufgerufen, wenn der Countdown ableaufen ist.
             */
            public void onFinish()
            {
                isRunning = false;
                startStopButton.setText("▶");
                timerText.setText("0s");

                // Game Over Activity starten
                Intent intent = new Intent(Level3Activity.this, GameOverActivity.class);
                startActivity(intent);
            }
        }.start();
    }

    /**
     * Stoppt den laufenden Timer und zeigt wieder das Play-Symbol an.
     */
    private void stopTimer()
    {
        this.isRunning = false;
        this.startStopButton.setText("▶");

        if(this.countDownTimer != null)
        {
            this.countDownTimer.cancel();
        }
    }

}





