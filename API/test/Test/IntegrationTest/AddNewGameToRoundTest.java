package IntegrationTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import Sportstats.Exception.InvalidInputException;
import Sportstats.Helper.DateTranslator;
import Sportstats.Service.Add.AddNewGameToRound;

/**
 * Test class that tests the {@link AddNewGameToRound} class.
 * 
 * @author Lara Aula
 * @version 2019-05-01
 */
public class AddNewGameToRoundTest {
	
	AddNewGameToRound addNewGameToRound;
	
	@Test
	public void testIfExceptionIsThrownWhenRoundDoesntExist() {
		assertThrows(InvalidInputException.class, () -> {
			addNewGameToRound = new AddNewGameToRound(DateTranslator.stringConvertToLocalDate("2012-05-22"), 2, 2, 1, 9); 
		});
	}
	
	@Test
	public void testIfExceptionIsThrownWhenArenaDoesntExist() {
		assertThrows(InvalidInputException.class, () -> {
			addNewGameToRound = new AddNewGameToRound(DateTranslator.stringConvertToLocalDate("2012-05-22"), 3, 2, 1, 7);
		});
	}
	
	@Test 
	public void testIfExceptionIsThrownWhenHomeTeamDoesntExist() {
		assertThrows(InvalidInputException.class, () -> {
			addNewGameToRound = new AddNewGameToRound(DateTranslator.stringConvertToLocalDate("2012-05-22"), 1, 3, 2, 7);
		});
	}
	
	@Test 
	public void testIfExceptionIsThrownWhenAwayTeamDoesntExist() {
		assertThrows(InvalidInputException.class, () -> {
			addNewGameToRound = new AddNewGameToRound(DateTranslator.stringConvertToLocalDate("2012-05-22"), 1, 2, 3, 7);
		});
	}
	
	@Test 
	public void testIfExceptionIsThrownWhenHomeAndAwayTeamIsSame() {
		assertThrows(InvalidInputException.class, () -> {
			addNewGameToRound = new AddNewGameToRound(DateTranslator.stringConvertToLocalDate("2012-05-22"), 1, 2, 2, 7);
		});
	}
	
	@Test
	public void testIfServiceIsRun() {
		addNewGameToRound = new AddNewGameToRound(DateTranslator.stringConvertToLocalDate("2012-05-22"), 1, 1, 2, 7);
		assertTrue(addNewGameToRound.run());
	}
}

