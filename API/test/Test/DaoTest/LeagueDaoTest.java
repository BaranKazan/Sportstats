package DaoTest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import Sportstats.Dao.LeagueDao;
import Sportstats.Domain.League;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import Sportstats.ConnectionManager.ConnectionManager;
import junit.framework.TestCase;

/*
 * 
 * 
 */

public class LeagueDaoTest extends TestCase {
	
	ConnectionManager connectionManager;
	
	public LeagueDaoTest() {
		connectionManager = mock(ConnectionManager.class);
	}

	@Test
	public void testGet() throws SQLException {
		int id = 0;
        String team = "Fotboll";
        int sportId = 0;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(id);
        when(rs.getString(2)).thenReturn(team);
        when(rs.getInt(3)).thenReturn(sportId);
        when(connectionManager.executeQuery("SELECT id, name, sport_id FROM League WHERE id=" + id)).thenReturn(rs);
        LeagueDao inst = new LeagueDao(connectionManager);
        League sports = new League(id, team, sportId);
        League result = inst.get(id);
        assertEquals(sports.getName(), result.getName());
	}
	
	@Test
	public void testSave() throws SQLException {
		LeagueDao leagueDao = new LeagueDao(connectionManager);
		League l = new League(1, "", 2);
		boolean result = true;
		PreparedStatement preparedStatement = mock(PreparedStatement.class);
		when(connectionManager.prepareStatement("INSERT INTO League(name, sport_id)" + "VALUES(?,?)")).thenReturn(preparedStatement);
		
		boolean res;
		res = leagueDao.save(l);
		assertEquals(result, res);
	}
	
	@Test
	public void testDelete() throws SQLException {
		League l = new League(1, "", 2);
		int id = 0;
		LeagueDao leagueDao = new LeagueDao(connectionManager);
		l.setId(1);
		l.setSportId(2);
		PreparedStatement preparedStatement = mock(PreparedStatement.class);
		
		when(connectionManager.prepareStatement("DELETE * FROM League WHERE id = " + id + ";")).thenReturn(preparedStatement);
		leagueDao.delete(id);
		verify(preparedStatement, times(1)).executeUpdate();	
		
	}
	
	@Test
	public void testUpdate() throws SQLException {
		League l = new League(1, "", 2);
		LeagueDao leagueDao = new LeagueDao(connectionManager);
		l.setId(1);
		l.setName("");
		l.setSportId(2);
		
		PreparedStatement preparedStatement = mock(PreparedStatement.class);
		
		when(connectionManager.prepareStatement("UPDATE League" + "SET id = ?, name = ?, sportId=?" + "WHERE id = ?")).thenReturn(preparedStatement);
		
		leagueDao.update(l);
		verify(preparedStatement, times(1)).executeUpdate();
	}	
}