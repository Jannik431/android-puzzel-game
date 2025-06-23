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

    private int formId;

    /**
     * Gibt an wie oft eine Form gesetzt werden darf
     */
    private int verbleibendeAnzahl = Integer.MAX_VALUE; // Standard = unbegrenzt


    public Form(int[][] form)
    {
        this.form = form;
        this.hoehe = form.length;
        this.breite = form[0].length;
    }
    public Form(int[][] form, int formId)
    {
        this.form = form;
        this.hoehe = form.length;
        this.breite = form[0].length;
        this.formId = formId;
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

    public int getFormId(){
        return formId;
    }

    public int getVerbleibendeAnzahl() {
        return verbleibendeAnzahl;
    }

    public void setVerbleibendeAnzahl(int verbleibendeAnzahl) {
        this.verbleibendeAnzahl = verbleibendeAnzahl;
    }

    public void decrementAnzahl() {
        if (verbleibendeAnzahl > 0) {
            verbleibendeAnzahl--;
        }
    }

}
