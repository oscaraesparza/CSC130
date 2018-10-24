package edu.csus.csc130.fall2017.assignment4;

public class BinomialCoefficients {
	
	
	/**
	 * Modified by: Oscar Esparza
	 * Recursive implementation of
	 * binomial coefficients generation
	 * @param n assume n>=0
	 * @param k assume 0<=k<=n
	 * @return
	 */
	public static long bcR(int n, int k) {
		if((k == 0) || k == n) 
			return 1;
		return bcR((n - 1),(k - 1)) + bcR((n - 1), k);  
	}
	
	/**
	 * Dynamic programming implementation of
	 * binomial coefficients generation
	 * @param n assume n>=0
	 * @param k assume 0<=k<=n
	 * @return
	 */
	public static long bcDP(int n, int k) {
		int array[][] = new int[n + 1][k + 1];
		
		for(int i = 0; i <= n; i++) {
			// find the smallest value
			for(int j = 0; j <= Math.min(i, k); j++) {
				if((j == 0) || (j == i))
					array[i][j] = 1;
				else
					array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
			}
		}
		return array[n][k];
	}

}
