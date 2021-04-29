package Sportstats.Service.List;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.Dao.GameDao;
import Sportstats.Dao.MetaInformationDao;
import Sportstats.Service.Runnable;

/**
 * A class that returns a game with the result as well as if the game was played
 * to extra time or not
 * 
 * @author Hassan Sheikha
 * @version 2019-05-31
 */
public class ListGameWithResult implements Runnable<List<Object>> {

	private int gameId;

	/**
	 * Constructs the service with the id of the game to list results for as the
	 * parameter.
	 * 
	 * @param gameId id of the game to list results for
	 */
	public ListGameWithResult(int gameId) {
		this.gameId = gameId;
	}

	/**
	 * Runs the service and returns the game result, including if the game was
	 * played to extra time.
	 * 
	 * @throws NoSuchElementException if no game with the given id exists, or if
	 *                                here is no metainformation for the game
	 * @return the list of game results
	 */
	@Override
	public List<Object> run() {
		GameDao gameDao = new GameDao();
		MetaInformationDao metaDao = new MetaInformationDao();
		List<Object> gameList = new ArrayList<Object>();

		try {
			gameDao.get(gameId);
			metaDao.getInformationByGameId(gameId);
		} catch (NoSuchElementException e) {
			throw e;
		}

		gameList.add("Home goals: " + gameDao.getGameResult(gameId).getHomeGoals());
		gameList.add("Away goals: "+ gameDao.getGameResult(gameId).getAwayGoals());
		gameList.add("Extra time: " + metaDao.playedExtraTime(gameId).getExtraTime());
		return gameList;
	}
}