/**
 * 
 */
package JUnit;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import org.junit.jupiter.api.Test;

import src.Smartphone.Index;

/**
 * @author Quentin Nater
 *
 */
class TestIndex extends Index 
{
	/**
	 * Test Directory
	 */
	@SuppressWarnings("null")
	@Test
	void testDiretoryConfigurations() 
	{
		try
		{
			Index i = new Index(); boolean test = false;
		
			File fURL = new File(i.getUrl());
			
			if(!fURL.isDirectory())
				fail("The URL is not a Directory : " + fURL.getAbsolutePath());
			
			File[] fListe = fURL.listFiles();
			for (int j = 0; j < fURL.listFiles().length; j++) 
			{
				if(fListe[j].getAbsolutePath().equals(getUrl()+"\\url.txt"))
					test = true;
			}
			
			if(!fURL.isDirectory())
				fail("The URL is not a Directory");
				
			if(!test)
				fail("No file of url configutation");
		}
		catch (Exception e) 
		{
			fail("Load");
		}
	}

}
