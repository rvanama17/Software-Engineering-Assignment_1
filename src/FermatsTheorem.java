
// "Finding possible near misses for Fermat’s Last Theorem"
// "FermatsTheorem.java" is the file that holds our program
// To run this program we don't need any external file 
// This program doesn't create any external file, if we run the jar file in command promt it shows the result in CMD itself.
// This program is contributed by 
// Our Email address: 
// for CPSC class
// Completed it on  Submitting it on 
// This program finds the near misses from Fermat's last Theorem 
// Took reference from geeksforgeeks website




import java.util.Scanner;

public class FermatsTheorem {

	static int k, n; // Unknown Variables
	static int kUpperLimit = 50; // UpperLimit of Variable K 

	static float smallestRelativeMiss = Float.MAX_VALUE; //Smallest relative miss
	static long smallestMiss = Long.MAX_VALUE; //Smallest actual miss

	public static void main(String[] args) {  // Main method
		Scanner scnr = new Scanner(System.in); // Used to obtain input
		do {
			System.out.println("Enter the value of k,it should be greater than 10 and less than " + kUpperLimit + ".");
			k = scnr.nextInt(); // Used to take input value of K
		} while (!checkInputValidity(k, 10, kUpperLimit)); // Check the input is correct or not if it is not valid then ask for the input until it is correct 

		do {
			System.out.println("Enter the value of n,it must be greater than 2 and less than 12");
			n = scnr.nextInt(); // Used to obtain value of n
		} while (!checkInputValidity(n, 3, 11)); // Used to check the input is valid or not

		scnr.close();
		TestData();
	}

	static boolean checkInputValidity(int actualVal, int minVal, int maxVal) {  // Used to check the value is in required range or not
		if (actualVal < minVal || actualVal > maxVal) {
			System.out.println("Value must be greater than " + (minVal - 1) + " and less than " + (maxVal + 1) + "\n");
			return false;
		}
		return true;
	}

	static void TestData() { // Used to test the combinations of variables
		for (int x = 10; x <= k; x++) { // Used to loop the x values
			for (int y = x; y <= k; y++) { // Same way as x this is used to loop the y value

				float closeVal = Float.MAX_VALUE; 
				int z = (x + y) / 2;

				if (z > k) {
					z = k;
					PrintResultValues(x, y, z, findRelativeMiss(x, y, z)); // Print the results of x, y, z
					continue; // Used to continue to next y value
				}

				while (z <= k) {
					float near = findRelativeMiss(x, y, z); // Used to find the closeness of x y z
					if (near > closeVal)
						break;

					closeVal = near; 
					z++;
				}
				z--;

				PrintResultValues(x, y, z, closeVal);
				if (x != y)
					PrintResultValues(y, x, z, closeVal); 
			}
		}
	}

	static void PrintResultValues(int x, int y, int z, float closeVal) { // Used to print the final values
		float relMiss = closeVal;

		if (smallestRelativeMiss > relMiss) {
			smallestRelativeMiss = relMiss;
			System.out.println("\nNew smallest relative miss.");
			System.out.println("x: " + x + " y: " + y + " z: " + z);
			System.out.println("Relative Miss (ratio): " + (1 + closeVal));
			System.out.println("Actual Miss: " + findActualMiss(x, y, z));
		}
	}

	public static float findRelativeMiss(int x, int y, int z) { // Used to get the relative miss from x,y,z,n
		double zVal = Math.pow(z, n);
		double xyVal = Math.pow(x, n) + Math.pow(y, n);
		return (float) Math.abs(1.0 - xyVal / zVal); //
	}

	public static long findActualMiss(int x, int y, int z) {
		double zVal = Math.pow(z, n); // Used to returns the base to the exponent power
		double xyVal = Math.pow(x, n) + Math.pow(y, n);
		return (long) (xyVal - zVal);
	}

}