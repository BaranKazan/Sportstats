package Sportstats.Domain;

/**
 * Domain class for Team.
 * 
 * @author Hassan Sheikha
 * @version 2019-05-27
 */

public class Team {
	
	private int id;
	private String name;
	private int seasonId;
	private int arenaId;
	private int sportId;
	
	public Team(int id, int seasonId, int arenaId, int sportId, String name) {
		this.id = id;
		this.seasonId = seasonId;
		this.arenaId = arenaId;
		this.name = name;
		this.sportId = sportId;
	}
	public Team(int seasonId, int arenaId, int sportId, String name) {
		this.seasonId = seasonId;
		this.arenaId = arenaId;
		this.sportId = sportId;
		this.name = name;
	}
	public Team(int sportId, String name) {
		this.sportId = sportId;
		this.name = name;
	}
	public Team(String name) {
		this.name = name;
	}
	public Team(int id, int seasonId, String name) {
		this.seasonId = seasonId;
		this.name = name;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public int getSeasonId() {
		return seasonId;
	}
	public int getArenaId() {
		return arenaId;
	}
	public int getSportId() {
		return sportId;
	}
	public void setSportId(int sportId) {
		this.sportId = sportId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}
	public void setArenaId(int arenaId) {
		this.arenaId = arenaId;
	}
}