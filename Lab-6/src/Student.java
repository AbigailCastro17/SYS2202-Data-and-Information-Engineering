
public class Student extends Person {

    //fields
    public double gpa;
    public String campusAddress;
    
    Student(String name, String ha) {
        super(name, ha);
        this.campusAddress = "";
        this.gpa = 0.0;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Student s1 = new Student("Abby", "126 Gooch");
        Student s2 = new Student("Abby", "126 Gooch");
        
        System.out.println(s1.equals(s2));
    }

}
