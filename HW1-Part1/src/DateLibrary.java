/**
* Homework 2B
* Abigail Castro , abc3gnm
*
*/
import java.util.*;

public class DateLibrary {
	
	//main method testing
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		String date1 = "2020-02-13";//after date2,valid format and date
		String date2 = "2001-08-17";//before date1, valid format and date
		String date3 = "2020-02-13";//same as date1
		String date4 = "000-02-12";//invalid format
		String date5 = "2020-13-12";//valid format, invalid month
		String date6 = "2020-02-32";//valid format, invalid day
		String date7 = "0000-02-13";//valid format, invalid year
		String date8 = "abcd-ef-gh";//invalid format
		String date9 = "2100-02-13";//valid format, not leap year
		String date10 = "2019-02-29";//valid format, not leap year so invalid
		String date11 = "2020-04-31";//valid format, invalid date
		
		
		
		System.out.println(DateLibrary.isValidDateFormat(date1));//true
		System.out.println(DateLibrary.isValidDateFormat(date4));//false
		System.out.println(DateLibrary.isValidDateFormat(date8));//false
		
		System.out.println(DateLibrary.getYear(date1));//2020
		System.out.println(DateLibrary.getYear(date7));//-1
		
		System.out.println(DateLibrary.getMonth(date1));//2
		System.out.println(DateLibrary.getMonth(date5));//-1
		
		System.out.println(DateLibrary.getDay(date1));//13
		System.out.println(DateLibrary.getDay(date6));//-1
		
		System.out.println(DateLibrary.isLeapYear(DateLibrary.getYear(date1)));//true
		System.out.println(DateLibrary.isLeapYear(DateLibrary.getYear(date9)));//false
		
		System.out.println(DateLibrary.isValidDate(date1));//true
		System.out.println(DateLibrary.isValidDate(date5));//false
		System.out.println(DateLibrary.isValidDate(date4));//false
		System.out.println(DateLibrary.isValidDate(date10));//false
		System.out.println(DateLibrary.isValidDate(date11));//false
		
		System.out.println(DateLibrary.compare(date1, date2));//positive
		System.out.println(DateLibrary.compare(date2, date1));//negative
		System.out.println(DateLibrary.compare(date1, date3));//0
		System.out.println(DateLibrary.compare(date1, date4));//0
		System.out.println(DateLibrary.compare(date11, date1));//0
	    System.out.println(DateLibrary.compare(date1, date10));//0
	    System.out.println(DateLibrary.isValidDate(date10));
        
	}
	
	//checks if substring is an int
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		try {
			Integer.parseInt(str);
		} 
		catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	//checks if the string input is in the YYYY-MM-DD format
	//does not check if the string is a valid date
	public static boolean isValidDateFormat(String date) {
		if(date.length()==10){
			if (date.substring(4, 5).equals("-") && date.substring(7, 8).equals("-")) {
				String strYear = date.substring(0,4);
				String strMonth = date.substring(5,7);
				String strDay = date.substring(8, 10);
				if (DateLibrary.isNumeric(strYear) && DateLibrary.isNumeric(strMonth) && DateLibrary.isNumeric(strDay)) {
				return true;	
				}
			}
		}
		return false;
	}
	
	
	//return the year from the date string
	//returns -1 if negative year invalid date format
	public static int getYear(String date) {
		if (DateLibrary.isValidDateFormat(date)) {
			String strYear = date.substring(0,4);
			if (strYear.compareTo("0001")>=0 && strYear.compareTo("9999")<=0) {
				return Integer.parseInt(date.substring(0,4));
			}
			else return -1;
		}
		else {
			
			return -1;	
		}
	}
	
	//return the month from the date string
	//return -1 if invalid month or invalid date format
	public static int getMonth(String date) {
		if (DateLibrary.isValidDateFormat(date)) {
			if(Integer.parseInt(date.substring(5,7))>0 && Integer.parseInt(date.substring(5,7))<13)
			return Integer.parseInt(date.substring(5,7));
		}
		return -1;	
	}
	
	//return the day from the date string
	//return -1 if day outside of [1,31] or invalid date format
	//does not check if valid day for that month
	public static int getDay(String date) {
		if (DateLibrary.isValidDateFormat(date)) {
			if(Integer.parseInt(date.substring(8,10))>0 && Integer.parseInt(date.substring(8,10))<32)
			return Integer.parseInt(date.substring(8,10));
		}
		return -1;
	}
	
	//returns true if leap year
	public static boolean isLeapYear(int year) {
		if(year%4==0) {
			if (year%100==0) {
				if (year%400==00) {
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}
	
	//return true if valid format and valid calendar day
	//returns false otherwise
	public static boolean isValidDate(String date) {
		ArrayList<Integer> longMonths = new ArrayList<Integer>();
		longMonths.add(1);
		longMonths.add(3);
		longMonths.add(5);
		longMonths.add(7);
		longMonths.add(8);
		longMonths.add(10);
		longMonths.add(12);
		ArrayList<Integer> shortMonths = new ArrayList<Integer>();
		shortMonths.add(4);
		shortMonths.add(6);
		shortMonths.add(9);
		shortMonths.add(11);
		
		if(DateLibrary.getYear(date)!=-1 && DateLibrary.getMonth(date)!=-1 && DateLibrary.getDay(date)!=-1) {
			if(longMonths.contains(DateLibrary.getMonth(date))) {
				if(DateLibrary.getDay(date)<=31) {
					return true;
				}
				else
					return false;
			}
			else if(shortMonths.contains(DateLibrary.getMonth(date))) {
				if(DateLibrary.getDay(date)<31) {
					return true;
				}
				else
					return false;
			}
			else if(DateLibrary.getMonth(date)==2) {
				if(DateLibrary.isLeapYear(DateLibrary.getYear(date)) && DateLibrary.getDay(date)<30) {
					return true;
				}
				else if(!DateLibrary.isLeapYear(DateLibrary.getYear(date)) && DateLibrary.getDay(date)<29) {
					return true;
				}
				else
					return false;
			}
		}
		return false;
	}
	
	//takes two dates, if date 1 is before date 2 returns a negative number
	//if date 1 is after date 2 returns a positive number
	//returns zero if same day
	//returns 0 if either or both date are invalid format
	public static int compare(String date1, String date2) {
		if (DateLibrary.isValidDateFormat(date1) && DateLibrary.isValidDateFormat(date2)) {
		    if (DateLibrary.isValidDate(date1) && DateLibrary.isValidDate(date2)) {
		          return date1.compareTo(date2);
		    }
		}
		return 0;
	}
	
}
