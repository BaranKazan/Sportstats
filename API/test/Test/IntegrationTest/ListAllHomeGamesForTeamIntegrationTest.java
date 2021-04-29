package IntegrationTest;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import Sportstats.Service.List.ListAllHomeGamesForTeam;

/**
 * 
 * @author a_ksy
 * 
 *  Integration test for the class {@link ListAllHomeGamesForTeam}
 *
 */

class ListAllHomeGamesForTeamIntegrationTest {

	@Test
	public void testListAllHomeGamesForTeam() {
		ListAllHomeGamesForTeam listAllHomeGamesForTeam = new ListAllHomeGamesForTeam(1);
		assertEquals(1, listAllHomeGamesForTeam.run().size());
	}
}
