import java.util.ArrayList;

public class UnionFind {
    int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        // set all the parents to be -1 to symbolize that they are disjoint
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid vertex. */
    private void validate(int v1) {
        // TODO
        if (v1 < 0 || v1 >= parent.length) {
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        if (parent[v1] < 0) {
            return -parent[v1];
        }
        return sizeOf(parent[v1]);
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean isConnected(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        if (find(v1) == find(v2)) {
            return true;
        }
        return false;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Connecting a
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void connect(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        if (isConnected(v1, v2)) {
            return;
        }
        int rootV1 = find(v1);
        int rootV2 = find(v2);
        int sizeV1 = sizeOf(rootV1);
        int sizeV2 = sizeOf(rootV2);

        if (sizeV1 > sizeV2) {
            parent[rootV2] = rootV1;
            parent[rootV1] -= sizeV2;
        } else {
            parent[rootV1] = rootV2;
            parent[rootV2] -= sizeV1;
        }
    }

    /* Returns the root of the set v1 belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int v1) {
        // TODO
        validate(v1);
        if (parent[v1] >= 0) {
            if (parent[parent[v1]] >= 0) {
                parent[v1] = parent[parent[v1]];
            }
            return find(parent[v1]);
        } else {
            return v1;
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        uf.connect(4, 3);
        uf.connect(3, 8);
        uf.connect(6, 5);
        uf.connect(9, 4);
        uf.connect(2, 1);
        uf.connect(5, 0);
        uf.connect(7, 2);
        uf.connect(6, 1);
        uf.connect(1, 0);
        uf.connect(6, 8);
        uf.find(8);
        uf.find(4);
    }

}
