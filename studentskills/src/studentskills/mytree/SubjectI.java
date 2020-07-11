package studentskills.mytree;


public interface SubjectI extends SubObjI
{

	public void notifyAll(SubjectI node,String oldValue, String newValue, Operation op);
	
	public void registerObservers(ObserverI obs1,ObserverI obs2);
	
}