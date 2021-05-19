import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
   Counts how many words in a file -- SOLUTION to Concurrency In-Class Activity (DAY 1)  
   STARTER CODE for Concurrency In-Class Activity DAY 2
*/
public class WordCountRunnable implements Runnable
{
   private String filename;
   private static int threadCount;
   public static int combinedWordCount = 0;
   
   Lock lock = new ReentrantLock();
   /**
      Constructs a WordCountRunnable object with a file name.
      @param aFilename the file name that needs to count word
   */
   public WordCountRunnable(String aFilename)
   {
      filename = aFilename;
   }

   public void run()
   {
       
       lock.lock();
       threadCount++;
       System.out.println("Number of threads running: " + threadCount);
	   int count = 0;
       try
       {
           Scanner in = new Scanner(new FileInputStream(filename)); // use Scanner to read the file (filename)
         
           while (in.hasNext()) // while there are more tokens (words)
           {
               in.next(); // consume the word (to be able to progress through the file)
               count++;   // count the number of words
           }
           in.close();   // close the scanner (important!)
           combinedWordCount = combinedWordCount + count;
           threadCount--;
           System.out.println("Number of threads running: " + threadCount);
           System.out.println(filename + ": " + count); // print the total number of words in the file
           if (threadCount==0) {
               System.out.println("Total word count: " + combinedWordCount);    
           }
           
       } 
       catch (FileNotFoundException e)
       {
           System.out.println(filename + " not found!");
       } 
       finally {
           lock.unlock(); 
       }
   }
   
}
