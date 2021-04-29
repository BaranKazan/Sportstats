package Sportstats.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.ConnectionManager.ConnectionManager;
import Sportstats.Domain.Arena;

/**
 * This Java class implements the {@link DaoInterface} and applies the CRUD
 * operations to the {@link Arena} domain.
 *
 * @author Baran Kazan
 * @version 2019-05-27
 */
public class ArenaDao implements DaoInterface<Arena> {

	private ConnectionManager connection;

	/**
	 * Constructs the data access object that saves that saves the requirement
	 * attribute to communicate server.
	 */
	public ArenaDao() {
		this(ConnectionManager.getInstance());
	}

	/**
	 * Constructs the service with a possibility to inject a connection as a
	 * dependency. This can be used for unit testing.
	 *
	 * @param connection the database connection to inject
	 */
	public ArenaDao(ConnectionManager connection) {
		this.connection = connection;
	}

	@Override
	public Arena get(int id) {
		Arena arena = null;

		try {
			ResultSet resultSet = connection.executeQuery("SELECT id, name, capacity FROM Arena WHERE id=" + id);

			if (!resultSet.next())
				throw new NoSuchElementException("The arena with id " + id + " doesen't exist in database");

			arena = new Arena(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arena;
	}

	@Override
	public List<Arena> getAll() {
		List<Arena> list = new ArrayList<Arena>();

		try {
			ResultSet resultSet = connection.executeQuery("SELECT id, name, capacity FROM Arena");

			if (!resultSet.next())
				throw new NoSuchElementException("There are no arenas in the databse");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				list.add(new Arena(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean save(Arena t) {
		boolean success = false;
		PreparedStatement preparedStatement;

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO Arena (name, capacity) VALUES (?, ?)");
			preparedStatement.setString(1, t.getName());
			preparedStatement.setInt(2, t.getCapacity());
			preparedStatement.executeUpdate();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public void update(Arena t) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection
					.prepareStatement("UPDATE Arena SET name = ?, capacity = ? WHERE id =" + t.getId());
			preparedStatement.setString(1, t.getName());
			preparedStatement.setInt(2, t.getCapacity());
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
			preparedStatement = connection.prepareStatement("DELETE FROM Arena WHERE id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}