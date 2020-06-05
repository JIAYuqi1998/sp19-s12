package hw2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.introcs.StdStats;

import static edu.princeton.cs.algs4.StdRandom.uniform;


public class PercolationStats {
    private double[] threshold;
    private Percolation[] grid;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        threshold = new double[T];
        for (int i = 0; i < N; i++) {
            grid[i] = pf.make(N);
            for (int j = 0; i < T; i++) {
                int thre = 0;
                while (true) {
                    int col = uniform(0, N);
                    int row = uniform(0, N);
                    grid[i].open(row, col);
                    thre += 1;
                    if (grid[i].percolates()) {
                        break;
                    }
                }
                threshold[j] = thre;
            }
        }
        // generate random position; open the position; repeat until percolates.

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(threshold.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(threshold.length);
    }
}
