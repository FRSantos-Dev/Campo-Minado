package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Board {

    private int lines;
    private int columns;
    private int mines;

    private final List<Camp> camps = new ArrayList<>();

    public Board(int linhas, int colunas, int minas) {
        this.lines = linhas;
        this.columns = colunas;
        this.mines = minas;

        generateCamps();
        associateNeighbours();
        raffleMines();
    }

    private void generateCamps() {
        for (int line = 0; line < lines; line++) {
            for (int column = 0; column < columns; column++) {
                camps.add(new Camp(line, column));
            }
        }
    }

    private void associateNeighbours() {
        for (Camp c1 : camps) {
            for (Camp c2 : camps) {
                c1.addNeighbour(c2);
            }
        }
    }

    private void raffleMines() {
        long armedMines = 0;
        Predicate<Camp> mined = c -> c.isMined();

        do {
            armedMines = camps.stream().filter(mined).count();
            int aleatory = (int) (Math.random() * camps.size());
            camps.get(aleatory).mined();
        } while (armedMines < mines);
    }

    public boolean goalCompleted() {
        return camps.stream().allMatch(c -> c.goalCompleted());
    }

    public void restart() {
        camps.stream().forEach(c -> c.restart());
        raffleMines();
    }

    @Override
    public String toString() {
        return "Board [lines=" + lines + ", columns=" + columns
                + ", mines=" + mines + ", camps=" + camps + "]";
    }

}
