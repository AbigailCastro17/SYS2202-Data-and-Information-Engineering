import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Homework 5 PrintQueue
 * 
 * Implement the class below as specified in the
 * homework 5 document.
 * 
 * @author Abigail Castro abc3gnm
 *
 */
 
// Don't forget to include the appropriate import statements
 
public class PrintQueue {
    
	private LinkedList<String> toPrint;     // the printer's list of documents
	private Lock documentChangeLock;  // a ReentrantLock lock
	private Condition hasPrintTask;   // a condition object
	private boolean isOn;             // boolean variable describing if the 
                                      // queue is on (accepting jobs)
	

	//TODO:  Handle locking and conditions around the
    //       enqueueing and dequeuing of documents
    //       in this PrintQueue's document list (toPrint)
    //       Note: See example in the zip folder 'Thread Example 6 - Bank Deadlock' 


    /**
     * Constructor
     */
    public PrintQueue() {
        toPrint = new LinkedList<String>(); // create the list of documents
        isOn = true; // turn on the print queue
		// Complete instantiation of documentChangeLock and hasPrintTask here
        documentChangeLock = new ReentrantLock();
        hasPrintTask = documentChangeLock.newCondition();
    }


    /**
     * dequeue
     * TODO: Write more comments
     * @throws InterruptedException 
     */
    //Remove the head element off the queue and return it
    public String dequeue() throws InterruptedException{
        // This seems dangerous! Something needs to change here...!
		// Hint: Only remove an item from the queue if it's not empty, 
		//       and do not dequeue if the queue is empty, but wait if the queue is on.
		// Implement this method
        documentChangeLock.lock();//only allows method to be used by one string at a time
        String card = null;//instantiate a String to return
        try {
            while (toPrint.isEmpty() && isOn) {//while the queue is empty and on
                hasPrintTask.await();//the thread waits
            }
            if (!toPrint.isEmpty()) //if the queue is not empty
                card = toPrint.remove(); // sets string to first document
        }
        finally {
            documentChangeLock.unlock();//makes sure that the thread is unlocked even after an exception occurs
        }  
        return card; //returns string
    }


    /**
     * enqueue
     * TODO: Write more comments
     */
    public void enqueue(String s) {
        // This seems dangerous! Something needs to change here...!
        documentChangeLock.lock();
		// Implement this method
        try {
            toPrint.add(s); // add to the list of documents
            hasPrintTask.signalAll();// Unblock other threads waiting on the condition by "signalAll"
            
        }
        finally {
            documentChangeLock.unlock();//makes sure that the thread is unlocked even after an exception occurs
        }
        
    }

    /**
     * turnOff
     * TODO: Turn off the print queue
     * set a boolean field denoting that the PrintQueue is no longer accepting jobs
     */
    public void turnOff() {
		// Implement this method
        documentChangeLock.lock();//only allows method to be used by one string at a time
        // Implement this method
        try {
            isOn=false;//sets the isOn variable to false
            hasPrintTask.signalAll();//signals the threads waiting in the dequeue method
            System.out.println("Terminated");//prints after the queue is terminated
        }
        finally {
            documentChangeLock.unlock();//makes sure that the thread is unlocked even after an exception occurs
        }
		
	}
	
    
    /**
     * isOn
     * TODO: Returns true if the PrintQueue is still accepting jobs, false if it has been “turned off."
     */
    public boolean isOn() {
		// Implement this method
		if (isOn)//if isOn variable is true, returns true
		    return true;
		return false;//otherwise returns false
	}	
    
}
