package Sportstats.Service.Add;

import java.util.NoSuchElementException;

import Sportstats.Dao.SportDao;
import Sportstats.Dao.TeamDao;
import Sportstats.Domain.Team;
import Sportstats.Exception.InvalidInputException;
import Sportstats.Helper.InputControll;
import Sportstats.Service.Runnable;

/**
 * Service class that adds a new {@link Team} to a {@link Sport}.
 * 
 * @author Hassan Sheikha
 * @version 2019-05-30
 */
public class AddTeamForSport implements Runnable<Object> {

	private final TeamDao teamDao = new TeamDao();
	private final SportDao sportDao = new SportDao();
	private String name;
	private int sportId;

	/**
	 * Constructs the service.
	 * 
	 * @param name    the name to give the team
	 * @param sportid the id of the sport to connect the team to
	 */
	public AddTeamForSport(String name, int sportid) {
		this.name = name;
		this.sportId = sportid;
	}

	/**
	 * Runs the service.
	 * 
	 * @throws InvalidInputException  if the team name given is invalid
	 * @throws NoSuchElementException if there is no team with the given id
	 * @return {@code true} if service was ran successfully {@code false} otherwise
	 */
	public Boolean run() {
		if (!InputControll.isStringOnlyAlphabeticAndNotEmpty(name.trim()))
			throw new InvalidInputException("The league name either contains illegal characters or is of size 0");

		try {
			sportDao.get(sportId);
		} catch (NoSuchElementException e) {
			throw e;
		}
		
		Team newTeam = new Team(sportId, name);
		return teamDao.save(newTeam);

	}
}