package edu.csus.csc130.fall2017.assignment2;

/**
 * Modified by: Oscar Esparza
 *
 */
public class Q9 {
	/**
	 * Binary tree node
	 */
	public static class Node<Key extends Comparable<Key>> {
		private Key key; // key
		private Node<Key> left, right; // links to subtrees

		public Node(Key key) {
			this.key = key;
		}

		public Node<Key> getLeft() {
			return left;
		}

		public void setLeft(Node<Key> left) {
			this.left = left;
		}

		public Node<Key> getRight() {
			return right;
		}

		public void setRight(Node<Key> right) {
			this.right = right;
		}
	}
	
/**
 * Check if the keys in the binary tree rooted with this node
 * follow binary search tree order.  
 * It is okay to create another method and call that method here.
 * 
 * Make sure the worst-case running time is linear, and explain 
 * why that is the case here.
 */
public static  <Key extends Comparable<Key>> boolean inSearchTreeOrder(Node node) 	{
	return check(node, null, null);
}

public static <Key extends Comparable<Key>> boolean check(Node root, Key min, Comparable key)
{
	if (root == null)
	{
		return true;
	}
	
	// checks if root is bigger than left child
	if (min != null && root.key.compareTo(min) <= 0)
	{
		return false;
	}
	
	// checks if root is bigger than right child
	if (key != null && root.key.compareTo(key) >= 0)
	{
		return false;
	}
	
	// recursion, so it can go through the whole tree
	return check(root.left, min,  root.key) && check(root.right, root.key, key);
}


}