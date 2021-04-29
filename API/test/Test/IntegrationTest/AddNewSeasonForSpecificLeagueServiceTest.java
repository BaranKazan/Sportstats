package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;

import Sportstats.Exception.InvalidInputException;
import Sportstats.Service.Add.AddNewSeasonForSpecificLeague;

/**
 * 
 * @author Hassan Sheikha
 * @author Lara Aula
 * @version 2019-05-01
 * 
 * Integration test class for {@link AddNewSeasinForSpecificLeague}
 * 
 */

class AddNewSeasonForSpecificLeagueServiceTest {

	private AddNewSeasonForSpecificLeague instance;

	@Test
	void testIfServiceExceptionIsThrownWhenInvalidSeasonSpanIsGiven() {
		  assertThrows(InvalidInputException.class, () -> {
	            instance = new AddNewSeasonForSpecificLeague(2, 2018, 2017, 2);
	        });
	}
	
	@Test
	void testIfServiceExceptionIsThrownWhenInvalidMaximumRoundsIsGiven() {
		  assertThrows(InvalidInputException.class, () -> {
	            instance = new AddNewSeasonForSpecificLeague(2, 2017, 2018, 0);
	        });
	}
	
	@Test
	void testIfServiceExceptionIsThrownWhenStartYearIsBelow2000() {
		  assertThrows(InvalidInputException.class, () -> {
	            instance = new AddNewSeasonForSpecificLeague(2, 1999, 2000, 2);
	        });
	}
	
	@Test
	void testIfServiceExceptionIsThrownWhenEndYearIsBelow2000() {
		  assertThrows(InvalidInputException.class, () -> {
	            instance = new AddNewSeasonForSpecificLeague(2, 2000, 1999, 2);
	        });
	}
	
	@Test
	void testIfServiceExceptionIsThrownWhenNonExistentLEagueIdIsGiven() {
		  assertThrows(InvalidInputException.class, () -> {
	            instance = new AddNewSeasonForSpecificLeague(1, 2000, 2001, 2);
	        });
	}
	
	@Test
	void testIfServiceExceptionIsNotThrownWhenValidParametersAreGiven() {
		boolean success = true;
		
		try{
			instance = new AddNewSeasonForSpecificLeague(2, 2000, 2001, 2);
		}catch(InvalidInputException e) {
			success = false;
		}
		
		assertEquals(true, success);
	}

	@Test
	void testThatRunMethodSavesTheNewSeason() {
		instance = new AddNewSeasonForSpecificLeague(2, 2018, 2019, 2);
		assertTrue(instance.run());
	}
}
