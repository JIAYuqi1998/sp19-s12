
public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
		if (rest == null) {
			return 1;
		}
		return 1 + this.rest.size();
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
		IntList p = this;
		int totalSize = 0;
		while (p != null) {
			totalSize += 1;
			p = p.rest;
		}
		return totalSize;
	}

	/** Returns the ith item of this IntList. */
	public int get(int i) {
		if (i == 0) {
			return first;
		}
		return rest.get(i - 1);
	}
	public static IntList incrList(IntList l, int x){
		int n = l.size();
		int final_value = l.get(n - 1) + x;
		IntList test = new IntList(final_value,null);
		for(int i = n-2; i >= 0 ; i -= 1){
			int value = l.get(i) + x;
			test = new IntList(value, test);
		}
		return test;
	}
	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);
		IntList newL = incrList(L,3);
		System.out.println(L.get(1));
		System.out.println(newL.get(1));

	}
} 