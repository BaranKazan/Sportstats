package Sportstats.Service.Connect;

import java.util.List;

import java.util.NoSuchElementException;

import Sportstats.Dao.RoundDao;
import Sportstats.Dao.SeasonDao;
import Sportstats.Domain.Round;
import Sportstats.Domain.Season;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Service.Runnable;
import Sportstats.Service.ServiceRunner;
import Sportstats.Service.List.ListGamesWithResultByRound;

/**
 * Service class that connects an already existing round with games to a season.
 * 
 * @author Lara Aula
 * @author Hassan Sheikha
 * @version 2019-05-01
 */
public class ConnectRoundToSeason implements Runnable<Boolean> {

	private final SeasonDao seasonDao = new SeasonDao();
	private final RoundDao roundDao = new RoundDao();

	private int roundID, seasonID;

	/**
	 * Constructs the service.
	 * 
	 * @param roundID  id of the round to connect to the season
	 * @param seasonID id of the season to connect the round to.
	 */
	public ConnectRoundToSeason(int roundID, int seasonID) {
		this.roundID = roundID;
		this.seasonID = seasonID;
	}

	/**
	 * When this service is run, the round is updated in the database with the
	 * season it is connected to.
	 * 
	 * @throws NoSuchElementException if a domain with the given id does not exist
	 *                                in the database. The detail message specifies
	 *                                which domain it regards.
	 * @throws InvalidInputException  if the season and round can't be connected
	 *                                with a detail message specifying why
	 * @return {@code true}
	 */
	@Override
	public Boolean run() {

		Round round;
		Season season;

		try {
			round = roundDao.get(roundID);
			season = seasonDao.get(seasonID);
		} catch (NoSuchElementException e) {
			throw e;
		}

		ServiceRunner<List<Object>> serviceRunner = new ServiceRunner<List<Object>>(
				new ListGamesWithResultByRound(round.getId()));

		if (serviceRunner.run().size() == 0)
			throw new InvalidInputException("A round cannot be added to a season if there are no games in a round.");

		if (roundDao.countRoundsInASeason(season.getId()) >= season.getMaximumRounds())
			throw new InvalidInputException(
					"This season has reached its maximum amount of rounds. You cannot add more rounds to this season");

		round.setSeasonID(season.getId());
		roundDao.update(round);
		return true;
	}

}
