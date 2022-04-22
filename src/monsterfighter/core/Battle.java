package monsterfighter.core;

import java.util.ArrayList;
import java.util.List;


public class Battle {
	private Item reward;
	private int gold;
	private int points;
	private ArrayList<ArrayList<Monster>> battles = new ArrayList<ArrayList<Monster>>();


	
	public void earntGold(int earntGold) {
	    gold = gold + earntGold;
	}
	
	public void gainedPoints(int gainedPoints) {
		points = points + gainedPoints;
	}
	


}
