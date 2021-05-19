import java.util.ArrayList;

public class MinFinalRunnable implements Runnable
{
	ArrayList<Integer> values;
	
	public MinFinalRunnable(ArrayList<MinFinderRunnable> threads)
	{
		values = new ArrayList<Integer>();
		for (int i = 0; i < threads.size(); i++)
		{
			values.add(threads.get(i).min);
		}
	}
	
	@Override
	public void run() 
	{
		int min = values.get(0);
		for (int i = 1; i < values.size(); i++)
		{
			if (values.get(i) < min)
			{
				min = values.get(i);
			}
		}
		System.out.println("Global min value is: " + min + ".");
	}

}
