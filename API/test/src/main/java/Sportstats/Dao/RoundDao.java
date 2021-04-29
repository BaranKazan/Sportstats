package Sportstats.Dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.ConnectionManager.ConnectionManager;
import Sportstats.Domain.Round;
import Sportstats.Exception.InvalidInputException;

/**
 * This Java class implements the {@link DaoInterface} and applies the CRUD
 * operations to the {@link Round} domain.
 * 
 * @author Lara Aula
 * @version 2019-04-16
 */
public class RoundDao implements DaoInterface<Round> {

	ConnectionManager dbSingleton = null;

	/**
	 * Constructs the data access object that saves that saves the requirement
	 * attribute to communicate server.
	 */
	public RoundDao() {
		this(ConnectionManager.getInstance());
	}

	/**
	 * Constructs the service with a possibility to inject a connection as a
	 * dependency. This can be used for unit testing.
	 *
	 * @param connection the database connection to inject
	 */
	public RoundDao(ConnectionManager ConnectionManager) {
		this.dbSingleton = ConnectionManager;
	}

	@Override
	public Round get(int id) throws NoSuchElementException {
		Round round = null;
		ResultSet rs = null;

		try {
			rs = dbSingleton.executeQuery("SELECT id, round_number, season_id " + "FROM Round WHERE id = " + id);

			if (!rs.next())
				throw new NoSuchElementException("The round with id " + id + " doesn't exist in database");

			round = new Round(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			dbSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return round;
	}

	@Override
	public List<Round> getAll() throws NoSuchElementException {
		List<Round> allRounds = new ArrayList<Round>();
		ResultSet resultSet = null;

		try {
			resultSet = dbSingleton.executeQuery("SELECT id, round_number, season_id FROM Round");

			if (!resultSet.next())
				throw new NoSuchElementException("The are no rounds in the season");

			resultSet.beforeFirst();

			while (resultSet.next())
				allRounds.add(new Round(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)));
			dbSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allRounds;
	}

	@Override
	public boolean save(Round t) {
		PreparedStatement ps = null;
		boolean saveSuccess = false;

		if (t.getRoundNumber() < 0)
			throw new InvalidInputException("Round number is invalid");

		try {
			ps = dbSingleton.prepareStatement("INSERT INTO Round(round_number, season_id) " + "VALUES(?,?)");
			ps.setInt(1, t.getRoundNumber());
			ps.setInt(2, t.getSeasonID());
			ps.executeUpdate();
			saveSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return saveSuccess;
	}

	@Override
	public void update(Round t) {

		PreparedStatement ps = null;

		try {
			ps = dbSingleton
					.prepareStatement("UPDATE Round SET round_number = ?, season_id = ? WHERE id =" + t.getId());
			ps.setInt(1, t.getRoundNumber());
			ps.setInt(2, t.getSeasonID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement preparedStatement;

		try {
			get(id);
		} catch (NoSuchElementException e) {
			throw e;
		}

		try {
			preparedStatement = dbSingleton.prepareStatement("DELETE FROM Round " + "WHERE id = " + id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Counts all rounds in a season.
	 * 
	 * @param seasonId the id of the season to count in
	 * @return amount of rounds in the season
	 * @throws NoSuchElementException if no season with the given id exists
	 */
	public int countRoundsInASeason(int seasonId) throws NoSuchElementException {
		ResultSet resultset = null;
		int amount = 0;

		try {
			resultset = dbSingleton.executeQuery("SELECT COUNT(season_id) FROM Round WHERE season_id = " + seasonId);

			if (!resultset.next())
				throw new NoSuchElementException("The season with id " + seasonId + " doesn't exist in database");

			amount = resultset.getInt(1);
			dbSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return amount;
	}

	/**
	 * Method that returns all rounds in a given season
	 * 
	 * @param seasonId the id of a season
	 * @return a list of rounds in the season
	 * @throws NoSuchElementException if no season with the given id exists
	 */
	public List<Round> listAllRoundsbySeason(int seasonId) throws NoSuchElementException {
		List<Round> lst = new ArrayList<Round>();

		try {
			ResultSet resultSet = dbSingleton.executeQuery("SELECT id FROM Round WHERE season_id = " + seasonId);

			if (!resultSet.next())
				throw new NoSuchElementException("The season with id " + seasonId + " doesn't exist in database");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				lst.add(new Round(resultSet.getInt(1)));
			}
			dbSingleton.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
}
