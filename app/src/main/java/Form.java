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
    private int [][] form; // 1 = belegt, 0 = leer

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

    /**
     * Methode gibt die Form zurück
     * @return Form
     */
    public int[][] getForm()
    {
        return this.form;
    }

    /**
     * Methode gibt die Höhe der Form
     * @return Höhe
     */
    public int getFormHoehe()
    {
        return this.hoehe;
    }

    /**
     * Methode gibt die Breite der Form
     * @return Breite
     */
    public int getBreite()
    {
        return this.breite;
    }
}
