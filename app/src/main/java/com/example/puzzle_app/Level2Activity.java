package com.example.puzzle_app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Level-Activity, die nach Auswahl im MainScreen aufgerufen wird.
 * Mittleres Tetris-Level
 * Formen können per Klick ins Spielfeld gesetzt werden.
 * Ziel ist es, das Spielfeld komplett mit Formen zu füllen, bevor die Zeit abläuft.
 */
public class Level2Activity extends AppCompatActivity {
    private GameView gameView;
    private GameBoard spielfeld;
    private FormPaletteView paletteView;
    private boolean isRunning = false;
    private static final long START_TIME = 25000;
    private long remainingTime;
    private Button startStopButton;
    private CountDownTimer countDownTimer;
    private TextView timerText;
    private ActivityResultLauncher<Intent> pauseActivityLauncher;
    private FormManager formManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.remainingTime = START_TIME; // Neu
        // Spielfeld vorbereiten
        this.spielfeld = new GameBoard(5,5);

        // GameView zeigt nur das Spielfeld (aktuelle Form = null)
        this.paletteView = new FormPaletteView(this, new ArrayList<>()); // noch leer

        Form l = FormFactory.getLForm();                l.setVerbleibendeAnzahl(1);
        Form t = FormFactory.getTForm();                t.setVerbleibendeAnzahl(1);
        Form iLang = FormFactory.getILangForm();        iLang.setVerbleibendeAnzahl(1);
        Form square = FormFactory.getKleinesRechteck(); square.setVerbleibendeAnzahl(1);
        Form iKurz = FormFactory.getIForm();            iKurz.setVerbleibendeAnzahl(1);
        Form mikro = FormFactory.getMikroRechteck();    mikro.setVerbleibendeAnzahl(1);
        Form liegendesI = FormFactory.getLiegendesI(); liegendesI.setVerbleibendeAnzahl(1);

        List<Form> formenListe = Arrays.asList(l, t, iLang, square, iKurz, mikro, liegendesI);

        // FormManager erzeugen
        this.formManager = new FormManager(formenListe, paletteView);

        // GameView anlegen & mit Manager verknüpfen
        this.gameView = new GameView(this, this.spielfeld, null);
        this.gameView.setFormManager(formManager);
        this.gameView.setFormPaletteView(paletteView);

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
                            this.resetGame();
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

//        // Thread zur Überwachung, ob das Level abgeschlossen ist
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);
                    if (spielfeld != null && spielfeld.isComplete()) {
                        runOnUiThread(() -> {
                            long timePassed = START_TIME - remainingTime;
                            stopTimer();
                            Intent intent = new Intent(Level2Activity.this, LevelCompletedActivity.class);
                            intent.putExtra("timePassed", timePassed);
                            System.out.println("Starte jetzt LevelCompletedActivity (debug)");
                            startActivity(intent);
                        });
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

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
                Intent intent = new Intent(Level2Activity.this, GameOverActivity.class);
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

    /**
     * Spiel wird zurückgesetzt. Spielfeld wird gelert und Formen zurückgesetzt.
     */
    private void resetGame() {
        // Spielfeld löschen
        spielfeld.removeAll();
        gameView.invalidate();

        // Formen zurücksetzen
        for (Form f : formManager.getAlleFormen()) {
            f.setVerbleibendeAnzahl(1);
        }

        // PaletteView neu setzen & aktualisieren
        paletteView.setFormen(formManager.getAlleFormen());
        paletteView.invalidate();
    }



}
