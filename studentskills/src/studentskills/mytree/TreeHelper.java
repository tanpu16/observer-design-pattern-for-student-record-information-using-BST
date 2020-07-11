package studentskills.mytree;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import studentskills.util.FileDisplayInterface;
import studentskills.util.FileProcessor;
import studentskills.util.MyLogger;
import studentskills.util.Results;
import studentskills.util.MyLogger.DebugLevel;

public class TreeHelper {
	
	boolean invalidInput,isEmptyFile,isEmptyLine;

	BST treeClone0,treeClone1,treeClone2;
	
	SubjectI clone0,clone1,clone2;
	
	//default TreeHelper constructor
	public TreeHelper()
	{
		invalidInput = false;
		isEmptyFile = false;
		isEmptyLine = false;
		treeClone0 = null;
		treeClone1 = null;
		treeClone2 = null;
		clone0 = null;
		clone1 = null;
		clone2 = null;
		MyLogger.writeMessage("TreeHelper constructor called", DebugLevel.CONSTRUCTOR);
	}
	
	
	@Override
	public String toString()
	{
		return "TreeHelper [invalidInput : "+invalidInput+" isEmptyFile : "+isEmptyFile+" isEmptyLine : "+isEmptyLine+" treeClone0 : "+treeClone0+" treeClone1 : "+treeClone1+" treeClone2 : "+treeClone2+
			" clone0 : "+clone0+" clone1 : "+clone1+" clone2 : "+clone2+"]"; 	
	}
	
	/*this is a helper method which takes instances of 2 classes used to perform operations on input file.
	operations like fetching line by using poll() method of FileProcessor class, error message in
	store method of Result class.
	@param fp an instance of FileProcessor.java class from util package
	@param errorRes an instance of Results.java class from util package
	@return void
	@see just an helper function for performing different operations on input.txt file like parse the input
	store error message, create clone of nodes and recursively call insert for creating BST and insertUpdate function
	to make any required changes
	*/
	public void InputParser(FileProcessor fp,FileDisplayInterface errorRes) throws IOException, CloneNotSupportedException
	{
		int bNumber;
		String firstName,lastName,major;
		double gpa;
		
		treeClone0 = new BST();
		treeClone1 = new BST();
		treeClone2 = new BST();

		try
		{
			String line = fp.poll();
			
			if(null == line)
			{
				isEmptyFile = true;
			}
			
			while(null != line)
			{
				if(line.contains(" "))
				{
					invalidInput = true;
				}

				 Set<String> skill = new HashSet<String>();
				 String[] values = line.split(",");
				 
				 String temp = values[0];
				 
				 bNumber = Integer.parseInt(temp.replaceAll("^|:.*$",""));

				 firstName = temp.replaceAll("^.*:|$","");
				 lastName  = values[1];
				 gpa = Double.parseDouble(values[2]);
				 major = values[3];
				 int len = values.length;
				 int i = 4;
				 while(len-4 > 0 && i < 14)
				 {
					 skill.add(values[i]);
					 len--;
					 i++;	 
				 }
				 
				 clone0 = new StudentRecord(bNumber,firstName,lastName,gpa,major,skill);
				 clone1 = ((StudentRecord)clone0).clone();
				 clone2 = ((StudentRecord)clone0).clone();
				 SubjectI tempNode = Search(bNumber, treeClone0.root);
				 
				 clone0.registerObservers((StudentRecord)clone1,(StudentRecord)clone2);
				 clone1.registerObservers((StudentRecord)clone0,(StudentRecord)clone2);
				 clone2.registerObservers((StudentRecord)clone0, (StudentRecord)clone1);
				 
				 if(null == tempNode)
				 {
					 insert(treeClone0,clone0);
					 insert(treeClone1,clone1);
					 insert(treeClone2,clone2);	  
				 }
				 else
				 {
					 insertUpdateNode(treeClone0, clone0);
					 clone0.notifyAll(tempNode,null,null, Operation.INSERT);
				 }
				 line = fp.poll();
			}

			 
		}
		catch(IOException | CloneNotSupportedException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		finally
		{
			fp.close();
		}
		try
		{
			if(invalidInput)
			{
				throw new IOException("Invalid Input! Exiting!!!");
			}
			else if(isEmptyFile)
			{
				throw new IOException("File is Empty! Exiting!!!");
			}
			else if(isEmptyLine)
			{
				throw new IOException("empty line in input file! Exiting!!!");
			}
			
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
			System.exit(0);
		}
		finally
		{
			fp.close();
		}
	}

	/*this is a helper method which takes instances of 2 classes used to perform operations on modification file.
	operations like fetching line by using poll() method of FileProcessor class,write error message in
	store method of Results class.
	@param fp an instance of FileProcessor.java class from util package
	@param errorRes,outputRes1,outputRes2,outputRes3 an instance of Results.java class from util package
	@return void
	@see just an helper function for performing different operations on mod file like parse the input, make modification
	in corresponding replica tree,store error message, also stores the final output into store method by calling printNodes method.
	*/
	public void modFileParser(FileProcessor fp,FileDisplayInterface outputRes1,FileDisplayInterface outputRes2,FileDisplayInterface outputRes3,FileDisplayInterface errorRes) throws IOException
	{
		int bNumber, replicaID;
		String oldValue,newValue;
		
		try
		{
			String line = fp.poll();
			
			if(null == line)
			{
				isEmptyFile = true;
			}
			
			while(null != line)
			{
				if(line.contains(" "))
				{
					invalidInput = true;
				}
				 
				
				String split[] = line.split(",");
				replicaID = Integer.parseInt(split[0]);
				bNumber = Integer.parseInt(split[1]);
				String temp = split[2];
				oldValue = temp.replaceAll("^|:.*$","");
				newValue = temp.replaceAll("^.*:|$","");
				
				
				if(!newValue.isEmpty())
				{
					SubjectI tempNode = null;
					
					if(replicaID == treeClone0.getTreeID())
					{
						tempNode = Search(bNumber, treeClone0.root);	
					}
					else if(replicaID == treeClone1.getTreeID())
					{
						tempNode = Search(bNumber, treeClone1.root);
					}
					else if(replicaID == treeClone2.getTreeID())
					{
						 tempNode = Search(bNumber, treeClone2.root);
						
					}
					tempNode.updateSubjectNode(tempNode,oldValue,newValue);
					tempNode.notifyAll(tempNode,oldValue,newValue,Operation.MODIFY);
					line = fp.poll();
				}
				else
				{
					errorRes.store("Input is invalid : request for modified value is empty");
					line = fp.poll();
				}
				 		 
			}
			
			printNodes((Results)outputRes1,treeClone0.root);
			printNodes((Results)outputRes2,treeClone1.root);
			printNodes((Results)outputRes3,treeClone2.root);
				 
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		finally
		{
			fp.close();
		}
		try
		{
			if(invalidInput)
			{
				throw new IOException("Invalid Input! Exiting!!!");
			}
			else if(isEmptyFile)
			{
				throw new IOException("File is Empty! Exiting!!!");
			}
			
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
			System.exit(0);
		}
		finally
		{
			fp.close();
		}
	}
	
	/*this is a insert method which takes 2 parameters, one is tree BST and another is node to insert into that BST.
	@param bst instance of bst class
	@param node instance of StudentRecord
	@return void
	@see just an insert fucntion to insert into BST
	*/
	public void insert(BST bst,SubjectI node)
	{
		MyLogger.writeMessage("Insert method for BST called", DebugLevel.BST);
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
	
	/*this is a set method which takes 2 parameters, old node and new node. Make changes into the node present into to tree 
	 as per new node.
	@param oldNode instance of StudentRecord
	@param newNode instance of StudentRecord
	@return void
	@see just an helper update function for insertUpdate method
	*/
	public void setEntry(SubjectI oldNode, SubjectI newNode)
	{
		oldNode.setFirstName(newNode.getFirstName());
		oldNode.setLastName(newNode.getLastName());
		oldNode.setGpa(newNode.getGpa());
		oldNode.setMajor(newNode.getMajor());
		oldNode.getSkills().addAll(newNode.getSkills());
	}
	
	/*this is a insertUpdateNode method which takes 2 parameters, one is tree BST and another is node which need to be updated
	@param bst instance of bst class
	@param node instance of StudentRecord
	@return void
	@see just an update function need to be updated while inserting node in to the BST
	*/
	public void insertUpdateNode(BST bst,SubjectI node)
	{
		SubjectI current = bst.root;
		SubjectI parent = null;
		MyLogger.writeMessage("At the time of parsing input file, update is called if new line came with same bNumber", DebugLevel.UPDATE);
		while(true)
		{
			parent = current;
			if(node.getbNumber() == parent.getbNumber())
			{
				setEntry(parent,node);
				return;
			}
			else if(node.getbNumber() < current.getbNumber())
			{
				current = current.getLeft();
				if(null == current)
				{
					setEntry(parent,node);
					return;
				}
					
			}
			else if(node.getbNumber() > current.getbNumber())
			{
				current = current.getRight();
				if(null == current)
				{
					setEntry(parent,node);
					return;
				}
			}
		}
	}
	
	/*this is a search method which takes 2 parameters, one is key need to be search in BST and another is root of BST
	@param int key(bNumber)
	@param root of BST
	@return found node i.e StudentRecord
	@see just an serach function which returns node if found and null otherwise
	*/
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
		MyLogger.writeMessage("Search method for BST called", DebugLevel.BST);
		return temp;
	}
	
	/*this is a printNode method which takes 2 parameters, one is result instance need to write into the tree and
	 another is root node of BST
	@param outRes of Results class
	@param root node of BST, instance of StudentRecord
	@return void
	@see just an method which stores the bNumber and Skills into StringBuffer by calling store method to write it
	into the output file and to stdout
	*/
	public void printNodes(Results outRes, SubjectI root)
	{
		if(null != root)
		{
			printNodes(outRes,root.getLeft());
			outRes.store(String.valueOf(root.getbNumber()));
			outRes.store(":");
			outRes.store(String.join(",",root.getSkills()));
			outRes.store("\n");
			printNodes(outRes,root.getRight());
		}
	}
}
