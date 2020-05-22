@SuppressWarnings("unchecked")
public class LinkedListDeque<T> {
    private class Stuffnode {
        private Stuffnode previous;
        private T item;
        private Stuffnode next;
        private Stuffnode(Stuffnode p, T first, Stuffnode n) {
            this.item = first;
            this.next = n;
            this.previous = p;
        }
    }
    private Stuffnode frontSentinel = new Stuffnode(null, (T) "fsentinel", null);
    private Stuffnode backSentinel = new Stuffnode(null, (T) "bsentinel", null);
    private int size;
    public LinkedListDeque() {
        this.frontSentinel.next = this.backSentinel;
        this.backSentinel.previous = this.frontSentinel;
        this.size = 0;
    }
/*    public LinkedListDeque(LinkedListDeque other) {
        this.frontSentinel.next = this.backSentinel;
        this.backSentinel.previous = this.frontSentinel;
        for (int i = 0; i < other.size(); i += 1) {
            this.addLast((T) other.get(i));
        }
    }*/
    public void addFirst(T item) {
        Stuffnode addnode = new Stuffnode(null, item, this.frontSentinel.next);
        this.frontSentinel.next = addnode;
        addnode.previous = this.frontSentinel;
        addnode.next.previous = addnode;
        this.size += 1;
    }
    public void addLast(T item) {
        Stuffnode addnode = new Stuffnode(this.backSentinel.previous, item, null);
        this.backSentinel.previous = addnode;
        addnode.next = this.backSentinel;
        addnode.previous.next = addnode;
        this.size += 1;
    }
    public boolean isEmpty() {
        return this.size == 0;
    }
    public int size() {
        return this.size;
    }
    public void printDeque() {
        if (this.frontSentinel.next == null) {
            System.out.println("Empty Query");
        } else {
            Stuffnode ptr = this.frontSentinel.next;
            while (ptr.next != this.backSentinel) {
                System.out.print(ptr.item + " ");
                ptr = ptr.next;
            } System.out.println(ptr.item + " ");
        }
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T a = this.frontSentinel.next.item;
            this.frontSentinel.next = this.frontSentinel.next.next;
            this.frontSentinel.next.previous = this.frontSentinel;
            this.size = this.size - 1;
            return a;
        }
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T b = this.backSentinel.previous.item;
            this.backSentinel.previous = this.backSentinel.previous.previous;
            this.backSentinel.previous.next = this.backSentinel;
            this.size = this.size - 1;
            return b;
        }
    }
    public T get(int index) {
        Stuffnode ptr = this.frontSentinel.next;
        for (int i = 0; i != index; i++) {
            ptr = ptr.next;
        }
        return ptr.item;
    }
    private T getHelper(Stuffnode helper, int index, int s, int constant) {
        if (index + s == constant) {
            return helper.item;
        } else {
            return this.getHelper(helper.next, index, s - 1, constant);
        }
    }
    public T getRecursive(int index) {
        if (index > this.size - 1) {
            System.out.println("Out of Range");
            return null;
        } else {
            return this.getHelper(this.frontSentinel.next, index,
                    this.size - 1, this.size - 1);
        }
    }
}

