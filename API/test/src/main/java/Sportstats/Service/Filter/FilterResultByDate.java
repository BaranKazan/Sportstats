package Sportstats.Service.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Sportstats.Dao.GameDao;
import Sportstats.Dao.TeamDao;
import Sportstats.Domain.Game;
import Sportstats.Domain.Team;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Helper.DateTranslator;
import Sportstats.Helper.ScoreCalculator;
import Sportstats.Service.Runnable;

/**
 * This service class filters an already existing list of {@link ResultTable}s
 * and filters them by game results between a date interval.
 * 
 * @author Lara Aula
 * @version 2019-05-30
 */
public class FilterResultByDate implements Runnable<List<ResultTable>> {

	private String startDate, endDate;
	private final GameDao gameDao = new GameDao();
	private final TeamDao teamDao = new TeamDao();
	private List<ResultTable> resultTablesToFilter;

	/**
	 * Constructs this service with the date interval as a parameter.
	 * 
	 * @param startDate            year to list results from
	 * @param endDate              year to list results to
	 * @param resultTablesToFilter result tables to filter
	 */
	public FilterResultByDate(String startDate, String endDate, List<ResultTable> resultTablesToFilter) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.resultTablesToFilter = resultTablesToFilter;
	}

	/**
	 * Runs the service and returns the results given in the constructor filtered by
	 * the date interval.
	 * 
	 * @return list containing all result tables
	 * @throws {@link InvalidInputException} if the start date is after the end date
	 */
	public List<ResultTable> run() {

		if (DateTranslator.stringConvertTolDate(startDate).after(DateTranslator.stringConvertTolDate(endDate)))
			throw new InvalidInputException("The start date can't be after the end date");

		List<ResultTable> resultTablesToCompareWith = new ArrayList<>();
		List<Game> gamesInInterval = gameDao.getAllGamesInDateInterval(startDate, endDate);
		List<Team> teams = teamDao.getAll();
		List<ResultTable> filteredList = new ArrayList<>();

		for (Team currentTeam : teams) {
			ResultTable resultForCurrentTeam = new ResultTable(currentTeam.getName(),
					ScoreCalculator.gamesPlayedForTeam(gamesInInterval, currentTeam.getId()),
					ScoreCalculator.getGoalsScoredForTeam(gamesInInterval, currentTeam.getId()),
					ScoreCalculator.getGoalsConcededForTeam(gamesInInterval, currentTeam.getId()),
					ScoreCalculator.gameResultsForTeam(gamesInInterval, currentTeam.getId()).get(ScoreCalculator.WINS),
					ScoreCalculator.gameResultsForTeam(gamesInInterval, currentTeam.getId())
							.get(ScoreCalculator.LOSSES),
					ScoreCalculator.gameResultsForTeam(gamesInInterval, currentTeam.getId()).get(ScoreCalculator.DRAWS),
					ScoreCalculator.gameResultsForTeam(gamesInInterval, currentTeam.getId())
							.get(ScoreCalculator.POINTS));

			resultTablesToCompareWith.add(resultForCurrentTeam);
		}
		resultTablesToCompareWith = resultTablesToCompareWith.stream()
				.filter(resultTable -> resultTable.getGamesPlayed() != 0).collect(Collectors.toList());

		int i = 0;
		while (i < resultTablesToCompareWith.size()) {

			for (ResultTable tableToFilter : resultTablesToFilter) {
				if (resultTablesToCompareWith.get(i).getTeamName().equals(tableToFilter.getTeamName())) {
					filteredList.add(tableToFilter);
				}
			}
			i++;
		}

		return filteredList;
	}

}