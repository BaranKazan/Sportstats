package Sportstats.Dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.ConnectionManager.ConnectionManager;
import Sportstats.Domain.Team;

/**
 * This Java class implements the {@link DaoInterface} and applies the CRUD
 * operations to the {@link Team} domain.
 * 
 * @author Hassan Sheikha
 * @version 2019-05-20
 */
public class TeamDao implements DaoInterface<Team> {
	ConnectionManager conMan = null;

	/**
	 * Constructs the data access object that saves that saves the required
	 * attribute to communicate server.
	 */
	public TeamDao() {
		conMan = ConnectionManager.getInstance();
	}

	/**
	 * Constructs the service with a possibility to inject a connection as a
	 * dependency. This can be used for unit testing.
	 *
	 * @param connection the database connection to inject
	 */
	public TeamDao(ConnectionManager conn) {
		this.conMan = conn;
	}

	@Override
	public Team get(int id) {
		Team team = null;

		try {
			ResultSet resultSet = conMan
					.executeQuery("SELECT id, season_id, arena_id, sport_id, name FROM Team WHERE id=" + id);

			if (!resultSet.next())
				throw new NoSuchElementException("The team with id " + id + " doesen't exist in database");

			team = new Team(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4),
					resultSet.getString(5));

			conMan.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return team;
	}

	@Override
	public List<Team> getAll() throws NoSuchElementException {
		ArrayList<Team> teamList = new ArrayList<Team>();
		ResultSet resultSet = null;

		try {
			resultSet = conMan.executeQuery("SELECT id, season_id, arena_id, sport_id, name FROM Team");

			if (!resultSet.next())
				throw new NoSuchElementException("The are no teams in the database");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				teamList.add(new Team(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
						resultSet.getInt(4), resultSet.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teamList;
	}

	@Override
	public boolean save(Team t) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = conMan
					.prepareStatement("INSERT INTO Team (name, season_id, arena_id, sport_id)" + "VALUES(?,?,?,?)");
			preparedStatement.setString(1, t.getName());
			preparedStatement.setInt(2, t.getSeasonId());
			preparedStatement.setInt(3, t.getArenaId());
			preparedStatement.setInt(4, t.getSportId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void update(Team t) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = conMan.prepareStatement(
					"UPDATE Team SET name = ?, season_id = ?, arena_id=?, sport_id=?" + " WHERE id =" + t.getId());
			preparedStatement.setString(1, t.getName());
			preparedStatement.setInt(2, t.getSeasonId());
			preparedStatement.setInt(3, t.getArenaId());
			preparedStatement.setInt(4, t.getSportId());
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
			preparedStatement = conMan.prepareStatement("DELETE FROM Team WHERE id = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that returns the name of a team.
	 * 
	 * @param id the id of a team
	 * @return the name of the team
	 * @throws NoSuchElementException if no team with the given id exists
	 */
	public Team getTeamName(int id) throws NoSuchElementException {
		Team team = null;

		try {
			ResultSet resultSet = conMan.executeQuery("SELECT name FROM Team " + "WHERE id=" + id);

			if (!resultSet.next())
				throw new NoSuchElementException("The team with id " + id + " doesen't exist in database");

			team = new Team(resultSet.getString(1));
			conMan.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return team;
	}

	/**
	 * Method that returns a list of all teams in a season.
	 * 
	 * @param seasonId the id of the season
	 * @return a list of all teams in a season
	 * @throws NoSuchElementException if no season with the given id exists
	 */
	public List<Team> getAllTeamsInASeason(int seasonId) throws NoSuchElementException {
		List<Team> teamList = new ArrayList<Team>();
		ResultSet resultSet = null;

		try {
			resultSet = conMan.executeQuery("SELECT id, season_id, name FROM Team" + " WHERE season_id=" + seasonId);

			if (!resultSet.next())
				throw new NoSuchElementException("The season with id " + seasonId + " doesen't exist in database");

			resultSet.beforeFirst();

			while (resultSet.next()) {
				teamList.add(new Team(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teamList;
	}
}