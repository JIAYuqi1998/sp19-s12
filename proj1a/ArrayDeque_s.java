import java.util.Objects;

@SuppressWarnings("unchecked")
public class ArrayDeque_s<T> {
    private T[] a;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double usageFactor =  0.25;
    private boolean flag1 = true;
    private boolean flag2 = true;
    public ArrayDeque_s() {
        size = 0;
        a = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }
    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            a = (T[]) this.resize();
            nextFirst = a.length - size - 1 + nextFirst;
        }
        size = size + 1;
        a[nextFirst] = item;
        nextFirst = minusOne(nextFirst);

/*        nextFirst = nextFirst - 1;
        if (nextFirst < 0) {
            nextFirst = a.length - 1;
        }*/
        /*the above can be changed into a function*/
    }
    public int minusOne(int index) {
        return (index - 1 + a.length) % a.length;
    }
    public void addLast(T item) {
        if (nextFirst == nextLast) {
            a = (T[]) this.resize();
            nextFirst = a.length - size + nextFirst - 1;
        }
        size = size + 1;
        a[nextLast] = item;
        nextLast = addOne(nextLast);
    }
/*        nextLast = nextLast + 1;
        if (nextLast == a.length) {
            nextLast = 0;*/
/*
        the above can be changed into a function
*/
    public int addOne(int index) {
        return (index + 1) % a.length;
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
            int ptr = nextFirst + 1;
            while (ptr != nextLast) {
                System.out.print(a[ptr] + " ");
                ptr = ptr + 1;
                if (ptr == a.length) {
                    ptr = 0;
                }
            }
            System.out.println();
        }
    }
    public T removeFirst() {
        if (size == 0) {
            System.out.println("Empty List");
            return null;
        } else {
            if ((double) size / a.length < usageFactor && a.length > 10) {
                int oriLen = a.length;
                int desLen = (int) (oriLen - size) / 2 + size;
                Object[] arr = new Object[desLen];
                if (nextFirst < nextLast) {
                    System.arraycopy(this.a, nextFirst + 1, arr, desLen - size, size);
                    this.a = (T[]) arr;
                    nextLast = 0;
                    nextFirst = desLen - size - 1;
                } else {
                    System.arraycopy(this.a, 0, arr, 0, nextLast);
                    System.arraycopy(this.a, nextFirst + 1, arr, desLen - size + nextLast,
                            size - nextLast);
                    this.a = (T[]) arr;
                    nextFirst = desLen - size + nextLast - 1;
                }
            }
            nextFirst = nextFirst + 1;
            if (nextFirst == a.length) {
                nextFirst = 0;
                flag1 = false;
            }
            size = size - 1;
            return a[nextFirst];
        }
    }
    public T removeLast() {
        if (size == 0) {
            System.out.println("Empty List");
            return null;
        } else {
            if ((double) size / a.length < usageFactor && a.length > 10) {
                int oriLen = a.length;
                int desLen = (int) (oriLen - size) / 2 + size;
                Object[] arr = new Object[desLen];
                if (nextFirst < nextLast) {
                    System.arraycopy(this.a, nextFirst + 1, arr, desLen - size, size);
                    this.a = (T[]) arr;
                    nextLast = 0;
                    nextFirst = desLen - size - 1;
                } else {
                    System.arraycopy(this.a, 0, arr, 0, nextLast);
                    System.arraycopy(this.a, nextFirst + 1, arr, desLen - size + nextLast,
                            size - nextLast);
                    this.a = (T[]) arr;
                    nextFirst = desLen - size + nextLast - 1;
                }
            }
            nextLast = nextLast - 1;
            if (nextLast < 0) {
                nextLast = a.length - 1;
                flag2 = false;
            }
            size = size - 1;
            return a[nextLast];
        }
    }
    public T get(int index) {
        if (nextFirst + index + 1 >= a.length) {
            return a[nextFirst + index - a.length + 1];
        }
        return a[nextFirst + index + 1];
    }
    private Object[] resize() {
        int oriLen = a.length;
        int desLen = (int) (oriLen / this.usageFactor);
        Object[] arr = new Object[desLen];
        System.arraycopy(this.a, 0, arr, 0, nextFirst + 1);
        System.arraycopy(this.a, nextFirst + 1, arr, desLen - oriLen + nextFirst + 1,
                oriLen - nextFirst - 1);
        return arr;
    }
    private void resize(int capacity) {
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
}
