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

	//static String[] trainers = {"Peach Ice", "Sour Apple", "Strawberry Burst", "Fatso the Fat Boy", "Panstu Sniffer", "Coke Addict", "Poo Sniffer", "Ice Wallow Come", "Lambogini" , "Poopy pant"};

}

