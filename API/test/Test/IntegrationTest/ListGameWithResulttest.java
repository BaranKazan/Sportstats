package IntegrationTest;

import org.junit.jupiter.api.Test;

import Sportstats.Service.List.ListGameWithResult;

/**
 * Integration test for {@link ListGameWithResult}
 * 
 * @author Hassan Sheikha
 * @version 2019-05-08
 * 
 *
 */

class ListGameWithResulttest {

	@Test
	void testListGameWithResult() {
		ListGameWithResult lg = new ListGameWithResult(3);
		lg.run();
		System.out.print(lg.run());
	}

}
