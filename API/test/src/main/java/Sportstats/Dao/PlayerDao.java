package Sportstats.Dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.ConnectionManager.ConnectionManager;
import Sportstats.Domain.Player;
import Sportstats.Helper.DateTranslator;

/**
 * This Java class implements the {@link DaoInterface} and applies the CRUD
 * operations to the {@link Player} domain.
 * 
 * @author Ali Shakeri
 * @version 2019-05-27
 */

public class PlayerDao implements DaoInterface<Player> {

	ConnectionManager connectionManager = null;

	/**
	 * Constructs the data access object that saves that saves the requirement
	 * attribute to communicate server.
	 */
	public PlayerDao() {
		connectionManager = ConnectionManager.getInstance();
	}

	/**
	 * Constructs the service with a possibility to inject a connection as a
	 * dependency. This can be used for unit testing.
	 *
	 * @param connection the database connection to inject
	 */
	public PlayerDao(ConnectionManager ConnectionManager) {
		this.connectionManager = ConnectionManager;
	}

	@Override
	public Player get(int id) throws NoSuchElementException {
		Player player = null;

		try {
			ResultSet resultSet = connectionManager
					.executeQuery("SELECT id, name, position, birth_date, team_id " + "FROM Player WHERE id = " + id);

			if (!resultSet.next())
				throw new NoSuchElementException("The player with id " + id + " doesen't exist in the database");

			player = new Player(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					DateTranslator.dateConvertToLocalDate(resultSet.getDate(4)), resultSet.getInt(5));

			connectionManager.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return player;
	}

	@Override
	public List<Player> getAll() throws NoSuchElementException {
		ArrayList<Player> list = new ArrayList<Player>();

		try {
			ResultSet resultSet = connectionManager
					.executeQuery("SELECT id, name, position, birth_date, team_id " + "FROM Player");

			if (!resultSet.next())
				throw new NoSuchElementException("The are no players in the database");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				list.add(new Player(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						DateTranslator.dateConvertToLocalDate(resultSet.getDate(4)), resultSet.getInt(5)));
			}
			connectionManager.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean save(Player t) {
		PreparedStatement preparedStatement = null;
		boolean saveSuccess = false;

		try {
			preparedStatement = connectionManager.prepareStatement(
					"INSERT INTO Player (name, position, birth_date, team_id) " + "VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, t.getName());
			preparedStatement.setString(2, t.getPosition());
			preparedStatement.setDate(3, DateTranslator.localDateConvertToDate(t.getBirthDate()));
			preparedStatement.setInt(4, t.getTeamId());
			preparedStatement.executeUpdate();
			saveSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saveSuccess;
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
			preparedStatement = connectionManager.prepareStatement("DELETE FROM Player WHERE id = " + id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Player t) {
		PreparedStatement preparedStatement;

		try {
			preparedStatement = connectionManager.prepareStatement(
					"UPDATE Player SET name = ?, position = ?, birth_date = ?, team_id = ?" + "WHERE id =" + t.getId());
			preparedStatement.setString(1, t.getName());
			preparedStatement.setString(2, t.getPosition());
			preparedStatement.setDate(3, DateTranslator.localDateConvertToDate(t.getBirthDate()));
			preparedStatement.setInt(4, t.getTeamId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}