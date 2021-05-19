// Abigail Castro abc3gnm
// partner id: st2ht
public class Rectangle {
	
	private double length, width;
	
	public Rectangle() {
		length = 1;
		width = 1;
	}
	
	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}
	
	//write an appropriate toString method, getters and setters
	public String toString() {
		return "Rectangle Dimensions: "+ this.length + " cm x " + this.width + " cm";
	}
	
	
	
	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rectangle r1 = new Rectangle(1.0,4.0);
		System.out.println(r1);
		Rectangle r2 = new Rectangle();
		System.out.println(r2);
		System.out.println(r2.getLength());
		System.out.println(r1.getWidth());
		r2.setLength(5);
		r2.setWidth(3);
		System.out.println(r2);
	}

}
