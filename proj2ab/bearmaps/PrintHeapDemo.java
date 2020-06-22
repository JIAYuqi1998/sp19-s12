package bearmaps;

/**
 * Created by hug.
 */
public class PrintHeapDemo {
    /** Prints out a vey basic drawing of the given array of Objects assuming it
     *  is a heap starting at index 1. You're welcome to copy and paste
     *  code from this method into your code, just make sure to cite
     *  this with the @source tag. */
    public static void printSimpleHeapDrawing(Object[] heap) {
        int depth = ((int) (Math.log(heap.length) / Math.log(2)));
        int level = 0;
        int itemsUntilNext = (int) Math.pow(2, level);
        for (int j = 0; j < depth; j++) {
            System.out.print(" ");
        }

        for (int i = 1; i < heap.length; i++) {
            System.out.printf("%d ", heap[i]);
            if (i == itemsUntilNext) {
                System.out.println();
                level++;
                itemsUntilNext += Math.pow(2, level);
                depth--;
                for (int j = 0; j < depth; j++) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }

    /** Prints out a drawing of the given array of Objects assuming it
     *  is a heap starting at index 1. You're welcome to copy and paste
     *  code from this method into your code, just make sure to cite
     *  this with the @source tag. */
    public static void printFancyHeapDrawing(Object[] items) {
        String drawing = fancyHeapDrawingHelper(items, 1, "");
        System.out.println(drawing);
    }

    /* Recursive helper method for toString. */
    private static String fancyHeapDrawingHelper(Object[] items, int index, String soFar) {
        if (index >= items.length || items[index] == null) {
            return "";
        } else {
            String toReturn = "";
            int rightIndex = 2 * index + 1;
            toReturn += fancyHeapDrawingHelper(items, rightIndex, "        " + soFar);
            if (rightIndex < items.length && items[rightIndex] != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + items[index] + "\n";
            int leftIndex = 2 * index;
            if (leftIndex < items.length && items[leftIndex] != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += fancyHeapDrawingHelper(items, leftIndex, "        " + soFar);
            return toReturn;
        }
    }


    public static void main(String[] args) {
/*        Integer[] example = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        printSimpleHeapDrawing(example);
        printFancyHeapDrawing(example);*/
        ArrayHeapMinPQ minPQ = new ArrayHeapMinPQ();
        minPQ.add(1,10.0);
        minPQ.add(2,5.0);
        minPQ.add(3,7.0);
        minPQ.add(4,3.0);
        minPQ.add(5,2.0);
        minPQ.add(6,11.0);
        minPQ.add(7,5.1);
        minPQ.add(8,3.5);
        minPQ.add(10,3.9);
        minPQ.changePriority(1,1);
        Integer[] test = minPQ.returnVal();
        printSimpleHeapDrawing(test);
        minPQ.removeSmallest();

        test = minPQ.returnVal();
        printSimpleHeapDrawing(test);


    }
}