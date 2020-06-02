package es.datastructur.synthesizer;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    public static void main(String[] args){
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(10);
        arb.enqueue(9);
        arb.enqueue(8);
        arb.enqueue(7);
        arb.enqueue(6);
        arb.enqueue(5);
        arb.enqueue(4);
        arb.enqueue(3);
        arb.enqueue(2);
        arb.enqueue(1);
        arb.dequeue();
        arb.dequeue();
        arb.enqueue(2);
        arb.enqueue(1);
        arb.peek();
        for (int i : arb) {
            System.out.println(i);
        }
    }
}
