package cards;

import game.Player;

public class MoveCard extends Card{

	protected MoveCard(String name, String description, boolean selfTarget) {
		super(name, description, selfTarget);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggerEffect(Player player) {
		// TODO Auto-generated method stub
		
	}
	
	//allows players to select target
		public void triggerEffect(Player[] player) {
			// TODO Auto-generated method stub
			
		}

}
