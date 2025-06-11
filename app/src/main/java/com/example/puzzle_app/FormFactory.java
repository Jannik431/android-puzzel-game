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
                }, 3);
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
                }, 1);
    }

    /**
     * Form für schweres Level
     * 1x5
     * @return Form
     */
    public static Form getILangForm()
    {
        return new Form(new int[][]
                {
                        {1},
                        {1},
                        {1},
                        {1},
                        {1}
                });
    }

    /**
     * Form für schweres Level.
     * 5x4
     * @return Form
     */
    public static Form getGrossesL()
    {
        return new Form(new int[][]
                {
                        {2, 0, 0, 0},
                        {2, 0, 0, 0},
                        {2, 0, 0, 0},
                        {2, 0, 0, 0},
                        {2, 2, 2, 2}
                }, 2 );
    }

    /**
     * Form für schweres Level.
     * 3x6
     * @return Form
     */
    public static Form getGrossesRechteck()
    {
        return new Form(new int[][]
                {
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1}
                });
    }

    /**
     * Form für schweres Level.
     * 3x3
     * @return Form
     */
    public static Form getRechteck()
    {
        return new Form(new int[][]
                {
                        {1,1,1},
                        {1,1,1},
                        {1,1,1}
                });
    }

    /**
     * Form für schweres Level
     * 2x2
     * @return Form
     */
    public static Form getKleinesRechteck()
    {
        return new Form(new int[][]
                {
                        {1,1},
                        {1,1}
                });
    }

    /**
     * Form für schweres Level.
     * @return Form
     */
    public static Form getGrossesT()
    {
        return new Form(new int[][]
                {
                        {1,1,1,1,1,1},
                        {0,0,0,1,0,0},
                        {0,0,0,1,0,0}
                });
    }

    /**
     * Form für schweres Level
     * @return Form
     */
    public static Form getFalschesL()
    {
        return new Form(new int[][]
                {
                        {0,0,0,1,1,1},
                        {0,0,0,1,1,1},
                        {0,0,0,1,1,1},
                        {0,0,0,1,1,1},
                        {1,1,1,1,1,1},
                        {1,1,1,1,1,1},
                        {1,1,1,1,1,1},
                });
    }
}
