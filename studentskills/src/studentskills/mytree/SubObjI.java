package studentskills.mytree;

import java.util.List;
import java.util.Set;

public interface SubObjI {
	
	public void updateSubjectNode(SubjectI node,String oldValue,String newValue);
	
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
