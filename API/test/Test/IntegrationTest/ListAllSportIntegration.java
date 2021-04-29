package IntegrationTest;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Sportstats.Domain.Sport;
import Sportstats.Service.List.ListAllSports;

/**
 * 
 * @author Hassan Sheikha
 *
 */
public class ListAllSportIntegration {

	@Test
	public void test() {
		List<Sport> list = new ArrayList<>();
		ListAllSports listAllSports = new ListAllSports();
		list = listAllSports.run();
		
		for (Sport e: list)
			System.out.println(e.getName());
	}

}
