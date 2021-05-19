//Lab 5: Inheritance 

public class Person implements Comparable<Person>{
    
	protected String name;
	protected String homeAddress;
	
	public Person(String name, String address) {
        this.name = name;
		this.homeAddress = address;
	}

	// NOTE: Best practice is to include a default constructor in the superclass.
	public Person() {
		this.name = "?";
		this.homeAddress = "?";
	}

	public String getMailingAddress() {
		return homeAddress;
	}
	
	public String toString() {
		return "{Person: name=" + name + ", homeAddress=" + homeAddress + "|";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return homeAddress;
	}

	public void setAddress(String address) {
		this.homeAddress = address;
	}
	
	public static void main(String[] args) {
		Person p1 = new Person("p1", "addr1");
		System.out.println(p1);
	}

    @Override
    public int compareTo(Person o) {
        // TODO Auto-generated method stub
        return this.name.compareTo(o.name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((homeAddress == null) ? 0 : homeAddress.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (homeAddress == null) {
            if (other.homeAddress != null)
                return false;
        } else if (!homeAddress.equals(other.homeAddress))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    
    
}
