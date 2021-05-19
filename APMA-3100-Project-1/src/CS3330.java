
public class CS3330 {

    public static int func(int m, int n)
    {
        int x;
        if (m<n) {
            x = n-m;
            //System.out.println("x1: " + x);
            if(m!=0) {
                int y = x;
                int z = m;
                //System.out.println(y + " " + z);
                return func(y,z);
            } //else
                //System.out.println("x11: " + x);
                //return x;
        } else {
            x = m-n;
            //System.out.println("x2: " + x);
            if (n!=0){
                int y = x;
                int z = n;
                //System.out.println(y + " " + z);
                return func(y,z);
            } //else
                //System.out.println("x21: " + x);
                //return x;
        }
        return x;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(func(1,0));
        System.out.println(func(0,1));
        System.out.println(func(6,3));
        System.out.println(func(4,16));
        System.out.println(func(8,2));
    }

}
