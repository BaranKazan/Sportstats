package DaoTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Test;

import Sportstats.ConnectionManager.ConnectionManager;
import Sportstats.Dao.PlayerDao;
import Sportstats.Domain.Player;
import Sportstats.Helper.DateTranslator;

public class PlayerDaoTest {
	
	ConnectionManager connection;
	
	public PlayerDaoTest() {
		connection = mock(ConnectionManager.class);
	}

	@Test
	public void testGet() throws SQLException {
		int id = 0;
		@SuppressWarnings("deprecation")
		Date date = new Date(2017, 5, 8);
		String name = "Didier";
		String nameTwo = "Lampard";
        ResultSet rs = mock(ResultSet.class);    
        
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(id);
        when(rs.getString(2)).thenReturn(name);
        when(rs.getString(3)).thenReturn(nameTwo);
        when(rs.getDate(4)).thenReturn(date);
        
        when(connection.executeQuery("SELECT * FROM Player WHERE id=" + id)).thenReturn(rs);
        
        PlayerDao inst = new PlayerDao(connection);
        
        Player player = new Player(name, nameTwo, DateTranslator.dateConvertToLocalDate(date), id);
        Player result = inst.get(id);
        
        assertEquals(result.getId(), player.getId());

        
	}

	@Test
	public void testSave() throws SQLException {
		LocalDate localDate = LocalDate.of(2017, 12, 12);
	
		int roundId = 3;
		PreparedStatement ps = mock(PreparedStatement.class);
        when(connection.prepareStatement("INSERT INTO Player (name, position, birth_date, team_id) " 
				+ "VALUES (?, ?, ?, ?)"))
        	.thenReturn(ps);
        PlayerDao inst = new PlayerDao(connection);
        Player player = new Player(roundId, null, null, localDate, roundId);
        boolean result = true;
        boolean res = inst.save(player);
        assertEquals(result, res);
	}

	@Test
	public void testUpdate() throws SQLException {
		LocalDate localDate = LocalDate.of(2017, 12, 12);
		int roundId = 3;
		Player player = new Player(roundId, null, null, localDate, roundId);
    	PreparedStatement ps = mock(PreparedStatement.class);    
        when(connection.prepareStatement(
        		"UPDATE Player SET id = ?, name = ?, position = ?, birth_date = ?, team_id = ?"
						+ "WHERE id = ?"))
        	.thenReturn(ps);
        PlayerDao inst = new PlayerDao(connection);        
        inst.update(player);
        verify(ps, times(1)).executeUpdate();
	}

	@Test
	public void testDelete() throws SQLException {
		int id = 45;
    	PreparedStatement ps = mock(PreparedStatement.class);        
        when(connection.prepareStatement("DELETE FROM Player WHERE id = " + id)).thenReturn(ps);    	
        PlayerDao inst = new PlayerDao(connection);        
        inst.delete(id);
        verify(ps, times(1)).executeUpdate();
	}

}
