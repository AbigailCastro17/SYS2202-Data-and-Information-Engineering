import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
  A bank account has a balance that can be changed by
  deposits and withdrawals
  
  - Using locks for synchronization
  
  *** NEW: Using Condition object to avoid Deadlocks ***

*/

public class BankAccount {
	private double balance;
	private Lock balanceChangeLock;  // lock
	private Condition sufficientFundsCondition; // Add condition object

	
	/**
	  Constructs a bank account with a zero balance
	*/
	public BankAccount(){
		balance = 0;
		balanceChangeLock = new ReentrantLock(); // lock
		// condition object associated with specific 
		// lock object (balanceChangeLock)
		sufficientFundsCondition = balanceChangeLock.newCondition();
		

	}
	
	/**
	  Deposits money into the bank account.
	  @param amount the amount to deposit
	  
	  *** NEW: using try/finally and lock to lock resource ***
	*/
	public void deposit(double amount){

		balanceChangeLock.lock();  // lock!
		try
		{
			System.out.print("Depositing " + amount);
			double newBalance = balance + amount;
			System.out.println(", new balance is " + newBalance);
			balance = newBalance;
			// Funds added to balance...
			// Unblock other threads waiting on the condition by "signalAll"
			sufficientFundsCondition.signalAll();
		}
		finally
		{
			balanceChangeLock.unlock(); // unlock!
		}
	}
	
	/**
	  Withdraws money from the bank account.
	  @param amount the amount to withdraw
	  
	  *** NEW: using try/finally and lock to lock resource ***
	*/
	public void withdraw(double amount) throws InterruptedException {
		
		balanceChangeLock.lock();  // lock!
		try
		{
			// Check condition:
			while (balance < amount) {  
				// If balance is less than withdrawal amount...
				// Condition object calls "await"
				sufficientFundsCondition.await();   // await!	
				// Another thread can now acquire the lock object
			}
			System.out.print("Withdrawing " + amount);
			double newBalance = balance - amount;
			System.out.println(", new balance is " + newBalance);
			balance = newBalance;
			
		}
		finally
		{
			balanceChangeLock.unlock(); // unlock!
		}		
	}
	
	/**
	  Gets the current balance of the bank account.
	  @return the current balance
	*/
	public double getBalance(){
		return balance;
	}
}
