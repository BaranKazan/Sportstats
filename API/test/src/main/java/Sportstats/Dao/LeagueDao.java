package Sportstats.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.Domain.League;
import Sportstats.ConnectionManager.ConnectionManager;

/**
 * This Java class implements the {@link DaoInterface} and applies the CRUD
 * operations to the {@link League} domain.
 * 
 * @author Peshang Suleiman
 * @version 2019-05-27
 */
public class LeagueDao implements DaoInterface<League> {

	ConnectionManager connectionManager = null;

	/**
	 * Constructs the service with a possibility to inject a connection as a
	 * dependency. This can be used for unit testing.
	 *
	 * @param connection the database connection to inject
	 */
	public LeagueDao(ConnectionManager connectionManager2) {
		connectionManager = connectionManager2;
	}

	/**
	 * Constructs the data access object that saves that saves the requirement
	 * attribute to communicate server.
	 */
	public LeagueDao() {
		connectionManager = ConnectionManager.getInstance();
	}

	@Override
	public boolean save(League t) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connectionManager
					.prepareStatement("INSERT INTO League(name, sport_id)" + "VALUES(?,?)");
			preparedStatement.setString(1, t.getName());
			preparedStatement.setInt(2, t.getSportId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public League get(int id) throws NoSuchElementException {
		League league = null;

		try {
			ResultSet resultSet = connectionManager
					.executeQuery("SELECT id, name, sport_id FROM League WHERE id=" + id);

			if (!resultSet.next())
				throw new NoSuchElementException("No league with the id " + id + " exists.");

			league = new League(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
			connectionManager.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return league;
	}

	@Override
	public List<League> getAll() throws NoSuchElementException {
		ArrayList<League> listAllLeagues = new ArrayList<League>();
		try {
			ResultSet resultSet = connectionManager.executeQuery("SELECT id, name, sport_id FROM League");

			if (!resultSet.next())
				throw new IllegalArgumentException("There are no leagues in the database");

			resultSet.beforeFirst();

			while (resultSet.next())
				listAllLeagues.add(new League(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));

			connectionManager.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listAllLeagues;
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
			preparedStatement = connectionManager.prepareStatement("DELETE FROM League WHERE id = " + id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(League t) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connectionManager
					.prepareStatement("UPDATE League SET name = ?, sportId=?" + "WHERE id =" + t.getId());
			preparedStatement.setString(1, t.getName());
			preparedStatement.setInt(2, t.getSportId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}