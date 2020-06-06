import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size;
    private Node root;
    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        } else {
            return root.get(key) != null;
        }
    }

    /** Returns the value corresponding to KEY or null if no such value exists. */
    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        Node lookup = root.get(key);
        if (lookup == null) {
            return null;
        }
        return lookup.val;
    }

    @Override
    public int size() {
        return size;
    }
    /** Inserts the key-value pair of KEY and VALUE into this dictionary,
     *  replacing the previous value associated to KEY, if any. */
    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value, null, null);
        } else {
            Node ptr = root;
            while (ptr != null) {
                if (key.compareTo(ptr.key) < 0) {
                    if (ptr.left == null) {
                        ptr.left = new Node(key, value, null, null);
                        break;
                    }
                    ptr = ptr.left;
                }
                if (key.compareTo(ptr.key) > 0) {
                    if (ptr.right == null) {
                        ptr.right = new Node(key, value, null, null);
                        break;
                    }
                    ptr = ptr.right;
                }
                if (key.compareTo(ptr.key) == 0) {
                    ptr.val = value;
                }
            }
        }
        size = size + 1;


    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
    // make a node class to store each entry of BST.
    private class Node {
        K key;
        V val;
        Node left;
        Node right;
        Node(K k, V v, Node l, Node r) {
            key = k;
            val = v;
            left = l;
            right = r;
        }
        Node get(K k) {
            if (k == null) {
                return null;
            }
            if (k.compareTo(key) > 0) {
                if (right == null) {
                    return null;
                }
                return right.get(k);
            }
            if (k.compareTo(key) < 0) {
                if (left == null) {
                    return null;
                }
                return left.get(k);
            }
            return this;
        }
    }
}
