package hw3.hash;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* DONE:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        double lowerBound = (double) oomages.size() / 50;
        double upperBound = oomages.size() / 2.5;
        List<List<Integer>> bucket = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            bucket.add(new ArrayList<>());
        }
        for (Oomage i : oomages) {
            int bucketNum = (i.hashCode() & 0x7FFFFFFF) % M;
            bucket.get(bucketNum).add(i.hashCode());
        }
        for (List<Integer> list : bucket) {
            int size = list.size();
            if (size < lowerBound || size > upperBound) {
                return false;
            }
        }
        return true;
    }
}
