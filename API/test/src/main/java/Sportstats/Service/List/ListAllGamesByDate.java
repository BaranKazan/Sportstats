package Sportstats.Service.List;

import java.util.List;
import Sportstats.Dao.GameDao;
import Sportstats.Domain.Game;
import Sportstats.Service.Runnable;

/**
 * Service that lists all games played or scheduled for a specific date.
 * 
 * @author Ali Shakeri
 * @author André Ksyt
 * @version 2019-05-06
 */
public class ListAllGamesByDate implements Runnable<List<Game>> {
	private String date;

	/**
	 * Constructs the service and takes the date to list games for as a parameter.
	 * 
	 * @param date the date string to list all games for
	 */
	public ListAllGamesByDate(String date) {
		this.date = date;
	}

	/**
	 * Runs the service and returns a list with all games played for the given date
	 * 
	 * @return a list of games played in a specific date
	 */
	@Override
	public List<Game> run() {
		return new GameDao().listAllGamesByDate(date);
	}
}
