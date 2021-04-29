package Sportstats.Service.List;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.Dao.GameDao;
import Sportstats.Dao.RoundDao;
import Sportstats.Dao.SeasonDao;
import Sportstats.Domain.Game;
import Sportstats.Domain.Round;
import Sportstats.Service.Runnable;

/**
 * A service class that lists all games in a season.
 * 
 * @author Ali Shakeri
 * @author Hassan Sheikha
 * @version 2019-04-30
 */
public class ListAllGamesForSeason implements Runnable<List<Game>> {

	private int seasonId;

	/**
	 * Constructs the service.
	 * 
	 * @param seasonId the id of the season to list all games in
	 */
	public ListAllGamesForSeason(int seasonId) {
		this.seasonId = seasonId;
	}

	/**
	 * Runs the service and returns a list with all the games connected to a season.
	 * 
	 * @return the list of all games
	 */
	@Override
	public List<Game> run() {

		try {
			new SeasonDao().get(seasonId);
		} catch (NoSuchElementException e) {
			throw e;
		}

		List<Game> gameList = new ArrayList<>();
		List<Round> roundList = new RoundDao().listAllRoundsbySeason(seasonId);

		for (Round r : roundList) {
			gameList.addAll(new GameDao().getGamesByRound(r.getId()));
		}
		return gameList;
	}
}
