package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import Sportstats.Domain.Game;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Service.ServiceRunner;
import Sportstats.Service.List.ListAllGamesWonByATeam;

/**
 * A test class that tests the {@link ListAllGamesWonByATeam} service and works with values that has been inserted into the database beforehand.
 * There are 4 teams in the database with the ids 1, 2, 8 and 10. In the database the losses by these teams are the following:
 * <br>
 * <br><b>Wins by team 1:</b> game 3,5
 * <br><b>Wins by team 2:</b> none
 * <br><b>Wins by team 8:</b> game 42,47
 * <br><b>Wins by team 10:</b> game 45
 * 
 * @author Lara Aula
 * @version 2019-05-09
 *
 */
class ListAllGamesWonByATeamIntegrationTest {

	ServiceRunner<List<Game>> runner;
	
	@Test
	void testIfExceptionIsThrownWhenTeamDoesntExist() {
		assertThrows(InvalidInputException.class, () -> {
			runner = new ServiceRunner<>(new ListAllGamesWonByATeam(3));;
        });
	}
	
	@Test
	void testListWinsForTeam1() {
		runner = new ServiceRunner<>(new ListAllGamesWonByATeam(1));
		assertEquals(2, runner.run().size());
	}
	
	@Test
	void testListWinsForTeam2() {
		runner = new ServiceRunner<>(new ListAllGamesWonByATeam(2));
		assertEquals(0, runner.run().size());
	}
	
	@Test
	void testListWinsForTeam8() {
		runner = new ServiceRunner<>(new ListAllGamesWonByATeam(8));
		assertEquals(2, runner.run().size());
	}
	
	@Test
	void testListWinsForTeam10() {
		runner = new ServiceRunner<>(new ListAllGamesWonByATeam(10));
		assertEquals(1, runner.run().size());
	}
	
	

}
