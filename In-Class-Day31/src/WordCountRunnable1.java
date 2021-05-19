import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
* Counts how many words in a file.
*/
public class WordCountRunnable1 implements Runnable {
    private String filename;
    // these variables are shared by all the threads
    private static int threadCount = 0;
    private static int combinedWordCount = 0;
    // create a lock for each shared resource
    private ReentrantLock threadCountLock = new ReentrantLock();
    private ReentrantLock combinedWordCountLock = new ReentrantLock();
    /**
     * Constructs a WordCountRunnable object with a file name.
     *
     * @param aFilename
     *            the file name that needs to count word
     */
    public WordCountRunnable1(String aFilename) {
        filename = aFilename;
    }
    public void run() {
        /** this pattern
         * will be repeated anywhere a lock is used.
         * First acquire the lock with the .lock() method. If another thread already has
         * possession of the lock, this thread will deactivate. At intervals, the JVM will
         * wake the thread up and again try to acquire the lock. Code after the .lock()
         * will not be executed until this thread has possession of the lock
         */
        threadCountLock.lock(); // will be incrementing the threadCount when a new thread is started
        try {
            /** inside the try block of code is where the shared resource is accessed
             * If this thread acquires a lock and then throws an error, however, this lock
             * becomes dead - it is marked as being owned by this thread, but the thread
             * terminates due to an error, before the lock is unlocked.
             */
            WordCountRunnable1.threadCount++;
        } finally {
            /** rely on the finally clause to release this lock after the try clause
             * completes or an error occurs and the thread terminates
             */
            threadCountLock.unlock();
        }
        int count = 0;
        try {
            Scanner in = new Scanner(new FileInputStream(filename));
            while (in.hasNext()) {
                in.next();
                count++;
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println(filename + " not found!");
        }
        System.out.println(filename + ": " + count);
        /** accessing the combinedWordCount variable to save the number of words counted
         * we use a separate lock so that combinedWordCount and threadCount do not have to share a lock
         *
         */
        combinedWordCountLock.lock();
        try {
            combinedWordCount += count;
        } finally {
            combinedWordCountLock.unlock();
        }
        /** once this thread is finished, we can decrement the threadCount
         *
         */
        threadCountLock.lock();
        try {
            threadCount--;
        } finally {
            threadCountLock.unlock();
        }
        // if this happens to be the last thread, we can print out the total
        if (threadCount == 0) {
            System.out.println(combinedWordCount + " words counted!");
        }
    }
}