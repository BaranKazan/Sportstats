package Sportstats.Service.Add;

import Sportstats.Dao.SportDao;
import Sportstats.Domain.Sport;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Helper.InputControll;
import Sportstats.Service.Runnable;

/**
 * Service class that adds a new {@link Sport}.
 * 
 * @author Hassan Sheikha
 * @version 2019-05-30
 */
public class AddNewSport implements Runnable<Boolean> {

	private final SportDao newSportDao = new SportDao();
	private String sportName;

	public AddNewSport(String sportName) {
		this.sportName = sportName;
	}

	/**
	 * Method for adding a new sport into the database with an input-exception
	 * 
	 * @param sport name for the new sport to add
	 * @throws InvalidInputException if the sport name given contains illegal
	 *                               characters
	 * @return {@code true} if service was ran successfully {@code false} otherwise
	 */
	public Boolean run() {

		if (!InputControll.isStringOnlyAlphabeticAndNotEmpty(sportName.trim())) {
			throw new InvalidInputException("The league name either contains illegal characters or is of size.");

		} else {
			Sport newSport = new Sport(sportName);
			return newSportDao.save(newSport);
		}
	}
}