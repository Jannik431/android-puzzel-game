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
     * Methode gibt eine verlängerte ILang-com.example.puzzle_app.Form zurück.
     * @return ILang-com.example.puzzle_app.Form
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
     * Methode gibt eine vergrößerte GrossesL-com.example.puzzle_app.Form zurück.
     * @return GrossesL-com.example.puzzle_app.Form
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
     * Methode gibt eine liegende LiegendesI-com.example.puzzle_app.Form zurück.
     * @return LiegendesI-com.example.puzzle_app.Form
     */
    public static Form getLiegendesI()
    {
        return new Form(new int[][]
                {
                        {6,6,6}
                },6);
    }

    /**
     * Methode gibt eine mittellange MitlleresI-com.example.puzzle_app.Form zurück.
     * @return MitlleresI-com.example.puzzle_app.Form
     */
    public static Form getMittleresI()
    {
        return new Form(new int[][]
                {
                        {7},
                        {7},
                        {7},
                        {7}
                },7);
    }

    /**
     * Methode gibt eine kleines Rechteck kleinesRechteck-com.example.puzzle_app.Form zurück.
     * @return kleinesRechteck-com.example.puzzle_app.Form
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
     * Methode gibt eine liegende Z-com.example.puzzle_app.Form zurück.
     * @return Z-com.example.puzzle_app.Form
     */
    public static Form getZ()
    {
        return new Form(new int[][]
                {
                        {9,0,0},
                        {9,9,9},
                        {0,0,9}
                },9);
    }

    /**
     * Methode gibt eine sehr kleines Rechteck MikroRechteck-com.example.puzzle_app.Form zurück.
     * @return MikroRechteck-com.example.puzzle_app.Form
     */
    public static Form getMikroRechteck()
    {
        return new Form(new int[][]
                {
                        {10}
                },10);
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
                return getLiegendesI();
            case 7:
                return getMittleresI();
            case 8:
                return getKleinesRechteck();
            case 9:
                return getZ();

            case 10:
                return getMikroRechteck();
            default:
                return null;
        }
    }
}
