package Sportstats.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.ConnectionManager.ConnectionManager;
import Sportstats.Domain.Season;

/**
 * This Java class implements the {@link DaoInterface} and applies the CRUD
 * operations to the {@link Season} domain.
 * 
 * @author Baran Kazan
 * @version 2019-04-29
 */

public class SeasonDao implements DaoInterface<Season> {

	private ConnectionManager connection;

	/**
	 * Constructor that saves the requirement attribute to communicate server.
	 */
	public SeasonDao() {
		this(ConnectionManager.getInstance());
	}

	/**
	 * Constructs the service with a possibility to inject a connection as a
	 * dependency. This can be used for unit testing.
	 *
	 * @param connection the database connection to inject
	 */
	public SeasonDao(ConnectionManager connection) {
		this.connection = connection;
	}

	@Override
	public Season get(int id) throws NoSuchElementException {
		Season season = null;

		try {
			ResultSet resultSet = connection.executeQuery(
					"SELECT id, league_id, start_year, end_year, maximum_rounds " + "FROM Season WHERE id=" + id);

			if (!resultSet.next())
				throw new NoSuchElementException("The season with id " + id + " doesen't exist in database");

			season = new Season(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4),
					resultSet.getInt(5));
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return season;
	}

	@Override
	public List<Season> getAll() throws NoSuchElementException {
		List<Season> list = new ArrayList<Season>();

		try {
			ResultSet resultSet = connection
					.executeQuery("SELECT id, league_id, start_year, end_year, maximum_rounds " + "FROM Season");

			if (!resultSet.next())
				throw new NoSuchElementException("The are no seasons in the database");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				list.add(new Season(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4),
						resultSet.getInt(5)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean save(Season t) {
		boolean success = false;
		PreparedStatement preparedStatement;

		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO Season (league_id, start_year, end_year, maximum_rounds) " + "VALUES (?, ?, ?, ?)");
			preparedStatement.setInt(1, t.getLeagueId());
			preparedStatement.setInt(2, t.getStartYear());
			preparedStatement.setInt(3, t.getEndYear());
			preparedStatement.setInt(4, t.getMaximumRounds());
			preparedStatement.executeUpdate();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public void update(Season t) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection
					.prepareStatement("UPDATE Season SET league_id = ?, start_year= ?, end_year= ?, maximum_rounds = ?"
							+ " WHERE id =" + t.getId());
			preparedStatement.setInt(1, t.getLeagueId());
			preparedStatement.setInt(2, t.getStartYear());
			preparedStatement.setInt(3, t.getEndYear());
			preparedStatement.setInt(4, t.getMaximumRounds());
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
			preparedStatement = connection.prepareStatement("DELETE FROM Season WHERE id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * method that returns the date of the season
	 * 
	 * @param id the id of the season
	 * @return the date of the season
	 * @throws NoSuchElementException if no season with the given id exists
	 */
	public Season getSeasonDate(int seasonID) throws NoSuchElementException {
		Season season = null;
		try {
			ResultSet resultSet = connection
					.executeQuery("SELECT start_year, end_year FROM Season " + "WHERE id=" + seasonID);

			if (!resultSet.next())
				throw new NoSuchElementException("The season with id " + seasonID + " doesen't exist in database");

			season = new Season(resultSet.getInt(1), resultSet.getInt(2));
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return season;
	}

	/**
	 * Gets all seasons for a specific league.
	 * 
	 * @param leagueId the id of the league to get all seasons for
	 * @return list of seasons for a league
	 * @throws NoSuchElementException if no league with the given id exists
	 */
	public ArrayList<Season> getAllSeasonsForLeague(int leagueId) {
		ResultSet resultSet;
		ArrayList<Season> seasonsForLeague = new ArrayList<Season>();

		try {
			resultSet = connection.executeQuery("SELECT id, league_id, start_year, end_year, maximum_rounds"
					+ " FROM Season WHERE league_id=" + leagueId);

			if (!resultSet.next())
				throw new NoSuchElementException("The are no seasons for this league in the database");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				seasonsForLeague.add(new Season(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
						resultSet.getInt(4), resultSet.getInt(5)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return seasonsForLeague;
	}
}