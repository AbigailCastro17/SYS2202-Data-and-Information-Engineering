
public class Circle {
	
	//fields
	public double radius;
	public Point center;
	
	public Circle() { //default constructor
		this.radius = 2.9;
		this.center = new Point (0.0, 0.0);
	}
	
	public Circle(double r, Point C) {
		this.radius = r;
		this.center = C;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point centerPt = new Point (1.0, 9.0);
		Circle c1 = new Circle (5.0, centerPt);
		System.out.println(c1);
	}

	public String toString() {
		return "The radius is " + this.radius + " cm. The center is at point " + this.center + "." ;
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	
}
