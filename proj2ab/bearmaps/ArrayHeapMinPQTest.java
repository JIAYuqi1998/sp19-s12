package bearmaps;

import edu.princeton.cs.algs4.In;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {

    @Test
    public void testAdd() {
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<Integer>();
        minPQ.add(1,10.0);
        minPQ.add(2,5.0);
        minPQ.add(3,7.0);
        minPQ.add(4,3.0);
        minPQ.add(5,2.0);
        minPQ.add(6,11.0);
        minPQ.add(7,5.1);
        minPQ.add(8,3.5);
        minPQ.add(9,3.9);
        Integer expected = 5;
        int expectedSize = 9;
        assertEquals(expected, minPQ.getSmallest());
        assertTrue(minPQ.contains(2));
        assertEquals(expectedSize, minPQ.size());
    }

    @Test
    public void testRemove() {
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<Integer>();
        minPQ.add(1,10.0);
        minPQ.add(2,5.0);
        minPQ.add(3,7.0);
        minPQ.add(4,3.0);
        minPQ.add(5,2.0);
        minPQ.add(6,11.0);
        minPQ.add(7,5.1);
        minPQ.add(8,3.5);
        minPQ.add(9,3.9);
        minPQ.removeSmallest();
        Integer expected = 4;
        int expectedSize = 8;
        assertEquals(expected, minPQ.getSmallest());
        assertTrue(minPQ.contains(2));
        assertEquals(expectedSize, minPQ.size());
    }

    @Test
    public void testChange() {
        ArrayHeapMinPQ minPQ = new ArrayHeapMinPQ();
        minPQ.add(1,10.0);
        minPQ.add(2,5.0);
        minPQ.add(3,7.0);
        minPQ.add(4,3.0);
        minPQ.add(5,2.0);
        minPQ.add(6,11.0);
        minPQ.add(7,5.1);
        minPQ.add(8,3.5);
        minPQ.add(10,3.9);
        minPQ.changePriority(1,1);
        assertEquals(1, minPQ.getSmallest());
    }
}