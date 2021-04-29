package IntegrationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import Sportstats.Domain.MetaInformation;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Service.Add.AddNewMetaInformationToGame;

public class GameInformationByGameIdIT {

	private AddNewMetaInformationToGame addInfo;

	@Test
	public void testWhenInvalidSpectators() {
		assertThrows(InvalidInputException.class , () -> {addInfo = new AddNewMetaInformationToGame(3, 120000, 8, 0,90);
		});
	}
	
	@Test
	public void testWithValidSpectators() {
		addInfo = new AddNewMetaInformationToGame(3, 5453, 2, 1,5);
		assertEquals(true, addInfo.run());
	}
	


}
 