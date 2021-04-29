package Sportstats.Service.Add;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import Sportstats.Dao.ArenaDao;
import Sportstats.Dao.GameDao;
import Sportstats.Dao.RoundDao;
import Sportstats.Dao.TeamDao;
import Sportstats.Domain.Game;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Service.Runnable;

/**
 * A Service class that adds a new game to a round.
 * 
 * @author Lara Aula
 * @author Baran Kazan
 * @author Hassan Sheikha
 * @version 2019-05-01
 */
public class AddNewGameToRound implements Runnable<Boolean> {

	private LocalDate dateOfGame;
	private int gameArena;
	private int homeTeam;
	private int awayTeam;
	private int roundId;

	private final RoundDao roundDao = new RoundDao();
	private final GameDao gameDao = new GameDao();
	private final ArenaDao arenaDao = new ArenaDao();
	private final TeamDao teamDao = new TeamDao();

	/**
	 * Constructs the service.
	 * 
	 * @param dateOfGame the date this game will be held
	 * @param gameArena  the id of the arena this game will be in
	 * @param homeTeam   the id of the home team
	 * @param awayTeam   the id of the away team
	 * @param roundId    the id of the round this game is in
	 */
	public AddNewGameToRound(LocalDate dateOfGame, int gameArena, int homeTeam, int awayTeam, int roundId) {

		this.roundId = roundId;
		this.gameArena = gameArena;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.dateOfGame = dateOfGame;
	}

	/**
	 * Saves the new game in the database.
	 * 
	 * @throws InvalidInputException  if the team id's given are the same
	 * @throws NoSuchElementException if any of the given id's does not exist in the
	 *                                database
	 * @return {@code true} if run was successful, {@code false} otherwise
	 */
	@Override
	public Boolean run() {

		if (awayTeam == homeTeam) {
			throw new InvalidInputException("A game can't have one team as both home and away team.");
		}

		try {
			roundDao.get(roundId);
			arenaDao.get(gameArena);
			teamDao.get(homeTeam);
			teamDao.get(awayTeam);
		} catch (NoSuchElementException e) {
			throw e;
		}

		Game newGame = new Game(dateOfGame, gameArena, homeTeam, awayTeam, roundId);
		return gameDao.save(newGame);
	}
}
