package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import Sportstats.Domain.Game;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Service.ServiceRunner;
import Sportstats.Service.List.ListAllGamesLostByATeam;

/**
 * A test class that tests the {@link ListAllGamesLostByATeam} service and works with values that has been inserted into the database beforehand.
 * There are 4 teams in the database with the ids 1, 2, 8 and 10. In the database the losses by these teams are the following:
 * <br>
 * <br><b>Losses by team 1:</b> none
 * <br><b>Losses by team 2:</b> game 3,5
 * <br><b>Losses by team 8:</b> game 45
 * <br><b>Losses by team 10:</b> game 42,47
 * 
 * @author Lara Aula
 * @version 2019-05-09
 *
 */
class ListAllGamesLostByATeamIntegrationTest {

	ServiceRunner<List<Game>> runner;
	

	@Test
	void testIfExceptionIsThrownWhenTeamDoesntExist() {
		assertThrows(InvalidInputException.class, () -> {
			runner = new ServiceRunner<>(new ListAllGamesLostByATeam(3));;
        });
	}
	
	@Test
	void testListLossesForTeam1() {
		runner = new ServiceRunner<>(new ListAllGamesLostByATeam(1));
		assertEquals(0, runner.run().size());
	}
	
	@Test
	void testListLossesForTeam2() {
		runner = new ServiceRunner<>(new ListAllGamesLostByATeam(2));
		assertEquals(2, runner.run().size());
	}
	
	@Test
	void testListLossesForTeam8() {
		runner = new ServiceRunner<>(new ListAllGamesLostByATeam(8));
		assertEquals(1, runner.run().size());
	}
	
	@Test
	void testListLossesForTeam10() {
		runner = new ServiceRunner<>(new ListAllGamesLostByATeam(10));
		assertEquals(2, runner.run().size());
	}
	
	

}
