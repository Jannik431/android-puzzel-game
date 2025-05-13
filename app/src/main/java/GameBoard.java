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
   private int[][] gitter;

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
        this.gitter = new int[this.reihen][this.spalten];
    }

    /**
     * Methode testet, ob das Spielfeld komplett gefüllt ist. Also ob das Spiel vorbei ist
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
                if(gitter[r][s] == 0)
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
