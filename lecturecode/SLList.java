public class SLList{
	private static class IntNode{
		public int item;
		public IntNode next;
		public IntNode(int i, IntNode n){
			item = i;
			next = n;
		}
	}
	public IntNode first;
	public int length;

	public SLList(int i){
		first = new IntNode(i, null);
		length = 1;
	}
	//* Add x to the front of list * /
	public void addFirst(int x){
		first = new IntNode(x, first);
		length = length + 1;
	}

	public int size(){
		return length;
	}
	public static void main(String[] args) {
		SLList newL = new SLList(15);
		newL.addFirst(10);
		newL.addFirst(5);
		System.out.println(newL.size());
	}
	//* Return the first element */
	public int getFirst(){
		return first.item;
	}
	public void addLast(int x){
		while(first.next != null){
			first = first.next;
		}
		first.next = new IntNode(x,null);
		length = length + 1
	}
	//* Iterative way */
	// public int size(){
	// 	int n = 0;
	// 	while(first.next != null){
	// 		n = n + 1;
	// 		first = first.next;
	// 	}
	// 	return n+1;
	// }

	//* recursive way */

}