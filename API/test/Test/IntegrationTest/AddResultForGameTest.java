package IntegrationTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Baran Kazan
 */

import org.junit.Test;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Service.Add.AddResultForGame;

public class AddResultForGameTest {
	
	AddResultForGame addResult;

	@Test
	public void addResultForGame() {
		addResult = new AddResultForGame(3, 7, 1);
		assertTrue(addResult.run());
	}
	
	@Test
	public void invalidGameId() {
		addResult = new AddResultForGame(-32, 4, 1);
		InvalidInputException exception = 
				assertThrows(InvalidInputException.class, () -> {
					addResult.run();
					});
		assertEquals("The gamd ID is invalid", exception.getMessage());
	}
}
