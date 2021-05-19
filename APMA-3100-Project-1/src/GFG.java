import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class GFG {

    //int busy = 0;
    //int unavailable = 1;
    //int available = 2;
    public static int w = 1;
    
 // Function to generate random numbers 
    static double randomNum(int j) 
    { 
        int x = 1000;
        int K = 16384;
        int a = 9429;
        int c = 3967;
          
      
        // Traverse to generate required 
        // numbers of random numbers 
        for(int i = 0; i < j; i++) 
        {  
            // Follow the linear congruential method 
            x = ((x * a) + c) % K; 
        } 
        double u = (double)x/K;
//        int f = j-1;
//        System.out.println("u"+ f + ": " + u);
        return u;
    } 
    
    static int random_var(double rn) { 
        double P_busy = 0.2;
        double P_unavailable = 0.3;
        //double available = 0.5;
        //System.out.println("rn:" +rn);
        if (P_busy >= rn)
            return 0; //busy
        else if ((P_busy + P_unavailable) >= rn)
            return 1; //unavailable
        else
            return 2; //available
    } 
    
    static double pickup_time(double rn) {
        return 12*Math.log(1/(1-rn));
    }
    
    static double W() {
        double total_time = 0;
        
        for (int i = 0; i<4; i++) {
            total_time += 6;
            int state = random_var(randomNum(w));
            //System.out.println(state);
            w += 1;
            if (state == 0) {//busy
                //System.out.println("busy");
                total_time += 4;
            } else if (state == 1) { //unavailable
                //System.out.println("unavailable");
                total_time += 26;
            } else {
                //System.out.println("available");
                double pickup_time = pickup_time(randomNum(w));
                w += 1;
                if(pickup_time<25) {
                    total_time += pickup_time;
                    break;
                } else
                    total_time += 26; 
            }
        }
        //System.out.println("w: "+ w);
        double scale = Math.pow(10, 4);
        return Math.round(total_time * scale) / scale;
    }
    
    public static double findMean(double a[], int n)
    {
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += a[i];
 
        return (double)sum / (double)n;
    }
 
    // Function for calculating median
    public static double findMedian(double a[], int n)
    {
        // First we sort the array
        Arrays.sort(a);
 
        // check for even case
        if (n % 2 != 0)
            return (double)a[n / 2];
 
        return (double)(a[(n - 1) / 2] + a[n / 2]) / 2.0;
    }
    
    public static double[] Quartiles(double[] val) {
        double ans[] = new double[3];

        for (int quartileType = 1; quartileType < 4; quartileType++) {
            float length = val.length + 1;
            double quartile;
            float newArraySize = (length * ((float) (quartileType) * 25 / 100)) - 1;
            Arrays.sort(val);
            if (newArraySize % 1 == 0) {
                quartile = val[(int) (newArraySize)];
                } else {
                int newArraySize1 = (int) (newArraySize);
                quartile = (val[newArraySize1] + val[newArraySize1 + 1]) / 2;
                 }
            ans[quartileType - 1] =  quartile;
        }
        return ans;
    }
    
    public static double Probability(double[] val, double n, String s) {
        int count = 0;
        if (s.equals("le")) {
            for (int i = 0; i<500; i++) {
                if (val[i] <= n)
                    count++;
            }
        } else if (s.equals("ge")) {
            for (int i = 0; i<500; i++) {
                if (val[i] >= n)
                    count++;
            }
        } else if (s.equals("l")) {
            for (int i = 0; i<500; i++) {
                if (val[i] < n)
                    count++;
            }
        } else {
            for (int i = 0; i<500; i++) {
                if (val[i] > n)
                    count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        double[] W = new double[500];
        FileWriter writer;
        try {
            writer = new FileWriter("output.txt");
            for (int i = 0; i <500; i++) {
                W[i] = W();
                writer.write(W[i] + "\t"+ "");
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //w=1;
//        double[] W = new double[500];
//        for (int i = 0; i <500; i++) {
//            W[i] = W();
//        }
        
        double mean = findMean(W,500);
        System.out.println("Mean: " + mean);
        double median = findMedian(W,500);
        System.out.println("Median: " + median);
        double[] q = Quartiles(W);
        System.out.println("Q1: " + q[0]);
        System.out.println("Q2: " + q[1]);
        System.out.println("Q3: " + q[2]);
        
        Arrays.sort(W);
        double smallest = W[0];
        double largest = W[499];
        
        double P6 = Probability(W,smallest,"le")/500;
        System.out.println("P(W<=6.0029) = " + P6);
        double P15 = Probability(W,15,"le")/500;
        System.out.println("P(W<=15) = " + P15);
        double P20 = Probability(W,20,"le")/500;
        System.out.println("P(W<=20) = " + P20);
        double P30 = Probability(W,30,"le")/500;
        System.out.println("P(W<=30) = " + P30);
        double P40 = Probability(W,40,"g")/500;
        System.out.println("P(W>40) = " + P40);
        double PW5 = Probability(W,15,"g")/500;
        System.out.println("P(W>W5) = " + PW5);
        double PW6 = Probability(W,29,"g")/500;
        System.out.println("P(W>W6) = " + PW6);
        double PW7 = Probability(W,55,"g")/500;
        System.out.println("P(W>W7) = " + PW7);
        

        //double range = largest - smallest;
        System.out.println("The sample space is (" + smallest + " <= W <= " + largest + ").");
        System.out.println(randomNum(51));
        System.out.println(randomNum(52));
        System.out.println(randomNum(53));
    }

}
