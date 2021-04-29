package Sportstats.Service.List;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Sportstats.Dao.GameDao;
import Sportstats.Dao.LeagueDao;
import Sportstats.Dao.RoundDao;
import Sportstats.Dao.SeasonDao;
import Sportstats.Dao.TeamDao;
import Sportstats.Domain.Game;
import Sportstats.Domain.Round;
import Sportstats.Service.Runnable;

/**
 * A class to list games between two teams with round, season, league and
 * eventual result.
 *
 * @author Peshang Suleiman
 * @version 2019-05-06
 */
public class ListGamesBetweenTwoTeamsInclResultRoundSeasonLeague implements Runnable<List<Object>> {

	private final GameDao gameDao = new GameDao();
	private final RoundDao roundDao = new RoundDao();
	private final SeasonDao seasonDao = new SeasonDao();
	private final LeagueDao leagueDao = new LeagueDao();
	private int teamOneId;
	private int teamTwoId;
	int number = 0;
	int seasonStart = 0;
	int seasonEnd = 0;
	int seasonId = 0;
	String leagueName = "";

	/**
	 * A method to check the inputs of team-id
	 *
	 * @param teamOneId teamOneId that the user is searching for
	 * @param teamTwoId teamTwoId that the is searching for
	 */
	public ListGamesBetweenTwoTeamsInclResultRoundSeasonLeague(int teamOneId, int teamTwoId) {
		this.teamOneId = teamOneId;
		this.teamTwoId = teamTwoId;
	}

	/**
	 * A method for creating a list of the games between two teams Adding round
	 * number, start- and end-year of the season, league name and the eventual
	 * result.
	 * 
	 * @return a list of all info
	 */
	@Override
	public List<Object> run() {
		
		try {
			new TeamDao().get(teamOneId);
			new TeamDao().get(teamTwoId);
		}catch(NoSuchElementException e) {
			throw e;
		}
		
		List<Game> listOfGamesBetweenTwoTeams = gameDao.getGamesBetweenTwoTeamsByTeamId(teamOneId, teamTwoId);
		List<Object> listOfAllInfo = new ArrayList<Object>();
		List<Round> listOfRound = new ArrayList<>();

		for (Game game : listOfGamesBetweenTwoTeams) {
			listOfRound.add(roundDao.get(game.getRoundId()));
		}
		
		int index = 0;
		
		for (Round round : listOfRound) {
			listOfAllInfo.add("Game ID: " + listOfGamesBetweenTwoTeams.get(index).getId());
			listOfAllInfo.add("Round ID: " + listOfGamesBetweenTwoTeams.get(index).getRoundId());
			listOfAllInfo.add("Arena ID: " + listOfGamesBetweenTwoTeams.get(index).getGameArena());
			listOfAllInfo.add("Date: " + listOfGamesBetweenTwoTeams.get(index).getDateOfThisGame());
			listOfAllInfo.add("HomeTeam ID: " + listOfGamesBetweenTwoTeams.get(index).getHomeTeam());
			listOfAllInfo.add("AwayTeam ID: " + listOfGamesBetweenTwoTeams.get(index).getAwayTeam());
			listOfAllInfo.add("HomeTeamGoals: " + listOfGamesBetweenTwoTeams.get(index).getHomeGoals());
			listOfAllInfo.add("AwayTeamGoals: " + listOfGamesBetweenTwoTeams.get(index).getAwayGoals());
			number = round.getRoundNumber();
			seasonId = seasonDao.get(round.getSeasonID()).getLeagueId();
			listOfAllInfo.add("Round number: " + number);
			seasonStart = seasonDao.get(round.getSeasonID()).getStartYear();
			seasonEnd = seasonDao.get(round.getSeasonID()).getEndYear();
			listOfAllInfo.add("Season span: " + seasonStart + "-" + seasonEnd);
			leagueName = leagueDao.get(seasonId).getName();
			listOfAllInfo.add("League name: " + leagueName);
			index++;
		}
		return listOfAllInfo;
	}
}