package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import Sportstats.Domain.Game;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Service.ServiceRunner;
import Sportstats.Service.List.ListAllGamesTiedByATeam;

/**
 * A test class that tests the {@link ListAllGamesTiedByATeam} service and works with values that has been inserted into the database beforehand.
 * There are 4 teams in the database with the ids 1, 2, 8 and 10. In the database the losses by these teams are the following:
 * <br>
 * <br><b>Ties by team 1:</b> game 44,46
 * <br><b>Ties by team 2:</b> 46
 * <br><b>Ties by team 8:</b> game 43,44
 * <br><b>Ties by team 10:</b> game 43
 * 
 * @author Lara Aula
 * @version 2019-05-09
 */
class ListAllGamesTiedByATeamIntegrationTest {

	ServiceRunner<List<Game>> runner;
	
	@Test
	void testIfExceptionIsThrownWhenTeamDoesntExist() {
		assertThrows(InvalidInputException.class, () -> {
			runner = new ServiceRunner<>(new ListAllGamesTiedByATeam(3));;
        });
	}
	
	@Test
	void testListTiesForTeam1() {
		runner = new ServiceRunner<>(new ListAllGamesTiedByATeam(1));
		assertEquals(2, runner.run().size());
	}
	
	@Test
	void testListTiesForTeam2() {
		runner = new ServiceRunner<>(new ListAllGamesTiedByATeam(2));
		assertEquals(1, runner.run().size());
	}
	
	@Test
	void testListTiesForTeam8() {
		runner = new ServiceRunner<>(new ListAllGamesTiedByATeam(8));
		assertEquals(2, runner.run().size());
	}
	
	@Test
	void testListTiesForTeam10() {
		runner = new ServiceRunner<>(new ListAllGamesTiedByATeam(10));
		assertEquals(1, runner.run().size());
	}
	
	

}
