package studentskills.util;

public class MyLogger
{

    public static enum DebugLevel { CONSTRUCTOR, FILE_PROCESSOR, NONE, UPDATE, NOTIFY, CLONE, REGISTER, BST, RESULT
                                   };

    private static DebugLevel debugLevel;


    /*this is a setter method which takes 1 parameter from command line for setting debug level
     and according to that level one switch case is executed
	@param levelIn set from command line
	@return void
	@see just set the debug level
	*/
    public static void setDebugValue (int levelIn) {
	switch (levelIn) {
		case 8: debugLevel = DebugLevel.RESULT; break;
		case 7: debugLevel = DebugLevel.BST; break;
		case 6: debugLevel = DebugLevel.REGISTER; break;
		case 5: debugLevel = DebugLevel.CLONE; break;
		case 4: debugLevel = DebugLevel.NOTIFY; break;
		case 3: debugLevel = DebugLevel.UPDATE; break;
		case 2: debugLevel = DebugLevel.CONSTRUCTOR; break;
		case 1: debugLevel = DebugLevel.FILE_PROCESSOR; break;
		default: debugLevel = DebugLevel.NONE; break;
	}
    }
    
    
    
    /*this method is used to set the debugLevel from enum listed
	@param enum DebugLevel
	@return void
	@see just set the Debug level
	*/
    public static void setDebugValue (DebugLevel levelIn) {
	debugLevel = levelIn;
    }

    /*this method is used to write a message to stdout according to the DebugLevel set
	@param String message written
	@param DebugLevel set
	@return void
	@see write message to stdout
	*/
    public static void writeMessage (String     message  ,
                                     DebugLevel levelIn ) {
	if (levelIn == debugLevel)
	    System.out.println(message);
    }

    public String toString() {
	return "The debug level has been set to the following " + debugLevel;
    }
}