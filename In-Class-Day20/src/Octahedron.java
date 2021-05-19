/**
 * 
 * @author CS 2110 faculty
 * Octahedron class
 * an Octahedron is a 3d shape made from only 8 equilateral triangles 
 *
 */

public class Octahedron implements Solids{
	
	private double edge;
	private String color;
	private String name;
	
	/** constructor */
	public Octahedron(double edge, String color, String name){
		this.edge = edge;
		this.color = color;
		this.name = name;
	}
	
	//Volume sqrt(2)/3 times edge^3
	public double getVolume(){
		return (Math.pow(2, 0.5)/3)*Math.pow(edge, 3);
	}
	
	public String getColor() {
           return color;
    }
    //return name of solid in a String
    public String getName() {
        return name;
    }
	
}
