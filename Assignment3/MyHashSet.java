package edu.csus.csc130.fall2017.assignment3;

/**
 * Modified by: Oscar Esparza
 *
 */
public class MyHashSet<Key extends Comparable<Key>> implements Set<Key> {
	int m = 13;
	LinearProbingHashST<Key, Integer> st;
	
	public MyHashSet() {
		st = new LinearProbingHashST<Key, Integer>(m);
	}
	
	public MyHashSet(int capacity) {
		m = capacity;
		st = new LinearProbingHashST<Key, Integer>(m);
	}	

	@Override
	public void add(Key key) {
		// Provide your implementation here
		//throw new UnsupportedOperationException();
		if(!(st.contains(key))) {
			st.put(key, 0); // because it only contains keys
		}
	}

	@Override
	public void delete(Key key) {
		// Provide your implementation here
		st.delete(key);
	}

	@Override
	public boolean contains(Key key) {
		// Provide your implementation here
		return st.contains(key);
	}

	@Override
	public boolean isEmpty() {
		// Provide your implementation here
		return (st.size() == 0);
	}

	@Override
	public int size() {
		// Provide your implementation here
		return st.size();
	}
}
