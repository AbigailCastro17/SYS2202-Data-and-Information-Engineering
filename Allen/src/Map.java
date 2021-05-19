import java.util.*;
public class Map {

    public ArrayList<Double> nums;
    
    public Map() {
        this.nums = new ArrayList<Double>();
    }
    //3,-1,4,1,-5 output:3486784401.0
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Map m = new Map();
        m.nums.add(3.0);
        m.nums.add(-1.0);
        m.nums.add(4.0);
        m.nums.add(1.0);
        m.nums.add(-5.0);
        
        System.out.println(m.myReduce(m.nums));
    }

    public double myReduce (ArrayList<Double> nums) {
        double value = nums.get(0);
        for (int i=1; i<nums.size();i++) {
            System.out.println(nums.get(i));
            value = Math.pow(value, nums.get(i));
            System.out.println(value);
        }
        return value;
    }
    
}
