import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue  {

 

    private ArrayList<String> queue;
    private int size = 0;
    private static final int DEAFULT_SIZE = 5;
    private Lock queueLock;
    private Condition queueChangeCondition;
 

    public static void main(String[] args) {
        
    }
    
    /**

      Constructs the maximum size of the queue to default size.

     */

    public Queue()  {

        size = DEAFULT_SIZE;
        queue = new ArrayList<String>(size);
        queueLock = new ReentrantLock();
        queueChangeCondition = queueLock.newCondition();

    }
    
    public boolean isEmpty()  { // is the queue empty?
        return queue.isEmpty(); // calling ArrayList isEmpty() method

    }

 

    public boolean isFull()  { // is the queue full
        return queue.size() == size-1;
    }
    
    /**
    Adds a string into the queue.
    @param item the item to add
   */
  public void add(String item) throws InterruptedException
  {
      queueLock.lock();
      try {
          while (queue.size()==size-1) {
              queueChangeCondition.await();
          }
          if (queue.size() != size-1){
              queue.add(item);
              queueChangeCondition.signalAll();
          }
      }
      finally {
          queueLock.unlock();
      }

      

  }

  /**
    Removes one item from the queue.
    @return the first item of the queue
   */
  public String remove() throws InterruptedException
  {
  
      String element = null;
      queueLock.lock();
      try {
          while (queue.isEmpty()){
              queueChangeCondition.await();
          }
          if (!queue.isEmpty()){
              element = queue.remove(0).toString();
              queueChangeCondition.signalAll();
          }
      }
      finally {
          queueLock.unlock();
      }
      return element;
      
  }
    
}