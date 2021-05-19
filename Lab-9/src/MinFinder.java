import java.util.ArrayList;

public class MinFinder
{
	
	public static void main(String[] args) 
	{
		String[] runs = {"Run 1.dms", "Run 2.dms", "Run 3.dms", "Run 4.dms"};
		ArrayList<MinFinderRunnable> threads = new ArrayList<MinFinderRunnable>();
		for (int i = 0; i < runs.length; i++)
		{
			threads.add(new MinFinderRunnable(runs[i]));
			Thread t = new Thread(threads.get(i));
			t.start();
		}
		
		
		Thread stalling = new Thread();
		try {
			stalling.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println("I N T E R R U P T E D");
		}
		MinFinalRunnable mfinr = new MinFinalRunnable(threads);
		System.out.println(mfinr.values);
		Thread t2 = new Thread(mfinr);
		t2.start();
	} 
	
}
