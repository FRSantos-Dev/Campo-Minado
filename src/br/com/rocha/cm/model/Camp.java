package model;

import java.util.ArrayList;
import java.util.List;

import test.br.com.rocha.cm.model.exception.ExplosionException;

public class Camp {

    private final int line;
    private final int column;

    private boolean open = false;
    private boolean mined = false;
    private boolean marked = false;

    private List<Camp> neighbours = new ArrayList<>();

    Camp(int line, int column) {
        this.line = line;
        this.column = column;
    }

    boolean addNeighbour(Camp neighbour) {
        boolean differentLine = line != neighbour.line;
        boolean differentColumn = column != neighbour.column;
        boolean diagonal = differentLine && differentColumn;

        int deltaLine = Math.abs(line - neighbour.line);
        int deltaColumn = Math.abs(column - neighbour.column);
        int deltaGeneral = deltaColumn + deltaLine;

        if (deltaGeneral == 1 && !diagonal) {
            neighbours.add(neighbour);
            return true;
        } else if (deltaGeneral == 2 && diagonal) {
            neighbours.add(neighbour);
            return true;
        } else {
            return false;
        }
    }

    void alternateMark() {
        if (!open) {
            marked = !marked;
        }
    }

    boolean open() {
        
        if (!open && !marked) {
            open = true;
            
            if (mined) {
                throw new ExplosionException();
            }

            if(safeNeighbourhood()){
                neighbours.forEach(v -> v.open());
            }
            return true;
        }else{
        return false;
        }
    }

    boolean safeNeighbourhood() {
        return neighbours.stream().noneMatch(v -> v.mined);
    }

    void mined(){
        mined = true;
    }

    public boolean isMarked(){
        return marked;
    }
}
