package edu.csus.csc130.fall2017.assignment3;

import java.util.ArrayList;
import java.util.List;

public class LinearProbingHashST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value>{
	private int n; // number of key-value pairs in the table
	private int m = 16; // size of linear-probing table
	private Key[] keys; // the keys
	private Value[] vals; // the values

	@SuppressWarnings("unchecked")
	public LinearProbingHashST() {
		keys = (Key[]) new Comparable[m];
		vals = (Value[]) new Object[m];
	}
	
	@SuppressWarnings("unchecked")
	public LinearProbingHashST(int capacity) {
		m = capacity;
		keys = (Key[]) new Comparable[m];
		vals = (Value[]) new Object[m];
	}	

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	public Value get(Key key) {
		for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
			if (keys[i].equals(key))
				return vals[i];
		}
		return null;
	}
	
	public void put(Key key, Value val) {
		//ensures that the table is at most one-half full
		if (n >= m / 2) resize(2 * m); 
		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
			// search hit, replace the value
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		// search miss, insert the new key value pair
		keys[i] = key;
		vals[i] = val;
		n++;
	}
	
public void resize(int cap) {
	LinearProbingHashST<Key, Value> newTable;
	// create new table with given capacity
	newTable = new LinearProbingHashST<Key, Value>(cap);
	// insert existing key value pairs into the new table
	for (int i = 0; i < m; i++) {
		if (keys[i] != null) {
			newTable.put(keys[i], vals[i]);
		}
	}
	// copy the new table fields to the existing table
	keys = newTable.keys;
	vals = newTable.vals;
	m = newTable.m;
}	
	
	public void delete(Key key) {
		if (!contains(key)) return;
		
		int i = hash(key);
		while (!key.equals(keys[i])) {
			i = (i + 1) % m;
		}
		keys[i] = null;
		vals[i] = null;
		
		i = (i + 1) % m;
		while (keys[i] != null) {
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
			n--;
			put(keyToRedo, valToRedo);
			i = (i + 1) % m;
		}
		
		n--;
		//ensure that the table is at least one-eighth full
		if (n > 0 && n == m / 8) {
			resize(m / 2);
		}
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
