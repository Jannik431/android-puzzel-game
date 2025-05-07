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
     * @param _breite Breite der Form
     * @param _hoehe Höhe der Form
     */
    public Form(int _breite, int _hoehe)
    {
        // IV setzen
        this.breite = _breite;
        this.hoehe = _hoehe;
        this.form = new int[this.breite][this.hoehe];
    }
}
