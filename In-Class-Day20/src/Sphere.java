/**
 * 
 * @author CS 2110 faculty
 * Sphere class
 * a Sphere is a 3d shape representing all points equidistant from a center point
 *
 */
public class Sphere implements Solids {
	
	private double radius;
	private String color, name;
	
	/** constructor */
	public Sphere(double radius, String color, String name){
		this.radius = radius;
		this.color = color;
		this.name = name;
	}
	
	//Volume = 4/3*pi*r^3
	public double getVolume(){
		return (4/3)*(Math.PI)*(Math.pow(radius, 3));
	}
	
	public String getColor() {
           return color;
    }
    //return name of solid in a String
    public String getName() {
        return name;
    }
}
