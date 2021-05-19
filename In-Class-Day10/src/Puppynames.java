
import java.util.HashSet; //allows us to use HashSet
public class Puppynames { //class
	
	//fields
	public HashSet<String> cards;
	public HashSet<String> topNames;

	
	Puppynames() { //default constructor
		this.cards = new HashSet<String>(); //assigns cards to a new HashSet
		this.topNames = new HashSet<String>(); //assigns topNames to a new HashSet
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Puppynames p = new Puppynames(); //makes new Puppynames object
		
		//adds names to cards
		p.cards.add("Sarah");
		p.cards.add("Lucky");
		p.cards.add("B");
		p.cards.add("Allen");
		p.cards.add("Bo");
		p.cards.add("Jax");
		
		//adds names to topNames
		p.topNames.add("Lucky");
		p.topNames.add("Jake");
		p.topNames.add("Bo");
		p.topNames.add("Bella");
		p.topNames.add("Charlie");
		p.topNames.add("Luna");
		p.topNames.add("Lucy");
		p.topNames.add("Max");
		p.topNames.add("Cooper");
		p.topNames.add("Jax");
		
		//runs names method on object p with parameters cards and topNames
		System.out.println(p.names(p.cards,p.topNames));
		
	}
	
	//names method with two HashSet parameters
	public HashSet<String> names(HashSet<String> cards, HashSet<String> topNames){
		HashSet<String> newNames = new HashSet<String>(); //makes new HashSet for the names that still need to be but on cards
		for(String ele : topNames) { //loops through topNames
			if(!cards.contains(ele)){ //if cards does not contain the element
				newNames.add(ele);//adds element to newNames
			}

		}
		return newNames; //returns newNames
	}


}
