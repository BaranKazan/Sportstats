package sportstats.domain;

public class League {
	
	private String name;
	private int id;
	
	
	public League(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}	
}

 