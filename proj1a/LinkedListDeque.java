@SuppressWarnings("ALL")
public class LinkedListDeque<T> {
    public class stuffnode{
        public stuffnode previous;
        public T item;
        public stuffnode next;
        public stuffnode( stuffnode p, T first, stuffnode n){
            this.item = first;
            this.next = n;
            this.previous = p;
        }
    }
    private stuffnode Frontsentinel = new stuffnode (null, (T) "fsentinel",null);
    private stuffnode Backsentinel = new stuffnode(null, (T) "bsentinel", null);
    private int size;
    public LinkedListDeque() {
        this.Frontsentinel.next = this.Backsentinel;
        this.Backsentinel.previous = this.Frontsentinel;
        this.size = 0;
    }
    public LinkedListDeque(LinkedListDeque other) {
        this.Frontsentinel.next = this.Backsentinel;
        this.Backsentinel.previous = this.Frontsentinel;
        for (int i = 0; i < other.size(); i += 1){
            this.addLast((T) other.get(i));
        }
    }
    public void addFirst(T item) {
        stuffnode addnode = new stuffnode(null,item, this.Frontsentinel.next);
        this.Frontsentinel.next = addnode;
        addnode.previous = this.Frontsentinel;
        addnode.next.previous = addnode;
        this.size += 1;
    }
    public void addLast(T item) {
        stuffnode addnode = new stuffnode(this.Backsentinel.previous ,item,null);
        this.Backsentinel.previous = addnode;
        addnode.next = this.Backsentinel;
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
        if(this.Frontsentinel.next == null) {
            System.out.println("Empty Query");
        }else {
            stuffnode ptr = this.Frontsentinel.next;
            while(ptr.next!= this.Backsentinel) {
                System.out.print(ptr.item + " ");
                ptr = ptr.next;
            }System.out.println(ptr.item + " ");
        }
    }
    @SuppressWarnings("UnusedReturnValue")
    public Object removeFirst() {
        this.Frontsentinel.next = this.Frontsentinel.next.next;
        this.Frontsentinel.next.previous = this.Frontsentinel;
        this.size = this.size - 1;
        return this.Frontsentinel.next.item;
    }
    @SuppressWarnings("unused")
    public Object removeLast() {
        this.Backsentinel.previous = this.Backsentinel.previous.previous;
        this.Backsentinel.previous.next = this.Backsentinel;
        this.size = this.size -1;
        return this.Backsentinel.previous.item;
    }
    public Object get(int index) {
        stuffnode ptr = this.Frontsentinel.next;
        for (int i = 0; i != index; i ++) {
            ptr = ptr.next;
        }
        return ptr.item;
    }
    public Object getHelper( stuffnode helper, int index, int size, int constant){
        if (index + size == constant){
            return helper.item;
        }else {
            return this.getHelper(helper.next, index, size - 1 , constant);
        }
    }
    public Object getRecursive( int index) {
        if (index > this.size -1){
            System.out.println("Out of Range");
            return null;
        }else {
             Object output = this.getHelper(this.Frontsentinel.next, index, this.size - 1, this.size - 1);
            return output;
        }
    }
}
