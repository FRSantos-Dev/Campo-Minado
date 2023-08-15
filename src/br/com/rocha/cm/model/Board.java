package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import exception.ExplosionException;

public class Board {

    private int lines;
    private int columns;
    private int mines;

    private final List<Camp> camps = new ArrayList<>();

    public Board(int lines, int columns, int mines) {
        this.lines = lines;
        this.columns = columns;
        this.mines = mines;

        generateCamps();
        associateNeighbours();
        raffleMines();
    }

    public void open(int line, int column){
        try {
            camps.parallelStream()
                .filter(c -> c.getLine() == line && c.getColumn() == column)
                .findFirst()
                .ifPresent(c -> c.open());
        } catch (ExplosionException e) {
            camps.forEach(c -> c.setOpen(true));
            throw e;
        }
    }

    public void alternateMark(int line, int column){
        camps.parallelStream()
            .filter(c -> c.getLine() == line && c.getColumn() == column)
            .findFirst()
            .ifPresent(c -> c.alternateMark());
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
            int aleatory = (int) (Math.random() * camps.size());
            camps.get(aleatory).mined();
            armedMines = camps.stream().filter(mined).count();
        } while (armedMines < mines);
    }

    public boolean goalCompleted() {
        return camps.stream().allMatch(c -> c.goalCompleted());
    }

    public void restart() {
        camps.stream().forEach(c -> c.restart());
        raffleMines();
    }

    
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("  ");
        for(int c = 0; c < columns; c++){
            sb.append(" ");
            sb.append(c);
            sb.append(" ");
        }

        sb.append("\n");

        int i = 0;
        for(int l = 0; l < lines; l++){
            sb.append(l);
            sb.append(" ");
            for(int c = 0; c < columns; c++){
                sb.append(" ");
                sb.append(camps.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
