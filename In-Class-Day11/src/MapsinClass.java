//Abby Castro abc3gnm

import java.util.*;
public class MapsinClass {
	
	public TreeMap<String, Integer> phoneBook;
	
	MapsinClass(){
		this.phoneBook = new TreeMap<String,Integer>();
	}

	public static void main(String[] args) {
		// Main Method Testing
		MapsinClass m = new MapsinClass();
		TreeMap<String, Integer> phoneBook = new TreeMap<String, Integer>();
		phoneBook.put("Lorna", 321);
		phoneBook.put("Anna", 456);
		phoneBook.put("Grace",789);
		phoneBook.put("Clayton",115);
		System.out.println(m.reverseBook(phoneBook));
		//Output should be: {115=Clayton, 321=Lorna, 456=Anna, 789=Grace}
		
	}

	
	public Map<Integer, String> reverseBook(Map<String, Integer> phoneBook){
		TreeMap<Integer, String> reverse = new TreeMap<Integer, String>();
		for(String key: phoneBook.keySet()) {
			reverse.put(phoneBook.get(key), key);
		}	
		return reverse;
	}
	
}
