package monsterfighter.core;

import java.util.ArrayList;
import java.util.List;

public class RandomEvent {
	
	private List<Boolean> levelUp;
	private List<Boolean> leaves;
	private boolean joins;
	
	public RandomEvent(ArrayList<Monster> party) {
		this.levelUp = monsterLevelUp(party);
		this.leaves = monsterLeaves(party);
		this.joins = monsterJoins(party);
	}
	
	public List<Boolean> getLevelUp() {
		return levelUp;
	}
	
	
	public List<Boolean> getMonsterLeaves() {
		return leaves;
	}
	
	public boolean getMonsterJoins() {
		return joins;
	}
	
	
	
	public List<Boolean> monsterLevelUp(ArrayList<Monster> party) {
		List<Boolean> levelUp = new ArrayList<>(party.size());
		double odds = 0.2;
		for (int i = 0; i < party.size(); i++) {
			odds += party.get(i).getWins()*0.05;
			if (party.get(i).getFaintedToday()) {
				odds *= 0.5;
			}
			boolean monsterLevelUp = Math.random() < odds;
			levelUp.add(monsterLevelUp);
		}
		return levelUp;
		
		
	}
	
	public List<Boolean> monsterLeaves(ArrayList<Monster> party) {
		List<Boolean> leaves = new ArrayList<>(party.size());
		double odds = 0.025;
		for (int i = 0; i < party.size(); i++) {
			if (party.get(i).getFaintedToday()) {
				odds *= 2;
			}
			boolean monsterLeaves = Math.random() < odds;
			leaves.add(monsterLeaves);
		}
		return leaves;
		
		
	}
	
	public boolean monsterJoins(ArrayList<Monster> party) {
		double odds = 0 + (0.05 * (4 - party.size()));
		boolean joins = Math.random() < odds;
		return joins;
	
	
	}
	
	
	

}
