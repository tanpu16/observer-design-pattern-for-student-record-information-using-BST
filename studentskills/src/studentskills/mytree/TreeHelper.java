package studentskills.mytree;

import java.io.IOException;

import java.util.HashSet;

import java.util.Set;

import studentskills.util.FileDisplayInterface;
import studentskills.util.FileProcessor;
import studentskills.util.Results;
import studentskills.util.StdoutDisplayInterface;


public class TreeHelper {
	

	boolean invalidInput,isEmptyFile;

	BST treeClone0;
	BST treeClone1;
	BST treeClone2;
	
	SubjectI clone0;
	SubjectI clone1;
	SubjectI clone2;
	
	
	public TreeHelper()
	{

		invalidInput = false;
		isEmptyFile = false;
	}
	
	/*
	@Override
	public String toString()
	{
		return "HelperClass [videoCount : "+videoCount+" popularity : "+popularity+" totalScore : "+totalScore+" videoName : "+videoName+" invalidInput : "+invalidInput+" isVideoPresentAdd : "+isVideoPresentAdd+
			" isVideoPresentR : "+isVideoPresentR+" isDecrease : "+isDecrease+" isEmptyFile : "+isEmptyFile+"]"; 	
	}
	*/
	
	/*this is a helper method which takes instances of 2 classes used to perform operations on input.txt file.
	operations like fetching line by using poll() method of FileProcessor class, Store final result in
	store method of Result class.
	@param fp an instance of FileProcessor.java class from util package
	@param res an instance of Results.java class from util package
	@return void
	@see just an helper function for performing different operations on input.txt file like parse the input
	store videos, calculate metrics then store output and display exceptions
	*/
	public void InputParser(FileProcessor fp) throws IOException, CloneNotSupportedException
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
				 

				 //System.out.println("in input : "+clone0.getFirstName()+" "+clone1.getFirstName()+" "+clone2.getFirstName());
				 
				 //System.out.println("observers "+clone1.getObservers());
				 
				 line = fp.poll();
			}

			/*
			System.out.println("Replica tree 0");
			treeClone0.display(treeClone0.root);
			
			System.out.println("Replica tree 1");
			treeClone1.display(treeClone1.root);
			
			System.out.println("Replica tree 2");
			treeClone2.display(treeClone2.root);
			*/
			 
		}
		catch(Exception e)
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

	public void modFileParser(FileProcessor fp,FileDisplayInterface outputRes1,FileDisplayInterface outputRes2,FileDisplayInterface outputRes3,StdoutDisplayInterface outStdout1,StdoutDisplayInterface outStdout2,StdoutDisplayInterface outStdout3) throws IOException
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
			
			
			printNodes((Results)outputRes1,(Results)outStdout1,treeClone0.root);
			printNodes((Results)outputRes2,(Results)outStdout2,treeClone1.root);
			printNodes((Results)outputRes3,(Results)outStdout3,treeClone2.root);
				 
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
	

	public void setEntry(SubjectI oldNode, SubjectI newNode)
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
	
	public void printNodes(Results outRes,Results outStd, SubjectI root)
	{
		if(null != root)
		{
			printNodes(outRes,outStd,root.getLeft());
			outRes.store(String.valueOf(root.getbNumber()));
			outRes.store(":");
			outRes.store(String.join(",",root.getSkills()));
			outRes.store("\n");
			outStd.store(String.valueOf(root.getbNumber()));
			outStd.store(":");
			outStd.store(String.join(",",root.getSkills()));
			outStd.store("\n");
			printNodes(outRes,outStd,root.getRight());
		}
	}


}
