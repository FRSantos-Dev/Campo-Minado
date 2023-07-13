package model;

import java.util.ArrayList;
import java.util.List;

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
    
}
