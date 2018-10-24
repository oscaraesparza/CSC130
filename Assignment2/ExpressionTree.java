package edu.csus.csc130.fall2017.assignment2;

import java.util.Stack;

/**
 * A binary tree representation of an expression with these operators: 
 * +, -, *, /.  Each operator requires two operands.
 * 
 * @author Oscar Esparza
 *
 */
public class ExpressionTree {
	Node root;
	
	// Node sub-class
	public class Node {
		private String key; // key
		private Node left, right; // links to subtrees

		public Node(String key) {
			this.key = key;
		}
	}
	
	// constructor to build a tree with postFix expression
	public ExpressionTree(String postFixExpression) {
		Stack<Node> stack = new Stack<Node>();
		String[] tokens = postFixExpression.split(" ");
		for (String token: tokens) {
			if (isOperator(token)) {
				Node right = stack.pop();
				Node left = stack.pop();
				Node node = new Node(token);
				node.left = left;
				node.right = right;
				stack.push(node);
			} else {
				stack.push(new Node(token));
			}
		}
		root = stack.pop();
	}
	
	private boolean isOperator(String token) {
		boolean result = false;
		switch(token) {
			case "+":
			case "-":
			case "*":
			case "/":
				result = true;
				break;
			default:
				result = false;
		}
		return result;
	}
	
	/**
	 * @return result of the expression
	 * @throws Exception
	 */
	public double evaluate() throws Exception {
		root.key = theAnswer(root);
		return Double.parseDouble(root.key);
		}
// Recursion, it keeps going until it gets to a node that has # children
// replaces operator with answer
public String theAnswer(Node node) {
	double answer = 0;
	// check left for operator
	if(isOperator(node.left.key)) {
		   node.left.key = theAnswer(node.left);
	}
	// check right
	if(isOperator(node.right.key)) {
		   node.right.key = theAnswer(node.right);
	}
	// as soon as left.key & right.key are #'s, do the math
	answer += solve(node);
	node.key = Double.toString(answer);
	return node.key;
	
}

public double solve(Node dowork) {
	double answer = 0; double num1 = 0; double num2 = 0;
	num1 = Double.parseDouble(dowork.left.key);
	num2 = Double.parseDouble(dowork.right.key);
    switch(dowork.key) {
    case "+":
        answer = num1 + num2;
        break;
    case "-":
        answer = num1 - num2;
        break;
    case "*":
        answer = num1 * num2;
        break;
    case "/":
        answer = num1 / num2;
        break;
    }
    return answer;
}
}