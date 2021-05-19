/**
 * 
 * @author CS 2110 faculty
 * Pyramid class
 * a pyramid is a 3d shape made from 1 rectangle and 4 triangles - like an octahedron bisected through the square
 *
 */
public class Pyramid implements Solids {

	private double length, width, height;
	private String color, name;

	/** constructor */
	public Pyramid(double length, double width, double height, String color, String name) {
		this.length = length;
		this.width = width;
		this.height = height;
		this.color = color;
		this.name = name;
	}
	
	
	//Volume = length*width*height/3
	public double getVolume(){
		return length*width*height/3;
	}
		
	public String getColor() {
	       return color;
	}
    //return name of solid in a String
    public String getName() {
        return name;
    }
}
