package monsterfighter.core;

import java.util.ArrayList;
import java.util.List;


public class Battle {
	private Item reward;
	private int gold;
	private int points;
	private ArrayList<ArrayList<Monster>> battles = new ArrayList<ArrayList<Monster>>();


	

	public void WildBattle(List<Monster> monsters, List<Item> items) {
		for (int i = 0; i < 3; i++) 
        {
            int index = (int)(Math.random() * battles.size());
  
            System.out.println("wild battle :"
                               + battles.get(index));
        }
		
	}
	
	public void TrainerBattle(List<Monster> monsters, List<Item> items) {
		for (int i = 3; i < 5; i++) 
        {
            int index = (int)(Math.random() * battles.size());
  
            System.out.println("wild battle :"
                               + battles.get(index));
        }
	}
		
	
	
	public void Trainers(List<Monster> monsters) {
	
		
	}
	
	public void earntGold(int earntGold) {
	    gold = gold + earntGold;
	}
	
	public void gainedPoints(int gainedPoints) {
		points = points + gainedPoints;
	}
	
	public void getReward(Item reward) {
		GameEnvironment.addToInventory(reward);
	}

}
