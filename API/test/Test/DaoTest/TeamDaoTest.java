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
import Sportstats.Dao.TeamDao;
import Sportstats.Domain.Team;

/**
 * 
 * @author Hassan Sheikha
 *
 */

public class TeamDaoTest extends TestCase {
	
	ConnectionManager conn;
    
    public TeamDaoTest() {
        conn = mock(ConnectionManager.class);
    }    
    
    @Test
    public void testGet() throws SQLException {
    	int id = 0;
        String team = "Fotboll";
        int seasonId = 0;
        int arenaId = 0;
        int sportId = 0;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(id);
        when(rs.getString(2)).thenReturn(team);
        when(rs.getInt(3)).thenReturn(seasonId);
        when(rs.getInt(4)).thenReturn(arenaId);
        when(rs.getInt(5)).thenReturn(sportId);
        when(conn.executeQuery("SELECT id, name, season_id, arena_id, sport_id FROM Team WHERE id=" + id)).thenReturn(rs);
        TeamDao inst = new TeamDao(conn);
        Team sports = new Team(id, seasonId, arenaId, sportId, team);
        Team result = inst.get(id);
        assertEquals(sports.getName(), result.getName());
    }
    
    @Test
    public void testDelete() throws SQLException {
    	Team t = new Team(1, 2, 3, 4, "");
    	int id = 0;
    	TeamDao inst = new TeamDao(conn);
        t.setArenaId(2);
        t.setId(1);  
        
        PreparedStatement ps = mock(PreparedStatement.class);
     
        when(conn.prepareStatement("DELETE FROM Team WHERE id = ?")).thenReturn(ps);
        inst.delete(id);
        verify(ps, times(1)).executeUpdate();
    }
    
    @Test
    public void testUpdate() throws SQLException {
    	Team t = new Team(1, 2, 3, 4, "");
    	TeamDao inst = new TeamDao(conn);
        t.setArenaId(2);
        t.setId(1);  
        t.setName("");
        t.setSeasonId(3);
        t.setSportId(4);
        
        PreparedStatement ps = mock(PreparedStatement.class);
     
        when(conn.prepareStatement("UPDATE Team SET name = ?, season_id = ?, arena_id=?, sport_id=?"
				+ " WHERE id = ?")).thenReturn(ps);
        inst.update(t);
        verify(ps, times(1)).executeUpdate();
    }
	
	@Test
    public void testSave() throws SQLException {
        TeamDao inst = new TeamDao(conn);
        Team t = new Team(1, 2, 3, 4, "");
        boolean result = true;
        PreparedStatement ps = mock(PreparedStatement.class);
        when(conn.prepareStatement("INSERT INTO Team (name, season_id, arena_id, sport_id)"
				+ "VALUES(?,?,?,?)")).thenReturn(ps);
        boolean res;
        res = inst.save(t);
        assertEquals(result, res);
    }
}