package com.example.puzzle_app;

/**
 * Diese Klasse repräsentiert eine com.example.puzzle_app.Form
 */
public class Form {
    /**
     * Breite der com.example.puzzle_app.Form
     */
    private int breite;

    /**
     * Höhe der com.example.puzzle_app.Form
     */
    private int hoehe;

    /**
     * Referenz auf die com.example.puzzle_app.Form
     */
    private int [][] form; // 1 = belegt, 0 = leer

    /**
     * Konstruktor der Klasse com.example.puzzle_app.Form, der ein com.example.puzzle_app.Form-Objekt erzeugt
     * @param form com.example.puzzle_app.Form die erzeugt werden soll
     */
    public Form(int[][] form)
    {
        this.form = form;
        this.hoehe = form.length;
        this.breite = form[0].length;
    }

    /**
     * Methode gibt die com.example.puzzle_app.Form zurück
     * @return com.example.puzzle_app.Form
     */
    public int[][] getForm()
    {
        return this.form;
    }

    /**
     * Methode gibt die Höhe der com.example.puzzle_app.Form
     * @return Höhe
     */
    public int getHoehe()
    {
        return this.hoehe;
    }

    /**
     * Methode gibt die Breite der com.example.puzzle_app.Form
     * @return Breite
     */
    public int getBreite()
    {
        return this.breite;
    }
}
