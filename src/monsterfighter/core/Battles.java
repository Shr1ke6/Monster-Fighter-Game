package monsterfighter.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import monsterfighter.core.GameEnvironment.Difficulty;

public class Battles {
	
	// An ArrayList of all available wild battles
	private ArrayList<Battle> wildBattles;
	
	// An ArrayList of all available trainer battles
	private ArrayList<Battle> trainerBattles;
	
	// List of trainer names
	private final List<String> trainers = Arrays.asList("Ben","Matt","Lee","Ian","John","Eva","Nancy","Haley","Jade","Beth");

	private Random rng = new Random();
	
	public Battles() {
		wildBattles = new ArrayList<Battle>(); 
		trainerBattles = new ArrayList<Battle>();
	}

	public ArrayList<Battle> getWildBattles() {
		return wildBattles;
	}

	public void addWildBattle(Battle wildBattle) {
		wildBattles.add(wildBattle);
	}
	
	public void removeWildBattle(Battle wildBattle) {
		wildBattles.remove(wildBattle);
	}

	public ArrayList<Battle> getTrainerBattles() {
		return trainerBattles;
	}

	public void addTrainerBattle(Battle trainerBattle) {
		trainerBattles.add(trainerBattle);
	}
	
	public void removeTrainerBattle(Battle trainerBattle) {
		trainerBattles.remove(trainerBattle);
	}

	public void fillBattles(List<Item> allItems, List<Monster> allMonsters, GameEnvironment.Difficulty difficulty, int day, int partySize) {
		fillWildBattles(allItems, allMonsters, difficulty, day);
		fillTrainerBattles(allItems, allMonsters, difficulty, day, partySize);
		
	}
	
	public void fillWildBattles(List<Item> allItems, List<Monster> allMonsters, Difficulty difficulty, int day) {
		// Clears out yesterdays wild battles
		if (wildBattles.size() > 0) {
			wildBattles.clear();
		}
		
		for (int i = 0; i < 2; i++) {
			// Selects a random item as a reward
			int randomNumber = rng.nextInt(0, allItems.size());
			Item reward = new Item(allItems.get(randomNumber));
			
			// Selects and scales a random monster
			ArrayList<Monster> monsters = new ArrayList<>();
			randomNumber = rng.nextInt(allMonsters.size());
			Monster monster = new Monster(allMonsters.get(randomNumber));
			monster.scaleMonster(day - 3);
			monsters.add(monster);
			// Selects the correct amount of points
			int points = calculatePoints("Wild", monsters.size(), difficulty);
			
			wildBattles.add(new WildBattle(reward, points, monsters));
		}
	}
	
	public void fillTrainerBattles(List<Item> allItems, List<Monster> allMonsters, Difficulty difficulty, int day, int partySize) {
		// Clears out yesterdays trainer battles
		if (trainerBattles.size() > 0) {
			trainerBattles.clear();
		}
		for (int i = 0; i < 3; i++) {
			// Selects a random party size based on the size of the users party
			int randomNumber = rng.nextInt(partySize + 1);
			
			// Fills the party with scaled monsters equal to selected size
			ArrayList<Monster> monsters = new ArrayList<>();
			for (int j = 0; j <= Math.min(randomNumber, 3); j++) {
				Monster monster = new Monster(allMonsters.get(rng.nextInt(allMonsters.size())));
				monster.scaleMonster(day - 1);
				monsters.add(monster);
			}
			
			// Selects a random name for the trainer
			String trainer = trainers.get(rng.nextInt(trainers.size()));
			
			//  Selects the correct amount of points
			int points = calculatePoints("Trainer", monsters.size(), difficulty);
			
			// Selects the correct amount of gold
			int gold = calculateGold(monsters.size(), difficulty);
			
			
			trainerBattles.add(new TrainerBattle(gold, points, monsters, trainer));
		}
	}
	
	public int calculatePoints(String battleType, int partySize, GameEnvironment.Difficulty difficulty) {
		int points = 50 * partySize + difficulty.getBattlePoints();
		if (!battleType.equals("Wild")) {
			points += 100;
		}
		return points;
	}
	
	public int calculateGold(int partySize, GameEnvironment.Difficulty difficulty) {
		int gold = 200 * partySize + difficulty.getBattleGold();
		return gold;
	}
}
