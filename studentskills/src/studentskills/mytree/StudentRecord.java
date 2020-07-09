package studentskills.mytree;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class StudentRecord implements SubjectI,ObserverI,Cloneable
{
	private int bNumber;
	private String firstName,lastName,major;
	private double gpa;
	private Set<String> skills;
	private SubjectI left;
	private SubjectI right;
	
	private List<ObserverI> Observers;
	
	public StudentRecord(int bNumberIn, String firstNameIn, String lastNameIn, double gpaIn, String majorIn, Set<String> skillIn)
	{
		setbNumber(bNumberIn);
		setFirstName(firstNameIn);
		setLastName(lastNameIn);
		setMajor(majorIn);
		setGpa(gpaIn);
		setSkills(skillIn);
		setLeft(null);	
		setRight(null);
		setObservers(new ArrayList<>());		
	}
	


	//@SuppressWarnings("deprecation")
	@Override
	protected StudentRecord clone() throws CloneNotSupportedException {
		StudentRecord clone = null;
        try{
            clone = (StudentRecord)super.clone()	;
 
            //clone.firstName = new String(clone.getFirstName());
            //clone.lastName = new String(clone.getLastName());
            //clone.gpa = new Double(clone.getGpa());
            //clone.bNumber = new Integer(clone.getbNumber());
            //clone.major = new String(clone.getMajor());
            clone.left = clone.getLeft();
            clone.right = clone.getRight();
            clone.skills = new HashSet<String>(clone.getSkills());
            
        }catch(CloneNotSupportedException cns){ 
            System.out.println("Error while cloning programmer"+cns);
        }
        return clone;
    }

	public int getbNumber() {
		return bNumber;
	}

	public void setbNumber(int bNumberIn) {
		bNumber = bNumberIn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstNameIn) {
		firstName = firstNameIn;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastNameIn) {
		lastName = lastNameIn;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String majorIn) {
		major = majorIn;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpaIn) {
		gpa = gpaIn;
	}

	public Set<String> getSkills() {
		return skills;
	}

	public void setSkills(Set<String> skillsIn) {
		skills = skillsIn;
	}

	public SubjectI getLeft() {
		return left;
	}

	public void setLeft(SubjectI leftIn) {
		left = leftIn;
	}

	public SubjectI getRight() {
		return right;
	}

	public void setRight(SubjectI rightIn) {
		right = rightIn;
	}  
	
	public void insert(BST bst,SubjectI node)
	{
		if(null == bst.root)
		{
			bst.root = node;
			return;
		}
		SubjectI current = bst.root;
		SubjectI parent = null;
		while(true)
		{
			parent = current;
			if(node.getbNumber() == parent.getbNumber())
			{
				parent = node;
				return;
			}
			else if(node.getbNumber() < current.getbNumber())
			{
				current = current.getLeft();
				if(null == current)
				{
					parent.setLeft(node);
					return;
				}
					
			}
			else if(node.getbNumber() > current.getbNumber())
			{
				current = current.getRight();
				if(null == current)
				{
					parent.setRight(node);
					return;
				}
			}

		}
		
	}
	
	public void update(SubjectI oldNode, SubjectI newNode)
	{
		oldNode.setFirstName(newNode.getFirstName());
		oldNode.setLastName(newNode.getLastName());
		oldNode.setGpa(newNode.getGpa());
		oldNode.setMajor(newNode.getMajor());
		oldNode.getSkills().addAll(newNode.getSkills());
	
	}
	public void insertUpdateNode(BST bst,SubjectI node)
	{
		
		SubjectI current = bst.root;
		SubjectI parent = null;
		while(true)
		{
			parent = current;
			if(node.getbNumber() == parent.getbNumber())
			{
				update(parent,node);
				return;
			}
			else if(node.getbNumber() < current.getbNumber())
			{
				current = current.getLeft();
				if(null == current)
				{
					update(parent,node);
					return;
				}
					
			}
			else if(node.getbNumber() > current.getbNumber())
			{
				current = current.getRight();
				if(null == current)
				{
					update(parent,node);
					return;
				}
			}

		}
	}
	
	public SubjectI Search(int key,SubjectI root)
	{
	
		SubjectI temp = null;
		if(null == root)
		{
			return temp;
		}
		else
		{
			if(root.getbNumber() == key)
			{
				temp = root;
			}
			else if(root.getbNumber() < key)
			{
				 temp = Search(key,root.getRight());
			}
			else if(root.getbNumber() > key)
			{
				temp = Search(key,root.getLeft());
			}
		}
		return temp;
	}



	public List<ObserverI> getObservers() {
		return Observers;
	}



	public void setObservers(List<ObserverI> observersIn) {
		Observers = observersIn;
	}



	public void registerObservers(ObserverI obs1,ObserverI obs2)
	{
		Observers.add(obs1);
		Observers.add(obs2);
	}


	
}