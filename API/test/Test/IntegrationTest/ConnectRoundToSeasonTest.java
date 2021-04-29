package IntegrationTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Sportstats.Exception.InvalidInputException;
import Sportstats.Service.ServiceRunner;
import Sportstats.Service.Connect.ConnectRoundToSeason;

/**
 * Java Test class that tests {@link ConnectRoundToSeason}. 
 *  
 * @author Lara Aula
 * @version 2019-05-01
 */
class ConnectRoundToSeasonTest {


	ServiceRunner<Boolean> serviceRunner;
	
		@Test
		void testServiceWhenRoundDoesntExist() {
			assertThrows(InvalidInputException.class, () -> {
				serviceRunner = new ServiceRunner<>(new ConnectRoundToSeason(22, 2));;
	        });
		}

		@Test
		void testServiceWhenSeasonDoesntExist() {
			assertThrows(InvalidInputException.class, () -> {
				serviceRunner = new ServiceRunner<>(new ConnectRoundToSeason(4, 1));;
	        });
		}
		
		@Test
		void testServiceWhenRoundDoesnthaveGames() {
			assertThrows(InvalidInputException.class, () -> {
				serviceRunner = new ServiceRunner<>(new ConnectRoundToSeason(4, 2));;
	        });
		}		
		
		@Test
		void testServiceWhenMaximumRoundsPerSeasonHasBeenReached() {
		assertThrows(InvalidInputException.class, () -> {
				serviceRunner = new ServiceRunner<>(new ConnectRoundToSeason(6, 4));;
	        });
		}


}
