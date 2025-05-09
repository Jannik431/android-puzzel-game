/**
 * Diese Klasse repräsentiert eine Form
 */
public class Form {
    /**
     * Breite der Form
     */
    private int breite;

    /**
     * Höhe der Form
     */
    private int hoehe;

    /**
     * Referenz auf die Form
     */
    private int [][] form;

    /**
     * Konstruktor der Klasse Form, der ein Form-Objekt erzeugt
     * @param form Form die erzeugt werden soll
     */
    public Form(int[][] form)
    {
        this.form = form;
        this.hoehe = form.length;
        this.breite = form[0].length;
    }
}
