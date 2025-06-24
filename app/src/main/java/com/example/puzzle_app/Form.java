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
     * Id der com.example.puzzle_app.Form
     */
    private int formId;

    /**
     * Gibt an wie oft eine Form gesetzt werden darf
     */
    private int verbleibendeAnzahl = Integer.MAX_VALUE; // Standard = unbegrenzt

    /**
     * Konstruktor der Klasse com.example.puzzle_app.Form, der ein com.example.puzzle_app.Form-Objekt erzeugt
     * @param form com.example.puzzle_app.Form die refernziert werden soll
     */
    public Form(int[][] form)
    {
        this.form = form;
        this.hoehe = form.length;
        this.breite = form[0].length;
    }

    /**
     * Konstruktor der Klasse Form
     * @param form Form die referenziert werden soll
     * @param formId ID der Form
     */
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

    /**
     * Methode gibt die ID der Form zurück
     * @return ID
     */
    public int getFormId(){
        return formId;
    }

    /**
     * Methode gibt an wie oft die Form noch gesetzt werden darf
     * @return Anzahl der verbleibenden Nutzungen
     */
    public int getVerbleibendeAnzahl() {
        return verbleibendeAnzahl;
    }

    /**
     * Methode setzt die IV die angibt, wie oft die Form noch gesetzt werden darf
     * @param verbleibendeAnzahl
     */
    public void setVerbleibendeAnzahl(int verbleibendeAnzahl) {
        this.verbleibendeAnzahl = verbleibendeAnzahl;
    }

    /**
     * Methode verringert die Anzahl der verbleidenen Nutzungen der Form
     */
    public void decrementAnzahl() {
        if (verbleibendeAnzahl > 0) {
            verbleibendeAnzahl--;
        }
    }
}
