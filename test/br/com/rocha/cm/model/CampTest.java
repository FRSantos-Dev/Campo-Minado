package test.br.com.rocha.cm.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Camp;

public class CampTest {

    private Camp camp;

    @Before
    void initiateCamp() {
        camp = new Camp(3, 3);
    }

    @Test
    void testRealNeighbourDistance1() {
        Camp neighbour = new Camp(3, 2);
        boolean result = camp.addNeighbour(neighbour);
        assertTrue(result);

    }

    @Test
    void testRealNeighbourDistance1Left() {
        Camp neighbour = new Camp(3, 2);
        boolean result = camp.addNeighbour(neighbour);
        assertTrue(result);
    }

    @Test
    void testRealNeighbourDistance1Right() {
        Camp neighbour = new Camp(3, 4);
        boolean result = camp.addNeighbour(neighbour);
        assertTrue(result);
    }

    @Test
    void testRealNeighbourDistance1Top() {
        Camp neighbour = new Camp(2, 3);
        boolean result = camp.addNeighbour(neighbour);
        assertTrue(result);
    }

    @Test
    void testRealNeighbourDistance1Bottom() {
        Camp neighbour = new Camp(2, 3);
        boolean result = camp.addNeighbour(neighbour);
        assertTrue(result);
    }

    
}
