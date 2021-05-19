
public class Point {
	
	private double x;
	private double y;

	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public static void main(String[] args) {
		Point p1 = new Point(2, 2);
		Point p2 = new Point(1, 2);
		System.out.println("Point p1 has x value: " + p1.getX());
		System.out.println("Point p2 has x value: " +  p2.getX());
		p2.setY(3);
		System.out.println("Point p1 has y value: " + p1.getY());
		System.out.println("Point p2 has y value: " + p2.getY());
		
		System.out.println(p1);
		System.out.println(p2);

		System.out.println(p1.distance(p2));
		
		Point p3 = p1; //alias two refs to the same Point
		Point p4 = new Point (2.0, 2.0); //equal to p1 diff objects
		Point p5 = new Point(9.0,4.9);//different
		
		//2 points
		System.out.println(p1.equals(p3)); //true
		System.out.println (p1 == p3); //true
		
		System.out.println(p1.equals(p4));// true (what we mean)
		System.out.println(p1 == p4);// false -- not what we mean
		
		System.out.println(p1.equals(p5)); //false
		System.out.println (p1 == p5); //false
	}
	
	public double distance(Point p){
		return Math.sqrt((this.x-p.x)*(this.x-p.x) + (this.y-p.y)*(this.y-p.y));
		
	}
	
	@Override
	public String toString(){
		return "(" + this.getX() + ", " + this.y + ")";
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

	
	
	
	
	public boolean equals (Object o) {
		if (o == null) {
			return false; //null check
		}
		if (o instanceof Point) { //are you a point
			Point p2 = (Point) o; //casting
			//now I can use p2 as a Point
			return (this.x == p2.x)&& (this.y == p2.y) ;
		}
		else {//o is not a Point
			return false;
		}
	}
	
	
}
