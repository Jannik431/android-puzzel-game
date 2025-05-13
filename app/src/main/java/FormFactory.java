/**
 * Klasse erstellt vogefertigte Tetris-Formen
 */
public class FormFactory {

    /**
     * Methode gibt eine L-Form zurück.
     * @return L-Form
     */
    public static Form getLForm()
    {
        return new Form(new int[][]
                {
                        {1,0},
                        {1,0},
                        {1,1}
                 });
    }

    /**
     * Methode gibt eine T-Form zurück.
     * @return T-Form
     */
    public static Form getTForm()
    {
        return new Form(new int[][]
                {
                        {1,1,1},
                        {0,1,0},
                        {0,1,0}
                });
    }

    /**
     * Methode gibt eine I-Form zurück.
     * @return I-Form
     */
    public static Form getIForm()
    {
        return new Form (new int[][]
                {
                        {1},
                        {1},
                        {1}
                });
    }
}
