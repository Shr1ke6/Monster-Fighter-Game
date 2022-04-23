package monsterfighter.core;

import java.util.ArrayList;
import java.util.List;


public class Battle {
	
	
	private Item reward;
	private int gold;
	private int points;
	private ArrayList<Monster> battles = new ArrayList<Monster>();
	private String trainer;

	public Battle(Item reward, int gold, int points, ArrayList<Monster> battles, String trainer) {
		this.reward = reward;
		this.gold = gold;
		this.points = points;
		this.battles = battles;
		this.trainer = trainer;
		
	}
	
	

}
