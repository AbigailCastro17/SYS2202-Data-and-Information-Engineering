import static org.junit.Assert.*;

import org.junit.Test;

public class InClass13Test {
	
	@Test (timeout=100)
	public void negativeExpTest() {
		
		double expected = 0.25;
		double actual = InClass13.power(2.0,-2);
		assertEquals("Error:", expected, actual,0.01);
	}
	
	@Test (timeout=100)
	public void zeroBaseTest() {
		
		double expected = 0.0;
		double actual = InClass13.power(0.0,2);
		assertEquals("Error:", expected, actual,0.01);
	}
	
	@Test (timeout=100)
	public void zeroBaseExpTest() {
		
		double expected = Double.NaN;
		double actual = InClass13.power(0.0,0);
		assertEquals("Error:", expected, actual,0.01);
	}
	
	//Other tests but above tests cover everything in InClass13
	/*@Test (timeout=100)
	public void positiveBaseExpTest() {
		
		double expected = 4.0;
		double actual = InClass13.power(2.0,2);
		assertEquals("Error:", expected, actual,0.01);
		
	}*/
	
	/*@Test (timeout=100)
	public void negativeBaseTest() {
		
		double expected = 4.0;
		double actual = InClass13.power(-2.0,2);
		assertEquals("Error:", expected, actual,0.01);	
	}*/
	
	
	
	/*@Test (timeout=100)
	public void negativeBaseExpTest() {
		
		double expected = 0.25;
		double actual = InClass13.power(-2.0,-2);
		assertEquals("Error:", expected, actual,0.01);
	}*/
	
	/*@Test (timeout=100)
	public void zeroExpTest() {
		
		double expected = 1.0;
		double actual = InClass13.power(2.0,0);
		assertEquals("Error:", expected, actual,0.01);
	}*/
	

	

}
