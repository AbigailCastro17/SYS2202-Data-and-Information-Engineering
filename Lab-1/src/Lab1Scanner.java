// Abigail Castro abc3gnm

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
	
public class Lab1Scanner {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		double[] values = new double[5];
		for(int i = 0; i < 5; i++){
			System.out.println("Enter the " + i + " value in the array.");
			values[i] = keyboard.nextDouble();	
		}
		keyboard.close();
		double x = 0;
		for (int j=0;j<5;j++) {
			x= x + values[j];
		}
		double y=x/5;
		System.out.println("The average of " + values[0] + ", " + values[1] + ", " + values[2] + ", " + values[3] + ", and " + values[4] + " is " + y + ".");
	
	/*	File theFile = new File("data1.txt");
		Scanner fileScnr = new Scanner(theFile);
		System.out.println(theFile.length());
		System.out.println("Path : " + theFile.getAbsolutePath());
	*/
		/*File file2 = new File("data2.txt");
		Scanner fileScnr = new Scanner(file2);
		
		int sum2 = 0;
		while(scan.hasNext())*/
		}
	/*public static boolean isInteger(String str) {    
	    // your code here 
		for (int i = 0; i<str.length();i++) {
		char c = str.charAt(i);
		if(c<48 || c>57) {
			return false;
		}
		}*/
		
	}

