package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Sportstats.Service.Add.AddNewLeagueToASport;
import Sportstats.Service.Add.AddNewTeamToASeason;

/**
 * 
 * @author Hassan Sheikha
 * @version 2019-05-01
 * 
 * Integration test class for {@link AddNewTeamToASeason}
 */

class AddNewTeamToASeasonTest {
	
	private AddNewTeamToASeason addTeam;

	@Test
	public void addNewTeamToASeason() {
		addTeam = new AddNewTeamToASeason("AC Milan U19", 2, 1, 3);
		assertTrue(addTeam.run());
	}

}
