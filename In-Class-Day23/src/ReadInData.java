/**
 * 
Download ReadInData.java, Test2.txt, Test3.txt, Test4.txt
Find the readData() method
Inside this method is a try block without 
any catch statements. Add the following 5 
catch statements to this file 
(Hint: put them in the right order):
- RuntimeException
- Exception
- FileNotFoundException
- NumberFormatException
- InputMismatchException
For each of these print out a short message describing the exception that occurred
Run the main() method and make sure you understand why the output is the way it is
Upload you java file to Collab
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReadInData {
	
	public static double readData(String fileName) {
		File file = new File(fileName);
		Scanner scan;
		double sum = 0.0;
		try {
			scan = new Scanner(file);
			int numOfValues = Integer.parseInt(scan.nextLine()); // Read in Val for # values
			for(int i = 0; i < numOfValues; i++) { // for-loop to get that many values
			    sum += scan.nextDouble(); // add the values together (doubles)
			}
			scan.close();
			return sum; // return the sum of all the values in the file 
		}//add catch statements here
	    catch (FileNotFoundException e) {
	        System.out.println("Could not find file.");
	    }
        catch (InputMismatchException e) {
            System.out.println("Input mismatch.");
        }
		catch (NumberFormatException e) {
		    System.out.println("Number formatted incorrectly.");
		}
        catch (RuntimeException e) {
            System.out.println("A runtime exception occured.");
        }
        catch (Exception e) {
            System.out.println("An error occured.");
        }
		finally {
		    System.out.println("End of program.");
		}
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(readData("Test3.txt"));
		System.out.println(readData("Test2.txt"));
		System.out.println(readData("Text4.txt")); // notice spelling of the file name! 
		System.out.println(readData("Test4.txt"));
	}

}
