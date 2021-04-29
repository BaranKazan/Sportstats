package Sportstats.Service.Add;

import java.util.NoSuchElementException;
import Sportstats.Dao.LeagueDao;
import Sportstats.Dao.SportDao;
import Sportstats.Domain.League;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Helper.InputControll;
import Sportstats.Service.Runnable;

/**
 * This class adds a league to a sport by calling the execute method.
 * 
 * @author Lara Aula
 * @author Baran Kazan
 * @author Hassan Sheikha
 * @version 2019-05-01
 */
public class AddNewLeagueToASport implements Runnable<Boolean> {

	private int sportId;
	private String leagueName;
	private final SportDao sportDao = new SportDao();
	private final LeagueDao leagueDao = new LeagueDao();

	/**
	 * Constructs a service that adds a league to a sport.
	 * 
	 * @param sportID    the id of the sport to add the league to
	 * @param leagueName the name of the new league to add the sport to
	 */
	public AddNewLeagueToASport(int sportId, String leagueName) {
		this.sportId = sportId;
		this.leagueName = leagueName;
	}

	/**
	 * When this method is called, a new {@link League} object is created and stored
	 * into the database.
	 * 
	 * @return Returns true if save is successful.
	 * @throws InvalidInputException  if the league name is invalid
	 * @throws NoSuchElementException if the sport id given does not exist
	 */
	public Boolean run() {
		boolean saveSuccess = false;

		try {
			sportDao.get(sportId);
		} catch (NoSuchElementException e) {
			throw e;
		}

		if (!InputControll.isStringOnlyAlphabeticAndNotEmpty(leagueName.trim())) {
			throw new InvalidInputException("The league name either contains illegal characters or is of size 0");
		}

		League newLeague = new League(leagueName, sportId);
		saveSuccess = leagueDao.save(newLeague);

		return saveSuccess;
	}
}
