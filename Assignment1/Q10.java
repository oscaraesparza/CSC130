package edu.csus.csc130.fall2017.assignment1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implemented by: Oscar Esparza
 *
 */
public class Q10 {
/**
 * Merges two sorted queues and returns a new queue in sorted order in linear time.  
 * You can use java.util.LinkedList  as a Queue implementation.  It is okay to 
 * modify the input queues.
 * 
 * @param queue1 queue with elements from head to tail in non-decreasing order
 * @param queue2 queue with elements from head to tail in non-decreasing order
 * @return queue that contains all elements form queue1 and queue2 in non-decreasing order
 * 
 * Time Complexity = O(n) because each while loop is O(n). The comparisons are O(1)
 */
public static <T extends Comparable<T>> Queue<T> merge(Queue<T> queue1, Queue<T> queue2) {
	Queue<T> queue3 = new LinkedList<T>(); //merged list
	
	// checks if both queues are empty, if not compare 
	while( (queue1.size() != 0) && (queue2.size() != 0)) {
		if (isLessThan(queue1.peek(), queue2.peek())){
			queue3.add(queue1.poll());
		}
		else 
			queue3.add(queue2.poll());			
	}
	// since it is in order, just put whatever is left in new queue
	while(!(queue1.isEmpty())){
		queue3.add(queue1.poll());
	}
	
	while(!(queue2.isEmpty())){
		queue3.add(queue2.poll());
	}
	return queue3;
	
	
}
// Copied from Dr. Yu Chen SortUtils class
private static<T extends Comparable<T>> boolean isLessThan(T v, T w){
	return v.compareTo(w) < 0;
	
}

}
