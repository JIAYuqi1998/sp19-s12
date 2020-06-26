package bearmaps.proj2ab;

import edu.princeton.cs.algs4.In;

import java.lang.reflect.Array;
import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
    private int size;
    private ArrayList<priorityNode> minPQ;
    private HashMap<T, Integer> itemMap;
    public ArrayHeapMinPQ() {
        size = 0;
        minPQ = new ArrayList<>();
        itemMap = new HashMap<>();
        minPQ.add(null);
    }
    @Override
    public void add(T item, double priority) {
        if (itemMap.containsKey(item)) {
            throw new IllegalArgumentException("Item already exists");
        }
        minPQ.add(new priorityNode(item, priority));
        size = size + 1;
        itemMap.put(item, size);
        swim(size);
    }

    private int parent(int index) {
        if (index == 1) return 1;
        return index / 2;
    }
    private void swim(int index) {
        int parent = parent(index);
        if (minPQ.get(index).priority < minPQ.get(parent).priority) {
            exchange(minPQ.get(parent), minPQ.get(index));
            itemMap.put(minPQ.get(index).val, index);
            itemMap.put(minPQ.get(parent).val, parent);
            swim(parent);
        }
    }

    private void exchange(priorityNode n1, priorityNode n2) {
        double tempPriority = n1.priority;
        T tempVal = n1.val;
        n1.val = n2.val;
        n1.priority = n2.priority;
        n2.val = tempVal;
        n2.priority = tempPriority;
    }
    public Integer[] returnVal() {
        Integer[] returnArray = new Integer[size + 1];
        returnArray[0] = 0;
        for (int i = 1; i <= size; i++) {
            returnArray[i] = (int)minPQ.get(i).val;
        }
        return returnArray;
    }
    @Override
    public boolean contains(T item) {
        return itemMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("Empty priority queue");
        }
        return minPQ.get(1).val;
    }

    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("Empty priority queue");
        }
        T returnValue = minPQ.get(1).val;
        priorityNode lastNode = minPQ.get(size);
        minPQ.get(1).priority = lastNode.priority;
        minPQ.get(1).val = lastNode.val;
        itemMap.remove(returnValue);
        minPQ.remove(size);
        size = size - 1;
        sink(1);
        return returnValue;
    }

    private int leftChild(int index) {
        if (index * 2 > size) {
            return index;
        }
        return index * 2;
    }
    private int rightChild(int index) {
        if (index * 2 > size) {
            return index;
        }
        return index * 2 + 1;
    }
    private void sink(int index) {
        int leftChild = leftChild(index);
        int rightChild = rightChild(index);
        int usedChild = leftChild;
        if (rightChild <= size) {
            if (minPQ.get(leftChild).priority > minPQ.get(rightChild).priority) {
                usedChild = rightChild;
            }
        }

        if (usedChild != 1 && minPQ.get(usedChild).priority < minPQ.get(index).priority) {
            exchange(minPQ.get(usedChild), minPQ.get(index));
            itemMap.put(minPQ.get(index).val, index);
            itemMap.put(minPQ.get(usedChild).val, leftChild);
            sink(usedChild);
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!itemMap.containsKey(item)) {
            throw new IllegalArgumentException("No such item");
        }
        int index = itemMap.get(item);
        double oriPriority = minPQ.get(index).priority;
        minPQ.get(index).priority = priority;
        if (oriPriority < priority) {
            sink(index);
        } else swim(index);
    }


    private class priorityNode implements Comparable<priorityNode> {
        private T val;
        private double priority;
        public priorityNode(T i, double p) {
            val = i;
            priority = p;
        }
        public void setPriority(double p) {
            priority = p;
        }
        public void setVal(T i) {
            val = i;
        }

        @Override
        public int compareTo(priorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.priority, other.priority);
        }
        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((priorityNode) o).val.equals(val);
            }
        }
    }
}