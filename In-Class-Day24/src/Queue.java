/*
Name:
Computing ID:

*/


public class Queue {

	final int INITIAL_SIZE = 10;
	String[] elements;
	int currentSize, head, tail;
	
	public Queue(){
		this.elements = new String[this.INITIAL_SIZE];
		this.currentSize = this.head = this.tail = 0;
	}
	
	public void add(String s) {
		// TODO: Complete this method to implement the add() method for a Queue
        growIfNecessary();
	    elements[tail] = s;
        tail = (tail + 1) % elements.length;
        currentSize++;
        System.out.println("Current Size:" + currentSize + "\n");
	}
	
	public String remove() {
		// TODO: Complete this method to implement the remove() method for a Queue
        if (head==tail) {
            System.out.println("Current Size:" + currentSize);
            return null + "\n";
        }
        else {
            String removed = elements[head];
            head = (head + 1) % elements.length;
            currentSize--;
            System.out.println("Current Size:" + currentSize);
            return "Removed Element: " + removed + "\n";
        }
        
	}
	
	private void growIfNecessary(){
		if(currentSize == elements.length){
			String[] newElements = new String[2*elements.length];
			for(int i = 0; i < elements.length; i++){
				newElements[i] = elements[(head+i)%elements.length];
			}
			elements = newElements;
			head = 0;
			tail = currentSize;
		}
	}
	
	
	public static void main(String[] args) {
        // TODO: Use main-method testing to test your code!
        // Optional suggestion: Write a print method to show the contents of the queue and maybe even your pointers
        Queue s = new Queue();
        s.add("Lorna");
        s.add("Sandy");
        s.add("Lucas");
        s.add("Zach");
        System.out.println(s.remove());
        System.out.println(s.remove());
        System.out.println(s.remove());
        System.out.println(s.remove());
        System.out.println(s.remove());
        s.add("Help");
        System.out.println(s.remove());
        System.out.println(s.remove());
        System.out.println(s.remove());

	}

}
