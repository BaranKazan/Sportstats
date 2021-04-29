package IntegrationTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Sportstats.Dao.LeagueDao;
import Sportstats.Domain.League;
import Sportstats.Service.ServiceRunner;
import Sportstats.Service.List.ListAllLeaguesBySportId;

/**
 * JUnit test class for the {@link ListAllLeaguesBySportId} Service. This test is a integration test.
 * @author laraa
 *
 */
public class ListAllLeaguesBySportIdIntegrationTest {
	

	LeagueDao leagueDao = new LeagueDao();
	ServiceRunner<List<League>> serviceRunner;

	@Test
	public void GetAllLeaguesInTheSportWithID11() {
		serviceRunner = new ServiceRunner<List<League>>(new ListAllLeaguesBySportId(11));
		for(League l: serviceRunner.run()) {
			System.out.println("Sport id: " + l.getSportId() + "\n League name: " + l.getName());
			System.out.println();
		}
	}

	@Test
	public void GetAllLeaguesInTheSportWithID12() {
		serviceRunner = new ServiceRunner<List<League>>(new ListAllLeaguesBySportId(12));
		for(League l: serviceRunner.run()) {
			System.out.println("Sport id: " + l.getSportId() + "\n League name: " + l.getName());
			System.out.println();
		}
	}

	@Test
	public void GetAllLeaguesInTheSportWithID13() {
		serviceRunner = new ServiceRunner<List<League>>(new ListAllLeaguesBySportId(13));
		assertEquals(serviceRunner.run().size(), 0);
	}

}
