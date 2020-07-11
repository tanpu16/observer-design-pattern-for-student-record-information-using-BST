package studentskills.mytree;

import studentskills.util.MyLogger;
import studentskills.util.MyLogger.DebugLevel;
import studentskills.util.Utility;

public class BST implements Cloneable{


	private final int treeID;
	SubjectI root;
	
	@Override
	public String toString()
	{
			return "Class BST [tree ID is -> "+treeID+"root is ->"+root+"]";
	}
	
	//Constructor to initialize root as null and it'll assign treeID to whenever object of this class created.
	public BST()
	{
		treeID = Utility.getUniqueId();
		root = null;
		MyLogger.writeMessage("BST constructor called", DebugLevel.CONSTRUCTOR);
	}
	
	//getter method for private member treeID
	public int getTreeID() {
		return treeID;
	}


}
