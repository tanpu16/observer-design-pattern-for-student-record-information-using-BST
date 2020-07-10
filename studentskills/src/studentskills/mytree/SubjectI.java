package studentskills.mytree;

import java.util.HashSet;
import java.util.Set;

public interface SubjectI extends SubObjI
{

	public void notifyAll(SubjectI node,String oldValue, String newValue, Operation op);
	public void updateSubjectNode(SubjectI node,String oldValue,String newValue);
	
}