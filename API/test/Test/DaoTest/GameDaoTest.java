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
import Sportstats.Dao.GameDao;
import Sportstats.Domain.Game;
import Sportstats.Helper.DateTranslator;

public class GameDaoTest {
	
	ConnectionManager connection;
	
	public GameDaoTest() {
		connection = mock(ConnectionManager.class);
	}

	@Test
	public void testGet() throws SQLException {
		int id = 0;
		@SuppressWarnings("deprecation")
		Date date = new Date(2017, 5, 8);
		int arenaId = 12;
		int homeTeam = 2;
		int awayTeam = 3;
		int roundId = 2;
				
        ResultSet rs = mock(ResultSet.class);    
        
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(id);
        when(rs.getDate(2)).thenReturn(date);
        when(rs.getInt(3)).thenReturn(arenaId);
        when(rs.getInt(4)).thenReturn(homeTeam);
        when(rs.getInt(5)).thenReturn(awayTeam);
        when(rs.getInt(6)).thenReturn(roundId);

        
        when(connection.executeQuery("SELECT * FROM Game WHERE id=" + id)).thenReturn(rs);
        
        GameDao inst = new GameDao(connection);
        
        Game arena = new Game(id, DateTranslator.dateConvertToLocalDate(date), arenaId, homeTeam, awayTeam, roundId, roundId, roundId);
        Game result = inst.get(id);
        
        assertEquals(result.getId(), arena.getId());
        assertEquals(result.getDateOfThisGame(), arena.getDateOfThisGame());
        assertEquals(result.getGameArena(), arena.getGameArena());
        assertEquals(result.getHomeTeam(), arena.getHomeTeam());
        assertEquals(result.getAwayTeam(), arena.getAwayTeam());
        assertEquals(result.getRoundId(), arena.getRoundId());
        
	}

	@Test
	public void testSave() throws SQLException {
		int id = 4;
		LocalDate localDate = LocalDate.of(2017, 12, 12);
		
		int arenaId = 4;
		int homeTeam = 2;
		int awayTeam = 1;
		int roundId = 3;
		PreparedStatement ps = mock(PreparedStatement.class);
        when(connection.prepareStatement("INSERT INTO Game (date, arena_id, "

					+ "home_team, away_team, round_id) "
					+ "VALUES (?, ?, ?, ?, ?)"))
        	.thenReturn(ps);
		GameDao inst = new GameDao(connection);
        Game game = new Game(id, localDate, arenaId, homeTeam, awayTeam, roundId, roundId, roundId);
        boolean result = true;
        boolean res = inst.save(game);
        assertEquals(result, res);
	}

	@Test
	public void testUpdate() throws SQLException {
		int id = 4;
		LocalDate localDate = LocalDate.of(2017, 12, 12);
		int arenaId = 4;
		int homeTeam = 2;
		int awayTeam = 1;
		int roundId = 3;
		Game game = new Game(id, localDate, arenaId, homeTeam, awayTeam, roundId, roundId, roundId);
    	PreparedStatement ps = mock(PreparedStatement.class);    
        when(connection.prepareStatement(
        		"UPDATE Game SET id = ?, date = ?, arena_id = ?, home_team= ?, away_team= ?, round_id= ?"
						+ " WHERE id =" + id))
        	.thenReturn(ps);
    	GameDao inst = new GameDao(connection);        
        inst.update(game);
        verify(ps, times(1)).executeUpdate();
	}

	@Test
	public void testDelete() throws SQLException {
		int id = 45;
    	PreparedStatement ps = mock(PreparedStatement.class);        
        when(connection.prepareStatement("DELETE FROM Game WHERE id = ?")).thenReturn(ps);    	
        GameDao inst = new GameDao(connection);        
        inst.delete(id);
        verify(ps, times(1)).executeUpdate();
	}

}
