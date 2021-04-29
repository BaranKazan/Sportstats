package IntegrationTest;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import Sportstats.Exception.InvalidInputException;
import Sportstats.Service.Add.AddNewLeagueToASport;

/**
 * 
 * @author barankazan
 * @author Lara Aula
 *
 */

public class AddNewLeagueToASportTest {

	private AddNewLeagueToASport addLeague;

	@Test
	public void execute() {
		addLeague = new AddNewLeagueToASport(3, "Suicide Squad");
		assertTrue(addLeague.run());
	}

	
	@Test 
	public void numberAsName() {
		addLeague = new AddNewLeagueToASport(4, "3421");
		InvalidInputException exception = 
				assertThrows(InvalidInputException.class, () -> {
					addLeague.run();
					});
		assertEquals("Invalid league name.", exception.getMessage());
	}
	
	@Test
	public void invalidIdNumber() {
		addLeague = new AddNewLeagueToASport(-5, "Fotboll");
		InvalidInputException exception = 
				assertThrows(InvalidInputException.class, () -> {
					addLeague.run();
					});
		assertEquals("Invalid sportId.", exception.getMessage());

	}
	
	@Test
	public void noNameSport() {
		addLeague = new AddNewLeagueToASport(3, "");
		InvalidInputException exception = 
				assertThrows(InvalidInputException.class, () -> {
					addLeague.run();
				});
		assertEquals("Invalid league name.", exception.getMessage());
	}
	
	@Test
	public void invalidSymbol() {
		addLeague = new AddNewLeagueToASport(3, "_-&%€•Ωé®†µüüıœπ˙æøﬁª√˛¸ƒ∂ß÷≈ç‹›‘’‚…");
	}

}
