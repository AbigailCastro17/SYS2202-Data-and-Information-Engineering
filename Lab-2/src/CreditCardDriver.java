//Demonstrates the CreditCard class
//NEEDS MODIFICATION

public class CreditCardDriver
{
	public static void main(String[] args)
	{
		final Money LIMIT = new Money(1000);
		final Money FIRST_AMOUNT = new Money(200);
		final Money SECOND_AMOUNT = new Money(10.02);
		final Money THIRD_AMOUNT = new Money(25);
		final Money FOURTH_AMOUNT = new Money(990);
		Person owner = new Person("Bruce", "Wayne", 
				new Address("123 Wayne Manor", "Gotham","NY", "54321"));
		CreditCard visa = new CreditCard(owner, LIMIT);

		//INSERT MAIN METHOD TESTING HERE
		/*Test the following CreditCard methods:
		 * - getPersonals()
		 * - getCreditLimit()
		 * - getBalance()
		 * - normal use of charge() with FIRST_AMOUNT then SECOND_AMOUNT
		 * - normal use of payment() with THIRD_AMOUNT
		 * - charge() that exceeds the balance limit with FOURTH_AMOUNT
		 */
		System.out.println(visa.getPersonals());
		visa.getCreditLimit();
		visa.getBalance();
		visa.charge(FIRST_AMOUNT);
		visa.charge(SECOND_AMOUNT);
		visa.payment(THIRD_AMOUNT);
		visa.charge(FOURTH_AMOUNT);
		
		//Hint: Print out the balance after each test & check that your 
		//output matches the output provided in the lab instructions.
	}
}