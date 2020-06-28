import edu.princeton.cs.algs4.Queue;
import java.util.concurrent.ThreadLocalRandom;

import edu.princeton.cs.algs4.Quick;
import org.junit.Assert;
import org.junit.Test;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue quicksortQueue = new Queue();
        for (int i = 0; i < 100; i++) {
            int j = ThreadLocalRandom.current().nextInt(1, 1000 + 1);
            quicksortQueue.enqueue(j);
        }
        QuickSort.quickSort(quicksortQueue);
        Assert.assertTrue(isSorted(quicksortQueue));
    }

    @Test
    public void testMergeSort() {
        Queue mergesortQueue = new Queue();
        for (int i = 0; i < 100; i++) {
            int j = ThreadLocalRandom.current().nextInt(1, 1000 + 1);
            mergesortQueue.enqueue(j);
        }
        MergeSort.mergeSort(mergesortQueue);
        Assert.assertTrue(isSorted(mergesortQueue));

    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
