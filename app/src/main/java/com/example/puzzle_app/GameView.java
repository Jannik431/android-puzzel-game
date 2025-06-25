package com.example.puzzle_app;

import android.view.MotionEvent;
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
    private final int zellenGroesse = 70;

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
    private int zellGroesse = 70;

    /**
     * Referenz auf die Miniaturdarstellung FormPaletteView
     */
    private FormPaletteView formPaletteView;

    /**
     * Referenz auf den FormManager
     */
    private FormManager formManager;

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

    public void setFormPaletteView(FormPaletteView paletteView) {
        this.formPaletteView = paletteView;
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
//                if(raster[i][j] == 1)
                if(raster[i][j] != 0)
                {
                    // Farbe je nach FormID setzen
                    switch(raster[i][j])
                    {
                        case 1:  paintZelle.setColor(Color.parseColor("#D7C4A3"));break;
                        case 2: paintZelle.setColor(Color.parseColor("#C45A4A"));break;
                        case 3: paintZelle.setColor(Color.parseColor("#5B3A29"));break;
                        case 4: paintZelle.setColor(Color.parseColor("#8B6D5C"));break;
                        case 5: paintZelle.setColor( Color.parseColor("#7F4A3C"));break;
                        case 6: paintZelle.setColor( Color.parseColor("#B36B43"));break;
                        case 7: paintZelle.setColor( Color.parseColor("#D9C4A5"));break;
                        case 8: paintZelle.setColor( Color.parseColor("#6E4B2A"));break;
                        case 9: paintZelle.setColor( Color.parseColor("#A0522D"));break;
                        case 10: paintZelle.setColor( Color.parseColor("#E3C699"));break;
                    }
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
                    if(muster[i][j] != 0)
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

    /**
     * Maße für die GameView festlegen, damit sie richtig dargestellt werden kann.
     * @param widthMeasureSpec horizontal space requirements as imposed by the parent.
     *                         The requirements are encoded with
     *                         {@link android.view.View.MeasureSpec}.
     * @param heightMeasureSpec vertical space requirements as imposed by the parent.
     *                         The requirements are encoded with
     *                         {@link android.view.View.MeasureSpec}.
     *
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int breite = spielfeld.getSpielfeld()[0].length * zellenGroesse;
        int hoehe = spielfeld.getSpielfeld().length * zellenGroesse;
        setMeasuredDimension(breite, hoehe);
    }

    @Override
    /**
     * onTouchEvent erkennt und verarbeitet klicks auf das Spielfeld.
     * Es wird immer die Form gelegt, die zuvor in der formPaletteView angeklickt wurde.
     * Wurde keine Form angeklickt, so pasiert nichts
     * @param event Ein Objekt der Klasse Motion Event welches über die nötigen Methoden
     *              verfügt um ein click und dessen Koordinaten zu registrieren
     */
    public boolean onTouchEvent(MotionEvent event) {
        // Prüft ob das Event ein klick war
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 1) Koordinaten des Klicks speichern
            float x = event.getX();
            float y = event.getY();

            System.out.println("GameView " + "Touch bei Pixel-Koordinaten: X=" + x + ", Y=" + y);

            // 2) Umrechnen in reihen und spalten.
            int spalte = (int) (x / zellenGroesse);
            int reihe  = (int) (y / zellenGroesse);

            System.out.println("GameView " + "Touch in Spielfeld-Zelle: [" + reihe + ", " + spalte + "]");

            invalidate(); // View neu zeichnen

            // 3) (Optional) Entfernen von individuellen Formen. Momentan deaktiviert, da unzuverlässig.
            boolean codeAbschnittIstAktiv = false;
            if (codeAbschnittIstAktiv) {
                spielfeld.removeForm(spielfeld.getGridPointValue(spalte, reihe));
            } else {
                // 4) Aktuell angeklickte Form von formPaletteView erfragen
                if (formPaletteView != null && formPaletteView.getAngeklickteForm() != null) {
                    Form aktuelleForm = formPaletteView.getAngeklickteForm();

                    // 5) Prüfung und anschließendes platzieren der Form
                    if (aktuelleForm.getVerbleibendeAnzahl() > 0 &&               // Wurde die Form noch nicht zu oft platziert?
                            spielfeld.canPlace(aktuelleForm, reihe, spalte)) {   // sind die Felder noch unbelegt und komplett innerhalb des Spielfelds?

                        spielfeld.placeForm(aktuelleForm, reihe, spalte);      // Platzieren

                        // 6) Verbleibende Anzahl reduzieren & Palette aktualisieren
                        if (formManager != null) {
                            formManager.decrement(aktuelleForm);
                        }

                        invalidate(); // Neu zeichnen
                    } else {
                        System.out.println("Form kann hier nicht platziert werden oder ist aufgebraucht.");
                    }
                } else {
                    System.out.println("Es wurde keine Form angeklickt!");
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Die IV FormManager wird gesetzt
     * @param manager FormManager der gesetzt werden soll
     */
    public void setFormManager(FormManager manager) {
        this.formManager = manager;
    }
}
