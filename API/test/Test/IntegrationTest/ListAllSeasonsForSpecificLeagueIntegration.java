package IntegrationTest;

import static org.junit.Assert.*;
import org.junit.Test;

import Sportstats.Service.List.ListAllSeasonsForSpecificLeague;

/**
 * 
 * @author 17alsh02
 *
 */
public class ListAllSeasonsForSpecificLeagueIntegration {

	@Test
	public void testRun() {
		ListAllSeasonsForSpecificLeague getAllSeasons = new ListAllSeasonsForSpecificLeague(1);
		assertEquals(0, getAllSeasons.run().size());
	}
}
