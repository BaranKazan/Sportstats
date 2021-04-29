package IntegrationTest;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import Sportstats.Service.List.ListAllAwayGamesForTeam;

/**
 * 
 * @author a_ksy
 * 
 *  Integration test for the class {@link ListAllAwayGamesForTeam}
 *
 */

class ListAllAwayGamesForTeamIntegration {

	@Test
	public void testListAllAwayGamesForTeam() {
		ListAllAwayGamesForTeam listAllAwayGamesForTeam = new ListAllAwayGamesForTeam(1);
		assertEquals(1, listAllAwayGamesForTeam.run().size());
	}
}
