package studentskills.mytree;

import java.util.List;
import java.util.Set;

public interface SubjectI
{

	public void notifyAll(SubjectI node,String oldValue, String newValue, Operation op);
	
	public void registerObservers(ObserverI obs1,ObserverI obs2);
	
	public void updateSubjectNode(SubjectI node,String oldValue,String newValue);
	
	//getter setter methods
	public int getbNumber();
	public void setbNumber(int bNumberIn);
	public String getFirstName();
	public void setFirstName(String firstNameIn);
	public String getLastName();
	public void setLastName(String lastNameIn);
	public String getMajor() ;
	public void setMajor(String majorIn);
	public double getGpa();
	public void setGpa(double gpaIn);
	public Set<String> getSkills();
	public void setSkills(Set<String> skillsIn);
	public SubjectI getLeft();
	public void setLeft(SubjectI leftIn);
	public SubjectI getRight();
	public void setRight(SubjectI rightIn);
	public List<ObserverI> getObservers();
	public void setObservers(List<ObserverI> observersIn);
}