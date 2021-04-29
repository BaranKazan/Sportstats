package Sportstats.Service.List;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.Dao.GameDao;
import Sportstats.Dao.TeamDao;
import Sportstats.Domain.Game;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Helper.ScoreCalculator;
import Sportstats.Service.Runnable;

/**
 * Service class for listing the biggest win between two teams
 *
 * @author Hassan Sheikha
 * @author Baran Kazan
 * @version 2019-05-30
 */
public class ListBiggestWin implements Runnable<String> {

	private final int firstTeam;
	private final int secondTeam;
	private final TeamDao teamDao = new TeamDao();
	private final GameDao gameDao = new GameDao();

	/**
	 * Constructs the service and saves an instance of the teams to compare each
	 * other to.
	 * 
	 * @param firstTeam  id of the first team
	 * @param secondTeam id of the second team
	 */
	public ListBiggestWin(int firstTeam, int secondTeam) {
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
	}

	/**
	 * Runs the service and returns a string that shows the result of the service
	 * being ran.
	 * 
	 * @return a string with the biggest win between two teams
	 * @throws NoSuchElementException if any given team id does not have a
	 *                                corresponding team in the database
	 */
	@Override
	public String run() {

		try {
			teamDao.get(firstTeam);
			teamDao.get(secondTeam);
		} catch (NoSuchElementException e) {
			throw e;
		}

		if (firstTeam == secondTeam)
			throw new InvalidInputException("The first team and the second team can't have the same value");

		List<Game> listOfGame = gameDao.getGamesBetweenTwoTeams(firstTeam, secondTeam);
		List<Integer> listOfGameResult = new ArrayList<>();

		int teamHome = 0;
		int teamAway = 0;
		int resultHolder = 0;
		int result = 0;

		String homeTeamName = null;
		String awayTeamName = null;

		for (Game game : listOfGame) {
			listOfGameResult.clear();
			listOfGameResult.add(game.getHomeGoals());
			listOfGameResult.add(game.getAwayGoals());

			resultHolder = ScoreCalculator.calculateWin(listOfGameResult);
			if (result > resultHolder) {
				continue;
			} else if (result < resultHolder) {
				result = resultHolder;
				teamHome = listOfGameResult.get(0);
				teamAway = listOfGameResult.get(1);
				homeTeamName = teamDao.get(game.getHomeTeam()).getName();
				awayTeamName = teamDao.get(game.getAwayTeam()).getName();
			} else {
				continue;
			}
		}

		return "The Biggest Win/Loss is the goal difference of: " + result + " Where " + homeTeamName + " Scored: "
				+ teamHome + " Goals and " + awayTeamName + " Scored: " + teamAway + " Goals";
	}
}