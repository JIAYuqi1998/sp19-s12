import java.util.Iterator;
import java.util.LinkedList;
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

    public void printInOrder() {
        printInOrder(root);
    }

    // print the binary search tree in increasing order of keys.
    //General idea: print the left tree first, then the node itself, then the right tree.
    // use recursion to do this.
    private void printInOrder(Node n) {
        if (n == null) {
            return;
        } else {
            if (n.right == null && n.left == null) {
                print(n);
            } else if (n.right == null) {
                printInOrder(n.left);
                print(n);
            } else if (n.left == null) {
                print(n);
                printInOrder(n.right);
            } else {
                printInOrder(n.left);
                print(n);
                printInOrder(n.right);
            }
        }
    }
    private void print(Node n) {
        System.out.println(n.key + ": " + n.val);
    }
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (root.get(key) == null) {
            return null;
        } else {
            V returnValue = root.get(key).val;
            Node deleteNode = root.get(key);
            if (deleteNode.left == null) {
                if (deleteNode.right == null) {
                    parent(key);
                } else {
                    deleteNode.val = deleteNode.right.val;
                    deleteNode.key = deleteNode.right.key;
                    deleteNode.left = deleteNode.right.left;
                    deleteNode.right = deleteNode.right.right;
                }
            } else {
                Node nodeBeforeReplace = deleteNode;
                Node nextNode = deleteNode.left;
                int count = 1;
                while (nextNode.right != null) {
                    if (count == 1) {
                        nodeBeforeReplace = nodeBeforeReplace.left;
                        count = count + 1;
                    } else {
                        nodeBeforeReplace = nodeBeforeReplace.right;
                    }
                    nextNode = nextNode.right;
                }
                deleteNode.key = nextNode.key;
                deleteNode.val = nextNode.val;
                if (count == 1) {
                    nodeBeforeReplace.left = nextNode.left;
                } else {
                    nodeBeforeReplace.right = null;
                }
            }
            size = size - 1;
            return returnValue;
        }
    }
    private void parent(K key) {
        if (key == null) {
            return;
        }
        Node ptr = root;
        Node parent = root;
        if (ptr.key.equals(key)) {
            root = null;
            return;
        } else if (key.compareTo(ptr.key) > 0){
            ptr = ptr.right;
            if (key.compareTo(ptr.key)== 0) {
                parent.right = null;
                return;
            }
        } else {
            ptr = ptr.left;
            if (key.compareTo(ptr.key)== 0) {
                parent.left = null;
                return;
            }
        }
        while (true) {
            if (key.compareTo(ptr.key) > 0) {
                ptr = ptr.right;
                parent = parent.right;
                if (key.compareTo(ptr.key) == 0) {
                    parent.right = null;
                    return;
                }
            } else {
                ptr = ptr.left;
                parent = parent.left;
                if (key.compareTo(ptr.key) == 0) {
                    parent.left = null;
                    return;
                }
            }
        }
    }
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (get(key) != value) {
            System.out.println("Fail to match the entered key with value");
            return null;
        }
        remove(key);
        return value;
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
