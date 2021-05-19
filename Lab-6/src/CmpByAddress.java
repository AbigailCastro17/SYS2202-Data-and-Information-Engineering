import java.util.Comparator;

public class CmpByAddress implements Comparator <Person> {

    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Person p1 = new Person("Mai", "3156 Grove Rd, Somewhere");
        Person p2 = new Person("Steve", "001 Terrace Road, Streetsville");
        CmpByAddress a1= new CmpByAddress();
        System.out.println(a1.compare(p1, p2));
        
    }

    @Override
    public int compare(Person o1, Person o2) {
        // TODO Auto-generated method stub
        return o1.homeAddress.compareTo(o2.homeAddress);
    }

}
