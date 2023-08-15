package view;

import java.util.Scanner;

import exception.SignOutException;
import model.Board;

public class BoardConsole {

    Scanner entry = new Scanner(System.in);
    private Board board;

    public BoardConsole(Board board) {
        this.board = board;

        executeGame();
    }

    private void executeGame() {
        try {
            boolean continueGame = true;

            while (continueGame) {

                System.out.println("Another game? (S/n) ");
                String answer = entry.nextLine();

                if("n".equalsIgnoreCase(answer)){
                    continueGame = false;
                } else {
                    board.restart();
                }
            }
        }catch(SignOutException e){
            System.out.println("See you later!");
        }finally{
            entry.close();
        }
    }   
}
