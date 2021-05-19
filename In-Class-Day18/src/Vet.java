/**
 * 
 * @author CS 2110 Faculty
 * Vet class, where all domesticated animals must visit
 *
 */
public class Vet {

	// TODO The following method is incomplete; you need to fix it to:
	// a) take an animal argument and b) have the animal make its noise
	public static void giveShot(Animal a) {
		// do horrible things to the animal
		
		System.out.println(String.valueOf(a.getClass()).substring(6) + " " + a.name + " after the shot cried " + a.makeNoise());

	}
	
	public static void main(String[] args) {
		Animal a = new Dog("Jack");
		//String aType = String.valueOf(a.getClass()).substring(6);
		Animal c = new Cat("Garfield");
		Animal b = new Animal("Marlow");

		// TODO Complete the rest of the code to print the following output:
		// > Dog Jack after the shot cried Woof!
		// > Cat Garfield after the shot cried Miaw!
		// > Animal Marlow after the shot cried ...
		Vet.giveShot(a);
		Vet.giveShot(c);
		Vet.giveShot(b);
	}

}
