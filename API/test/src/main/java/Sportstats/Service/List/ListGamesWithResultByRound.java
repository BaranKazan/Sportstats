package Sportstats.Service.List;

import java.util.ArrayList;

import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.Dao.GameDao;
import Sportstats.Dao.MetaInformationDao;
import Sportstats.Dao.RoundDao;
import Sportstats.Domain.Game;
import Sportstats.Service.Runnable;

/**
 * Service class that lists games in a round with the eventual results
 * 
 * @author Hassan Sheikha
 * @version 2019-05-07
 */

public class ListGamesWithResultByRound implements Runnable<List<Object>> {

	private final GameDao gameDao = new GameDao();
	private final MetaInformationDao metaDao = new MetaInformationDao();
	private int roundId;

	/**
	 * Constructs the service with the round to list all games for as a parameter.
	 * 
	 * @param roundId id of the round to list all games for
	 */
	public ListGamesWithResultByRound(int roundId) {
		this.roundId = roundId;
	}

	/**
	 * Runs the service and returns a list of games played in a specific round. This
	 * method also prints if the game was played to extra time
	 * 
	 * @return a list of all games with their metainformation
	 */
	@Override
	public List<Object> run() {

		try {
			new RoundDao().get(roundId);
		} catch (NoSuchElementException e) {
			throw e;
		}

		List<Game> listOfGames = gameDao.getGamesByRound(roundId);
		List<Object> gameList = new ArrayList<Object>();

		for (Game game : listOfGames) {
			if (game.getRoundId() == roundId) {
				gameList.add(game);
				if (game.getAwayGoals() >= 0 && game.getHomeGoals() >= 0) {
					try {
						gameList.add(metaDao.playedExtraTime(game.getId()));
					} catch (NoSuchElementException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return gameList;
	}
}