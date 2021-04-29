package Sportstats.Dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.ConnectionManager.ConnectionManager;
import Sportstats.Domain.Game;
import Sportstats.Helper.DateTranslator;

/**
 * This Java class implements the {@link DaoInterface} and applies the CRUD
 * operations to the {@link Game} domain.
 *
 * @author Baran Kazan
 * @version 2019-05-27
 */
public class GameDao implements DaoInterface<Game> {

	ConnectionManager connection = null;

	/**
	 * Constructor that saves the required attribute to communicate server.
	 */
	public GameDao() {
		this(ConnectionManager.getInstance());
	}

	/**
	 * Another constructor that can be injected with dependency for testing.
	 *
	 * @param connection the database connection to inject
	 */
	public GameDao(ConnectionManager connection) {
		this.connection = connection;
	}

	@Override
	public Game get(int id) throws NoSuchElementException {
		Game game = null;

		try {
			ResultSet resultSet = connection.executeQuery("SELECT id, date, arena_id, home_team, away_team,"
					+ " round_id, home_team_goals, away_team_goals FROM Game WHERE id=" + id);

			if (!resultSet.next())
				throw new NoSuchElementException("The game with id " + id + " doesen't exist in database");

			game = new Game(resultSet.getInt(1), DateTranslator.dateConvertToLocalDate(resultSet.getDate(2)),
					resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
					resultSet.getInt(7), resultSet.getInt(8));
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return game;
	}

	@Override
	public List<Game> getAll() {
		List<Game> list = new ArrayList<Game>();

		try {
			ResultSet resultSet = connection.executeQuery(
					"SELECT id, date, arena_id, home_team, away_team, round_id, home_team_goals, away_team_goals FROM Game");

			if (!resultSet.next())
				throw new NoSuchElementException("There are no games in the databse");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				list.add(new Game(resultSet.getInt(1), DateTranslator.dateConvertToLocalDate(resultSet.getDate(2)),
						resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
						resultSet.getInt(7), resultSet.getInt(8)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean save(Game t) {
		boolean success = false;
		PreparedStatement preparedStatement;

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO Game (date, arena_id,"
					+ " home_team, away_team, round_id, home_team_goals, away_team_goals) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setDate(1, DateTranslator.localDateConvertToDate(t.getDateOfThisGame()));
			preparedStatement.setInt(2, t.getGameArena());
			preparedStatement.setInt(3, t.getHomeTeam());
			preparedStatement.setInt(4, t.getAwayTeam());
			preparedStatement.setInt(5, t.getRoundId());
			preparedStatement.setInt(6, t.getHomeGoals());
			preparedStatement.setInt(7, t.getAwayGoals());
			preparedStatement.executeUpdate();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public void update(Game t) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE Game SET date = ?, arena_id = ?, home_team= ?, away_team= ?, round_id= ?, home_team_goals, away_team_goals"
							+ " WHERE id =" + t.getId());
			preparedStatement.setDate(1, DateTranslator.localDateConvertToDate(t.getDateOfThisGame()));
			preparedStatement.setInt(2, t.getGameArena());
			preparedStatement.setInt(3, t.getHomeTeam());
			preparedStatement.setInt(4, t.getAwayTeam());
			preparedStatement.setInt(5, t.getRoundId());
			preparedStatement.setInt(6, t.getHomeGoals());
			preparedStatement.setInt(7, t.getAwayGoals());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement preparedStatement = null;

		try {
			get(id);
		} catch (NoSuchElementException e) {
			throw e;
		}

		try {
			preparedStatement = connection.prepareStatement("DELETE FROM Game WHERE id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a result to the game. The result added is home goals and away goals.
	 *
	 * @param game the game object to update the result for
	 */
	public void addResult(Game game) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE Game SET home_team_goals = ?, away_team_goals = ? " + " WHERE id =" + game.getId());
			preparedStatement.setInt(1, game.getHomeGoals());
			preparedStatement.setInt(2, game.getAwayGoals());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns all games for a specific round.
	 * 
	 * @param roundId the id of the round to get all games for
	 * @return list of the games in the given round
	 */
	public List<Game> getGamesByRound(int roundId) {
		List<Game> list = new ArrayList<Game>();

		try {
			ResultSet resultSet = connection.executeQuery("SELECT id, date, arena_id, home_team, away_team, round_id, "
					+ "home_team_goals, away_team_goals FROM Game WHERE round_id =" + roundId);

			resultSet.beforeFirst();

			while (resultSet.next()) {
				list.add(new Game(resultSet.getInt(1), DateTranslator.dateConvertToLocalDate(resultSet.getDate(2)),
						resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
						resultSet.getInt(7), resultSet.getInt(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Gets all home games played by a team.
	 * 
	 * @param homeTeamId The id of the team for which to find home games
	 * @throws NoSuchElementException if the team with the given id doesn't exist in
	 *                                the database
	 * @return returns a {@link ArrayList} of home games
	 */
	public List<Game> listAllHomeGamesForTeam(int homeTeamId) {
		List<Game> listAllHomeMatches = new ArrayList<Game>();

		try {
			ResultSet resultSet = connection.executeQuery("SELECT id, date, arena_id, home_team, away_team, round_id,"
					+ " home_team_goals, away_team_goals FROM Game WHERE home_team = " + homeTeamId);

			if (!resultSet.next())
				throw new NoSuchElementException(
						"There are no home games with the id " + homeTeamId + " in the database");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				listAllHomeMatches
						.add(new Game(resultSet.getInt(1), DateTranslator.dateConvertToLocalDate(resultSet.getDate(2)),
								resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
								resultSet.getInt(7), resultSet.getInt(8)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listAllHomeMatches;

	}

	/**
	 * Gets all away games played by a team.
	 * 
	 * @param awayTeamId The id of the team for which to find away games
	 * @throws NoSuchElementException if no team with the given id exits in the
	 *                                database
	 * @return returns a {@link ArrayList} of away games
	 */
	public ArrayList<Game> listAllAwayGamesForTeam(int awayTeamId) {
		ArrayList<Game> list = new ArrayList<Game>();

		try {
			ResultSet resultSet = connection.executeQuery("SELECT id, date, arena_id, home_team, away_team, round_id,"
					+ " home_team_goals, away_team_goals FROM Game WHERE away_team = " + awayTeamId);

			if (!resultSet.next()) {
				throw new NoSuchElementException(
						"There are no away games for the team " + awayTeamId + " in the database");
			}

			resultSet.beforeFirst();

			while (resultSet.next()) {

				list.add(new Game(resultSet.getInt(1), DateTranslator.dateConvertToLocalDate(resultSet.getDate(2)),
						resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
						resultSet.getInt(7), resultSet.getInt(8)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Returns all games for the given date.
	 * 
	 * @param dateIn the date to list all games for
	 * @return a list of all games in the given date
	 */
	public List<Game> listAllGamesByDate(String dateIn) {
		List<Game> list = new ArrayList<Game>();

		try {
			ResultSet resultSet = connection.executeQuery("SELECT id, date, arena_id, home_team, away_team, round_id,"
					+ " home_team_goals, away_team_goals FROM Game WHERE date =\"" + dateIn + "\"");

			if (!resultSet.next())
				throw new NoSuchElementException("There are no games played on " + dateIn + " in the database");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				list.add(new Game(resultSet.getInt(1), DateTranslator.dateConvertToLocalDate(resultSet.getDate(2)),
						resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
						resultSet.getInt(7), resultSet.getInt(8)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Returns all games between 2 dates.
	 * 
	 * @param startDate the date to search from
	 * @param endDate   the date to search to
	 * @return a list of all games in the desired date interval
	 * @since 2019-05-23
	 * @author Lara Aula
	 */
	public List<Game> getAllGamesInDateInterval(String startDate, String endDate) {
		List<Game> list = new ArrayList<Game>();

		try {
			ResultSet resultSet = connection.executeQuery("SELECT id, date, arena_id, home_team, away_team, round_id,"
					+ " home_team_goals, away_team_goals FROM Game WHERE date between\"" + startDate + "\" and \""
					+ endDate + "\"");

			while (resultSet.next()) {
				list.add(new Game(resultSet.getInt(1), DateTranslator.dateConvertToLocalDate(resultSet.getDate(2)),
						resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
						resultSet.getInt(7), resultSet.getInt(8)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * This method returns a list containing all games a team has won.
	 *
	 * @param teamID the id of the team to list all won games for
	 * @return the list of all won games by the team
	 */
	public List<Game> listAllGamesWonByTeam(int teamID) {
		List<Game> listOfAllGamesWonByTeam = new ArrayList<Game>();

		try {
			ResultSet resultSet = connection.executeQuery(
					" SELECT DISTINCT Game.id, date, arena_id, home_team, away_team, round_id, home_team_goals, away_team_goals FROM Game, Metainformation "
							+ " WHERE Metainformation.game_id = Game.id AND ((away_team = " + teamID
							+ " and winner = 1) or (home_team = " + teamID + " and winner = 0))");

			if (!resultSet.next())
				throw new NoSuchElementException(
						"There are no games won be the team with id " + teamID + " in the database");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				listOfAllGamesWonByTeam
						.add(new Game(resultSet.getInt(1), DateTranslator.dateConvertToLocalDate(resultSet.getDate(2)),
								resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
								resultSet.getInt(7), resultSet.getInt(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfAllGamesWonByTeam;
	}

	/**
	 * * This method returns a list containing all games a team has lost.
	 *
	 * @param teamID the id of the team to list all lost games for
	 * @return the list of all lost games by the team
	 */
	public List<Game> listAllGamesLostByTeam(int teamID) {
		List<Game> listOfAllGamesWonByTeam = new ArrayList<Game>();

		try {
			ResultSet resultSet = connection.executeQuery(
					" SELECT DISTINCT Game.id, date, arena_id, home_team, away_team, round_id, home_team_goals, away_team_goals FROM Game, Metainformation "
							+ " WHERE Metainformation.game_id = Game.id AND ((away_team = " + teamID
							+ " and winner = 0) or (home_team = " + teamID + " and winner = 1))");

			if (!resultSet.next())
				throw new NoSuchElementException(
						"There are no games lost by the team with id " + teamID + " in the database");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				listOfAllGamesWonByTeam
						.add(new Game(resultSet.getInt(1), DateTranslator.dateConvertToLocalDate(resultSet.getDate(2)),
								resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
								resultSet.getInt(7), resultSet.getInt(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfAllGamesWonByTeam;
	}

	/**
	 * This method returns a list containing all games a team tied in.
	 *
	 * @param teamID the id of the team to list all tied games for
	 * @return the list of all tied games by the team
	 */
	public List<Game> listAllGamesTiedByTeam(int teamID) {
		List<Game> listOfAllGamesWonByTeam = new ArrayList<Game>();

		try {
			ResultSet resultSet = connection.executeQuery(
					" SELECT DISTINCT Game.id, date, arena_id, home_team, away_team, round_id, home_team_goals, away_team_goals FROM Game, Metainformation "
							+ " WHERE Metainformation.game_id = Game.id AND ((away_team = " + teamID
							+ " and winner = 2) or (home_team = " + teamID + " and winner = 2))");

			if (!resultSet.next())
				throw new NoSuchElementException(
						"There are no games tied by the team with id " + teamID + " in the database");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				listOfAllGamesWonByTeam
						.add(new Game(resultSet.getInt(1), DateTranslator.dateConvertToLocalDate(resultSet.getDate(2)),
								resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
								resultSet.getInt(7), resultSet.getInt(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfAllGamesWonByTeam;
	}

	/**
	 * Returns the total amount of wins in a season by a team.
	 *
	 * @param teamID   id of the team to count for
	 * @param seasonID id of the season to count in
	 * @return the amount of wins
	 *
	 * @author Lara Aula
	 * @version 2019-05-13
	 */
	public int countAllWinsByTeamInASeason(int teamID, int seasonID) {
		ResultSet resultSet = null;
		int allWins = 0;

		try {
			resultSet = connection.executeQuery("SELECT COUNT(Game.id) " + "FROM Game, Metainformation, Round "
					+ "WHERE Metainformation.game_id = Game.id AND ((away_team = " + teamID
					+ " and winner = 1) or (home_team = " + teamID + " and winner = 0))"
					+ "AND Round.id = Game.round_id AND Round.season_id =" + seasonID);

			if (resultSet.next())
				allWins = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allWins;
	}

	/**
	 * Returns the total amount of losses in a season by a team.
	 *
	 * @param teamID   id of the team to count for
	 * @param seasonID id of the season to count in
	 * @return the amount of losses
	 *
	 * @author Lara Aula
	 * @version 2019-05-13
	 */
	public int countAllLossesByTeamInASeason(int teamID, int seasonID) {
		ResultSet resultSet = null;
		int allLosses = 0;

		try {
			resultSet = connection.executeQuery("SELECT COUNT(Game.id) " + "FROM Game, Metainformation, Round "
					+ "WHERE Metainformation.game_id = Game.id AND ((away_team = " + teamID
					+ " and winner = 0) or (home_team = " + teamID + " and winner = 1))"
					+ "AND Round.id = Game.round_id AND Round.season_id =" + seasonID);

			if (resultSet.next())
				allLosses = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allLosses;
	}

	/**
	 * Returns the total amount of ties in a season by a team.
	 *
	 * @param teamID   id of the team to count for
	 * @param seasonID id of the season to count in
	 * @return the amount of ties
	 *
	 * @author Lara Aula
	 * @version 2019-05-13
	 */
	public int countAllTiesByTeamInASeason(int teamID, int seasonID) {
		ResultSet resultSet = null;
		int allTies = 0;

		try {
			resultSet = connection.executeQuery("SELECT COUNT(Game.id) " + "FROM Game, Metainformation, Round "
					+ "WHERE Metainformation.game_id = Game.id AND ((away_team = " + teamID
					+ " and winner = 2) or (home_team = " + teamID + " and winner = 2))"
					+ "AND Round.id = Game.round_id AND Round.season_id =" + seasonID);

			if (resultSet.next())
				allTies = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allTies;
	}

	/**
	 * Gets the games where two teams have faced each other in a season and the
	 * results of those games
	 *
	 * @param teamOneId id of the first team
	 * @param teamTwoId id of the second team
	 * @return a list of the teams and the result
	 */

	public List<Game> getGamesBetweenTwoTeams(int teamOneId, int teamTwoId) {
		List<Game> list = new ArrayList<>();

		try {
			ResultSet resultSet = connection
					.executeQuery("SELECT id, home_team, away_team, home_team_goals, away_team_goals FROM Game "
							+ "WHERE (away_team =" + teamTwoId + " AND home_team =" + teamOneId + ") OR (away_team ="
							+ teamOneId + " AND home_team =" + teamTwoId + ")");

			while (resultSet.next()) {
				list.add(new Game(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4),
						resultSet.getInt(5)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * A method that returns a game object with the set id
	 *
	 * @param id of a game
	 * @return a Game object with the given id
	 */
	public Game getGameResult(int id) {
		Game game = null;

		try {
			ResultSet resultSet = connection
					.executeQuery("SELECT id, home_team, away_team, home_team_goals, away_team_goals FROM Game "
							+ "WHERE id = " + id);

			while (resultSet.next()) {
				game = (new Game(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4),
						resultSet.getInt(5)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return game;
	}

	/**
	 * Gets the amount of games played by a team.
	 *
	 * @param teamId  id of the team
	 * @param roundId id of the round
	 * @return an integer of the total amount played
	 */
	public int getCountGamesForTeam(int teamId, int roundId) {
		List<Game> list = new ArrayList<Game>();

		try {
			ResultSet resultSet = connection.executeQuery("SELECT id, date, arena_id, home_team, away_team, round_id,"
					+ "home_team_goals, away_team_goals FROM Game WHERE round_id = " + roundId + " AND(home_team ="
					+ teamId + " OR away_team =" + teamId + ")");

			while (resultSet.next()) {
				list.add(new Game(resultSet.getInt(1), DateTranslator.dateConvertToLocalDate(resultSet.getDate(2)),
						resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
						resultSet.getInt(7), resultSet.getInt(8)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list.size();
	}

	/**
	 * Gets the home goals scored by a team during a round.
	 *
	 * @param teamId  id of the team
	 * @param roundId id of the round
	 * @return an integer of the total amount scored
	 */
	public int homeGoalsScoredByTeam(int teamId, int roundId) {
		Game game = null;
		int goals = 0;

		try {
			ResultSet resultSet = connection
					.executeQuery("SELECT id, round_id, home_team_goals, away_team_goals FROM Game "
							+ "WHERE round_id =" + roundId + " AND (home_team =" + teamId + ")");

			while (resultSet.next()) {
				game = (new Game(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4)));
				goals += game.getHomeGoals();
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goals;
	}

	/**
	 * Gets the away goals scored by a team during a round
	 *
	 * @param teamId  id of the team
	 * @param roundId id of the round
	 * @return an integer of the total amount scored
	 */
	public int awayGoalsScoredByTeam(int teamId, int roundId) {
		Game game = null;
		int goals = 0;

		try {
			ResultSet resultSet = connection
					.executeQuery("SELECT id, round_id, home_team_goals, away_team_goals FROM Game "
							+ "WHERE round_id = " + roundId + " AND (away_team =" + teamId + ")");

			while (resultSet.next()) {
				game = (new Game(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4)));
				goals += game.getAwayGoals();
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goals;
	}

	/**
	 * Gets the home goals conceded by a team during a round
	 *
	 * @param teamId  id of the team
	 * @param roundId id of the round
	 * @return an integer of the total amount conceded
	 */
	public int homeGoalsConcededByTeam(int teamId, int roundId) {
		Game game = null;
		int goals = 0;

		try {
			ResultSet resultSet = connection
					.executeQuery("SELECT id, round_id, home_team_goals, away_team_goals FROM Game "
							+ "WHERE round_id = " + roundId + " AND (home_team =" + teamId + ")");

			while (resultSet.next()) {
				game = (new Game(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4)));
				goals += game.getAwayGoals();
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goals;
	}

	/**
	 * Gets the away goals conceded by a team during a round
	 *
	 * @param teamId  id of the team
	 * @param roundId id of the round
	 * @return an integer of the total amount conceded
	 */

	public int awayGoalsConcededByTeam(int teamId, int roundId) {
		Game game = null;
		int goals = 0;

		try {
			ResultSet resultSet = connection
					.executeQuery("SELECT id, round_id, home_team_goals, away_team_goals FROM Game "
							+ "WHERE round_id = " + roundId + " AND (away_team =" + teamId + ")");

			while (resultSet.next()) {
				game = (new Game(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4)));
				goals += game.getHomeGoals();
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goals;
	}

	/**
	 * A method that returns a games between two teams by the team ids.
	 * 
	 * @param teamOneId id of the first team
	 * @param teamTwoId id of the second
	 * @return an empty arraylist if no games between the teams have been played,
	 *         else a list containing all the games played between the teams
	 */
	public List<Game> getGamesBetweenTwoTeamsByTeamId(int teamOneId, int teamTwoId) {
		List<Game> list = new ArrayList<Game>();

		try {
			ResultSet resultSet = connection.executeQuery(
					"SELECT id, date, arena_id, home_team, away_team, round_id, home_team_goals, away_team_goals FROM Game "
							+ "WHERE (away_team =" + teamTwoId + " AND home_team =" + teamOneId + ") OR (away_team ="
							+ teamOneId + " AND home_team =" + teamTwoId + ")");

			while (resultSet.next()) {
				list.add(new Game(resultSet.getInt(1), DateTranslator.dateConvertToLocalDate(resultSet.getDate(2)),
						resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6),
						resultSet.getInt(7), resultSet.getInt(8)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}