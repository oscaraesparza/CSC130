package edu.csus.csc130.fall2017.assignment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Modified By:  Oscar Esparza
 *
 * @param <Key>
 * @param <Value>
 */
public class QuadraticProbingHashST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value>{
	private int n; // number of key-value pairs in the table
	private int m = 16; // size of linear-probing table
	private Key[] keys; // the keys
	private Value[] vals; // the values

	@SuppressWarnings("unchecked")
	public QuadraticProbingHashST() {
		keys = (Key[]) new Comparable[m];
		vals = (Value[]) new Object[m];
	}
	
	@SuppressWarnings("unchecked")
	public QuadraticProbingHashST(int capacity) {
		m = capacity;
		keys = (Key[]) new Comparable[m];
		vals = (Value[]) new Object[m];
	}	

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

/* If search key exists in the table, return associated value;
 * otherwise return null
 */
public Value get(Key key) {
	// Provide your implementation here
	int count = 0;
	int j = hash(key); //keeps original hash(key)
	for(int i = hash(key); keys[i] != null; i = (j + (count * count)) % m) {
		if(keys[i].equals(key)) 
			return vals[i];
		count++;
	}
	return null;
	
}

/* Insert the key value pair into the table
 * If the key exists in the table, update the associated value with new value;
 * No resize is needed for this implementation
 */
public void put(Key key, Value val) { 
	// Provide your implementation here
	int count=0;  
	int i;
	int j = hash(key); //important for loop
	for(i = hash(key); keys[i] != null; i = (j + (count * count)) % m ) {
		if(keys[i].equals(key)) {
			vals[i] = val;
			return;
		}
		count++; //1*1 2*2 3*3 ....
	}
	keys[i] = key;
	vals[i] = val;
	n++; //increase table size
}
	
	public void delete(Key key) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	
	public boolean contains(Key key) {
		return (get(key) != null);
	}

	@Override
	public boolean isEmpty() {
		return n==0;
	}

	@Override
	public int size() {
		return n;
	}

	@Override
	public Iterable<Key> keys() {
		List<Key> list = new ArrayList<Key>();
		for (Key key: keys) {
			list.add(key);
		}
		return list;
	}

}
