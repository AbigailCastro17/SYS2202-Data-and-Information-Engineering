/*
 Abigail Castro abc3gnm
 Alex Kwong ask8kb
 Darrion Chandler djc3mu
 */



import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
public class PointsInAPlane {

	public static void main(String[] args) {
		// you'll put your tests here
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the x value.");
		double x = keyboard.nextInt();
		System.out.println("Enter the y value.");
		double y = keyboard.nextInt();
		keyboard.close();
		double z = points(x,y);
		System.out.println(z);

	}
	public static double points(double x, double y) {
		double z = 0;
		if (x>=0 && y>=0) {
			z = x * y;
		}
		else if (x<0 && y>=0) {
			z = x-y;
		}
		else if (x<0 && y<0) {
			z = x/y;	
		}
		else {
			z = x+y;
		}
		return z;
		
	}
	
	 

}
