// Abigail Castro abc3gnm

import java.util.Scanner;

public class OneDArrayActivity {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("How many values do you want in the array?");
		int x = keyboard.nextInt();
		int[] values = new int[x];
		for(int i = 0; i < x; i++){
			System.out.println("Enter the " + i + " value in the array");
			values[i] = keyboard.nextInt();
		}
		
		for(int j = 0; j < values.length; j++){
			System.out.print(values[j] + " ");
		}
		
		//Input your code here.
		boolean flag = true;
		
			for (x = 0; x < values.length-1; x++) {
				if(values[x+1]>values[x]) {
					flag=true;
				}
				else {
					flag=false;
					break;
				}
					
			} 
			
		if (flag==false) {
			System.out.println("The array is not increasing.");
		}
		else {
			System.out.println("The array is increasing.");
		}
		

		keyboard.close();
	}

}
