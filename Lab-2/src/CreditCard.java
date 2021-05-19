//Defines a credit card
//NEEDS MODIFICATION

public class CreditCard
{
	private Money balance;		//the current balance
	private Money creditLimit;	//the approved credit limit
	private Person owner;		//the owner of the credit card

	//Constructor creates a credit card for the Person parameter
	//with an approved credit limit of the Money parameter
	//and sets the balance to a Money amount of zero 
	public CreditCard(Person newCardholder, Money limit) 
	{
		//MUST COMPLETE THIS
		this.owner = newCardholder;
		this.creditLimit = limit;
		this.balance = new Money(0.0);
	}

	//Accessor method returns the balance
	public Money getBalance()
	{
		//MUST COMPLETE THIS
		Money b = new Money(balance);
		System.out.println("Balance: "+b);
		return b;
	}

	//Accessor method returns the credit limit
	public Money getCreditLimit()
	{
		//MUST COMPLETE THIS
		Money c = new Money(creditLimit);
		System.out.println("Credit Limit: "+c);
		return c;
	}

	//Accessor method returns information about the owner
	public String getPersonals()
	{
		//MUST COMPLETE THIS
		return owner.toString();
	}
	
	//Method to make a charge to the credit card (but only if
	//the credit limit will not be exceeded)
	public void charge(Money amount)
	{
		//MUST COMPLETE THIS
		Money x = amount.add(balance);
		if(x.compareTo(creditLimit)==1) {
			System.out.println("Attempt to charge " + amount);
			System.out.println("Exceeds credit limit");
			System.out.println("Balance: " + balance);
		}
		else {
			balance = x;
			System.out.println("Attempt to charge " + amount);
			System.out.println("Balance: " + balance);
		}
	}

	//Method to make a payment to the credit card
	public void payment(Money amount)
	{
		//MUST COMPLETE THIS
		balance = balance.subtract(amount);
		System.out.println("Attempt to charge " + amount);
		System.out.println("Balance: "+ balance);
		
	}		
}