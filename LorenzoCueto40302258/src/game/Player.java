package game;

public class Player {
	private String name;

	
	public Player(String name) {
		if(name != null)
			this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
