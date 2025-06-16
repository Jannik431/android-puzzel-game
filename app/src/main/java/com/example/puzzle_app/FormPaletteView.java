package com.example.puzzle_app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
/**
 * Diese View zeig eine Liste von Formen als Miniaturansicht.
 */
public class FormPaletteView extends View{
    /**
     * Liste aller darzustellenden Formen
     */
    private List<Form> formen;

    /**
     * Paint-Objekt für Zellen
     */
    private Paint paintZelle;

    /**
     * Zellgröße in Pixeln
     */
    private int zellenGroesse = 40;

    /**
     * Abstand zwischen Formen
     */
    private int padding = 20;
    /**
     * Hier werden die klickbereiche als Rectangle gespeichert. onTouchEvent braucht diese List
     * um den user click mit den Positionen der Formen abzugleichen.
     */
    private List<Rect> klickbereiche = new ArrayList<>();
    /**
     * Speichert die aktuell angeklickte Form. Dies ist auch nötig für die onTouchEvent Methode
     */
    private Form angeklickteForm = null;

    /**
     * View erstellen
     * @param context Android-Kontext der Activity, in der die View verwendet wird. Wird benötigt,
     *      *                um auf Systemressourcen und Framework-Funktionen zuzugreifen.
     * @param formen Liste der darzustellenden Formen
     */
    public FormPaletteView(Context context, List<Form> formen)
    {
        super(context);
        this.formen = formen;

        paintZelle = new Paint();
        paintZelle.setColor(Color.DKGRAY);
        paintZelle.setStyle(Paint.Style.FILL);
    }

    /**
     * Methode zeichnet die Miniaturansicht der Formen und speichert die entsprechenden klickbereiche.
     * Diese klickbereiche sind notwendig für die onTouchEvent Methode
     * @param canvas the canvas on which the background will be drawn
     */
    protected void onDraw(Canvas canvas)
    {

        klickbereiche.clear(); // Klickbereiche zurückgesetzt, damit onTouchEvent() nicht mit alten Werten arbeitet
        super.onDraw(canvas);

        int maxBreite = getWidth();
        int maxFormBreite = 5; // z.B. 5x5 Raster für jede Miniatur
        int maxFormHoehe = 5;
        int formBlockBreite = maxFormBreite * zellenGroesse;
        int formBlockHoehe = maxFormHoehe * zellenGroesse;

        int x = 0;
        int y = 0;
        int zeilenHoehe = formBlockHoehe;

        List<List<Form>> zeilen = new ArrayList<>();
        List<Form> aktuelleZeile = new ArrayList<>();
        int aktuelleZeilenBreite = 0;

        // Zeilen vorab gruppieren
        for (Form form : formen) {
            if (aktuelleZeilenBreite + formBlockBreite > maxBreite) {
                zeilen.add(aktuelleZeile);
                aktuelleZeile = new ArrayList<>();
                aktuelleZeilenBreite = 0;
            }
            aktuelleZeile.add(form);
            aktuelleZeilenBreite += formBlockBreite + padding;
        }
        if (!aktuelleZeile.isEmpty()) {
            zeilen.add(aktuelleZeile);
        }

        // Jetzt zeilenweise zeichnen
        for (List<Form> zeile : zeilen) {
            int gesamtBreite = zeile.size() * (formBlockBreite + padding) - padding;
            int startX = (maxBreite - gesamtBreite) / 2;
            x = startX;

            for (Form form : zeile) {

                // Klickbereich für diese Form speichern (onTouchEvent braucht Information)
                // (!!) Eventuell problematisch (!!) Allocations sollten eigentlich nicht in draw() Methoden stattfinden. Aber ist denke ich erstmal in Ordnung.
                Rect klickbereich = new Rect(x, y, x + formBlockBreite, y + formBlockHoehe);
                klickbereiche.add(klickbereich);

                int formW = form.getBreite();
                int formH = form.getHoehe();
                int[][] zellen = form.getForm();

                // Zentrierte Offset innerhalb des Blocks
                int offsetX = (maxFormBreite - formW) * zellenGroesse / 2;
                int offsetY = (maxFormHoehe - formH) * zellenGroesse / 2;

                for (int i = 0; i < formH; i++) {
                    for (int j = 0; j < formW; j++) {
                        if (zellen[i][j] != 0) {
                            canvas.drawRect(
                                    x + offsetX + j * zellenGroesse,
                                    y + offsetY + i * zellenGroesse,
                                    x + offsetX + (j + 1) * zellenGroesse,
                                    y + offsetY + (i + 1) * zellenGroesse,
                                    paintZelle
                            );
                        }
                    }
                }

                x += formBlockBreite + padding;
            }

            y += zeilenHoehe + padding;
        }
    }

    /**
     * Liste der Formen festlegen + neu zeichnen
     * @param formen Formen die dargestellt werden sollen
     */
    public void setFormen(List<Form> formen)
    {
        this.formen = formen;
        invalidate();
    }

    /**
     * Benutzerdefinierte View erstellen, breite und höhe festlegen
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
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);

        int maxFormBreite = 5; // Zellen
        int maxFormHoehe = 5;  // Zellen
        int formBlockBreite = maxFormBreite * zellenGroesse;
        int formBlockHoehe = maxFormHoehe * zellenGroesse;

        int aktuelleBreite = 0;
        int gesamtHoehe = formBlockHoehe; // mindestens eine Zeile
        int zeilen = 1;

        for (int i = 0; i < formen.size(); i++) {
            if (aktuelleBreite + formBlockBreite > maxWidth) {
                zeilen++;
                aktuelleBreite = 0;
            }
            aktuelleBreite += formBlockBreite + padding;
        }

        gesamtHoehe = zeilen * (formBlockHoehe + padding);

        // Höhe explizit setzen
        setMeasuredDimension(maxWidth, gesamtHoehe);
    }

    /**
     * Registriert Klick Koordinaten innerhalb der FormPaletteView.
     * Wird eine Form angeklickt, so wird sie in angeklickteForm gespeichert.
     * Mit dem Getter getAngeklickteForm kann sich diese Form später geholt werden
     *
     * @param event The motion event object
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Prüft ob das registrierte Event ein user click ist (ACTION_DOWN)
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Speichert die Koordinaten des Klicks
            float x = event.getX();
            float y = event.getY();

            // -- Nur für die Konsole zum Debuggen --
            System.out.println("FormPaletteView " + "Touch bei Pixel-Koordinaten: X=" + x + ", Y=" + y);

            // Umrechnung in Zell-Koordinaten
            int spalte = (int) (x / zellenGroesse);
            int reihe = (int) (y / zellenGroesse);
            System.out.println("FormPaletteView " + "Touch in Spielfeld-Zelle: [" + reihe + ", " + spalte + "]");
            // -- Nur für die Konsole zum Debuggen --

            for (int i = 0; i < klickbereiche.size(); i++) {
                Rect r = klickbereiche.get(i);
                if (r.contains((int)x, (int)y)) {
                    angeklickteForm = FormFactory.getFormById(formen.get(i).getFormId());
                    invalidate();
                    break;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * GameView nutzt diesen getter um die aktuell angeklickte Form auf dem Spielfeld zu platzieren
     * @return Gibt die zuletzt angeklickte Form zurück. Kann auch null zurückgeben!
     */
    public Form getAngeklickteForm() {
            return angeklickteForm;

    }
}