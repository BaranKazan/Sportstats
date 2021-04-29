package IntegrationTest;

import java.util.List;

import org.junit.jupiter.api.Test;

import Sportstats.Domain.Game;
import Sportstats.Service.ServiceRunner;
import Sportstats.Service.List.ListBiggestWin;

/**
 * Integration test case for {@link ListBiggestWin}
 * 
 * @author Hassan Sheikha
 * @version 2019-05-08
 *
 */

class ListBiggestWinTest {

	ServiceRunner<List<Game>> serviceRunner;
	
	@Test
	void testListBiggestWin() {
		
		ListBiggestWin listBiggestWin = new ListBiggestWin(1,2);
		listBiggestWin.run();
		System.out.println(listBiggestWin.run());
	}

}
