package edu.csus.csc130.fall2017.assignment1;

/**
 * Modified by: Oscar Esparza
 *
 */
public class MaxPQ<Key extends Comparable<Key>> {
	private static int DEFAULT_CAPCITY=20;
	// heap-ordered complete binary tree
	// in pq[1..N] with pq[0] unused
	private Key[] pq; 
	private int size = 0;
	private Key small = null;
	
	public MaxPQ() {
		this(DEFAULT_CAPCITY);
	}

	@SuppressWarnings("unchecked")
	public MaxPQ(int maxN) {
		pq = (Key[]) new Comparable[maxN + 1];
	}	
	
	public void insert(Key v) {
		if(small == null)
			small = v;
		else if(isLessThan(v, small))
			small = v;
		pq[++size] = v;
		swim(size);
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}
	
	public Key max() {
		if (size==0) {
			return null;
		} else {
			return pq[1];
		}
	}
	
	/**
	 * Returns minimum key of the max-heap.  The implementation should 
	 * use constant time and constant extra space.  The existing 
	 * methods involving changing minimum might need to be updated.
	 * 
	 * @return minimum key, null if the heap is empty
	 */
	public Key min() {

		if (size == 0)  
			return null;
		if (size == 1)
			return pq[1];
		
		return small;
		
	}

	public Key delMax() {
		Key max = pq[1]; // Retrieve max key from top.

		swap(1, size--); // Exchange with last item.
		pq[size + 1] = null; // Avoid loitering.
		sink(1); // Restore heap property.

		return max;
	}
	

	private void swim(int k) {
		while (k > 1 && isLessThan(pq[k/2], pq[k])) {
			swap(k/2, k);
			k = k/2;
		}
	}

	private void sink(int k) {
		while (2*k <= size) {
			int j = 2*k;
			if (j < size && isLessThan(pq[j], pq[j + 1]))
				j++; // j is the index of the largest children
			if (!isLessThan(pq[k], pq[j]))
				break;
			swap(k, j);
			k = j;
		}
	}

	private void swap(int i, int j) {
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	private boolean isLessThan(Key v, Key w) {
		return v.compareTo(w) < 0;
	}
	
}
