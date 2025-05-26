/**
 * Diese Klasse verwaltet das Spielfeld
 */
public class GameBoard {
    /**
     * Anzahl der Reihen des Spielfelds
     */
    private int reihen;

    /**
     * Anzahl der Spalten des Spielfelds
     */
    private int spalten;

    /**
     * Gitter des Spielfelds
     */
   private int[][] spielfeld;

    /**
     * Konstruktor der Klasse GameBoard, mit der ein Spielfeld erzeugt ewrden kann
     * @param _reihen Anzahl der Reihen, die das Spielfeld haben soll
     * @param _spalten Anzahl der Spalten, die das Spielfeld haben soll
     */
    public GameBoard(int _reihen, int _spalten)
    {
        // IV setzen
        this.reihen = _reihen;
        this.spalten = _spalten;
        this.spielfeld = new int[this.reihen][this.spalten];
    }

    /**
     * Methode prüft, ob die übergebene Form an der angegebenen Position eingefüt werden kann.
     * @param _form Form die eingefügt werden soll
     * @param _reihe Reihe in der die Form eingefügt werden soll
     * @param _spalte Spalte in der die Form eingefügt werden soll
     * @return Wahrheitswert -> passt die Form rein?
     */
    public boolean canPlace(Form _form, int _reihe, int _spalte)
    {
        // Zeilenmuster der Form holen
        int[][] zellen = _form.getForm();

        // über das Zeilenmuster laufen
        for(int i = 0; i < _form.getHoehe(); i++)
        {
            for(int j = 0; j < _form.getBreite(); j++)
            {
                // wenn das Zeilenmuster belegt ist
                if(zellen[i][j] == 1)
                {
                    // Zielreihe & -spalte
                    int r = _reihe + i;
                    int s = _spalte + j;

                    // überprüfen, ob die Form im Spielfeld liegt
                    if(r >= this.reihen || s >= this.spalten || r < 0 || s < 0)
                    {
                        // -> liegt außerhalb
                        return false;
                    }

                    // Überprüfen, ob die Zellen im Spielfeld bereits belegt sind
                    if(this.spielfeld[r][s] == 1)
                    {
                        // -> bereits belegt
                        return false;
                    }
                }
            }
        }
        // ansonsten -> kann eingefügt werden
        return true;
    }

    /**
     * Methode setzt die übergebene Form an die angegebene Position.
     * @param _form Form die gesetzt werden soll
     * @param _reihe Reihe in der die Form platziert werden soll
     * @param _spalte Spalte in der die Form platziert werden soll
     */
    public void placeForm(Form _form, int _reihe, int _spalte) {
        // prüfen, ob die Form an der angegebenen Stelle überhaupt platziert werden kann
        if (this.canPlace(_form, _reihe, _spalte)) {
            // Zellenmuster der Form holen
            int[][] zellen = _form.getForm();

            // über Zellenmuster laufen
            for (int i = 0; i < _form.getHoehe(); i++) {
                for (int j = 0; j < _form.getBreite(); j++) {
                    // an den Stellen wo 1 im Zellenmuster steht, wird die Form im Spielfeld gesetzt
                    if (zellen[i][j] == 1) {
                        // Form platzieren
                        this.spielfeld[_reihe + i][_spalte + j] = 1;
                    }
                }
            }
        } else {
            System.out.println("Form kann hier nicht platziert werden.");
        }
    }


     /** Methode testet, ob das Spielfeld komplett gefüllt ist. Also ob das Spiel vorbei ist
     * @return true or false, ist das Spielfeld voll?
     */
    public boolean isComplete()
    {
        // über das Spielfeld laufen
        for(int r = 0; r < reihen; r++)
        {
            for(int s = 0; s < spalten; s++)
            {
                // wenn noch eine null vorhanden ist, gibt es ein leeres Feld
                if(spielfeld[r][s] == 0)
                {
                    // Spielfeld ist nicht voll
                    return false;
                }
            }
        }
        // ansonsten -> Spielfeld muss voll sein
        return true;
    }
}
