import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Homework 5 Card Creator
 * 
 * This class defines the thread task that will
 * "come up with" and submit greeting card ideas
 * to the print queue.  We have added the code
 * necessary to read from the file, but it's up to
 * you to handle turning off the printer (keeping
 * track of how many threads are open) and adding
 * the read-in line from the inspiration file to
 * the queue.
 * 
 * @author Abigail Castro abc3gnm
 *
 */
public class CardCreator implements Runnable {
	
	/**
	 * Print queue to add new card ideas
	 */
	private PrintQueue printQueue;
	
	/**
	 * Inspiration file name
	 */
	private String filename;
	
	private static int threadCount; //keeps track of the number of threads running
	
	/**
	 * CardCreator constructor
	 */	
	public CardCreator(PrintQueue d, String filename) {
		printQueue = d;
		this.filename = filename;
	}

	/**
	 * Run method that is the main method for the thread
	 */
	@Override
	public void run() {
		Scanner s = null;//instantiate a scanner
		threadCount++;//adds to number of threads running
		try {
			s = new Scanner(new FileReader(filename));//creates new file reader to 
			while (s.hasNextLine()) {
				// TODO: Read the next line from the inspiration file
				// TODO: Enqueue the line into the print queue
			    printQueue.enqueue(s.nextLine());
				// TODO: Have the thread sleep for 1 second (1000) (don't used a number < 1000) 
			    Thread.sleep(1000);
			}
		} catch (IOException e) {
			System.out.println("Could not read file");
		} catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
			if (s != null)//if there is no more to scan
				s.close();//close the scanner
			    threadCount--;//decrements threadCount to keep accurate count of number of threads running
			    if (threadCount==0) {//checks if all the threads are done running
			        printQueue.turnOff();//turns off
			    }
			// TODO: Turn off the print queue (if applicable)
            //       i.e., if you're the last card creator left -- how do you know you're the last?
		}
	}

}
