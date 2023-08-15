package test.br.com.rocha.cm.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import exception.ExplosionException;
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

    @Test
    void testNeighbourDistance2() {
        Camp neighbour = new Camp(2, 2);
        boolean result = camp.addNeighbour(neighbour);
        assertTrue(result);
    }

    @Test
    void testNotNeighbour() {
        Camp neighbour = new Camp(1, 1);
        boolean result = camp.addNeighbour(neighbour);
        assertTrue(!result);
    }

    @Test
    void testDefaultValueCheckedAttribute() {
        assertFalse(camp.isMarked());
    }

    @Test
    void testAlternateMark() {
        assertFalse(camp.isMarked());
    }

    @Test
    void testAlternateMarkDualCall() {
        camp.alternateMark();
        camp.alternateMark();
        assertFalse(camp.isMarked());
    }

    @Test
    void testOpenNotMinedNotMarked() {
        assertTrue(camp.open());
    }

    @Test
    void testOpenNotMinedMarked() {
        camp.alternateMark();
        camp.mined();
        assertTrue(camp.open());
    }

    @Test
    void testOpenMinedNotMarked() {
        camp.mined();

        assertThrows(ExplosionException.class, () -> {
            camp.open();
        });
    }

    @Test
    void testOpenWithNeighbours1() {
        Camp camp11 = new Camp(1, 1);
        Camp camp22 = new Camp(2, 2);
        camp22.addNeighbour(camp11);

        camp.addNeighbour(camp22);
        camp.isOpen();

        assertTrue(camp22.isOpen() && camp11.isOpen());
    }

    @Test
    void testOpenWithNeighbours2() {
        Camp camp11 = new Camp(1, 1);
        Camp camp12 = new Camp(1, 1);
        camp12.isMined();

        Camp camp22 = new Camp(2, 2);
        camp22.addNeighbour(camp11);
        camp22.addNeighbour(camp12);

        camp.addNeighbour(camp22);
        camp.isOpen();

        assertTrue(camp22.isOpen() && !camp11.isOpen());
    }
}
