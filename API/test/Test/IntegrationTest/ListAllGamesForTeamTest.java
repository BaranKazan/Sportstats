package IntegrationTest;

import java.util.List;

import org.junit.jupiter.api.Test;

import Sportstats.Dao.GameDao;
import Sportstats.Domain.Game;
import Sportstats.Service.ServiceRunner;
import Sportstats.Service.List.ListAllGamesForTeam;

/**
 * 
 * @author Hassan Sheikha
 * @version 2019-04-30
 * 
 * Integration test for the class {@link ListAllGamesForTeam}
 *
 */

class ListAllGamesForTeamTest {

	GameDao gameDao = new GameDao();
	ServiceRunner<List<Game>> serviceRunner;
	
	@Test
	void testListAllGamesByTeam() {
		serviceRunner = new ServiceRunner<List<Game>>(new ListAllGamesForTeam(2));
		for(Game game: serviceRunner.run()) {
			System.out.println("Game id: " + game.getId() + "\n HomeTeam: " + game.getHomeTeam() + "\n AwayTeam: " + game.getAwayTeam());
			System.out.println();
			
		}
	}
}
