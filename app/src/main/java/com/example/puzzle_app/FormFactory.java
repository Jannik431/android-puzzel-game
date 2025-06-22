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
                 },1);
    }

    /**
     * Methode gibt eine T-com.example.puzzle_app.Form zurück.
     * @return T-com.example.puzzle_app.Form
     */
    public static Form getTForm()
    {
        return new Form(new int[][]
                {
                        {2,2,2},
                        {0,2,0},
                        {0,2,0}
                }, 2);
    }

    /**
     * Methode gibt eine I-com.example.puzzle_app.Form zurück.
     * @return I-com.example.puzzle_app.Form
     */
    public static Form getIForm()
    {
        return new Form(new int[][]
                {
                        {3},
                        {3},
                        {3}
                }, 3);
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
                        {4},
                        {4},
                        {4},
                        {4},
                        {4}
                },4);
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
                        {5, 0, 0, 0},
                        {5, 0, 0, 0},
                        {5, 0, 0, 0},
                        {5, 0, 0, 0},
                        {5, 5, 5, 5}
                }, 5);
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
//                        {6,6,6},
                        {6,6,6},
                        {6,6,6},
                        {6,6,6},
                        {6,6,6},
                        {6,6,6}
                },6);
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
                        {7,7,7},
                        {7,7,7},
                        {7,7,7}
                },7);
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
                        {8,8},
                        {8,8}
                },8);
    }

    /**
     * Form für schweres Level.
     * @return Form
     */
    public static Form getGrossesT()
    {
        return new Form(new int[][]
                {
                        {9,9,9,9,9,9},
                        {0,0,0,9,0,0},
                        {0,0,0,9,0,0}
                },9);
    }

    /**
     * Form für schweres Level
     * @return Form
     */
    public static Form getFalschesL()
    {
        return new Form(new int[][]
                {
                        {0,0,0,10,10,10},
                        {0,0,0,10,10,10},
                        {0,0,0,10,10,10},
                        {0,0,0,10,10,10},
                        {10,10,10,10,10,10},
                        {10,10,10,10,10,10},
                        {10,10,10,10,10,10},
                },10);
    }
    public static Form getMikroRechteck()
    {
        return new Form(new int[][]
                {
                        {11}
                },11);
    }



    /**
     * Gibt basierend auf der formId eine neue Form zurück.
     * @param formId Die ID der gewünschten Form
     * @return Neue Instanz der Form oder null, falls unbekannt
     */
    public static Form getFormById(int formId)
    {
        switch (formId) {
            case 1:
                return getLForm();
            case 2:
                return getTForm();
            case 3:
                return getIForm();
            case 4:
                return getILangForm();
            case 5:
                return getGrossesL();
            case 6:
                return getGrossesRechteck();
            case 7:
                return getRechteck();
            case 8:
                return getKleinesRechteck();
            case 9:
                return getGrossesT();
            case 10:
                return getFalschesL();
            case 11:
                return getMikroRechteck();
            default:
                return null;
        }
    }
}
