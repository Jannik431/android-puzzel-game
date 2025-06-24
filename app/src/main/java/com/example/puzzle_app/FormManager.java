package com.example.puzzle_app;

import java.util.ArrayList;
import java.util.List;

/**
 * Der FormManager verwaltet eine Liste von Form-Objekten sowie
 * deren Darstellung in einer Miniaturansicht (FormPaletteView)
 * Er hält die verbleibenden Verwendungen von Formen aktuell und sorgt dafür,
 * dass die Palette nur Formen anzeigt, die noch verfügbar sind.
 */
public class FormManager {

    /**
     * Alle Formen des Levels, unabhängig davon ob sie verfügbar sind oder nicht.
     */
    private final List<Form> alleFormen;

    /**
     * Die View-Komponente, die die verfügbaren Formen visuell darstellt.
     */
    private final FormPaletteView paletteView;

    /**
     * Erstellt einen neuen FormManager mit der gegebenen Formenliste und der zugehörigen Palette-View.
     * @param formen      Liste aller Formen, inkl. ihrer maximalen Anzahl.
     * @param paletteView Die View, in der verfügbare Formen angezeigt werden.
     */
    public FormManager(List<Form> formen, FormPaletteView paletteView) {
        this.alleFormen = formen;
        this.paletteView = paletteView;
        updatePalette();
    }

    /**
     * Verringert die verfügbare Anzahl einer gegebenen Form um eins und aktualisiert die Palette.
     * @param form Die platzierte Form, deren Anzahl reduziert werden soll.
     */
    public void decrement(Form form) {
        form.decrementAnzahl();
        updatePalette();
    }

    /**
     * Aktualisiert die FormPaletteView, sodass nur noch die Formen mit
     * verbleibender Anzahl größer 0 angezeigt werden.
     */
    private void updatePalette() {
        List<Form> verfuegbare = new ArrayList<>();
        for (Form f : alleFormen) {
            if (f.getVerbleibendeAnzahl() > 0) {
                verfuegbare.add(f);
            }
        }
        paletteView.setFormen(verfuegbare);
    }

    /**
     * Gibt die vollständige Liste aller Formen zurück – unabhängig von deren Verfügbarkeit.
     * @return Liste aller Formen im aktuellen Level.
     */
    public List<Form> getAlleFormen() {
        return alleFormen;
    }
}