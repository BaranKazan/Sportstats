
package IntegrationTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import Sportstats.Service.Add.AddNewRoundToSeason;


/**
 * 
 * @author a_ksy
 *
 * Integration test for the class {@link AddNewRoundToSeason}
 */

public class AddNewRoundToSeasonIntegrationTest {
	
	public AddNewRoundToSeasonIntegrationTest() {
		
	}
	
	AddNewRoundToSeason addNewRoundToSeason;

	@Test
	public void testAddNewRoundToSeason() {
		addNewRoundToSeason = new AddNewRoundToSeason(1, 2);
		assertTrue(addNewRoundToSeason.run());
	}
}