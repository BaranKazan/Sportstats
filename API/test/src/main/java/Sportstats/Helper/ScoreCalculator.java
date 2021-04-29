package Sportstats.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Sportstats.Dao.GameDao;
import Sportstats.Domain.Game;
import Sportstats.Domain.MetaInformation;
import Sportstats.Domain.Team;
import Sportstats.Exception.SportstatsHelperException;

/**
 * A helper class that calculates the difference in goals scored for both
 * participating teams in a set of games.
 *
 * @author Hassan Sheikha
 * @author Baran Kazan
 * @version 2019-05-09
 */
public class ScoreCalculator {

	/**
	 * Possible parameter for the get() method on the list that is returned from
	 * {@link #seasonResultForTeam(Team, int)}. Using this will return the amount of
	 * wins from the list
	 *
	 * @since 2019-05-13
	 */
	public static final int WINS = 0;

	/**
	 * Possible parameter for the get() method on the list that is returned from
	 * {@link #seasonResultForTeam(Team, int)}. Using this will return the amount of
	 * losses from the list
	 *
	 * @since 2019-05-13
	 */
	public static final int LOSSES = 1;

	/**
	 * Possible parameter for the get() method on the list that is returned from
	 * {@link #seasonResultForTeam(Team, int)}. Using this will return the amount of
	 * draws from the list
	 *
	 * @since 2019-05-13
	 */
	public static final int DRAWS = 2;

	/**
	 * Possible parameter for the get() method on the list that is returned from
	 * {@link #seasonResultForTeam(Team, int)}. Using this will return the amount of
	 * points from the list
	 *
	 * @since 2019-05-13
	 */
	public static final int POINTS = 3;

	public static int addGoals(int homeGoals, int awayGoals) {
		return homeGoals + awayGoals;
	}

	/**
	 * A method that calculates the biggest difference in goals to determine a
	 * winner
	 *
	 * @param listOfGame list of a result in a game
	 * @return the biggest win as an integer
	 */
	public static int calculateWin(List<Integer> listOfGame) {
		List<Integer> scoreList;
		scoreList = listOfGame;
		int scoreHomeOne = scoreList.get(0);
		int scoreAwayOne = scoreList.get(1);
		int biggestWin = 0;

		if (scoreHomeOne > scoreAwayOne) {
			biggestWin = scoreHomeOne - scoreAwayOne;
		} else if (scoreHomeOne < scoreAwayOne) {
			biggestWin = scoreAwayOne - scoreHomeOne;
		} else if (scoreHomeOne == scoreAwayOne) {
			biggestWin = 0;
		}
		return biggestWin;

	}

	/**
	 * This static method returns the winner of the game given in the parameter.
	 * Throws {@link SportstatsHelperException} if there are no goals to compare.
	 *
	 * @param game the game to calculate the winner of
	 * @return {@link MetaInformation#AWAY} if the away team won,
	 *         {@link MetaInformation#HOME} if the home team won and
	 *         {@link MetaInformation#NONE} if the game was a tie.
	 * 
	 * @author Lara Aula
	 * @since 2019-05-09
	 */
	public static int calculateWinner(Game game) {
		try {
			int goalDifference = game.getHomeGoals() - game.getAwayGoals();

			if (goalDifference < 0) {
				return MetaInformation.AWAY;
			} else if (goalDifference > 0) {
				return MetaInformation.HOME;
			} else {
				return MetaInformation.NONE;
			}

		} catch (NullPointerException e) {
			throw new SportstatsHelperException(
					"This game seems to not have been played. Add home goals and away goals for the game and try again.");
		}
	}

	/**
	 * This method returns a list containing the result of a season for a team.The
	 * values below show what will be returned when you use them as a parameter for
	 * the {@link ArrayList#get(int)} method on the list that will be returned.
	 * <li><b>{@link #WINS}: </b>Games Won</li>
	 * <li><b>{@link #LOSSES}: </b>Games Lost</li>
	 * <li><b>{@link #DRAWS}: </b>Games Drawed</li>
	 * <li><b>{@link #POINTS}: </b>Points</li>
	 *
	 * @param team     the id of the team to see season results for
	 * @param seasonId the id of the season
	 * @param gameId   the id of a game
	 * @return a list containing all results
	 * 
	 * @since 2019-05-13
	 * @author Lara Aula
	 * @author Hassan Sheikha
	 */
	public static List<Integer> seasonResultForTeam(Team team, int seasonId, int gameId) {
		int gamesWon;
		int gamesLost;
		int gamesDraw;
		int points = 0;
		GameDao gameDao = new GameDao();

		gamesWon = gameDao.countAllWinsByTeamInASeason(team.getId(), seasonId);
		points = gamesWon * 3;
		gamesLost = gameDao.countAllLossesByTeamInASeason(team.getId(), seasonId);
		gamesDraw = gameDao.countAllTiesByTeamInASeason(team.getId(), seasonId);
		points += gamesDraw;

		ArrayList<Integer> resultList = new ArrayList<>();
		resultList.add(gamesWon);
		resultList.add(gamesLost);
		resultList.add(gamesDraw);
		resultList.add(points);
		return resultList;
	}

	/**
	 * Returns the amount of times a team played a game in a list of games.
	 * 
	 * @param gamesToLookIn the list to count amount of playd games from
	 * @param teamId        the id of the team to count played games for
	 * @return the amount of games the team played
	 * 
	 * @since 2019-05-24
	 * @author Lara Aula
	 */
	public static int gamesPlayedForTeam(List<Game> gamesToLookIn, int teamId) {
		int gamesPlayed = 0;

		for (Game currentGame : gamesToLookIn) {
			if (currentGame.getHomeTeam() == teamId || currentGame.getAwayTeam() == teamId)
				gamesPlayed++;
		}
		return gamesPlayed;
	}

	/**
	 * Method that returns all goals conceded for a team in all the games given in
	 * the constructor.
	 * 
	 * @param gamesToCountFrom the list of games to count from
	 * @param teamID           id of the team to get conceded goals for
	 * @return amount of conceded goals
	 * 
	 * @since 2019-05-24
	 * @author Lara Aula
	 */
	public static int getGoalsConcededForTeam(List<Game> gamesToCountFrom, int teamID) {
		int totalHomeConceded;
		totalHomeConceded = gamesToCountFrom.stream().filter(game -> game.getHomeTeam() == teamID)
				.mapToInt(game -> game.getAwayGoals()).sum();

		int totalAwayConceded = gamesToCountFrom.stream().filter(game -> game.getAwayTeam() == teamID)
				.mapToInt(game -> game.getHomeGoals()).sum();

		return totalHomeConceded + totalAwayConceded;
	}

	/**
	 * Method that returns all goals scored for a team in all the games given in the
	 * constructor.
	 * 
	 * @param gamesToCountFrom the list of games to count from
	 * @param teamID           id of the team to get conceded goals for
	 * @return amount of scored goals
	 * 
	 * @since 2019-05-24
	 * @author Lara Aula
	 */
	public static int getGoalsScoredForTeam(List<Game> gamesToCountFrom, int teamID) {
		int totalHomeScored = gamesToCountFrom.stream().filter(game -> game.getHomeTeam() == teamID)
				.mapToInt(game -> game.getHomeGoals()).sum();

		int totalAwayScored = gamesToCountFrom.stream().filter(game -> game.getAwayTeam() == teamID)
				.mapToInt(game -> game.getAwayGoals()).sum();

		return totalHomeScored + totalAwayScored;
	}

	/**
	 * This method returns a list containing the result of the list of games for a
	 * team.The values below show what will be returned when you use them as a
	 * parameter for the {@link ArrayList#get(int)} method on the list that will be
	 * returned.
	 * <li><b>{@link #WINS}: </b>Games Won</li>
	 * <li><b>{@link #LOSSES}: </b>Games Lost</li>
	 * <li><b>{@link #DRAWS}: </b>Games Drawed</li>
	 * <li><b>{@link #POINTS}: </b>Points</li>
	 *
	 * @param gamesToCountFor the list of games to calculate results for
	 * @return a list containing all results
	 * 
	 * @since 2019-05-24
	 * @author Lara Aula
	 */
	public static List<Integer> gameResultsForTeam(List<Game> gamesToCountFor, int teamID) {
		int gamesWon;
		int gamesLost;
		int gamesDraw;
		int points = 0;

		gamesWon = gamesToCountFor.stream()
				.filter(game -> ((game.getHomeTeam() == teamID) && (game.getHomeGoals() > game.getAwayGoals()))
						|| ((game.getAwayTeam() == teamID) && (game.getAwayGoals() > game.getHomeGoals())))
				.collect(Collectors.toList()).size();

		points = gamesWon * 3;

		gamesLost = gamesToCountFor.stream()
				.filter(game -> ((game.getHomeTeam() == teamID) && (game.getHomeGoals() < game.getAwayGoals()))
						|| ((game.getAwayTeam() == teamID) && (game.getAwayGoals() < game.getHomeGoals())))
				.collect(Collectors.toList()).size();

		gamesDraw = gamesToCountFor.stream()
				.filter(game -> ((game.getHomeTeam() == teamID) && (game.getHomeGoals() == game.getAwayGoals()))
						|| ((game.getAwayTeam() == teamID) && (game.getAwayGoals() == game.getHomeGoals())))
				.collect(Collectors.toList()).size();

		points += gamesDraw;

		ArrayList<Integer> resultList = new ArrayList<>();
		resultList.add(gamesWon);
		resultList.add(gamesLost);
		resultList.add(gamesDraw);
		resultList.add(points);
		return resultList;
	}
}
