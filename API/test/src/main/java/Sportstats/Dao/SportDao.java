package Sportstats.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.ConnectionManager.ConnectionManager;
import Sportstats.Domain.Sport;

/**
 * This Java class implements the {@link DaoInterface} and applies the CRUD
 * operations to the {@link Sport} domain.
 * 
 * @author Ali Shakeri
 * @version 2019-05-27
 */

public class SportDao implements DaoInterface<Sport> {

	ConnectionManager connectionManager = null;

	/**
	 * Constructor that saves the required attribute to be able to communicate with
	 * the server.
	 */
	public SportDao() {
		connectionManager = ConnectionManager.getInstance();
	}

	/**
	 * Constructs the service with a possibility to inject a connection as a
	 * dependency. This can be used for unit testing.
	 *
	 * @param connection the database connection to inject
	 */
	public SportDao(ConnectionManager ConnectionManager) {
		this.connectionManager = ConnectionManager;
	}

	@Override
	public Sport get(int id) throws NoSuchElementException {
		Sport sport = null;

		try {
			ResultSet resultSet = connectionManager.executeQuery("SELECT id, name FROM Sport WHERE id=" + id);

			if (!resultSet.next())
				throw new NoSuchElementException("The Sport with id " + id + " doesen't exist in database");

			sport = new Sport(resultSet.getInt(1), resultSet.getString(2));
			connectionManager.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sport;
	}

	@Override
	public List<Sport> getAll() throws NoSuchElementException {

		ArrayList<Sport> list = new ArrayList<Sport>();

		try {
			ResultSet resultSet = connectionManager.executeQuery("SELECT id, name FROM Sport");

			if (!resultSet.next())
				throw new NoSuchElementException("The are no sports in the database");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				list.add(new Sport(resultSet.getInt(1), resultSet.getString(2)));
			}
			connectionManager.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean save(Sport t) {
		PreparedStatement preparedStatement = null;
		boolean saveSucess = false;

		try {

			preparedStatement = connectionManager.prepareStatement("INSERT INTO Sport (name) " + "VALUES (?)");
			preparedStatement.setString(1, t.getName());
			preparedStatement.executeUpdate();
			saveSucess = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saveSucess;
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
			preparedStatement = connectionManager.prepareStatement("DELETE FROM Sport WHERE id = " + id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Sport t) {
		PreparedStatement preparedStatement;

		try {
			preparedStatement = connectionManager.prepareStatement("UPDATE Sport SET name = ? WHERE id =" + t.getId());
			preparedStatement.setString(1, t.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}