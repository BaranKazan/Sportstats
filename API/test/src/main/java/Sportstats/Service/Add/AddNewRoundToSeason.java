package Sportstats.Service.Add;

import java.util.NoSuchElementException;
import Sportstats.Dao.RoundDao;
import Sportstats.Dao.SeasonDao;
import Sportstats.Domain.Round;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Service.Runnable;

/**
 * Service class that creates a new Round in a Season
 * 
 * @author Baran Kazam
 * @author Lara Aula
 * @author Hassan Sheikha
 * @version 2019-05-01
 */
public class AddNewRoundToSeason implements Runnable<Boolean> {

	private int roundNbr;
	private int seasonId;
	private final SeasonDao seasonDao = new SeasonDao();
	private final RoundDao roundDao = new RoundDao();

	/**
	 * Constructs the service. Throws {@link InvalidInputException} if an invalid
	 * round number is given or if the league id given doesn't exist.
	 * 
	 * @param roundNbr the number of this round
	 * @param seasonId the id of the season this round is in
	 */
	public AddNewRoundToSeason(int roundNbr, int seasonId) {
		this.seasonId = seasonId;
		this.roundNbr = roundNbr;
	}

	/**
	 * Saves the new round in the database.
	 * 
	 * @return {@code true} if the run was successful, {@code false} otherwise
	 * @throws NoSuchElementException if the season id given in the constructor
	 *                                doesn't exist
	 */
	@Override
	public Boolean run() {

		Round newRound = new Round(roundNbr, seasonId);

		try {
			seasonDao.get(seasonId);
		} catch (NoSuchElementException e) {
			throw e;
		}

		if (seasonDao.get(seasonId).getMaximumRounds() <= roundDao.countRoundsInASeason(seasonId))
			throw new InvalidInputException("Maximum amount of rounds for this round has been exceeded.");
		else
			return roundDao.save(newRound);
	}
}
