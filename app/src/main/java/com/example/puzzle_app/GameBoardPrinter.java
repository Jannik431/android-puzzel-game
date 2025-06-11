package com.example.puzzle_app;

public class GameBoardPrinter {

    public void printGameBoard(GameBoard _gameBoard){
        for(int i=0;i<_gameBoard.getSpielfeld().length;i++){
            System.out.println("");
            for(int j=0;j<_gameBoard.getSpielfeld()[i].length;j++){
//                System.out.print("-");
                System.out.print(_gameBoard.getSpielfeld()[i][j]);
                System.out.print(" ");
            }
        }
    }
    public void placeForm(GameBoard _gameboard, Form _form, int _reihe, int _spalte){
        _gameboard.placeForm(_form, _reihe, _spalte);
        printGameBoard(_gameboard);
    }


//    public void removeForm(GameBoard _gameBoard, Form _form_id) {
//        System.out.println("Debugging: Form mit formId: " + _form_id.getFormId() +  " wird entfernt.");
//        for (int r = 0; r < _gameBoard.getSpielfeld().length; r++) {
//            for (int s = 0; s < _gameBoard.getSpielfeld()[r].length; s++) {
//                if (_gameBoard.getSpielfeld()[r][s] == _form_id.getFormId())
//                    _gameBoard.getSpielfeld()[r][s] = 0;
//            }
//        }
//        printGameBoard(_gameBoard);
//    }
public void removeForm(GameBoard _gameBoard, int _form_id) {
    for (int r = 0; r < _gameBoard.getSpielfeld().length; r++) {
        for (int s = 0; s < _gameBoard.getSpielfeld()[r].length; s++) {
            if (_gameBoard.getSpielfeld()[r][s] == _form_id)
                _gameBoard.getSpielfeld()[r][s] = 0;
        }
    }
    printGameBoard(_gameBoard);
}





}
