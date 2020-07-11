package studentskills.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import studentskills.util.FileDisplayInterface;
import studentskills.util.FileProcessor;
import studentskills.util.MyLogger;
import studentskills.util.Results;
import studentskills.mytree.TreeHelper;

/**
 * @author John Doe
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 7;

	public static void main(String[] args) throws InvalidPathException, SecurityException, FileNotFoundException, IOException {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if (args.length != 7) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		System.out.println("Hello World! Lets get started with the assignment");
		
		
		FileProcessor fp = null;
		TreeHelper hp = new TreeHelper();
		try 
		{
		
		MyLogger.setDebugValue(Integer.parseInt(args[6]));
		
		FileDisplayInterface outputRes1 = new Results(args[2]);
		FileDisplayInterface outputRes2 = new Results(args[3]);
		FileDisplayInterface outputRes3 = new Results(args[4]);
		
		FileDisplayInterface errorRes = new Results(args[5]);

		
			fp = new FileProcessor(args[0]);
			hp.InputParser(fp,errorRes);
		
			fp = new FileProcessor(args[1]);
			hp.modFileParser(fp,outputRes1,outputRes2,outputRes3,errorRes);
			
			outputRes1.writeToFile();
			outputRes2.writeToFile();
			outputRes3.writeToFile();
			errorRes.writeToFile();
			
			System.out.println("Tree1");
			((Results)outputRes1).writeToStdout();
			System.out.println("Tree2");
			((Results)outputRes2).writeToStdout();
			System.out.println("Tree3");
			((Results)outputRes3).writeToStdout();
			
			
		} 
		catch (InvalidPathException | SecurityException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} 
		 catch (IOException ie)
		{
			ie.printStackTrace();
			System.exit(0);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			fp.close();
		}
	}
}
