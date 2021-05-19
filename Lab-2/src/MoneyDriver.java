//Demonstrates the Money class
//DOES NOT NEED TO BE MODIFIED

public class MoneyDriver
{
	public static void main(String[] args)
	{
		//Below are some constant variables for testing purposes
		final long BEGINNING = 500L;
		final Money FIRST_AMOUNT = new Money(10.02);
		final Money SECOND_AMOUNT = new Money(10.02);
		final Money THIRD_AMOUNT = new Money(10.88);
		
		Money balance = new Money(BEGINNING);

		//Test toString()
		System.out.println("The current amount is " + balance.toString());
		
		//Test add()
		balance = balance.add(SECOND_AMOUNT);
		System.out.println("Adding " + SECOND_AMOUNT + " gives " + balance.toString());
		
		//Test subtract()
		balance = balance.subtract(THIRD_AMOUNT);
		System.out.println("Subtracting " + THIRD_AMOUNT + " gives " + balance.toString());
		
		//Test equals()
		boolean equal = SECOND_AMOUNT.equals(FIRST_AMOUNT);
		if(equal) {
			System.out.println(SECOND_AMOUNT + " equals " + FIRST_AMOUNT); 
			//Since FIRST_AMOUNT is the same as SECOND_AMOUNT, the first statement should reflect this.
		}
		else {
			System.out.println(SECOND_AMOUNT + " does not equal " + FIRST_AMOUNT);
		}
		
		equal = THIRD_AMOUNT.equals(FIRST_AMOUNT);
		if(equal) {
			System.out.println(THIRD_AMOUNT + " equals " + FIRST_AMOUNT);
		}
		else {
			System.out.println(THIRD_AMOUNT + " does not equal " + FIRST_AMOUNT); 
			//Since THIRD_AMOUNT is not equal to FIRST_AMOUNT, the second statement should reflect this.
		}
	}
}