/**
 * 
 */
package JUnit;
import org.junit.jupiter.api.Test;

import src.Smartphone.Index;
import src.Smartphone.Locked;

/**
 * @author Quentin Nater
 *
 */
class TestLocked extends Locked 
{
	/**
	 * Test Locked
	 */
	@Test
	void testIndex() 
	{
		@SuppressWarnings("unused")
		Locked l = new Locked();
		
		@SuppressWarnings("unused")
		Index i = new Index();
	}

}
