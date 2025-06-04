package com.example.puzzle_app;

/**
 * Klasse erstellt vogefertigte Tetris-Formen
 */
public class FormFactory {

    /**
     * Methode gibt eine L-com.example.puzzle_app.Form zurück.
     * @return L-com.example.puzzle_app.Form
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
     * Methode gibt eine T-com.example.puzzle_app.Form zurück.
     * @return T-com.example.puzzle_app.Form
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
     * Methode gibt eine I-com.example.puzzle_app.Form zurück.
     * @return I-com.example.puzzle_app.Form
     */
    public static Form getIForm()
    {
        return new Form(new int[][]
                {
                        {1},
                        {1},
                        {1}
                });
    }
}
