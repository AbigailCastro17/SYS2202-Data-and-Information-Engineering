import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

public class PancakeMakerTest {

	PancakeMaker p = new PancakeMaker();
	
	@Test(timeout=100)
    public void someIngredientsTest() {
        /*
         * Your test implementation goes here.
         */
		String[] ingredientsIHave = {"flour","salt"};
		
		ArrayList<String> ingredients1 = new ArrayList<String>();
		ingredients1.add("egg");
		ingredients1.add("buttermilk");
		ingredients1.add("powder");
		ingredients1.add("soda");
		
		
    	ArrayList<String> expected1 = ingredients1;
    	ArrayList<String> actual1 = p.determineWholeFoodsOrder(ingredientsIHave);
    	//System.out.println(Arrays.toString(actual1.toArray()));
    	assertEquals("The expected",expected1 ,actual1);
  
    	
	}
	
	@Test(timeout=100)
	public void noIngredientsTest() {
		
		String[] ingredientsIHave2 = {};
		
		ArrayList<String> ingredients2 = new ArrayList<String>();
		ingredients2.add("egg");
		ingredients2.add("buttermilk");
		ingredients2.add("powder");
		ingredients2.add("soda");
		ingredients2.add("salt");
		ingredients2.add("flour");
		
    	ArrayList<String> expected2 = ingredients2;
    	ArrayList<String> actual2 = p.determineWholeFoodsOrder(ingredientsIHave2);

    	assertEquals("The expected",expected2 ,actual2);
	}
	
	@Test(timeout=100)
	public void sameIngredientsTest() {
		String[] ingredientsIHave3 = {"flour","salt","soda", "powder", "buttermilk", "egg"};
		
		ArrayList<String> ingredients3 = new ArrayList<String>();
		
    	ArrayList<String> expected3 = ingredients3;
    	ArrayList<String> actual3 = p.determineWholeFoodsOrder(ingredientsIHave3);
    	assertEquals("The expected",expected3 ,actual3);
		
	}
}