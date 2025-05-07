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
        this.reihen = _reihen;
        this.spalten = _spalten;
        this.gitter = new int[this.reihen][this.spalten];
    }
}
