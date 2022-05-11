package monsterfighter.core;

import java.util.Collections;
import java.util.List;

public abstract class Battle {
	
	final private int points;
	final private List<Monster> monsters;

	public Battle(int points, List<Monster> monsters) {
		this.points = points;
		this.monsters = monsters;
	}
	
	public int getPoints() {
		return points;
	}
	
	public List<Monster> getMonsters() {
		return monsters;
	}
	
	public int getConsciousMonsters() {
		int conscious = monsters.size();
		for (Monster monster: monsters ) {
			if (monster.getStatus() == Monster.Status.FAINTED) {
				conscious -= 1;
			}
		}
		return conscious;
	}
	
	public void nextMonster() {
		Collections.rotate(monsters, -1);
	}
		
	public String battleStatus() {
		Monster activeMonster = monsters.get(0);
		return activeMonster.battleDescription();
	}
	
	@Override
	public String toString() {
		return "Points: " + points;
	}
}
