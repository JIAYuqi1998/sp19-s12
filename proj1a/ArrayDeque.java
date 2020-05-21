@SuppressWarnings("unchecked")
public class ArrayDeque<T> {
    T[] a;
    int size;
    int nextFirst;
    int nextLast;
    double UsageFactor =  0.25;
    boolean flag1 = true;
    boolean flag2 = true;
    public ArrayDeque() {
        size = 0;
        a = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }
    public ArrayDeque(ArrayDeque other) {
    }
    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            a = (T[]) this.resize();
            nextFirst = a.length - size -1 + nextFirst;
        }
        size = size + 1;
        a[nextFirst] = item;
        nextFirst = nextFirst - 1;
        if(nextFirst < 0) {
            nextFirst = a.length - 1;
        }
    }
    public void addLast(T item) {
        if (nextFirst == nextLast) {
            a = (T[]) this.resize();
            nextFirst = a.length - size;
        }
        size = size + 1;
        a[nextLast] = item;
        nextLast = nextLast + 1;
        if (nextLast == a.length -1) {
            nextLast = 0;
        }
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
            }System.out.println();
        }
    }
    public T removeFirst() {
        if (size == 0) {
            System.out.println("Empty List");
            return null;
        }else {
            if((double)size/a.length < UsageFactor && a.length > 10) {
                int ori_len = a.length;
                int des_len = (int) (ori_len - size)/2 + size;
                Object[] arr = new Object[des_len];
                if (flag1) {
                    System.arraycopy(this.a,0,arr,0,nextLast);
                    System.arraycopy(this.a, nextFirst + 1, arr, des_len - ori_len + nextFirst + 1,
                            ori_len - nextFirst - 1);
                    this.a = (T[]) arr;
                    nextFirst = des_len - ori_len + nextFirst;
                } else {
                    System.arraycopy(this.a, nextFirst + 1, arr, nextFirst + 1,
                            nextLast - nextFirst - 1);
                    this.a = (T[]) arr;
                }
            }
            nextFirst = nextFirst + 1;
            if (nextFirst == a.length){
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
        }else {
            if((double)size/a.length < UsageFactor && a.length > 10) {
                int ori_len = a.length;
                int des_len = (int) (ori_len - size)/2 + size;
                Object[] arr = new Object[des_len];
                if (flag2) {
                    System.arraycopy(this.a,0,arr,0,nextLast);
                    System.arraycopy(this.a, nextFirst + 1, arr, des_len - ori_len + nextFirst + 1,
                            ori_len - nextFirst - 1);
                    this.a = (T[]) arr;
                    nextFirst = des_len - ori_len + nextFirst;
                } else {
                    System.arraycopy(this.a, nextFirst + 1, arr, des_len - nextLast + nextFirst,
                            nextLast - nextFirst);
                    this.a = (T[]) arr;
                    nextFirst = des_len - nextLast + nextFirst - 1;
                    nextLast = des_len - 1;
                }
            }
            nextLast = nextLast - 1;
            if (nextLast < 0){
                nextLast = a.length - 1;
                flag2= false;
            }
            size = size - 1;
            return a[nextLast];
        }
    }
    public T get(int index){
        return a[nextFirst + index + 1];
    }
    private Object[] resize(){
        int ori_len = a.length;
        int des_len = (int) (ori_len/ this.UsageFactor);
        Object[] arr = new Object[des_len];
        System.arraycopy(this.a, 0, arr, 0, nextFirst + 1);
        System.arraycopy(this.a, nextFirst + 1, arr, des_len - ori_len + nextFirst + 1,
                ori_len - nextFirst - 1);
        return arr;
    }
}
