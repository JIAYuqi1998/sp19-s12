import java.util.Objects;

@SuppressWarnings("unchecked")
public class ArrayDeque<T> {
    private T[] a;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double usageFactor =  0.25;
    private int initCapacity = 8;
    private int rFactor = 2;
    public ArrayDeque() {
        size = 0;
        a = (T[]) new Object[initCapacity];
        nextFirst = 3;
        nextLast = 4;
    }
    public int minusOne(int index) {
        return (index - 1 + a.length) % a.length;
    }
    public int addOne(int index) {
        return (index + 1) % a.length;
    }
    public void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int curr = addOne(nextFirst);
        for(int i = 0; i < size; i++) {
            newArray[i] = a[curr];
            curr = addOne(curr);
        }
        a = newArray;
        nextFirst = capacity - 1;
        nextLast = size;
    }
    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            resize(a.length * rFactor);
        }
        size = size + 1;
        a[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
    }

    public void addLast(T item) {
        if (nextFirst == nextLast) {
            resize(a.length * rFactor);
        }
        size = size + 1;
        a[nextLast] = item;
        nextLast = addOne(nextLast);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            System.out.println("Empty List");
        } else {
            for(int i = 0; i < size; i++) {
                System.out.println(get(i) + " ");
            }
            System.out.println();
        }
    }
    public T removeFirst() {
        if (size == 0) {
            System.out.println("Empty List");
            return null;
        } else {
            nextFirst = addOne(nextFirst);
            T returnItem = a[nextFirst];
            a[nextFirst] = null;
            size = size - 1;
            if (a.length >= 16 && size < a.length * usageFactor) {
                resize(a.length / 2);
            }
            return returnItem;
        }
    }
    public T removeLast() {
        if (size == 0) {
            System.out.println("Empty List");
            return null;
        } else {
            nextLast = minusOne(nextLast);
            T returnItem = a[nextLast];
            a[nextLast] = null;
            size = size - 1;
            if (a.length >= 16 && size < a.length * usageFactor) {
                resize(a.length / 2);
            }

            return returnItem;
        }
    }
    public T get(int index) {
        if (nextFirst + index + 1 >= a.length) {
            return a[nextFirst + index - a.length + 1];
        }
        return a[nextFirst + index + 1];
    }

}
