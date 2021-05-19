
public class Circle {

	private Point p;
	private double radius;
	
	public Circle(Point c, double rad){
		p = c;
		radius = rad;
	}
	
	public Circle(){
		p = new Point(0,0);
		radius = 1;
	}
	
	
	
	public static void main(String[] args) {
	
		Point p = new Point(1,1);
		Circle c = new Circle(p, 3);
		System.out.println(c);
		
		String s = "3.1415";
		double pi = Double.parseDouble(s);
		System.out.println(pi * 5);
		
		Circle c2 = c;
		System.out.println(c2.equals(c));
		System.out.println(c2==c);
		
		
		Circle c3 = new Circle(p,3);
		System.out.println(c3.equals(c));
		System.out.println(c3==c);
		
		Circle c4 = new Circle(p,4);
		System.out.println(c4.equals(c));
		System.out.println(c4==c);
	}
	
	@Override
	public String toString(){
		return "<" + p + ", " + "radius: " + radius +">";
	}

	public boolean equals (Object o) {
		if (o == null) {
			return false; //null check
		}
		if (o instanceof Circle) { //are you a point
			Circle c1 = (Circle) o; //casting
			//now I can use c1 as a Circle
			return (this.radius == c1.radius)&& (this.p == c1.p) ;
		}
		else {//o is not a Point
			return false;
		}
	
	}
	
	
}
