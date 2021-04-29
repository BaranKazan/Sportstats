package Sportstats.Service.List;

import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.Dao.GameDao;
import Sportstats.Domain.Game;
import Sportstats.Service.Runnable;

/**
 * Service class that returns all home games for a team.
 * 
 * @author André Ksyt
 * @version 2019-05-30
 */
public class ListAllHomeGamesForTeam implements Runnable<List<Game>> {

	int teamId;

	/**
	 * Constructs the service with the team to list all home games for as parameter.
	 * 
	 * @param teamId id of the team to list all home games for
	 */
	public ListAllHomeGamesForTeam(int teamId) {
		this.teamId = teamId;
	}

	/**
	 * Runs the service and returns a list of all home games played by a team.
	 * 
	 * @throws NoSuchElementException if the team with the given id doesn't exist
	 * @return the list containing all home games for the team
	 */
	@Override
	public List<Game> run() {
		try {
			return new GameDao().listAllHomeGamesForTeam(teamId);
		} catch (NoSuchElementException e) {
			throw e;
		}
	}
}