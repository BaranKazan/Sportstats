package Sportstats.Service.List;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import Sportstats.Dao.GameDao;
import Sportstats.Domain.Game;
import Sportstats.Service.Runnable;

/**
 * Service that lists all away games for a team.
 * 
 * @author Ali Shakeri
 * @version 2019-05-30
 */
public class ListAllAwayGamesForTeam implements Runnable<ArrayList<Game>> {

	int teamId;

	/**
	 * Constructs the service with the team to list all away games for as parameter.
	 * 
	 * @param teamId id of the team to list all away games for
	 */
	public ListAllAwayGamesForTeam(int teamId) {
		this.teamId = teamId;
	}

	/**
	 * Returns a list of all away games for a specific team.
	 * 
	 * @return returns a {@link ArrayList} of {@link Game}'s
	 */
	@Override
	public ArrayList<Game> run() {
		try {
			return new GameDao().listAllAwayGamesForTeam(teamId);
		} catch (NoSuchElementException e) {
			throw e;
		}
	}
}