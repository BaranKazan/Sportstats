package IntegrationTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import Sportstats.Service.Add.AddTeamForSport;

/**
 * 
 * Integration test to add a team to sport
 * @author Peshang
 *
 */

public class AddTeamForSportIT {

	@Test
	public void test() {
		AddTeamForSport add = new AddTeamForSport("AIK", 1);
		add.run();
		assertEquals(true, add.run());
	}
}
