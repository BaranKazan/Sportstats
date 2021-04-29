package Sportstats.Service.Add;

import java.util.NoSuchElementException;

import Sportstats.Dao.GameDao;
import Sportstats.Domain.Game;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Service.Runnable;

/**
 * Service class for updating the result of a game.
 * 
 * @author Hassan Sheikha
 * @version 2019-04-29
 */
public class AddResultForGame implements Runnable<Boolean> {

	private final GameDao gameDao = new GameDao();
	private int gameId;
	private int homeGoals;
	private int awayGoals;

	public AddResultForGame(int gameId, int homeGoals, int awayGoals) {
		this.gameId = gameId;
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
	}

	/**
	 * Method for adding the result to the game.
	 * 
	 * @throws InvalidInputException if there is no game with the id given in the
	 *                               constructor
	 * @return {@code true} if service was ran successfully
	 */
	@Override
	public Boolean run() {

		try {
			gameDao.get(gameId);
		} catch (NoSuchElementException e) {
			throw e;
		}

		Game newGame = new Game(homeGoals, awayGoals, gameId);
		gameDao.addResult(newGame);
		return true;
	}
}
