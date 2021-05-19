
public class PezDispenser {
	
	//fields
	public String characterName;//e.g.Yoshi
	public String sleeveColor;//e.g. red
	public int count;//num of candies currently in the dispenser
	//public int capacity;//how many candies can it hold
	public final int CAPACITY = 12;
	
	//constructor
	public PezDispenser(String name, String sleeve, int cap) {
		characterName = name;
		sleeveColor = sleeve;
		count = 0;
		// NOT include the constant here
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//other methods
	public void load() {
		count = CAPACITY; //usually 12
	}
	public boolean dispense() {
		if (count>0) {
			count--; //subtract one candy --> reduce count by one
			return true;
		}
		else
			return false;	
	}
}
