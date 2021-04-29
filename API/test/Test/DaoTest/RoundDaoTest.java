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
import Sportstats.Dao.RoundDao;
import Sportstats.Domain.Round;

/**
 * Test class for the {@link RoundDao} class.
 * 
 * @author Lara Aula
 * @version 2019-04-17
 */

public class RoundDaoTest extends TestCase {
	
	ConnectionManager conn;
    
    public RoundDaoTest() {
        conn = mock(ConnectionManager.class);
    }    
    
    @Test
    public void testGet() throws SQLException {
    	int id = 1;
        int roundNumber = 1;
        int seasonId = 1;
        ResultSet rs = mock(ResultSet.class);
        
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(id);
        when(rs.getInt(2)).thenReturn(roundNumber);
        when(rs.getInt(3)).thenReturn(seasonId);
        when(conn.executeQuery("SELECT * " +
				"FROM Round " +
				"WHERE id = " + id)).thenReturn(rs);
        
        RoundDao inst = new RoundDao(conn);

        Round expected = new Round(seasonId, seasonId, seasonId);
        expected.setId(1);
        expected.setRoundNumber(1);

        
        Round result = inst.get(id);
        
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getRoundNumber(), result.getRoundNumber());
        assertEquals(expected.getSeasonID(), result.getSeasonID());
    }
    
    @Test
    public void testDelete() throws SQLException {
    	int id = 1;
        int roundNumber = 1;
        int seasonId = 1;

        Round round = new Round(seasonId, seasonId, seasonId);

        round.setRoundNumber(roundNumber);
        round.setId(id);
    	
    	RoundDao inst = new RoundDao(conn); 
        
        PreparedStatement ps = mock(PreparedStatement.class);
     
        when(conn.prepareStatement("DELETE FROM Round " +
				"WHERE id = " + id)).thenReturn(ps);
        inst.delete(round.getId());
        
        verify(ps, times(1)).executeUpdate();
    }
    
    @Test
    public void testUpdate() throws SQLException {
    	int id = 0;
    	int roundNumber = 1;
        int seasonId = 1;

    	Round round = new Round(seasonId, seasonId, seasonId);
    	round.setId(id);
    	round.setRoundNumber(roundNumber);

    	
    	RoundDao inst = new RoundDao(conn);
    	
    	round.setRoundNumber(++roundNumber);
        PreparedStatement ps = mock(PreparedStatement.class);
     
        when(conn.prepareStatement("UPDATE Round SET round_number = ?, season_id = ?"
				+ " WHERE id = ?")).thenReturn(ps);
        
        inst.update(round);
        
        verify(ps, times(1)).executeUpdate();
    }
	
	@Test
    public void testSave() throws SQLException {
        RoundDao inst = new RoundDao(conn);
        
        int id = 0;
    	int roundNumber = 1;
        int seasonId = 1;
        Round round = new Round(seasonId, seasonId);

    	round.setId(id);
    	round.setRoundNumber(roundNumber);
        
        PreparedStatement ps = mock(PreparedStatement.class);    
        when(conn.prepareStatement("INSERT INTO Round(round_number, season_id) "
				+ "VALUES(?,?)")).thenReturn(ps);
        inst.save(round);
        verify(ps, times(1)).executeUpdate();
    }
	
	@Test
	public void testGetWithWrongId() throws SQLException{
		int id = 1;
        int roundNumber = 1;
        int seasonId = 1;
        ResultSet rs = mock(ResultSet.class);
        
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(id);
        when(rs.getInt(2)).thenReturn(roundNumber);
        when(rs.getInt(3)).thenReturn(seasonId);
        when(conn.executeQuery("SELECT * " +
				"FROM Round " +
				"WHERE id = " + id)).thenReturn(rs);
        
        RoundDao inst = new RoundDao(conn);
        
        Round result = inst.get(id);
        
        assertNotSame(result.getId(), 7);
	}
}