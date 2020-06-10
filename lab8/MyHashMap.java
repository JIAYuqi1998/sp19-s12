import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V>{
    private int size;
    private HashSet<K> mySet;
    private ArrayList<Node>[] buckets;
    private double loadFactor;
    /**
     * @param DEFAULT_BIN_SIZE The default binSize.
     */
    private static final int DEFAULT_BIN_SIZE = 16;
    /**
     * @param DEFAULT_LOAD_FACTOR The default loadFactor.
     */
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    public MyHashMap(){
        this(DEFAULT_BIN_SIZE,DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        size = 0;
        this.loadFactor = loadFactor;
        mySet = new HashSet<K>();
        buckets = new ArrayList[initialSize];
        for (int i = 0; i < initialSize; i++) {
            buckets[i] = new ArrayList<Node>();
        }
    }

    private class Node<K, V> {
        K key;
        V val;
        Node(K k, V v) {
            this.key = k;
            this.val = v;
        }
    }
    @Override
    public void clear() {
        size = 0;
        mySet.clear();
        buckets = new ArrayList[DEFAULT_BIN_SIZE];
    }

    @Override
    public boolean containsKey(K key) {
        return mySet.contains(key);
    }

    private int hash(K key) {
        return ((key.hashCode() & 0x7fffffff) % buckets.length);
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int bucketIndex = hash(key);
        ArrayList<Node> ithBucket = buckets[bucketIndex];
        for (Node i : ithBucket) {
            if (i.key.equals(key)) {
                return (V) i.val;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (size() / buckets.length > loadFactor) {
            resize(size * 2);
        }
        int bucketIndex = hash(key);
        ArrayList<Node> ithBucket = buckets[bucketIndex];
        if (containsKey(key)) {
            for (Node i : ithBucket) {
                if (i.key.equals(key)) {
                    i.val = value;
                    return;
                }
            }
        }
        ithBucket.add(new Node(key,value));
        mySet.add(key);
        size = size + 1;
    }

    private void resize(int changedSize) {
        size = 0;
        mySet = new HashSet<K>();
        ArrayList<Node> bucketCopy = new ArrayList<>();
        for (ArrayList<Node> i : buckets) {
            if (i != null) {
                for (Node j : i) {
                    bucketCopy.add(j);
                }
            }
        }
        buckets = new ArrayList[changedSize];

        for (int i = 0; i < changedSize; i++) {
            buckets[i] = new ArrayList<Node>();
        }

        for (Node i : bucketCopy) {
            this.put((K)i.key, (V)i.val);
        }
    }
    @Override
    public Set keySet() {
        return mySet;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        return new HashMapIterator();
    }
    private class HashMapIterator implements Iterator{
        Iterator myIterator = mySet.iterator();
        @Override
        public boolean hasNext() {
            return myIterator.hasNext();
        }

        @Override
        public V next() {
            K key = (K)myIterator.next();
            return get(key);
        }
    }
}
