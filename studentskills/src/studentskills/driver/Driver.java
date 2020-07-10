package studentskills.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import studentskills.util.FileDisplayInterface;
import studentskills.util.FileProcessor;
import studentskills.util.Results;
import studentskills.util.StdoutDisplayInterface;
import studentskills.mytree.SubjectI;
import studentskills.mytree.TreeHelper;

/**
 * @author John Doe
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

	public static void main(String[] args) throws InvalidPathException, SecurityException, FileNotFoundException, IOException, CloneNotSupportedException {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if (args.length != 5) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		System.out.println("Hello World! Lets get started with the assignment");
		
		FileProcessor fp = null;
		TreeHelper hp = new TreeHelper();
		FileDisplayInterface outputRes1 = new Results(args[2]);
		FileDisplayInterface outputRes2 = new Results(args[3]);
		FileDisplayInterface outputRes3 = new Results(args[4]);
	
		StdoutDisplayInterface outStdout1 = new Results(args[1]);
		StdoutDisplayInterface outStdout2 = new Results(args[1]);
		StdoutDisplayInterface outStdout3 = new Results(args[1]);
		//Results modRes = new Results(args[1]);
		try 
		{
			fp = new FileProcessor(args[0]);
			hp.InputParser(fp);
		
			fp = new FileProcessor(args[1]);
			hp.modFileParser(fp,outputRes1,outputRes2,outputRes3,outStdout1,outStdout2,outStdout3);
			
			outputRes1.writeToFile();
			outputRes2.writeToFile();
			outputRes3.writeToFile();
			
			
			System.out.println("Tree1");
			outStdout1.writeToStdout();
			System.out.println("Tree2");
			outStdout2.writeToStdout();
			System.out.println("Tree3");
			outStdout3.writeToStdout();
			
			
		} 
		catch (InvalidPathException | SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		finally
		{
			fp.close();
		}
	}
}
