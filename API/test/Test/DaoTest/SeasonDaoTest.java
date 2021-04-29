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
import Sportstats.Dao.SeasonDao;
import Sportstats.Domain.Season;

/**
 * Testing the SeasonDao class.
 * @author barankazan
 *
 */
public class SeasonDaoTest {
	
	ConnectionManager connection;

	public SeasonDaoTest() {
		connection = mock(ConnectionManager.class);
	}

	@Test
	public void testGet() throws SQLException {
		int id = 34;
		int leagueId = 12;
        int startYear = 2018;
        int endYear = 2019;
        ResultSet rs = mock(ResultSet.class);
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(id);
        when(rs.getInt(2)).thenReturn(leagueId);
        when(rs.getInt(3)).thenReturn(startYear);
        when(rs.getInt(4)).thenReturn(endYear);
        when(connection.executeQuery("SELECT id, league_id, start_year, end_year FROM Season WHERE id=" + id))
        	.thenReturn(rs);
        SeasonDao inst = new SeasonDao(connection);
        Season expSeason = new Season(id, leagueId, startYear, endYear);
        Season result = inst.get(id);
        assertEquals(expSeason.getId(), result.getId());
        assertEquals(expSeason.getLeagueId(), result.getLeagueId());
        assertEquals(expSeason.getStartYear(), result.getStartYear());
        assertEquals(expSeason.getEndYear(), result.getEndYear());
	}

	@Test
	public void testSave() throws SQLException {
		PreparedStatement ps = mock(PreparedStatement.class);
        when(connection.prepareStatement("INSERT INTO Season (league_id, start_year, end_year) VALUES (?, ?, ?)"))
        	.thenReturn(ps);
		SeasonDao inst = new SeasonDao(connection);
        Season season = new Season(7, 5, 2016, 2018);
        boolean result = true;
        boolean res;
        res = inst.save(season);
        assertEquals(result, res);
	}

	@Test
	public void testUpdate() throws SQLException {
		Season season = new Season(4, 3, 2012, 2013);
    	PreparedStatement ps = mock(PreparedStatement.class);    
        when(connection.prepareStatement("UPDATE Season SET id = ?, league_id = ?, start_year= ?, end_year= ? WHERE id = ?"))
        	.thenReturn(ps);
    	SeasonDao inst = new SeasonDao(connection);        
        inst.update(season);
        verify(ps, times(1)).executeUpdate();
	}

	@Test
	public void testDelete() throws SQLException {
		int id = 45;
    	PreparedStatement ps = mock(PreparedStatement.class);        
        when(connection.prepareStatement("DELETE FROM Season WHERE id = ?")).thenReturn(ps);    	
        SeasonDao inst = new SeasonDao(connection);        
        inst.delete(id);
        verify(ps, times(1)).executeUpdate();
	}
}