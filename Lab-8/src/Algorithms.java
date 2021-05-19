import java.util.ArrayList;

public class Algorithms {
	
	public static final int MAX = 500;

	public static void main(String[] args) {}
	
	public static int algorithm1(int[] array) {
		int n = 0;
		
		for(int i = 0; i < array.length; i++) {
			n += array[i];
		}
		
		return n;
	}
	
	public static int algorithm2(int[] array) {
		int n = 0;
		
		for(int i = 0; i < 10000000; i++) {
			n += array[0];
		}
		
		return n;
	}
	
	public static void algorithm3(int[] array) {
		for(int i = array.length - 1; i >= 0; i--) {
			for(int j = 0; j < i; j++) {
				if(array[j] > array[j+1]) {
					swap(array, j, j+1);
				}
			}
		}
	}
	
	public static boolean algorithm4(ArrayList<Integer> array, int n) {
		if(array.contains(n)) {
			return true;
		}
		return false;
	}
	
	public static ArrayList<Integer> algorithm5(ArrayList<Integer> array1, ArrayList<Integer> array2) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		
		for(int i = 0; i < array1.size(); i++) {
			if(array2.contains(array1.get(i))) {
				arrayList.add(array1.get(1));
			}
		}
		
		return arrayList;
	}
	
	public static boolean algorithmBonus(int[] array, int n) {
		boolean[] checked = new boolean[array.length];
		for(int i = 0; i < checked.length; i++) {
			checked[i] = false;
		}
		
		while(true) {
			int ind = (int)(Math.random() * array.length);
			if(array[ind] == n) {
				return true;
			}
			
			checked[ind] = true;
			if(isAllChecked(checked)) {
				return false;
			}
		}
	}
	
	// Checks if all the values in the array are true
	public static boolean isAllChecked(boolean[] array) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == false) {
				return false;
			}
		}
		return true;
	}

	
	// Swaps the values of two indices in an array
	public static void swap(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	
	//DISREGARD THESE METHODS WHEN CONSIDERING THE ABOVE ALGORITHMS
	public static int[] generateRandomArray(int n) {
		int[] arr = new int[n];
		
		//loop through array, generate numbers between 1 and 500
		for(int i = 0; i < n; i++) {
			arr[i] = (int) (Math.random() * MAX + 1);
		}
		
		return arr;
	}
}
