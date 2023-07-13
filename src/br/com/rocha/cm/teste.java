

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;



class teste{

    @Test
    void testIgual(){

        int a = 1 + 1;
        assertEquals(2, a);
    }

    @Test
    void testIgualTres(){
        int x = 2 + 10 - 9;
        assertEquals(3, x);
    }
}