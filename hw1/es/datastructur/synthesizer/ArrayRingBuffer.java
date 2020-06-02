package es.datastructur.synthesizer;
import java.util.Iterator;
import java.util.Objects;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     * @param x
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else {
            rb[last] = x;
            fillCount += 1;
            last = (last + 1) % capacity();
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            T returnValue = rb[first];
            fillCount -= 1;
            first = (first + 1) % capacity();
            return returnValue;
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            return rb[first];
        }
    }


    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }
    @Override
    public Iterator<T> iterator(){
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T>{
        int pos;
        int count;
        public ArraySetIterator() {
            pos = first;
            count = 0;
        }
        @Override
        public boolean hasNext() {
            return count != fillCount();
        }

        @Override
        public T next() {
            T returnValue = rb[pos];
            pos = (pos + 1) % capacity();
            count += 1;
            return returnValue;
        }
    }

    @Override
    public boolean equals(Object o) {
        boolean returnValue = true;
        if (o.getClass() != this.getClass()) {
            return false;
        } else {
            ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
            if (other.fillCount != this.fillCount) {
                return false;
            } else {
                int thisPos = first;
                int otherPos = other.first;
                for (int i = 0; i < fillCount; i++, thisPos++, otherPos++) {
                    returnValue = returnValue && rb[thisPos] == other.rb[otherPos];
                }
                return returnValue;
            }
        }
    }
}

