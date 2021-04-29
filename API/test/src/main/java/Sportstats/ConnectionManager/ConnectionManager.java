package Sportstats.ConnectionManager;

import java.sql.*;

/**
 * Java class that uses a driver to connect to the database.
 * 
 * @author Åke Wallin
 */
public class ConnectionManager {
	private static final String USER = "ofk17hsa";
	private static final String PASSWORD = "ofk17hsa";
	private static final String CONNECTION_URL = "jdbc:mysql://node77222-sql-prgvteknik-19.jls-sto1.elastx.net:11057/ofk17hsa?useSSL=false";
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	private static ConnectionManager instance = null;

	private ConnectionManager() {
	}

	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	private Connection getConnection() {
		try {
			connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		return connection;
	}

	private Statement getStatement(Connection connection) {
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.err.println("Could not resolve statement");
			System.err.println(e.getMessage());
		}
		return statement;
	}

	public ResultSet executeQuery(String sqlString) throws SQLException {
		resultSet = this.getStatement(this.getConnection()).executeQuery(sqlString);

		return resultSet;
	}

	public PreparedStatement prepareStatement(String statementString) throws SQLException {
		return this.getConnection().prepareStatement(statementString);
	}

	public void close() {
		try {
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
			// System.out.println("Connection closed.");

		} catch (SQLException e) {
			System.err.println("Could not close statement or  the connection");
			System.err.println(e.getMessage());
		}
	}
}