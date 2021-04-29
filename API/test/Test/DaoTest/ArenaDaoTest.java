package DaoTest;

import junit.framework.TestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import Sportstats.ConnectionManager.ConnectionManager;
import Sportstats.Dao.ArenaDao;
import Sportstats.Domain.Arena;

/**
 * Test class for the {@link ArenaDao} class.
 * 
 * @author Lara Aula
 * @version 2019-04-17
 */

public class ArenaDaoTest extends TestCase {
	
	ConnectionManager conn;
    
    public ArenaDaoTest() {
        conn = mock(ConnectionManager.class);
    }    
    
    @Test
    public void testGet() throws SQLException {
    	int id = 0;
        String arenaName = "Arenan";
        int capacity = 100;
        ResultSet rs = mock(ResultSet.class);
        
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(id);
        when(rs.getString(2)).thenReturn(arenaName);
        when(conn.executeQuery("SELECT id, name, capacity " + "FROM Arena WHERE id=" + id)).thenReturn(rs);
        
        ArenaDao inst = new ArenaDao(conn);
        
        Arena arena = new Arena(id, arenaName, capacity);
        Arena result = inst.get(id);
        
        assertEquals(result.getId(), arena.getId());
        assertEquals(result.getName(), arena.getName());
    }
    
    @Test
    public void testDelete() throws SQLException {
    	int id = 0;
    	String name = "Arenan";
    	int capacity = 100;
    	Arena arena = new Arena(id, name, capacity);
    	
    	ArenaDao inst = new ArenaDao(conn); 
        
        PreparedStatement ps = mock(PreparedStatement.class);
     
        when(conn.prepareStatement("DELETE FROM Arena WHERE id = ?")).thenReturn(ps);
        inst.delete(arena.getId());
        
        verify(ps, times(1)).executeUpdate();
    }
    
    @Test
    public void testUpdate() throws SQLException {
    	int id = 0;
    	String name = "Arenan";
    	String newName = "Arena";
    	int capacity = 100;
    	
    	Arena arena = new Arena(id, name, capacity);
    	ArenaDao inst = new ArenaDao(conn);
    	
    	arena.setName(newName);
        PreparedStatement ps = mock(PreparedStatement.class);
     
        when(conn.prepareStatement("UPDATE Arena SET name = ?, capacity = ?" + " WHERE id =" + id)).thenReturn(ps);
        
        inst.update(arena);
        verify(ps, times(1)).executeUpdate();
    }
	
	@Test
    public void testSave() throws SQLException {
        ArenaDao inst = new ArenaDao(conn);
        
        int id = 0;
        String name = "Arenan";
        int capacity = 100;
        
        Arena t = new Arena(name, capacity);
        
        PreparedStatement ps = mock(PreparedStatement.class);    
        when(conn.prepareStatement("INSERT INTO Arena (name, capacity) " + "VALUES (?, ?)")).thenReturn(ps);
        inst.save(t);
        verify(ps, times(1)).executeUpdate();
    }
	
	@Test
	public void testGetWithWrongId() throws SQLException{
    	int id = 0;
        String arenaName = "Arenan";
        ResultSet rs = mock(ResultSet.class);
        
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(id);
        when(rs.getString(2)).thenReturn(arenaName);
        when(conn.executeQuery("SELECT id, name, capacity " + "FROM Arena WHERE id=" + id)).thenReturn(rs);
        
        ArenaDao inst = new ArenaDao(conn);
        Arena result = inst.get(id);
        
        assertNotSame(result.getId(), 1);
	}
}