// LAB 9: Recursion, Pt. 1

public class MaxMin {
	
	public static void main(String[] args) 
	{
		int[] values = {19, 12, 13, 14, 17, 18, 16, 14, 11};
		int[] maxMinResults = maxMin(values);
		System.out.println(maxMinResults[0] + " and " + maxMinResults[1]);
	}
	
	/*  maxMin() - Return an array containing the minimum and maximum values of the input array.
	 * 	@param list: an array of ints as input
	 * 	
	 * 	Use the helper function to perform the recursion with the additional parameters
	 * 	
	 * 	@return: an int array, one index is minimum and the other is the maximum value.
	 */
	public static int[] maxMin(int[] list) {
		return maxMinHelper(list, 0, list.length-1);
	}

	/*  maxMinHelper() - Helper function for the maxMin function.
	 * 	@param list: an array of ints as input
	 * 	@param first: the first index of the list (for recursion)
	 * 	@param last: the last index of the list (for recursion)
	 * 	
	 * 	Identify a base case, and work it out so that the array returns the min and max values.
	 * 	Then, identify what the method does to recursively arrive at the base case.
	 * 	
	 * 	@return: an int array, one index is minimum and the other is the maximum value.
	 */
	public static int[] maxMinHelper(int[] list, int first, int last)
	{
	    int[] array = new int[2];
        if (list.length == 2) {
            
            if (list[0]<list[1]) {
                array[0] = list[first];
                array[1] = list[last];
            }
            else {
                array[0] = list[last];
                array[1] = list[first];
            }
            System.out.println(array[0]+" "+array[1]);
            return array;
        }
		if (list.length <= 1) {
            array[0] = list[first];
            array[1] = list[first];
            System.out.println(array[0]+" "+array[1]);
            return array;
		}

		else {
		    int index = list.length/2;
		    int[] a1 = new int[index];
		    int[] a2 = new int[list.length-index];
		    for (int i=0; i<index ;i++) {
		        a1[i] = list[i];
		        //System.out.println("a1 "+ i + " "+ list[i]);
		    }
		    int j = 0;
		    for (int i=index; i<list.length; i++) {
		        a2[j]=list[i];
		        //System.out.println("a2 "+ j + " " + list[i]);
		        j++;
		    }
		    
		    int[] a3 = maxMinHelper(a1,0,a1.length-1);
		    //System.out.println("a3 "+a3[0]+" "+a3[1]+" "+a3.length);
		    int[] a4 = maxMinHelper(a2,0,a2.length-1);
		    //System.out.println("a4 "+a4[0]+" "+a4[1]+" "+a4.length);

		    if (a3[0]<=a4[0]&& a3[1]>=a4[1]) {
		        array[0]=a3[0];
		        array[1]=a3[1];
		    }
		    if (a3[0]>=a4[0]&& a3[1]<=a4[1]) {
		        array[0]=a4[0];
                array[1]=a4[1];
		    }
		    if (a3[0]<=a4[0]&& a3[1]<=a4[1]) {
		        array[0]=a3[0];
                array[1]=a4[1];
		    }
		    if (a3[0]<=a4[0]&& a3[1]>=a4[1]) {
                array[0]=a4[0];
                array[1]=a3[1];
            }
		    System.out.println("array "+ array[0]+" "+array[1]);
		    return array;
		}
	}
}
