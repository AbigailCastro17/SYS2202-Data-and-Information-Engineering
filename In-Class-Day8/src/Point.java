//Point class - a point in 2D space
public class Point {

	//fields - instance variables	
	public double x; //x-coordinate
	public double y; //y-coordinate
	
	//constructor for the Point class
	public Point (double x, double y) { //or (double myX, double myY)
		this.x = x; //this.x = myX
		this.y = y; //this.y = myY
		//myY = this.y NOT CORRECT!
	}
	
	//default constructor
	public Point() {
		this.x = 0.0; //setting default values
		this.y = 0.0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Point p; //a Point reference, not an actual Point
		Point p1 = new Point(2.0,3.5); //uses default constructor
		Point p2 = new Point (2.1,3.6); //other constructor b/c passed two values
		
		//Test constructor
		System.out.println(p1); //print my point please??
		System.out.println(p2);
		p2.setX(5.0);
		System.out.println(p2);

	}
	
	
	
	
	public String toString() {
		return "("+ this.x + ", " + this.y + ")";
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	

	
	
	
}
