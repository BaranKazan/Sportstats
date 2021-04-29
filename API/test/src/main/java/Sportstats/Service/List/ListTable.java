package Sportstats.Service.List;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.Dao.GameDao;
import Sportstats.Dao.RoundDao;
import Sportstats.Dao.SeasonDao;
import Sportstats.Dao.TeamDao;
import Sportstats.Domain.Game;
import Sportstats.Domain.Round;
import Sportstats.Domain.Team;
import Sportstats.Helper.ScoreCalculator;
import Sportstats.Service.Runnable;
import Sportstats.Service.Filter.ResultTable;

/**
 * 
 * A class that lists a table of data for a specific season. The data that will
 * be listed is all accumulated data for the teams that have played during a
 * season where the data for each team will be the following:
 * 
 * <li>Team name</li>
 * <li>Games played</li>
 * <li>Goals scored</li>
 * <li>Goals conceded</li>
 * <li>Maximum amount of games in a season</li>
 * <li>Games won</li>
 * <li>Games lost</li>
 * <li>Games drawn</li>
 * <li>Points</li>
 * 
 * 
 * @author Hassan Sheikha
 * @author Lara Aula
 * @version 2019-05-30
 */

public class ListTable implements Runnable<List<ResultTable>> {

	private int seasonId;
	private final SeasonDao seasonDao = new SeasonDao();
	private final RoundDao roundDao = new RoundDao();
	private final GameDao gameDao = new GameDao();
	private final TeamDao teamDao = new TeamDao();
	private int totalGamesPlayed;
	private int totalGoalsScored = 0;
	private int totalGoalsConceded = 0;
	private List<Round> roundList = new ArrayList<Round>();
	private List<Game> gameList = new ArrayList<Game>();
	private List<Team> teamList = new ArrayList<Team>();
	private int index = 0;

	/**
	 * Constructs this service with the given season id as parameter to list all the
	 * info in.
	 * 
	 * @param seasonId the id of the season to list all the information in
	 */
	public ListTable(int seasonId) {
		this.seasonId = seasonId;
	}

	/**
	 * Runs the service and returns a list where each element is a
	 * {@link ArrayList<String>} containing all statistics for a team in a season.
	 * The first element in the list will only contain the season span and the rest
	 * of the list contains season statistics for all teams in a season.
	 * 
	 * @throws NoSuchElementException if the season with the given id does not exist
	 * @return a list containing all information for a season
	 */
	@Override
	public List<ResultTable> run() {
		List<ResultTable> seasonTable = new ArrayList<>();

		try {
			seasonDao.get(seasonId);
		} catch (NoSuchElementException e) {
			throw e;
		}

		roundList = roundDao.listAllRoundsbySeason(seasonId);
		gameList = getGamesForRound(roundList);
		teamList = getAllTeamsInSeason(seasonId);

		for (Team team : teamList) {

			totalGoalsConceded = 0;
			totalGoalsScored = 0;
			totalGamesPlayed = 0;

			for (Round round : roundList) {
				int homeGoals = gameDao.homeGoalsScoredByTeam(gameList.get(index).getHomeTeam(), round.getId());
				int awayGoals = gameDao.awayGoalsScoredByTeam(gameList.get(index).getHomeTeam(), round.getId());
				int homeConceded = gameDao.homeGoalsConcededByTeam(gameList.get(index).getHomeTeam(), round.getId());
				int awayConceded = gameDao.awayGoalsConcededByTeam(gameList.get(index).getHomeTeam(), round.getId());

				totalGoalsScored += awayGoals + homeGoals;

				totalGoalsConceded += awayConceded + homeConceded;

				totalGamesPlayed++;

			}

			int game = gameList.get(index).getId();
			ResultTable currentTable = new ResultTable(team.getName(), totalGamesPlayed, totalGoalsScored,
					totalGoalsConceded,
					ScoreCalculator.seasonResultForTeam(team, seasonId, game).get(ScoreCalculator.WINS),
					ScoreCalculator.seasonResultForTeam(team, seasonId, game).get(ScoreCalculator.LOSSES),
					ScoreCalculator.seasonResultForTeam(team, seasonId, game).get(ScoreCalculator.DRAWS),
					ScoreCalculator.seasonResultForTeam(team, seasonId, game).get(ScoreCalculator.POINTS));
			seasonTable.add(currentTable);
			index++;

		}

		return seasonTable;
	}

	/**
	 * Method that gets all the teams in a season.
	 * 
	 * @param seasonId the id of the season
	 * @return a list of all the teams
	 */
	private List<Team> getAllTeamsInSeason(int seasonId) {
		List<Team> teamList = new ArrayList<Team>();
		teamList = teamDao.getAllTeamsInASeason(seasonId);
		return teamList;

	}

	/**
	 * Gets all games in the rounds in a season.
	 * 
	 * @param roundList the list of rounds
	 * @return a list of rounds
	 */
	private List<Game> getGamesForRound(List<Round> roundList) {
		for (Round round : roundList) {
			gameList.addAll(gameDao.getGamesByRound(round.getId()));
		}

		return gameList;
	}

}
