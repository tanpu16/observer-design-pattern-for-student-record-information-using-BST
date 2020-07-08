package studentskills.mytree;

import java.io.IOException;

import java.util.HashSet;

import java.util.Set;

import studentskills.util.FileProcessor;
import studentskills.util.Results;


public class TreeHelper {
	
	int bNumber;
	String firstName,lastName,major;
	double gpa;
	boolean invalidInput,isEmptyFile;
	
	public TreeHelper()
	{
		bNumber = 0;
		firstName = null;
		lastName = null;
		major = null;
		gpa = 0.0;
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
	public void InputParser(FileProcessor fp,Results res) throws IOException, CloneNotSupportedException
	{
		
		BST bst = new BST();
		BST TreeClone1 = (bst).clone();
		BST TreeClone2 = (bst).clone();
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
				 
				 
					 
				 SubjectI node = new StudentRecord(bNumber,firstName,lastName,gpa,major,skill);
				 SubjectI Clone1 = ((StudentRecord)node).clone();
					 SubjectI tempNode = node.Search(node, bst.root);
				//System.out.println("in helper out original "+node.getFirstName()+" clone "+Clone1.getFirstName());
					 
				 //System.out.println("  "+node+"  "+Clone1);
				 if(null != tempNode)	
				 {
					 //System.out.println("in helper "+tempNode.getbNumber());
					 node.insertUpdateNode(bst, node);
					 //System.out.println("*****in helper out original "+node.getFirstName()+node.getGpa()+" clone "+Clone1.getFirstName()+Clone1.getGpa());
				 }
				 else
				 {
					 //System.out.println("in insert "+node.bNumber);
					 node.insert(bst,node);
					 node.insert(TreeClone1,Clone1);
				 }
				 //System.out.println(" "+bNumber+" "+firstName+" "+lastName+" "+gpa+" "+major+" "+skill);
				 //System.out.println("in helper out original "+node.firstName+" clone "+Clone1.firstName);

				 line = fp.poll();
			}

			//System.out.println("test");
			bst.display(bst.root);
			
			System.out.println("Replica tree");
			
			TreeClone1.display(TreeClone1.root);
			 
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
