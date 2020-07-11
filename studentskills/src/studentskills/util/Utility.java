package studentskills.util;

public class Utility {

	private static int count=0;
	
	/*this is a static method which returns unique id whenever called
	@param NA
	@return unique int id
	@see just an getter method which returns private member of  class Utility
	*/
	public static int getUniqueId()
	{
		count++;
		return (count-1);
	}
}
