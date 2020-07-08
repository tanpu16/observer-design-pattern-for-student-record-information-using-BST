package studentskills.mytree;

import java.util.HashSet;
import java.util.Set;

public interface SubObjI {

	
	public void insert(BST bst,SubjectI node);
	public void update(SubjectI oldNode, SubjectI newNode);
	public void insertUpdateNode(BST bst,SubjectI node);
	
	public SubjectI Search(SubjectI key,SubjectI root);
	/*
	int bNumber = 0;
	String firstName = null ,lastName = null ,major = null;
	double gpa = 0.0;
	Set<String> skills = new HashSet<String>();
	SubjectI left = null;
	SubjectI right = null;
	*/

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
}
