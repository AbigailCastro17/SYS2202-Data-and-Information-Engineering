import java.util.*;

public class RandomNumberGenerator {
    
    public int a = 9429;
    public int c = 3967;
    public int K = 2^14;
    public int seed = 1000;
    //public int x;
    
    public RandomNumberGenerator() {
        
    }
    
    public int randomNum(int num) {
        num = ((a*seed) + c)%K;
        return num/K;
    }
    

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RandomNumberGenerator rng = new RandomNumberGenerator();
        
    }
}