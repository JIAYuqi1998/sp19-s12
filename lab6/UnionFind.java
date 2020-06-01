public class UnionFind {
    int[] parent;
    int[] size;
    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = -1;
            size[i] = 1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >= parent.length){
            throw new IllegalArgumentException("Index " + vertex + " is out of bounds!");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        return size[v1];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        if (parent[v1] == -1) {
            return -size[v1];
        }
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        int root1 = find(v1);
        int root2 = find(v2);
        boolean returnValue = root1 == root2;
        while (parent[v1] != root1) {
            int temp1 = v1;
            v1 = parent[v1];
            parent[temp1] = root1;
            size[v1] -= 1;
        }
        while (parent[v2] != root2) {
            int temp2 = v2;
            v2 = parent[v2];
            parent[temp2] = root2;
            size[v2] -= 1;
        }
        return returnValue;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) { } else {
            if (size[v1] <= size[v2]) {
                parent[p1] = p2;
                size[p2] += size[p1];
            } else {
                parent[p2] = p1;
                size[p1] += size[p2];
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int i = vertex;
        while (parent[i] >= 0) {
            i = parent[i];
        } return i;
    }

    public static void main(String[] args) {
        UnionFind x = new UnionFind(10);
        x.union(1,0);
        x.union(2,0);
        x.union(3,0);
        x.union(4,5);
        x.union(6,5);
        x.union(4,3);
        boolean y = x.connected(3,6);
        x.connected(2,4);
        x.connected(3,0);
    }
}
