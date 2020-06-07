package hw3.hash;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

public class HashTableVisualizer {

    public static void main(String[] args) {
        /* scale: StdDraw scale
           N:     number of items
           M:     number of buckets */

        /* After getting your simpleOomages to spread out
           nicely, be sure to try
           scale = 0.5, N = 2000, M = 100. */

        double scale = 0.5;
        int N = 2000;
        int M = 10;

        HashTableDrawingUtility.setScale(scale);
        List<Oomage> oomies = new ArrayList<>();
        for (int i=0; i!=N; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j=0; j!=6; j++) {
                temp.add(StdRandom.uniform(0, 255));
            }
            temp.add(123);
            temp.add(231);
            temp.add(135);
            temp.add(88);
            oomies.add(new ComplexOomage(temp));
/*            System.out.println(temp.hashCode());
            System.out.println(temp.hashCode() & 0x7FFFFFFF);
            System.out.println("======");*/
        }
/*        for (int i = 0; i < N; i += 1) {
//            oomies.add(SimpleOomage.randomSimpleOomage());
            oomies.add(ComplexOomage.randomComplexOomage());
        }*/
        visualize(oomies, M, scale);
    }

    public static void visualize(List<Oomage> oomages, int M, double scale) {
        HashTableDrawingUtility.drawLabels(M);
        int[] numInBucket = new int[M];
        for (Oomage s : oomages) {
            int bucketNumber = (s.hashCode() & 0x7FFFFFFF) % M;
            double x = HashTableDrawingUtility.xCoord(numInBucket[bucketNumber]);
            numInBucket[bucketNumber] += 1;
            double y = HashTableDrawingUtility.yCoord(bucketNumber, M);
            s.draw(x, y, scale);
        }
    }
} 
