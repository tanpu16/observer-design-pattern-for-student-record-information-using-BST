package studentskills.driver;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import studentskills.util.FileProcessor;
import studentskills.util.Results;
import studentskills.mytree.TreeHelper;

/**
 * @author John Doe
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) throws InvalidPathException,SecurityException,IOException {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if (args.length != 2) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		System.out.println("Hello World! Lets get started with the assignment");
		
		FileProcessor fp = null;
		TreeHelper hp = new TreeHelper();
		Results res = new Results(args[1]);
		try 
		{
			fp = new FileProcessor(args[0]);
			hp.InputParser(fp,res);
		

		} 
		catch (InvalidPathException | SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
