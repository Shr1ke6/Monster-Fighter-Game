package monsterfighter.core;

import java.util.List;

public class Battle {
	
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
	
	@Override
	public String toString() {
		return "Points: " + points;
	}
}
