package IntegrationTest;

import java.util.List;

import org.junit.jupiter.api.Test;

import Sportstats.Dao.GameDao;
import Sportstats.Domain.Game;
import Sportstats.Service.ServiceRunner;
import Sportstats.Service.List.ListAllGamesForSeason;
import Sportstats.Service.List.ListAllGamesForTeam;

/**
 * 
 * @author Hassan Sheikha
 * @author Alireza Shakeri Shemirani
 * @version 2019-04-30
 * 
 * integration test for {@link ListAllGamesForSeason}
 *
 */

class ListAllGamesForSeasonTest {
	
	GameDao gameDao = new GameDao();
	ServiceRunner<List<Game>> serviceRunner;

	@Test
	void testListAllGamesBySeason() {
		serviceRunner = new ServiceRunner<List<Game>>(new ListAllGamesForSeason(3));
		for(Game game: serviceRunner.run()) {
			System.out.println("Game id: " + game.getId());
			System.out.println("Game date: " + game.getDateOfThisGame());
		}
	}
}
