package com.example.puzzle_app;

import java.util.ArrayList;
import java.util.List;

public class FormManager {

    private final List<Form> alleFormen;
    private final FormPaletteView paletteView;

    public FormManager(List<Form> formen, FormPaletteView paletteView) {
        this.alleFormen = formen;
        this.paletteView = paletteView;
        updatePalette();
    }

    public void decrement(Form form) {
        form.decrementAnzahl();
        updatePalette();
    }

    private void updatePalette() {
        List<Form> verfuegbare = new ArrayList<>();
        for (Form f : alleFormen) {
            if (f.getVerbleibendeAnzahl() > 0) {
                verfuegbare.add(f);
            }
        }
        paletteView.setFormen(verfuegbare);
    }

    public List<Form> getAlleFormen() {
        return alleFormen;
    }
}