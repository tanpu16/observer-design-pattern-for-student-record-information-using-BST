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
	
	private List<ObserverI> observers;
	
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
	
	@Override
	protected StudentRecord clone() throws CloneNotSupportedException {
		StudentRecord clone = null;
        try{
            clone = (StudentRecord)super.clone()	;
            clone.left = clone.getLeft();
            clone.right = clone.getRight();
            clone.skills = new HashSet<String>(clone.getSkills());
            clone.observers = new ArrayList<ObserverI>();
            
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
	
	public void registerObservers(ObserverI obs1,ObserverI obs2)
	{
		
		observers.add(obs1);
		observers.add(obs2);
		
	}

	public void update(SubjectI node,SubjectI observer, String oldValue, String newValue, Operation op)
	{
		if(op == Operation.MODIFY)
		{
			
			if(observer.getFirstName().equals(oldValue))
			{
				observer.setFirstName(newValue);
			}
			else if(observer.getLastName().equals(oldValue))
			{
				observer.setLastName(newValue);
			}
			else if(observer.getMajor().equals(oldValue))
			{
				observer.setMajor(newValue);
			}
			else 
			{
				for(String itr : observer.getSkills())
				{
					if(itr.equals(oldValue))
					{
						observer.getSkills().remove(oldValue);
						observer.getSkills().add(newValue);
					}
				}
			}
		}
	
		else if(op == Operation.INSERT)
		{
						
			observer.setFirstName(node.getFirstName());
			observer.setLastName(node.getLastName());
			observer.setGpa(node.getGpa());
			observer.setMajor(node.getMajor());
			observer.getSkills().addAll(node.getSkills());
		}
	
	}
	
	
	public void updateSubjectNode(SubjectI node,String oldValue,String newValue)
	{
		if(node.getFirstName().equals(oldValue))
		{
			node.setFirstName(newValue);
		}
		else if(node.getLastName().equals(oldValue))
		{
			node.setLastName(newValue);
		}
		else if(node.getMajor().equals(oldValue))
		{
			node.setMajor(newValue);
		}
		else 
		{
			for(String itr : node.getSkills())
			{
				if(itr.equals(oldValue))
				{
					node.getSkills().remove(oldValue);
					node.getSkills().add(newValue);
				}
			}
		}
	}
	

	public void notifyAll(SubjectI node,String oldValue, String newValue, Operation op)
	{
		
		ObserverI observer1 = node.getObservers().get(0);
		ObserverI observer2 = node.getObservers().get(1);
		
		observer1.update(node,(StudentRecord)observer1,oldValue,newValue,op);
		observer2.update(node,(StudentRecord)observer2,oldValue,newValue,op);
	
	}

	public List<ObserverI> getObservers() {
		return observers;
	}

	public void setObservers(List<ObserverI> observersIn) {
			observers = observersIn;
	}

}