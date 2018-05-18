/**
 * 
 */
package src.Smartphone;
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
	 * Test method for {@link Smartphone.Contact#main(java.lang.String[])}.
	 */
	@Test
	void testMain() 
	{
		@SuppressWarnings("unused")
		Contact c = new Contact();
	}

	/**
	 * Test method for {@link Smartphone.Contact#Contact()}.
	 */
	@Test
	void testContact()
	{
		File f = new File("C:\\Smartphone");
		if(!f.isDirectory())
			fail("Not a Directory");
	}

	/**
	 * Test method for {@link Smartphone.Contact#Contact(java.lang.String)}.
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
