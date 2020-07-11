package studentskills.mytree;

public interface ObserverI extends SubObjI
{
	public void update(SubjectI node, SubjectI observer, String oldValue, String newValue, Operation op);
	
}