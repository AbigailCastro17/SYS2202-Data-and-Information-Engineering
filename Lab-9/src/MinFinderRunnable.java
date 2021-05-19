import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MinFinderRunnable implements Runnable
{
	/* These are the class variables in MinFinderRunnable.
	 * The MinFinalRunnable class reads the value at min, so be sure to update it as you use the run() method.
	 */
	String filename;
	int min;

	/* This is the constructor for the MinFinderRunnable class.
	 * It takes in a String for the filename so that the run() method can have its Scanner instance read that file.
	 * It should also initialize min, to avoid NullPointerExceptions when the run() method is called.
	 */
	public MinFinderRunnable(String filename)
	{
	    this.filename = filename;
	    this.min = 0;
	}


	/* This is the run method for the MinFinderRunnable class.
	 * It starts with a Scanner class, to read in the file with the String filename.
	 * Then it should go through the file & set class variable min to the minimum value of the file.
	 */
	@Override
	public void run()
	{
		try {
			Scanner reader;
			reader = new Scanner(new File(filename));
			System.out.println("Scanning file "+filename);
			ArrayList<Integer> ints = new ArrayList<Integer>();
			while (reader.hasNext()) {
			    ints.add(Integer.parseInt(reader.next()));  
		        min = ints.get(0);
		        for (int i=1; i<ints.size(); i++) {
		            System.out.println("Testsing "+ints.get(i)+" is less than "+min+"? On file " + filename);
		            if (ints.get(i)<min) {
                    min=ints.get(i);
		            }
		        }
			}
			System.out.println(filename + " min value: " + min + "\n");
		}
		catch (FileNotFoundException e)
		{
			System.err.println("ERROR: File " + filename + " not found.");
		}
	}
}
