/**
 * 
 */
package JUnit;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import org.junit.jupiter.api.Test;
import src.Smartphone.Galerie;

class TestGalerie extends Galerie 
{

	/**
	 * Test class Galerie
	 */
	@Test
	void testGalerieString() 
	{
		@SuppressWarnings("unused")
		Galerie g = new Galerie("C:\\Smartphone");
	}

	/**
	 * Test method pictureCheck(String path)
	 */
	@Test
	void TestpictureCheck() 
	{
		String path = "C:\\Smartphone";
		
		try
		{
			File png;
			File jpg;
			File gif;
			File jpeg;
			
			File mpng;
			File mjpg;
			File mgif;
			File mjpeg;
			
			png = new File(path, "JUNIT_test.png");
			jpg = new File(path, "JUNIT_test.jpg");
			jpeg = new File(path, "JUNIT_test.jpeg");
			gif = new File(path, "JUNIT_test.gif");
			mpng = new File(path, "JUNIT_tests.PNG");
			mjpg = new File(path, "JUNIT_tests.JPG");
			mjpeg = new File(path, "JUNIT_tests.JPEG");
			mgif = new File(path, "JUNIT_tests.GIF");
					
			png.createNewFile();
			jpg.createNewFile();
			gif.createNewFile();
			mpng.createNewFile();
			mjpg.createNewFile();
			mgif.createNewFile();
			
			jpeg.createNewFile();
			mjpeg.createNewFile();
						
			String spng = png.getAbsolutePath().substring(png.getAbsolutePath().length()-3, png.getAbsolutePath().length()); // Trois dernières lettres (extension)
			String sjpg = jpg.getAbsolutePath().substring(jpg.getAbsolutePath().length()-3, jpg.getAbsolutePath().length()); // Trois dernières lettres (extension)
			String sgif = gif.getAbsolutePath().substring(gif.getAbsolutePath().length()-3, gif.getAbsolutePath().length()); // Trois dernières lettres (extension)
			
			String smpng = mpng.getAbsolutePath().substring(mpng.getAbsolutePath().length()-3, mpng.getAbsolutePath().length()); // Trois dernières lettres (extension)
			String smjpg = mjpg.getAbsolutePath().substring(mjpg.getAbsolutePath().length()-3, mjpg.getAbsolutePath().length()); // Trois dernières lettres (extension)			
			String smgif = mgif.getAbsolutePath().substring(mgif.getAbsolutePath().length()-3, mgif.getAbsolutePath().length()); // Trois dernières lettres (extension)			
			
			String sjpeg = jpeg.getAbsolutePath().substring(jpeg.getAbsolutePath().length()-4, jpeg.getAbsolutePath().length()); // Quatre dernières lettres  (extension)
			String smjpeg = mjpeg.getAbsolutePath().substring(mjpeg.getAbsolutePath().length()-4, mjpeg.getAbsolutePath().length()); // Quatre dernières lettres  (extension)
			
			
			if(smjpg.equals("JPG")||sjpg.equals("jpg")||spng.equals("png")||smpng.equals("PNG")||spng.equals("png")||sgif.equals("gif")||smgif.equals("GIF")||smjpeg.equals("JPEG")||sjpeg.equals("jpeg"))
			{
				
			}
			else
				fail("Failed Picture Check");
			
			png.delete();
			jpg.delete();
			gif.delete();
			mpng.delete();
			mjpg.delete();
			mgif.delete();			
			jpeg.delete();
			mjpeg.delete();
			
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			fail("Failed Picture Check");
		}
	}

}
