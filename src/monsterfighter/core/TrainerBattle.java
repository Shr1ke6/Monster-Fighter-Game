package monsterfighter.core;

import java.util.ArrayList;

public class TrainerBattle extends Battle {
	
	final private int gold;
	final private String trainer;
	
	public TrainerBattle(int gold, int points, ArrayList<Monster> monsters, String trainer) {
		super(points, monsters);
		this.gold = gold;
		this.trainer = trainer;
	}

	public int getGold() {
		return gold;
	}
	
	public String getTrainer() {
		return trainer;
	}
	
	@Override
	public String battleStatus() {
		String description = "Trainer: " + trainer + " " + super.getConsciousMonsters() + "x M\n" + super.battleStatus() + " " ;
		return description;
	}
	
	@Override
	public String toString() {
		String description = "Trainer: " + trainer + " " + getMonsters().size() + "x Monsters " + super.toString() +" Gold: " + gold;
		return description;
	}
}

