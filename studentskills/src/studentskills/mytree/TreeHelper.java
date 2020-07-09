package studentskills.mytree;

import java.io.IOException;

import java.util.HashSet;

import java.util.Set;

import studentskills.util.FileProcessor;
import studentskills.util.Results;


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
		treeClone1 = (treeClone0).clone();
		treeClone2 = (treeClone0).clone();

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
				 
				 
					 
				 SubjectI clone0 = new StudentRecord(bNumber,firstName,lastName,gpa,major,skill);
				 SubjectI clone1 = ((StudentRecord)clone0).clone();
				 SubjectI clone2 = ((StudentRecord)clone0).clone();
				 
				 clone0.registerObservers((StudentRecord)clone1, (StudentRecord)clone2);
				 clone1.registerObservers((StudentRecord)clone0, (StudentRecord)clone2);
				 clone2.registerObservers((StudentRecord)clone0, (StudentRecord)clone1);
				
				 
				 SubjectI tempNode = clone0.Search(clone0.getbNumber(), treeClone0.root);
				 

				 if(null != tempNode)	
				 {
					 clone0.insertUpdateNode(treeClone0, clone0);
					 //clone1.insertUpdateNode(treeClone1, clone1);
					 clone2.insertUpdateNode(treeClone2, clone2);
				 }
				 else
				 {
					 clone0.insert(treeClone0,clone0);
					 clone1.insert(treeClone1,clone1);
					 clone2.insert(treeClone2,clone2);
				 }


				 line = fp.poll();
			}

			System.out.println("Replica tree 0");
			treeClone0.display(treeClone0.root);
			
			System.out.println("Replica tree 1");
			treeClone1.display(treeClone1.root);
			
			System.out.println("Replica tree 2");
			treeClone2.display(treeClone2.root);
			 
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
				throw new Exception("Invalid Input! Exiting!!!");
			}
			else if(isEmptyFile)
			{
				throw new Exception("File is Empty! Exiting!!!");
			}
			
		}
		catch(Exception ie)
		{
			ie.printStackTrace();
			System.exit(0);
		}
		finally
		{
			fp.close();
		}
		

	}

	public void modFileParser(FileProcessor fp) throws IOException
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
				 
				 //System.out.println("In modify "+replicaID+" "+bNumber+" "+oldValue+" "+newValue);
				 
				 line = fp.poll();
			}
			
				 
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
				throw new Exception("Invalid Input! Exiting!!!");
			}
			else if(isEmptyFile)
			{
				throw new Exception("File is Empty! Exiting!!!");
			}
			
		}
		catch(Exception ie)
		{
			ie.printStackTrace();
			System.exit(0);
		}
		finally
		{
			fp.close();
		}
	}

}
