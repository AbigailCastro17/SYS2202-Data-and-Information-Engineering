//Lab 4
//RandomWords.java
import java.nio.charset.StandardCharsets;

/**
 * Use this to test your hash function on 100 random words and a hash table with 500 entries.
 *  You should use linear probing as your collision resolution strategy. How many collisions do you have? 
 *  Test your hash function again using 300 words, 500 words, and 700 words. Do the number of collisions 
 *  increase or decrease as the number of words increases? What do your results (especially from the 700 words input)
 *  tell you about how large the table should be, relative to the number of inputs?

Hint: to make a hash table, start by creating an array of 500 elements. 
Then, for each random string, take the result of the hash function and insert the element at that particular index.
 For example, if hashFunction("abcd") returns 58, then insert the string "abcd" at index 58 in the array.
 */

import java.util.Random;

public class RandomWords {

	public static void main(String[] args) {
		
		/*String[] s1= RandomWords.generateRandomWords(5);
		System.out.println(Arrays.toString(s1));
		System.out.println(RandomWords.collisionCount(s1));*/
		
		System.out.println("100 words");
		System.out.println(RandomWords.collisionCount(RandomWords.generateRandomWords(100)));
		System.out.println(RandomWords.collisionCount(RandomWords.generateRandomWords(100)));
		System.out.println(RandomWords.collisionCount(RandomWords.generateRandomWords(100)));
		
		System.out.println("300 words");
		System.out.println(RandomWords.collisionCount(RandomWords.generateRandomWords(300)));
		System.out.println(RandomWords.collisionCount(RandomWords.generateRandomWords(300)));
		System.out.println(RandomWords.collisionCount(RandomWords.generateRandomWords(300)));
		
		System.out.println("500 words");
		System.out.println(RandomWords.collisionCount(RandomWords.generateRandomWords(500)));
		System.out.println(RandomWords.collisionCount(RandomWords.generateRandomWords(500)));
		System.out.println(RandomWords.collisionCount(RandomWords.generateRandomWords(500)));
		
		//System.out.println(RandomWords.collisionCount(RandomWords.generateRandomWords(700)));
		//System.out.println(RandomWords.collisionCount(RandomWords.generateRandomWords(700)));
		//System.out.println(RandomWords.collisionCount(RandomWords.generateRandomWords(700)));
		//System.out.println(RandomWords.hashFunction("abcd"));
		
	}

	//A static method that takes in the number or words you would like in your array of 
	//random words.  Each word is between 3 and 10 characters in length and contain only
	//lower case letters.  
	public static String[] generateRandomWords(int numberOfWords)
	{
	    String[] randomStrings = new String[numberOfWords];
	    Random random = new Random();
	    for(int i = 0; i < numberOfWords; i++)
	    {
	        char[] word = new char[random.nextInt(8)+3]; // Words of length 3 through 10. (Because 1 and 2 letter words are boring.)
	        for(int j = 0; j < word.length; j++)
	        {
	            word[j] = (char)('a' + random.nextInt(26));
	        }
	        randomStrings[i] = new String(word);
	    }
	    return randomStrings;
	}
	
	public static int hashFunction(String str) {
		int sum = 0;
		byte[] ascii = str.getBytes(StandardCharsets.US_ASCII);
		for (int i=0;i<ascii.length;i++) {
			sum = sum + ascii[i];
		}
		return sum%500;
	}
	
	public static int collisionCount(String[] randomStrings) {
		String[] random = new String[500];
		int count = 0;
		for(String ele : RandomWords.generateRandomWords(100)) {
			int x= RandomWords.hashFunction(ele);
			if (random[x]!=null) {
				while (random[x]!=null) {
					count=count+1;
					x=x+1;
				}
				random[x]=ele;
			}
			else
				random[x]=ele;
		}
		return count;
	}

}

	
