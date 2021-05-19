
/**
 * Homework 5
 * 
 * Implement the following methods on recursion
 * as defined in the homework 5 document.
 * 
 * @author Abigail Castro abc3gnm
 *
 */
public class Recursion {
    
    public static boolean palindrome(String s) {
        //finds if a word is the same backwards and forwards by checking if the first and last letter are the same
        //and checks first and last character for each substring without the first and last character of the previous string
        if(s.length() <= 1) {//base case
            return true;
        }    
        if(s.charAt(0) == s.charAt(s.length()-1)) {//is true if the first and last letter is the same
            return palindrome(s.substring(1, s.length()-1));//recursive case
        }
        return false;//returns false otherwise
    }
    
    public static String reverseString(String s) {
        if (s.length()<=1) {//if the string is empty or only has one character return the string
            return s; 
        }
        else {
        return reverseString(s.substring(1)) + s.charAt(0);//return the reverse of the substring plus the first character
        }
    }
    
    public static int handshakes(int n) {//Each person shakes hands once with every other person, return the number of handshakes
        if (n<2) //If there are fewer than 2 people, then no one can shake hands
            return 0;
        else//return the sum of every number less than n
            return (n-1)+handshakes(n-1);
    }    
    
  //a recursive mathematical algorithm that can be used to test how well a computer performs recursion
    public static long ackermann(long m, long n) { 
        if (m==0)
            return n+1;
        if (n==0)
            return ackermann(m-1,1);
        else
            return ackermann(m-1,ackermann(m,n-1));
    }
    


    public static void main(String[] args) {
        
        String word1 = "racecar";       // true
        String word2 = "rotor";         // true
        String word3 = "motor";         // false
        String word4 = "axpghpxa";      // false
        String word5 = "xrtiiyrx";      // false
        String word6 = "wollem mellow"; // true
        
        // palindrome tests
        System.out.println("Is " + word1 + " a palindrome? " + palindrome(word1));
        System.out.println("Is " + word2 + " a palindrome? " + palindrome(word2)); 
        System.out.println("Is " + word3 + " a palindrome? " + palindrome(word3)); 
        System.out.println("Is " + word4 + " a palindrome? " + palindrome(word4)); 
        System.out.println("Is " + word5 + " a palindrome? " + palindrome(word5)); 
        System.out.println("Is " + word6 + " a palindrome? " + palindrome(word6)); 
        
        //reverseString tests 
        System.out.println(reverseString(word1));
        System.out.println(reverseString(word2));
        System.out.println(reverseString(word3));
        System.out.println(reverseString(word4));
        System.out.println(reverseString(word5));
        System.out.println(reverseString(word6));
        
        //handshakes tests
        System.out.println(handshakes(0));
        System.out.println(handshakes(3));
        System.out.println(handshakes(6));
        
        //ackermann tests
        System.out.println(ackermann(0,0));//1
        System.out.println(ackermann(2,0));//3
        System.out.println(ackermann(3,4));//125

    }
}