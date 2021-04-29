package IntegrationTest;

import static org.junit.Assert.*;
import org.junit.Test;

import Sportstats.Service.Add.AddNewSport;

/**
 * 
 * @author Hassan Sheikha
 *
 */
public class AddNewSportIntegration {
	
	private AddNewSport ans;


	@Test
	public void testRun() {
		ans = new AddNewSport("Suicide v 2");
		ans.run();
		assertEquals(true, ans.run());	
	}
}
