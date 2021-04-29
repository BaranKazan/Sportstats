package Sportstats.Service.Add;

import java.util.NoSuchElementException;

import Sportstats.Dao.ArenaDao;

import Sportstats.Dao.SeasonDao;
import Sportstats.Dao.SportDao;
import Sportstats.Dao.TeamDao;
import Sportstats.Domain.Team;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Helper.InputControll;
import Sportstats.Service.Runnable;

/**
 * Service class that adds a new {@link Team} to a {@link Season}.
 * 
 * @author Baran Kazan
 * @author Hassan Sheikha
 * @version 2019-05-01
 */
public class AddNewTeamToASeason implements Runnable<Boolean> {

	private String name;
	private int seasonId;
	private int arenaId;
	private int sportId;

	private final TeamDao teamDao = new TeamDao();
	private final SeasonDao seasonDao = new SeasonDao();
	private final ArenaDao arenaDao = new ArenaDao();
	private final SportDao sportDao = new SportDao();

	/**
	 * Constructs the service with the required parameters to add a new team into
	 * the season.
	 * 
	 * @param name     the name of the team
	 * @param seasonId the id of the season
	 * @param arenaId  the id of the arena
	 * @param sportId  the id of the sport
	 */
	public AddNewTeamToASeason(String name, int seasonId, int arenaId, int sportId) {
		this.seasonId = seasonId;
		this.arenaId = arenaId;
		this.sportId = sportId;
		this.name = name;
	}

	/**
	 * The method saves the team in the database.
	 * 
	 * @throws NoSuchElementException if any object with a given id doesn't exist in
	 *                                the database with a detail message specifying
	 *                                which domain it was
	 * @throws InvalidInputException  if the Team name contains any invalid
	 *                                characters
	 * @return {@link true} if service was ran successfully, {@link false} otherwise
	 */
	@Override
	public Boolean run() {

		if (!InputControll.isStringOnlyAlphabeticAndNotEmpty(name.trim())) {
			throw new InvalidInputException("The team name can only consist of letters and can't be empty.");
		}

		try {
			seasonDao.get(seasonId);
			arenaDao.get(arenaId);
			sportDao.get(sportId);
		} catch (NoSuchElementException e) {
			throw e;
		}

		Team newTeam = new Team(seasonId, arenaId, sportId, name);
		return teamDao.save(newTeam);
	}
}