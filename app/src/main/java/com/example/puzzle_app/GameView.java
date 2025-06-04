package com.example.puzzle_app;

import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Klasse für die visuelle Darstellung
 */
public class GameView extends View{
    /**
     * verweist auf das Spielfeld
     */
    private GameBoard spielfeld;

    /**
     * verweist auf die aktuelle com.example.puzzle_app.Form
     */
    private Form aktuelleForm;

    /**
     * Konstante, die die Pixelgröße der Zellen festlegt
     */
    private final int zellenGroesse = 80;

    /**
     * Konstante, die den Abstand zwischen Zellen festlegt
     */
    private final int abstand = 2;

    /**
     * Darstellung für belegte Felder
     */
    private Paint paintZelle;

    /**
     * Darstellung für leere Felder
     */
    private Paint paintHintergrund;

    /**
     * Darstellung für die aktuelle, noch nicht platziere com.example.puzzle_app.Form
     */
    private Paint paintForm;

    /**
     * Pixelgröße einer Zelle
     */
    private int zellGroesse = 80;

    /**
     * Konstruktor, der ein Spielfeld und eine com.example.puzzle_app.Form zuweist und die Darstellungen festlegt
     * @param context Android-Kontext der Activity, in der die View verwendet wird. Wird benötigt,
     *                um auf Systemressourcen und Framework-Funktionen zuzugreifen.
     * @param spielfeld Spielfeld das dargestellt werden soll.
     * @param form Aktuelle com.example.puzzle_app.Form, die angezeigt bzw. platziert werden kann.
     */
    public GameView(Context context, GameBoard spielfeld, Form form)
    {
        super(context);

        // IV setzen
        this.spielfeld = spielfeld;
        this.aktuelleForm = form;

        // Paints vorbereiten
        // Darstellung der belegten Zellen:
        this.paintZelle = new Paint();
        this.paintZelle.setColor(Color.DKGRAY);
        this.paintZelle.setStyle(Paint.Style.FILL);

        // Darstellung der leeren Zellen:
        this.paintHintergrund = new Paint();
        this.paintHintergrund.setColor(Color.LTGRAY);
        this.paintHintergrund.setStyle(Paint.Style.FILL);

        // Darstellung noch nicht platzierte com.example.puzzle_app.Form:
        this.paintForm = new Paint();
        this.paintForm.setColor(Color.BLUE);
        this.paintForm.setStyle(Paint.Style.FILL);
    }

    /**
     * Methode zeichnet das Spielfeld, so wie belegte Felder und die noch nicht platziere com.example.puzzle_app.Form.
     * @param canvas the canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        int[][] raster = this.spielfeld.getSpielfeld();

        // Spielfeld zeichnen
        for(int i = 0; i < raster.length; i++)
        {
            for(int j = 0; j < raster[i].length; j++)
            {
                // wenn Zelle belegt:
                if(raster[i][j] == 1)
                {
                    canvas.drawRect(
                            j * this.zellGroesse,
                            i * this.zellGroesse,
                            (j + 1) * this.zellGroesse,
                            (i + 1) * this.zellGroesse,
                            paintZelle
                    );
                }
                else
                {
                    // wenn Zelle nicht belegt:
                    canvas.drawRect(
                            j * this.zellGroesse,
                            i * this.zellGroesse,
                            (j + 1) * this.zellGroesse,
                            (i + 1) * this.zellGroesse,
                            paintHintergrund
                    );
                }
            }
        }
        // aktuelle noch nicht platzierte com.example.puzzle_app.Form zeichnen
        if(this.aktuelleForm != null)
        {
            int[][] muster = this.aktuelleForm.getForm();

            // com.example.puzzle_app.Form unterhalb des Spielfelds zeichnen
            int offsetY = raster.length + 1;

            for(int i = 0; i < muster.length; i++)
            {
                for(int j = 0; j < muster[i].length; j++)
                {
                    // wenn Zelle belegt
                    if(muster[i][j] == 1)
                    {
                        canvas.drawRect(
                                j * zellGroesse,
                                (offsetY + i) * zellGroesse,
                                (j + 1) * zellGroesse,
                                (offsetY + i + 1) * zellGroesse,
                                paintForm
                        );
                    }
                }
            }
        }

    }

    /**
     * Methode setzt eine neue com.example.puzzle_app.Form als aktuelle com.example.puzzle_app.Form
     * @param form com.example.puzzle_app.Form die als nächstes zur Auswahl stehen soll
     */
    public void setAktuelleForm(Form form)
    {
        this.aktuelleForm = form;

        // Ansicht aktualisieren
        invalidate();
    }
}
