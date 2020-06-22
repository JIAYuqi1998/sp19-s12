
package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

/**
 * Created by hug. Demonstrates how you can use either
 * System.currentTimeMillis or the Princeton Stopwatch
 * class to time code.
 */
public class TimingTestDemo {
    public static void main(String[] args) {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<Integer>();
        Random r = new Random();
        for (int i = 0; i < 1000000; i += 1) {
            test.add(i, 100000 - i);
        }
        for (int i = 0; i < 1000000; i += 1) {
            test.changePriority(i, r.nextDouble());
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i += 1) {
            test.removeSmallest();
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 1000.0 + " seconds.");
    }
}