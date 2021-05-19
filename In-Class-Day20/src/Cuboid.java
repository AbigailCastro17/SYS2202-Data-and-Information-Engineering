/**
 * 
 * @author CS 2110 faculty
 * Cuboid class
 * a cuboid is a 3d rectangle - 6 rectangles joined at right angles
 *
 */
public class Cuboid implements Solids {

	private double length, width, height;
	private String color, name;
	
	/** constructor */
	public Cuboid(double length, double width, double height, String color, String name){
		this.length = length;
		this.width = width;
		this.height = height;
		this.color = color;
		this.name = name;
	}
	
	//Volume = length*width*height
	public double getVolume(){
		return length*width*height;
	}
	
	public String getColor() {
           return color;
    }
    //return name of solid in a String
    public String getName() {
        return name;
    }
	
	
}
