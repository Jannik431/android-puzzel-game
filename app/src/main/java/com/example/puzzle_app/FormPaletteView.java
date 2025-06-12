package com.example.puzzle_app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
     * Methode zeichnet die Miniaturansicht der Formen
     * @param canvas the canvas on which the background will be drawn
     */
    protected void onDraw(Canvas canvas)
    {
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
        // Maximale Breite aller Formen bestimmen
        int y = 0;
        int x = 0;
        int maxBreite = MeasureSpec.getSize(widthMeasureSpec);
        int maxZeilenHoehe = 0;

        for(Form form: formen)
        {
            int formBreite = form.getBreite() * zellenGroesse;
            int formHoehe = form.getHoehe() * zellenGroesse;
            if(x + formBreite > maxBreite)
            {
                x = 0;
                y += maxZeilenHoehe + padding;
                maxZeilenHoehe = 0;
            }

            x += formBreite + padding;
            if(formHoehe > maxZeilenHoehe)
            {
                maxZeilenHoehe = formHoehe;
            }
        }
        y += maxZeilenHoehe;

        setMeasuredDimension(maxBreite, y);
    }
}