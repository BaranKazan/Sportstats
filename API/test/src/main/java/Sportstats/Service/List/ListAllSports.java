package Sportstats.Service.List;

import java.util.List;

import Sportstats.Dao.SportDao;
import Sportstats.Domain.Sport;
import Sportstats.Service.Runnable;

/**
 * Service class that returns a list with all sports in the database.
 * 
 * @author Hassan Sheikha
 * @version 2019-05-30
 */

public class ListAllSports implements Runnable<List<Sport>> {

	/**
	 * Creates and returns a list containing all sports.
	 * 
	 * @return list of all sports
	 */
	@Override
	public List<Sport> run() {
		return new SportDao().getAll();
	}
}