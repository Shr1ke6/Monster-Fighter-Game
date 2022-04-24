package monsterfighter.core;

import java.util.ArrayList;
import java.util.List;

public class WildBattle extends Battle{

	final private Item reward;
	
	public WildBattle(Item reward, int points, ArrayList<Monster> monsters) {
		super(points, monsters);
		this.reward = reward;
	}

	public Item getReward() {
		return reward;
	}
	
	@Override
	public String toString() {
		String description;
		return description;
	}
	
}
