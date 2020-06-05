package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;


/* The virtual top node is connected to all open site in the first row.
 * The virtual bottom node is connected to all open site in the last row.
 * If the top node and the bottom node is connected, then the system percolate.
 * Backwash: Some open sites cannot be connected to the top but can be connected to the bottom,
 * since the top and the bottom are connected, those sites can still be connected to the top indirectly.
 * Solution: maintain two WeightedQuickUnionUF, use the one which only has top node to check if is full,
 * and use the other which have both to check if percolate
 */

public class Percolation {
    private WeightedQuickUnionUF grid;
    private int size;
    private boolean[] status;
    private WeightedQuickUnionUF ufExcludeBottom;
    //use status to track whether the site is open or not.
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        grid = new WeightedQuickUnionUF(N * N + 2);
        size = N;
        status = new boolean[N * N + 1];
        ufExcludeBottom = new WeightedQuickUnionUF(N * N + 1);
        java.util.Arrays.fill(status, false);
    }

    // return the 1-D location of a point in the grid (0 - n2)
    private int xyTo1D(int row, int col) {
        return size * row + col + 1;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (status[xyTo1D(row, col)]) {
            return;
        }
        status[xyTo1D(row, col)] = true;
        // connect with the top virtual item
        if (row == 0) {
            grid.union(xyTo1D(row, col), 0);
            ufExcludeBottom.union(xyTo1D(row, col), 0);

        }
        // connect with the bottom virtual item
        if (row == size - 1) {
            grid.union(xyTo1D(row, col), size * size + 1);
        }
        //up
        if (row - 1 >= 0) {
            if (status[xyTo1D(row - 1, col)]) {
                grid.union(xyTo1D(row, col), xyTo1D(row - 1, col));
                ufExcludeBottom.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            }
        }
        //below
        if (row + 1 <= size - 1) {
            if (status[xyTo1D(row + 1, col)]) {
                grid.union(xyTo1D(row, col), xyTo1D(row + 1, col));
                ufExcludeBottom.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }
        }
        //left
        if (col - 1 >= 0) {
            if (status[xyTo1D(row, col - 1)]) {
                grid.union(xyTo1D(row, col), xyTo1D(row, col - 1));
                ufExcludeBottom.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            }
        }
        //right
        if (col + 1 <= size - 1) {
            if (status[xyTo1D(row, col + 1)]) {
                grid.union(xyTo1D(row, col), xyTo1D(row, col + 1));
                ufExcludeBottom.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return status[xyTo1D(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
            throw new IndexOutOfBoundsException();
        }

        return ufExcludeBottom.connected(xyTo1D(row, col), 0);
    }

    // number of open sites
    public int numberOfOpenSites() {
        int sum = 0;
        for (boolean i : status) {
            if (i) {
                sum = sum + 1;
            }
        }
        return sum;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(0, size * size + 1);
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
        Percolation test = new Percolation(10);
        System.out.println(test.isFull(0,0));
        test.open(0, 0);
/*        test.open(3, 2);
        test.open(3, 4);
        test.open(2, 2);
        test.open(2, 1);
        test.open(1, 1);
        test.open(4, 2);*/
        boolean returnValue = test.percolates();
        System.out.println(returnValue);
    }

}
