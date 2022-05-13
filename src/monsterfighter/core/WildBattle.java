package monsterfighter.core;

import java.util.ArrayList;

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
		String description = "Monster: " + getMonsters().get(0).getName() + " Type: " + getMonsters().get(0).getType().value + " " + super.toString() + " Reward: " + reward.getName();
		return description;
	}
	
}
