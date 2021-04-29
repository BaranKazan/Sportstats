package Sportstats.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.ConnectionManager.ConnectionManager;
import Sportstats.Domain.MetaInformation;

/**
 * This Java class implements the {@link DaoInterface} and applies the CRUD
 * operations to the {@link MetaInformation} domain.
 * 
 * @author Hassan Sheikha
 * @version 2019-05-20
 */
public class MetaInformationDao implements DaoInterface<MetaInformation> {

	ConnectionManager connection = null;

	/**
	 * Constructor that saves the required attribute to be able to communicate with
	 * the server.
	 */
	public MetaInformationDao() {
		this(ConnectionManager.getInstance());
	}

	/**
	 * Constructs the service with a possibility to inject a connection as a
	 * dependency. This can be used for unit testing.
	 *
	 * @param connection the database connection to inject
	 */
	public MetaInformationDao(ConnectionManager connection) {
		this.connection = connection;
	}

	@Override
	public MetaInformation get(int id) throws NoSuchElementException {
		MetaInformation metaInfo = null;
		try {
			ResultSet resultSet = connection.executeQuery(
					"SELECT id, game_id, spectators, winner, extraTime, partialHomeGoals, partialAwayGoals FROM Metainformation WHERE id= "
							+ id);

			if (!resultSet.next())
				throw new NoSuchElementException("The metainformation doesen't exist in database");

			metaInfo = new MetaInformation(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
					resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return metaInfo;
	}

	@Override
	public List<MetaInformation> getAll() throws NoSuchElementException {
		List<MetaInformation> list = new ArrayList<MetaInformation>();

		try {
			ResultSet resultSet = connection.executeQuery(
					"SELECT id, game_id, spectators, winner, extraTime, partialHomeGoals, partialAwayGoals FROM Metainformation");

			if (!resultSet.next())
				throw new NoSuchElementException("The is no metainformation");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				list.add(new MetaInformation(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
						resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean save(MetaInformation t) {
		boolean success = false;
		PreparedStatement preparedStatement;

		try {
			preparedStatement = connection.prepareStatement("INSERT INTO Metainformation"

					+ "(game_id, spectators, winner, extraTime, partialHomeGoals, partialAwayGoals) "
					+ "VALUES (?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, t.getGameId());
			preparedStatement.setInt(2, t.getSpectators());
			preparedStatement.setInt(3, t.getWinner());
			preparedStatement.setInt(4, t.getExtraTime());
			preparedStatement.setInt(5, t.getPartialHomeGoals());
			preparedStatement.setInt(6, t.getPartialAwayGoals());
			preparedStatement.executeUpdate();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public void update(MetaInformation t) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE Metainformation SET game_id = ?, spectators = ?, winner = ?, extraTime = ?, partialHomeGoals = ?, partialAwayGoals = ?"
							+ " WHERE id =" + t.getId());
			preparedStatement.setInt(1, t.getGameId());
			preparedStatement.setInt(2, t.getSpectators());
			preparedStatement.setInt(3, t.getWinner());
			preparedStatement.setInt(4, t.getExtraTime());
			preparedStatement.setInt(5, t.getPartialHomeGoals());
			preparedStatement.setInt(6, t.getPartialAwayGoals());
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
			preparedStatement = connection.prepareStatement("DELETE FROM Metainformation WHERE id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that takes a game id and returns a list of meta information about the
	 * game
	 * 
	 * @param gameId the id of a game
	 * @return a list of information for a game with the given id
	 * @throws NoSuchElementException
	 */
	public List<MetaInformation> getInformationByGameId(int gameId) throws NoSuchElementException {
		List<MetaInformation> list = new ArrayList<MetaInformation>();

		try {
			ResultSet resultSet = connection.executeQuery(
					"SELECT game_id, spectators, winner, extraTime, partialHomeGoals, partialAwayGoals FROM Metainformation "
							+ "WHERE game_id = " + gameId);

			if (!resultSet.next())
				throw new NoSuchElementException("There is no metainformation for the game with id " + gameId
						+ ". Either the game hasn't been played or the game doesn't exist.");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				list.add(new MetaInformation(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
						resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6)));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Method that returns the value 1 or 0 depending if the game was played to
	 * extra time or not
	 * 
	 * @param gameId id of a game
	 * @throws NoSuchElementException
	 * @return MetaInformation containing if game was played to extra time or not
	 */
	public MetaInformation playedExtraTime(int gameId) throws NoSuchElementException {
		MetaInformation metaInfo = null;

		try {
			ResultSet resultSet = connection.executeQuery("SELECT extraTime FROM Metainformation WHERE id=" + gameId);

			if (!resultSet.next())
				throw new NoSuchElementException("The game with id " + gameId + " doesen't exist in database");

			metaInfo = new MetaInformation(resultSet.getInt(1));
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return metaInfo;

	}
}