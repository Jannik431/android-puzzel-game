package com.example.puzzle_app;

public class GameBoardPrinter {

    public void printGameBoard(GameBoard gameBoard){
        for(int i=0;i<gameBoard.getSpielfeld().length;i++){
            System.out.println("");
            for(int j=0;j<gameBoard.getSpielfeld()[i].length;j++){
//                System.out.print("-");
                System.out.print(gameBoard.getSpielfeld()[i][j]);
                System.out.print(" ");
            }
        }
    }
    public void placeForm(GameBoard gameboard, Form _form, int _reihe, int _spalte){
        gameboard.placeForm(_form, _reihe, _spalte);
        printGameBoard(gameboard);
    }

}
