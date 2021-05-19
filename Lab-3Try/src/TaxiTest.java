import static org.junit.Assert.*;

import org.junit.*;


public class TaxiTest {
	
	Taxi t;
	
	@Before
	public void setup() {
		//t = new Taxi();
		t = new Taxi(12.00, 5);
	}

    //Sample Unit Test
    @Test(timeout=100)
    public void testPickUpSuccess() {
        assertTrue(t.pickUp(5));//true
    }
    
    //calculateFare() test
    
	@Test(timeout=100)
    public void testCalculateFare() {
        /*
         * Your test implementation goes here.
         */
    	double expected1 = 60.0;
    	double actual1 = t.calculateFare(1, 5);
    	double expected2 = 0.0;
    	double actual2 = t.calculateFare(0,5);
    	double expected3 = 0.0;
    	double actual3 =t.calculateFare(1, -5);
    	assertEquals("The expected",expected1,actual1,0.01);
    	assertEquals("The expected",expected2,actual2,0.01);
    	assertEquals("The expected",expected3,actual3,0.01);
    }
    
    //pickUp() test #1
    @Test(timeout=100)
    public void testPickUpEnoughRoom() {
        /*
         * Make sure pickUp() returns true when you try to pick up some
         * number of passengers within capacity.
         */
    	assertTrue(t.pickUp(1));
    	assertFalse(t.pickUp(-1));
    	assertFalse(t.pickUp(6));
    }
    
    //pickUp() test #2
        /*
         * Make sure pickUp() returns false when you try to pick up more
         * passengers than the capacity.
         */
}
