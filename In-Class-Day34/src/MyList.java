/**
 * MyList
 * 
 * CS 2110: April 15, 2020
 *
 */

public class MyList {
	/**
	 * Node sub-class
	 *
	 */
	private class Node {
		int value;
		Node next;
		
		public Node(int value) {
			this.value = value;
			next = null;
		}
	}
	
	//fields
	private Node head, tail;
	private int size = 0;
	
	/**
	 * Return whether or not the list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Get the current size of the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Add the node to the end of the list
	 */
	public void add(int x) {
		Node newNode = new Node(x);
		if (isEmpty()) {
			//if it's empty, the new node becomes both the first and last node
			//   in the list
			head = newNode;
			tail = newNode;
			size++; //don't forget to increment size!
		} else {
			//otherwise, add it to the tail exactly like queue
			tail.next = newNode;
			tail = newNode;
			size++;
		}
	}
	
	/**
	 * Get the index-th value of the List
	 * Example: 5 -> 7 -> 8 -> 10 -> 2 -> 3
	 * get(3) would return 10
	 * get(1) would return 7
	 */
	public int get(int index) {
		//make sure index is in bounds
		if ((index < 0) || index >= size()) {
			//if it's not, throw an exception
			throw new IllegalArgumentException("Error: " + index + " is not a valid index");
		}
		//create our traveling node
		Node t = head;
		
		//loop to the index-th value
		while (index > 0) {
			index--;
			t = t.next;
		}
		
		//return the value at that node
		return t.value;
	}
	
	/**
	 * Check if the list contains the value "target"
	 * @param target
	 * @return
	 */
	
	public boolean contains(int target) {
		//A simple for loop to iterate through the list
		for (Node t = head; t != null; t = t.next) {
			//if t's value is the target
			if (t.value == target) {
				return true; //we found it!
			}
		}
		//if the loop ends, we've checked every value and have not
		//found target
		return false;
	}
	
	
	/**
	 * Return the first index where target is found in the list
	 * If target isn't found, return -1;
	 * @param target
	 * @return
	 */
	public int find(int target) {
		//TODO: Stub
	    int index=0;
	    for (Node t = head; t != null; t = t.next) {
            //if t's value is the target
            if (t.value == target) {
                return index; //we found it!
            }
            index++;
        }
        //if the loop ends, we've checked every value and have not
        //found target
        return -1;
	}
	
	/**
	 * Add a node containing "value" at position "index" in the linked list
	 * @param index
	 * @param value
	 */
	public void add(int index, int value) {
		//make sure index is in bounds
		if ((index < 0) || index > size()) {
			throw new IllegalArgumentException("Error: " + index + " is not a "
					+ "valid index to add at");
		}
		//create the node
		Node newNode = new Node(value);
		
		//if the list is empty, add the node as you would to any empty list
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		}
		//if index is 0, add to the front
		else if (index == 0) {
			newNode.next = head;
			head = newNode;
		//if index == size(), we are adding at the tail:
		} else if(index == size()) {
			tail.next = newNode;
			tail = newNode;
		} else {			
			//create the traveling node
			Node t = head;
			
			//get the the node before index
			while (index > 1) {
				index--; //decrement index to signal getting closer
				t = t.next; //move to the next node
			}
			//add the node after t
			newNode.next = t.next; //have newNode.next point to t's next
			t.next = newNode; //have t.next point to newNode
		}
		size++; //increment size
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Head: ");
		if (isEmpty()) {
			sb.append("null");
			return sb.toString();
		} else {
			sb.append(head.value);
		}
		for (Node t = head.next; t != null; t = t.next) {
			sb.append(" -> " + t.value);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		MyList list = new MyList();
		System.out.println(list.isEmpty() + " should be true");
		//test add at 0 index on empty list
		list.add(0, 8);
		//test simple add at the end
		list.add(7);
		
		//test add at index for the end of the list
		list.add(2,5);	
		list.add(3);
		list.add(0);
		
		//test add into the middle of the list
		list.add(1, 6);
		list.add(9);
		//test size
		System.out.println(list.size() + " should be 7");
		
		//test toString
		System.out.println(list);
		
		//test get
		System.out.println(list.get(3) + " should be 5");
		
		//test find
		System.out.println(list.find(9) + " should be 6"); //this test will fail until
		                                                   //you implement find()
		System.out.println(list.find(2) + " should be -1");
		System.out.println(list.find(8) + " should be 0");
		System.out.println(list.find(6) + " should be 1");
		System.out.println(list.find(7) + " should be 2");
		System.out.println(list.find(5) + " should be 3");
		System.out.println(list.find(3) + " should be 4");
		System.out.println(list.find(0) + " should be 5");
		
		//test contains
		System.out.println(list.contains(3) + " should be true");
		System.out.println(list.contains(-5) + " should be false");
	}
}
