package DaoTest;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import Sportstats.ConnectionManager.ConnectionManager;
import Sportstats.Dao.SportDao;
import Sportstats.Domain.Sport;

public class SportDaoTest {
	
	ConnectionManager connection;
	
	public SportDaoTest() {
		connection = mock(ConnectionManager.class);
	}

	@Test
	public void testGet() throws SQLException {
		String name = "testName";
		int id = 0;
        ResultSet rs = mock(ResultSet.class);    
        
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(id);

        
        when(connection.executeQuery("SELECT * FROM Sport WHERE id=" + id)).thenReturn(rs);
        
        SportDao inst = new SportDao(connection);
        
        Sport sport = new Sport(name);
        Sport result = inst.get(id);
        
        assertEquals(result.getId(), sport.getId());
        
        
	}

	@Test
	public void testSave() throws SQLException {
		String name = "testName";
		PreparedStatement ps = mock(PreparedStatement.class);
        when(connection.prepareStatement("INSERT INTO Sport (name) " + "VALUES (?)"))
        	.thenReturn(ps);
        SportDao inst = new SportDao(connection);
        Sport sport = new Sport(name);
        boolean result = true;
        boolean res = inst.save(sport);
        assertEquals(result, res);
	}

	@Test
	public void testUpdate() throws SQLException {
		String name = "testName";
		Sport sport = new Sport(name);
    	PreparedStatement ps = mock(PreparedStatement.class);    
        when(connection.prepareStatement(
        		"update Sport set name = ?" + "where id = ?"))
        	.thenReturn(ps);
        SportDao inst = new SportDao(connection);        
        inst.update(sport);
        verify(ps, times(1)).executeUpdate();
	}

	@Test
	public void testDelete() throws SQLException {
		int id = 45;
    	PreparedStatement ps = mock(PreparedStatement.class);        
        when(connection.prepareStatement("delete from Sport where id = "+id)).thenReturn(ps);    	
        SportDao inst = new SportDao(connection);        
        inst.delete(id);
        verify(ps, times(1)).executeUpdate();
	}

}
