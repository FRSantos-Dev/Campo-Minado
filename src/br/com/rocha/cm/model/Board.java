package model;

import java.util.ArrayList;
import java.util.List;

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

        private void generateCamps(){
            for(int line = 0; line < lines; line++){
                for(int column = 0; column < columns; column++){
                    camps.add(new Camp(line, column));
                }
            }
    }

        private void associateNeighbours() {
            for(Camp c1: camps){
                for(Camp c2: camps){
                    c1.addNeighbour(c2);
                }
            }
    }

        private void raffleMines() {
    }
}
