package Sportstats.Service.List;

import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.Dao.GameDao;
import Sportstats.Dao.TeamDao;
import Sportstats.Domain.Game;
import Sportstats.Service.Runnable;

/**
 * This service class extends the {@link Sportstats.Service.Runnable} that lists
 * all games a team has won with the help of a team id. The Games listed will
 * show both when the team won in a home game or away game.
 * 
 * @author Lara Aula
 * @version 2019-05-07
 */
public class ListAllGamesWonByATeam implements Runnable<List<Game>> {

	private int teamID;

	/**
	 * Constructs this service with the team to list all wins for.
	 * 
	 * @param teamID the id of the team to list all won games for
	 */
	public ListAllGamesWonByATeam(int teamID) {
		this.teamID = teamID;
	}

	/**
	 * Returns the list containing all won games by the team.
	 *
	 * @throws NoSuchElementException if no team eith the given id exists
	 * @return the list of all won games
	 */
	@Override
	public List<Game> run() {
		TeamDao teamDao = new TeamDao();

		try {
			teamDao.get(teamID);
		} catch (NoSuchElementException e) {
			throw e;
		}
		return new GameDao().listAllGamesWonByTeam(teamID);
	}
}