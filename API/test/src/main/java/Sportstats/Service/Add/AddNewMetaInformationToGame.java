package Sportstats.Service.Add;

import java.util.NoSuchElementException;

import Sportstats.Dao.ArenaDao;
import Sportstats.Dao.GameDao;
import Sportstats.Dao.MetaInformationDao;
import Sportstats.Domain.Arena;
import Sportstats.Domain.Game;
import Sportstats.Domain.MetaInformation;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Helper.ScoreCalculator;
import Sportstats.Service.Runnable;

/**
 * Service class that adds meta-information to a specific game.
 * 
 * @author Peshang Suleiman
 * @author Lara Aula
 * @version 2019-05-09
 */

public class AddNewMetaInformationToGame implements Runnable<Boolean> {

	private final MetaInformationDao metaInformationDao = new MetaInformationDao();
	private final ArenaDao arenaDao = new ArenaDao();
	private final GameDao gameDao = new GameDao();
	private int spectators;
	private int extraTime;
	private int partialHomeGoals;
	private int partialAwayGoals;
	private int gameId;

	/**
	 * Constructs the service that adds meta-informtion when the service is run.
	 * Throws {@link ServiceException} if any invalid parameters are given.
	 *
	 * @param gameId           the id of the game this meta-information is added to
	 * @param spectators       the amount of spectators in the game
	 * @param partialHomeGoals the partial home goals
	 * @param partialAwayGoals the partial away goals
	 */

	public AddNewMetaInformationToGame(int gameId, int spectators, int extraTime, int partialHomeGoals,
			int partialAwayGoals) {
		this.spectators = spectators;
		this.gameId = gameId;
		this.partialHomeGoals = partialHomeGoals;
		this.partialAwayGoals = partialAwayGoals;
		this.spectators = spectators;
		this.extraTime = extraTime;
	}

	/**
	 * Runs this service by creating a {@link MetaInformation} object and calling
	 * {@link MetaInformationDao#save(MetaInformation)} method.
	 * 
	 * @return {@code true} if service was run successfully, {@code false} otherwise
	 * @throws InvalidInputException  if any parameters given in the constructor are
	 *                                invalid with a detail message specifying the
	 *                                reason the exception was thrown
	 * @throws NoSuchElementException if there was no game with the id given in the
	 *                                constructor
	 */
	public Boolean run() {

		Game gameToAddMetaInformationTo;

		if (spectators < 0)
			throw new InvalidInputException("Spectators can not be negative");

		if (extraTime > 2 || extraTime < 0)
			throw new InvalidInputException("Extra time number has to be between 0 and 2");

		try {
			gameToAddMetaInformationTo = gameDao.get(gameId);
		} catch (NoSuchElementException e) {
			throw e;
		}

		Arena arena = arenaDao.get(gameToAddMetaInformationTo.getGameArena());
		if (spectators > arena.getCapacity()) {
			throw new InvalidInputException("There can't be more specators than the maximum arena capacity");
		}

		MetaInformation newMetainformation = new MetaInformation(gameToAddMetaInformationTo.getId(), spectators,
				ScoreCalculator.calculateWinner(gameToAddMetaInformationTo), extraTime, partialHomeGoals,
				partialAwayGoals);
		return metaInformationDao.save(newMetainformation);
	}
}
