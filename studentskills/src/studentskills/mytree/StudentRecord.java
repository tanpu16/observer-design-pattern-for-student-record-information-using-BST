package studentskills.mytree;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import studentskills.util.MyLogger;
import studentskills.util.MyLogger.DebugLevel;


public class StudentRecord implements SubjectI,ObserverI,Cloneable
{
	private int bNumber;
	private String firstName,lastName,major;
	private double gpa;
	private Set<String> skills;
	private SubjectI left;
	private SubjectI right;
	
	private List<ObserverI> observers;
	
	//parameterized constructor served as node of BST
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
		MyLogger.writeMessage("StudentRecord constructor called", DebugLevel.CONSTRUCTOR);
	}
	
	@Override
	public String toString()
	{
			return "Class StudentRecord [bNumber -> "+bNumber+"firstName -> "+firstName+"lastName -> "+lastName+"major -> "+major+"gpa -> "+gpa+"skills -> "+skills+"left -> "+left+"right -> "+right+"]";
	}
	

	/*clone is a overriden method, which is used to make clones of node.
	@param NA
	@return StudentRecord object
	@see print nothing but create deep copy whenever called
	*/
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
        MyLogger.writeMessage("Cloned method for cloning node is called", DebugLevel.CLONE);
        return clone;
    }

	/*getter method which returns private member bNumber
	@param NA
	@return int
	@see print nothing but return bNumber
	 */
	public int getbNumber() {
		return bNumber;
	}

	/*set value of private member bNumber 
	@param bNumber
	@return NA
	@see print nothing but set current bNumber
	*/
	public void setbNumber(int bNumberIn) {
		bNumber = bNumberIn;
	}

	/*getter method which returns private member firstName
	@param NA
	@return String
	@see print nothing but return firstName
	*/
	public String getFirstName() {
		return firstName;
	}

	/*set value of private member firstName
	@param firstName
	@return NA
	@see print nothing but set current firstName
	*/
	public void setFirstName(String firstNameIn) {
		firstName = firstNameIn;
	}

	/*getter method which returns private member lastName
	@param NA
	@return String
	@see print nothing but return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/*set value of private member lastName
	@param lastName
	@return NA
	@see print nothing but set current lastName
	*/
	public void setLastName(String lastNameIn) {
		lastName = lastNameIn;
	}

	/*getter method which returns private member major
	@param NA
	@return String
	@see print nothing but return major
	 */
	public String getMajor() {
		return major;
	}

	/*set value of private member major
	@param major
	@return NA
	@see print nothing but set current major
	*/
	public void setMajor(String majorIn) {
		major = majorIn;
	}

	/*getter method which returns private member gpa
	@param NA
	@return double
	@see print nothing but return gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/*set value of private member gpa
	@param gpa
	@return NA
	@see print nothing but set current gpa
	*/
	public void setGpa(double gpaIn) {
		gpa = gpaIn;
	}

	/*getter method which return private member set of skills
	@param NA
	@return Set of String
	@see print nothing but return set of String
	 */
	public Set<String> getSkills() {
		return skills;
	}

	/*set value of private member skills (set)
	@param skills set
	@return NA
	@see print nothing but set current skills
	*/
	public void setSkills(Set<String> skillsIn) {
		skills = skillsIn;
	}

	/*getter method returns private member left
	@param NA
	@return SubjectI
	@see print nothing but return left value
	 */
	public SubjectI getLeft() {
		return left;
	}

	/*set value of private member left
	@param left
	@return NA
	@see print nothing but set current left value
	*/
	public void setLeft(SubjectI leftIn) {
		left = leftIn;
	}

	/*getter method returns private member right
	@param NA
	@return SubjectI
	@see print nothing but return right
	 */
	public SubjectI getRight() {
		return right;
	}

	/*set value of private member right
	@param right
	@return NA
	@see print nothing but set current right
	*/
	public void setRight(SubjectI rightIn) {
		right = rightIn;
	}  
	
	/*registerObservers method just store the list of observers in a list for subject
	@param observers
	@return NA
	@see print nothing but store observers in list
	*/
	public void registerObservers(ObserverI obs1,ObserverI obs2)
	{
		observers.add(obs1);
		observers.add(obs2);
		
		MyLogger.writeMessage("register observers method", DebugLevel.REGISTER);
	}
	
	/*this method called from notifyAll method, which updates the observers for corresponding subject and also
	 check if the call is for modify or insert and update made accordingly
	@param Subject node, Observer,old and modified string values,operation
	@return NA
	@see print nothing but update the observers
	*/
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
	
		MyLogger.writeMessage("Update method for updating observers is called", DebugLevel.UPDATE);
	}
	
	/*this method used for updating subject, if an input came in modification file for updating subject node
	@param subject node, old and modified string value
	@return NA
	@see print nothing but update/modify the subject node
	*/
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
		MyLogger.writeMessage("Update method for updating/modifying subject node is called", DebugLevel.UPDATE);
	}
	

	/*this method notify all the observers regarding subject node is modified and in turn call update for observers 
	@param subject node, old and modified value, operation insert/modify
	@return NA
	@see print nothing but notify observers
	*/
	public void notifyAll(SubjectI node,String oldValue, String newValue, Operation op)
	{
		
		ObserverI observer1 = node.getObservers().get(0);
		ObserverI observer2 = node.getObservers().get(1);
		
		observer1.update(node,(StudentRecord)observer1,oldValue,newValue,op);
		observer2.update(node,(StudentRecord)observer2,oldValue,newValue,op);
		
		MyLogger.writeMessage("notifyAll method to notify observers", DebugLevel.NOTIFY);
	
	}

	/*getter method which return list of observers
	@param NA
	@return List 
	@see print nothing but return list of observers
	 */
	public List<ObserverI> getObservers() {
		return observers;
	}

	/*setter method for private member observers(list) of a class 
	@param input observer list
	@return NA
	@see print nothing but set observers for corresponding subject node
	*/
	public void setObservers(List<ObserverI> observersIn) {
			observers = observersIn;
	}

}