package view;

import java.util.Arrays;
import java.util.Scanner;

import exception.ExplosionException;
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

                if ("n".equalsIgnoreCase(answer)) {
                    continueGame = false;
                } else {
                    board.restart();
                }
            }
        } catch (SignOutException e) {
            System.out.println("See you later!");
        } finally {
            entry.close();
        }
    }

    private void gameCycle() {
        try {
            while (!board.goalCompleted()) {
                System.out.println(board);

                String typed = captureTypedValue("Type (x, y): ");

                java.util.Iterator<Integer> xy = Arrays.stream(typed.split(","))
                        .map(e -> Integer.parseInt(e.trim())).iterator();

                typed = captureTypedValue("1 - Open or 2 - (Un)Mark: ");

                if("1".equals(typed)){
                    board.open(xy.next(), xy.next());
                } else if("2".equals(typed)){
                    board.alternateMark(xy.next(), xy.next());
                }
            }
            System.out.println(board);
            System.out.println("You win !!");
        } catch (ExplosionException e) {
            System.out.println(board);
            System.out.println("You lose!");
        }
    }

    private String captureTypedValue(String text) {
        System.out.println(text);
        String typed = entry.nextLine();

        if ("exit".equalsIgnoreCase(typed)) {
            throw new SignOutException();
        }
        return typed;
    }
}
