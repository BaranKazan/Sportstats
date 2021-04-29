package Sportstats.Service.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import Sportstats.Dao.GameDao;
import Sportstats.Dao.TeamDao;
import Sportstats.Domain.Game;
import Sportstats.Domain.Team;
import Sportstats.Helper.ScoreCalculator;
import Sportstats.Service.Runnable;

/**
 * This service class filters an already existing list of {@link ResultTable}s
 * and filters them by game results for all home games.
 * 
 * @version 2019-05-30
 * @author Lara Aula
 */
public class FilterResultByHomeGame implements Runnable<List<ResultTable>> {

	private final TeamDao teamDao = new TeamDao();
	private final GameDao gameDao = new GameDao();
	private List<ResultTable> resultTablesToFilter;

	/**
	 * Constructs this service.
	 * 
	 * @param resultTablesToFilter the list of result tables to filter by home game
	 */
	public FilterResultByHomeGame(List<ResultTable> resultTablesToFilter) {
		this.resultTablesToFilter = resultTablesToFilter;
	}

	/**
	 * Runs the service and returns a list containing all {@link ResultTable} for
	 * when teams have played home games.
	 * 
	 * @return list containing all result tables
	 */
	@Override
	public List<ResultTable> run() {

		List<Team> teams = teamDao.getAll();
		List<ResultTable> resultTablesToCompareWith = new ArrayList<ResultTable>();
		List<ResultTable> filteredList = new ArrayList<ResultTable>();

		try {
			for (Team currentTeam : teams) {

				List<Game> games = gameDao.listAllHomeGamesForTeam(currentTeam.getId());

				ResultTable resultForCurrentTeam = new ResultTable(currentTeam.getName(), games.size(),
						ScoreCalculator.getGoalsScoredForTeam(games, currentTeam.getId()),
						ScoreCalculator.getGoalsConcededForTeam(games, currentTeam.getId()),
						ScoreCalculator.gameResultsForTeam(games, currentTeam.getId()).get(ScoreCalculator.WINS),
						ScoreCalculator.gameResultsForTeam(games, currentTeam.getId()).get(ScoreCalculator.LOSSES),
						ScoreCalculator.gameResultsForTeam(games, currentTeam.getId()).get(ScoreCalculator.DRAWS),
						ScoreCalculator.gameResultsForTeam(games, currentTeam.getId()).get(ScoreCalculator.POINTS));

				resultTablesToCompareWith.add(resultForCurrentTeam);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
		resultTablesToCompareWith = resultTablesToCompareWith.stream()
				.filter(resultTable -> resultTable.getGamesPlayed() != 0).collect(Collectors.toList());

		int i = 0;
		while (i < resultTablesToCompareWith.size()) {

			for (ResultTable tableToFilter : resultTablesToFilter) {
				if (resultTablesToCompareWith.get(i).getTeamName().equals(tableToFilter.getTeamName())) {
					filteredList.add(resultTablesToCompareWith.get(i));
				}

			}

			i++;
		}

		return filteredList;
	}

}
