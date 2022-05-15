package monsterfighter.core;

import java.util.Collections;
import java.util.List;

/**
 * Abstract class that models the common
 * functionality of a battle.
 */
public abstract class Battle {
	
	// The number of points that is provided by winning the Battle
	final private int points;
	
	// The list of Monster's that may appear in the Battle
	final private List<Monster> monsters;

	/**
	 * Creates a Battle, with a set number of points, and a party of monsters.
	 * 
	 * @param points The number of points that is provided by winning the Battle
	 * @param monsters The list of {@link Monster}'s that may appear in the Battle
	 */
	public Battle(int points, List<Monster> monsters) {
		this.points = points;
		this.monsters = monsters;
	}
	
	/**
	 * Gets the number of points for this Battle.
	 * 
	 * @return The number of points for this Battle
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Gets the list of {@link Monster}'s that may appear in the Battle.
	 * 
	 * @return The list of {@link Monster}'s that may appear in the Battle.
	 */
	public List<Monster> getMonsters() {
		return monsters;
	}
	
	/**
	 * Gets the number of {@link Monster}'s that are 
	 * {@link Monster.Status.CONSCIOUS}
	 * 
	 * @return The number of {@link Monster}'s that are 
	 * {@link Monster.Status.CONSCIOUS}
	 */
	public int getConsciousMonsters() {
		int conscious = monsters.size();
		for (Monster monster: monsters ) {
			if (monster.getStatus() == Monster.Status.FAINTED) {
				conscious -= 1;
			}
		}
		return conscious;
	}
	
	/**
	 * Adds the first {@link Monster} in the list to the end of the list
	 */
	public void nextMonster() {
		Collections.rotate(monsters, -1);
	}
	
	/**
	 * Gets a battle description for the first {@link Monster} in the list
	 * 
	 * @return A description including the {@link Monster} name, current health 
	 * and max health.
	 */
	public String battleStatus() {
		Monster activeMonster = monsters.get(0);
		return activeMonster.battleDescription();
	}
	
	/**
	 * Provides a basic description of this Battle
	 * 
	 * @return A description of the points provided by the Battle
	 */
	@Override
	public String toString() {
		return "Points: " + points;
	}
}
