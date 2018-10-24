package edu.csus.csc130.fall2017.assignment3;

public abstract class AbstractSymbolTable<Key, Value> 
		implements SymbolTable<Key, Value> {

	@Override
	public boolean contains(Key key) {
		return get(key) != null;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

}

