/**
 * 
 */
package JUnit;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import org.junit.jupiter.api.Test;

import src.Smartphone.Contact;
import src.Smartphone.Index;

/**
 * @author Quentin Nater
 *
 */
class TestContact extends Contact 
{

	/**
	 * Test Directory
	 */
	@Test
	void testDiretoryConfigurations() 
	{
		File directory = new File("C:\\Smartphone");
		if(!directory.isDirectory())
		{
			fail("Not a Directory");
		}
	}
	
	/**
	 * Test URL
	 */
	@Test
	void testURLConfigurations() 
	{
		File directory = new File("C:\\Smartphone");
		File url = new File("C:\\Smartphone", "url.txt");
		if(!directory.isDirectory())
			fail("Not a Directory");
		else
		{
			if(!url.isFile())
				fail("Not a File");
		}
	}
		
	/**
	 * Test class Contact
	 */
	@Test
	void testContactString() 
	{
		Index i 	= new Index();
		String url 	= i.getUrl();
		
		File f 		= new File(url);
		
		if(!f.isDirectory())
			fail("Not a Directory");
	}
}
