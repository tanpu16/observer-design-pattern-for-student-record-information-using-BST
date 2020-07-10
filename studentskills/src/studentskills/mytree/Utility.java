package studentskills.mytree;

public class Utility {

	private static int count=0;
	
	public static int getUniqueId()
	{
		count++;
		return (count-1);
	}
}
