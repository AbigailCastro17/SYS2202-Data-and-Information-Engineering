
public class Insertion {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Comparable[] ints = new Comparable[] {0,2,1,4};
        System.out.println("Before: "+ Insertion.toString(ints));
        System.out.println("After: " + Insertion.toString(Insertion.insertionSort(ints)) + "\n");
        
        Comparable[] chars = new Comparable[] {"d","c","b","a"};
        System.out.println("Before: " + Insertion.toString(chars));
        System.out.println("After: " + Insertion.toString(Insertion.insertionSort(chars)) + "\n");
        
        Comparable[] strings = new Comparable[] {"Allen","Abby","Bo","Alex","Wen","James","Darrion"};
        System.out.println("Before: " + Insertion.toString(strings));
        System.out.println("After: " + Insertion.toString(Insertion.insertionSort(strings)) + "\n");
    }

    public static Comparable[] insertionSort(Comparable[] arr) {
        Comparable[] sortedArray = new Comparable[arr.length];
        for(int i=0;i<arr.length;i++) {
            Comparable k = arr[i];
            int j = i-1;
            while (j>=0 && arr[j].compareTo(k)>0) {
                arr[j+1] = arr[j];
                j=j-1;
            }
            arr[j+1]=k;
        }
        return arr;
    }
    
    public static String toString(Comparable[] arr) {
        String str="";
        for (int i=0;i<arr.length;i++) {
            if(i==arr.length-1) {
                str=str+arr[i];
            }
            else 
            str=str+arr[i]+", ";
        }
        return str;
    }
}
