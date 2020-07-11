package studentskills.util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import studentskills.util.MyLogger.DebugLevel;

import java.io.FileWriter;
import java.io.BufferedWriter;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	String path;
	StringBuffer finalResult=new StringBuffer(); 
	
	//parameterized constructor
	public Results(String FilePath) 
	{
		MyLogger.writeMessage("Results constructor called", DebugLevel.CONSTRUCTOR);
		path = FilePath;
	}

	@Override
	public String toString()
	{
			return "Class Results [Path is -> "+path+"]";
	}
	
	/*store is void method,in which finalResult stores the final output in StringBuffer
	which we can write into the file and stdout
	@param str - rotated words or metrics values
	@return void
	@see print nothing but append output line for tree traversal and error message
	*/
	public void store(String str)
	{
				
		finalResult.append(str);				
	
	}
	

	/*this is generic void method, which write output to all 3 output.txt or error.txt depends on
	the instance used to call this method.
	@param NA
	@return void
	@see write content of the StringBuffer into the file.
	*/
	public void writeToFile() throws IOException,InvalidPathException,SecurityException,FileNotFoundException
	{
		MyLogger.writeMessage("Write to file method called", DebugLevel.RESULT);
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)));
		
		try
		{
			bw.write(finalResult.toString());
			bw.close();
		}
		catch (InvalidPathException | SecurityException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} 
		catch(IOException  e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		finally
		{
			bw.close();
		}

	}
	
	/*this is void method which is used to write output to console.
	@param NA
	@return NA
	@see write content of StringBuffer to stdout
	 */
	public void writeToStdout()
	{
		MyLogger.writeMessage("Write to stdout method called", DebugLevel.RESULT);
		System.out.println(finalResult);	
	}
}
