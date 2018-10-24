package edu.csus.csc130.fall2017.assignment1;

/**
 * Implemented by: Oscar Esparza
 *
 */
public class Q11 {
	
	/**
	 * Sorts the input array in O(n) time in worst case
	 * @param n
	 * @param a integer array, each element is in [0, n^3-1]
	 * Time Complexity is O(n+m) for Count Sort
	 */
	public static void sort(int n, int[] a) {
		
		// finds the highest #
		int max = (n*n*n)-1;
		/*	//origninal implementation
	    for (int i = 1; i < n; i++)
	        if (a[i] > max)
	            max = a[i];
	    */
	    for (int i = 1; max/i > 0; i *= 10)
            countSort(a, n, i);

}
// This is by Dr. Yu Chen
// Slightly Modified by Oscar Esparza	
static void countSort(int a[], int n, int exp) {
	// create count array and initialize elements to 0
    int count[] = new int[10];
    int aux[] = new int[n]; // output array

  
    for (int i = 0; i < n; i++)
        count[ (a[i]/exp)%10 ]++;

    for (int i = 1; i < 10; i++)
        count[i] += count[i - 1];

    for (int i = n - 1; i >= 0; i--)
    {
        aux[count[ (a[i]/exp)%10 ] - 1] = a[i];
        count[ (a[i]/exp)%10 ]--;
    }


    for (int i = 0; i < n; i++)
        a[i] = aux[i];
}
}