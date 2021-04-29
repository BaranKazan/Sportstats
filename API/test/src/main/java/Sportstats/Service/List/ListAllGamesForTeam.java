package Sportstats.Service.List;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import Sportstats.Dao.GameDao;
import Sportstats.Dao.TeamDao;
import Sportstats.Domain.Game;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Service.Runnable;

/**
 * Service class that returns all games played, and games that will be played by
 * a team.
 * 
 * @author Baran Kazan
 * @author Hassan Sheikha
 * @author Lara Aula
 * @version 2019-05-30
 */
public class ListAllGamesForTeam implements Runnable<List<Game>> {

	private int teamId;
	private final TeamDao teamDao = new TeamDao();
	private final GameDao gameDao = new GameDao();

	/**
	 * Constructs the service with the id to list games for as a parameter.
	 * 
	 * @param teamId id of team to list games for
	 */
	public ListAllGamesForTeam(int teamId) {
		this.teamId = teamId;
	}

	/**
	 * The run method returns the list of matches that a team is related to.
	 * 
	 * @throws NoSuchElementException if the team with the id given doesn't exist
	 * @return the list containing all games a team is involved in
	 */
	@Override
	public List<Game> run() {
		List<Game> listOfAllGames;

		try {
                        teamDao.get(teamId);
		} catch (NoSuchElementException e) {
			throw new InvalidInputException("No team with the given id exists");
		}

		listOfAllGames = gameDao.getAll().stream()
				.filter(team -> team.getAwayTeam() == teamId || team.getHomeTeam() == teamId)
				.collect(Collectors.toList());

		return listOfAllGames;
	}
}