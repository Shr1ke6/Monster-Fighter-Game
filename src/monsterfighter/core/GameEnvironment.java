package monsterfighter.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import monsterfighter.ui.GameEnvironmentUi;

public class GameEnvironment {

    // The user interface to be used by this manager
	private final GameEnvironmentUi ui;
	
	// The list of all {@link Monster}s
	private final List<Monster> allMonsters;
	
	// List of trainer names
	private final List<String> trainers = Arrays.asList("Ben","Matt","Lee","Ian","John","Eva","Nancy","Haley","Jade","Beth");

	// The list of available starting {@link Monster}s 
	private final List<Monster> startingMonsters = new ArrayList<Monster>();
	
	// The list of all {@link Items}s
	private final List<Item> allItems;

	// The array list representing the players inventory. 
	// Contains starting {@link items}s at initialisation.
	private final List<ArrayList<Item>> inventory = new ArrayList<ArrayList<Item>>();

	// The array list of Monsters in the users party
	private ArrayList<Monster> party = new ArrayList<Monster>(3);
	
	// The array list of fainted Monsters 
	private ArrayList<Monster> fainted = new ArrayList<Monster>();

	// The name of the user using this manager
	private String name;
	
	// The total number of days the game will last
	private int totalDays;
	
	// The current day
	private int day = 1;
	
	// The game difficulty
	private Difficulty difficulty;
	
	// The users gold
	private int goldBalance = 1000;
	
	// The users points
	private int points = 0;
	
	// The shop
	private ArrayList<ArrayList<Purchasable>> shop = new ArrayList<ArrayList<Purchasable>>();
	
	// An ArrayList of all available battles
	private ArrayList<Battle> battles = new ArrayList<Battle>(); 
	
	// Random events that occur upon resting
	private RandomEvent randomEvents;
	
	// Random number generator
	private Random rng = new Random();
	
	
	
	// Enum that stores the difficulty options for the game 
    public enum Difficulty {
	    EASY(100, 50, "Easy"),
	    NORMAL(75, 25, "Medium"),
	    HARD(50, 0, "Hard");

	    private final String name;
	    private final int startingGold;
	    private final int battleGold;

	    Difficulty(int startingGold, int battleGold, String name){
	        this.startingGold = startingGold;
	        this.battleGold = battleGold;
	        this.name = name;
	    }
	    
	    @Override
	    public String toString() {
			return "Difficulty: " + name + " Starting Gold: " + startingGold + " Bonus gold per battle: " + battleGold ;
		}
	}

	/**
	 * Creates a RocketManager with the given user interface and rockets.
	 *
	 * @param ui The user interface that this manager should use
	 * @param rockets The list of available rockets that the user can choose from when
	 *                configuring this manager
	 */
	public GameEnvironment(GameEnvironmentUi ui, List<Monster> monsters, List<Item> items) {
		this.ui = ui;
		this.allMonsters = monsters;
		for (int i = 0; i < 3; i++) {
			this.startingMonsters.add(new Monster(monsters.get(i)));
		}
		this.allItems = items;
		for (int i = 0; i < items.size(); i++) {
			this.inventory.add(new ArrayList<Item>());
		}
		// Adds 3 potion's to the inventory 
		for (int i = 0; i < 3; i++) {
			addToInventory(allItems.get(1));
		}
		for (int i = 0; i < 5; i++) {
			if (i < 3) {
				//battles.add(new WildBattle());
			} else {
				//battles.add(new TrainerBattle(monsters, i - 3));
				
			}
			
		}
		
	}
	

	
	/**
	 * Starts this game. Must be called from the event dispatch thread (EDT) if the user interface is a Swing gui.
	 * This method calls {@link GameEnvironmentUi#setup(GameEnvironment)} to initiate setup of the user interface.
	 */
	public void start() {
		ui.setup(this);
	}
	
	
	/**
	 * This method should be called by the user interface when {@link GameEnvironmentUi#setup(RocketManager)}
	 * has been completed. This method calls {@link GameEnvironmentUiUi#start()} to tell the user interface to start.
	 *
	 * @param name The name of the user that is playing the game.
	 * @param party The party of the player after they selected their starting monster.
	 */
	public void onSetupFinished(String name, int totalDays, Monster startingMonster, Difficulty difficulty) {
		this.name = name;
		this.totalDays = totalDays;
		this.party.add(startingMonster);
		this.difficulty = difficulty;
		this.goldBalance += difficulty.startingGold;
		fillShop();
		fillBattles();
		ui.start();
	}
	
	/**
	 * Gets the name of the user that configured this manager.
	 *
	 * @return The name entered by the user when configuring this manager
	 */
	public String getName() {
		return name;
	}
	
	public int getTotalDays() {
		return totalDays;
	}
	
	public int getDays() {
		return day;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}

	public int getGoldBalance() {
		return goldBalance;
	}
	
	public int getPoints() {
		return points;
	}
	
	public RandomEvent getRandomEvent() {
		return randomEvents;
	}
	
	public List<Monster> getAllMonsters() {
		return Collections.unmodifiableList(allMonsters);
	}
	
	public List<Item> getAllItems() {
		return Collections.unmodifiableList(allItems);
	}
	
	public List<Battle> getBattles() {
		return Collections.unmodifiableList(battles);
	}
	
	public List<ArrayList<Purchasable>> getShop() {
		return Collections.unmodifiableList(shop);
	}
	
	public List<Monster> getStartingMonsters() {
		return Collections.unmodifiableList(startingMonsters);
	}
	
	public List<Monster> getParty() {
		return Collections.unmodifiableList(party);
	}
	
	public List<Battle> getWildBattles() {
		return battles.subList(0, 3);
	}
	
	public List<Battle> getTrainerBattles() {
		return battles.subList(3, 5);
	}
	
	public List<ArrayList<Item>> getInventory() {
		ArrayList<ArrayList<Item>> inventoryUI = new ArrayList<ArrayList<Item>>();;
		for (ArrayList<Item> item : inventory) {
			if (!item.isEmpty()) {
				inventoryUI.add(item);
			}
		}
		return Collections.unmodifiableList(inventoryUI);
	}
	
	/**
	 * Checks to see if user inventory is empty
	 * @return isEmpty States whether the user's {@link inventory} is empty or not
	 */
	public boolean inventoryIsEmpty() {
		boolean isEmpty = true;
		for (ArrayList<Item> item : inventory) {
			if (!item.isEmpty()) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}
	
	/**
	 * Checks to see if the shop is empty
	 * @return isEmpty States whether the shop {@link shop} is empty or not
	 */
	public boolean shopIsEmpty() {
		boolean isEmpty = true;
		for (ArrayList<Purchasable> item : shop) {
			if (!item.isEmpty()) {
				isEmpty = false;
			}
		}
		return isEmpty;
	}
	
	public void switchMonsters(int option1, int option2) {
		Monster monster1 = party.get(option1);
		Monster monster2 = party.get(option2);
		party.set(option1, monster2);
		party.set(option2, monster1);
	}
	
	public void addToParty(Monster monster) {
		if (party.size() >= 4) {
			throw new IllegalStateException("Party full, cannot buy another monster!\n");	
		} else {
			party.add(monster);
		}
	}
	
	public int getItemID(int inventoryID) {
		int i = 0;
		int j = 0;
		for (ArrayList<Item> items: inventory) {
			if (items.size() > 0) {
				if (i == inventoryID) {
					j = items.get(0).getIndex();
				}
				i++;
			}
		}
		return j;
	}
	
	public void useItem(int monsterID, int itemID) {
		Monster monster = party.get(monsterID);
		Item item = inventory.get(itemID).get(0);
		try {
			item.useItem(monster);
			inventory.get(itemID).remove(0);
		} catch(IllegalStateException e) {
			ui.showError(e.getMessage());
		}
	}
	
	/**
	 * Sells an item from inventory 
	 * 
	 * @param itemID
	 */
	public void sellItem(int itemID) {
		Item item = inventory.get(itemID).get(0);
		goldBalance += item.getSellPrice();
		inventory.get(itemID).remove(0);
	}
	
	/**
	 * Sells a monster from party
	 * 
	 * @param monsterID
	 */
	public void sellMonster(int monsterID) {
		Monster monster = party.get(monsterID);
		goldBalance += monster.getSellPrice();
		party.remove(monsterID);
	}

	public void addToInventory(Item reward) {
		inventory.get(reward.getIndex()).add(reward);
	}
	
	public Monster scaleMonster(Monster monster, int scalar) {
		for (int i = 0; i < scalar; i++) {
			levelUp(monster);
		}
		return monster;
	}
	
	public void levelUp(Monster monster) {
		int randomNumber = rng.nextInt(2);
		if (randomNumber == 0) {
			monster.setMaxHealth(20);
		} else {
			monster.setAttack(10);
		}
	}
	
	public void fillShop() {
		final ArrayList<Monster> allMonstersCopy = new ArrayList<Monster>(allMonsters);
		if (shop.size() > 0) {
			shop.clear();
		}
		for (int i = 0; i <= allItems.size() + 2; i++) {
			shop.add(new ArrayList<Purchasable>());
			if (i > 2) {
				for (int j = 0; j < ((allItems.get(i-3).getStoreQuantity()) * ((int) Math.ceil((double)day / 3))) ; j++) {
					shop.get(i).add(allItems.get(i-3));
				}
			} else {
				int randomNumber = rng.nextInt(allMonstersCopy.size());
				Monster monster = new Monster(allMonsters.get(randomNumber));
				shop.get(i).add(scaleMonster(monster, day - 1));
				allMonstersCopy.remove(randomNumber);
			}
		}
	}
	
	public void purchase(int shopID) {
		try {
			if (shop.get(shopID).size() == 0) {
				throw new IllegalStateException("No Items left! Come back tomorrow for new stock");	
			}
			Purchasable object = shop.get(shopID).get(0);
			if (goldBalance >= object.getBuyPrice()) {
				if (object instanceof Monster) {
					addToParty((Monster) object);
				} else {
					addToInventory((Item) object);
				}
				goldBalance -= object.getBuyPrice();
				shop.get(shopID).remove(0);
				if (shop.get(shopID).size() == 0) {
					shop.remove(shopID);
				}
			} else {
				throw new IllegalStateException("Not enough gold!\n");
			}
		} catch (IllegalStateException e) {
			ui.showError(e.getMessage());
		}
	}
	
	
	public void nextDay() {
		day += 1;
		fillShop();
		fillBattles();
		randomEvents = new RandomEvent(party);
		for (int i = 0; i < party.size(); i++) {
			if (randomEvents.getMonsterLeaves().get(i)) {
				party.remove(i);
			} else if (randomEvents.getLevelUp().get(i) && !randomEvents.getMonsterLeaves().get(i)) {
				levelUp(party.get(i));
			} 
		}
		if (randomEvents.getMonsterJoins()) {
			int randomNumber = rng.nextInt(allMonsters.size());
			Monster monster = allMonsters.get(randomNumber);
			party.add(monster);
		}
		for (Monster monster : party) {
			monster.receiveHealth(1000000);
			monster.setFaintedToday(false);
			monster.setWins(0);
		}
	}
			
	public void fillBattles() {
		
		if (battles.size() > 0) {
			battles.clear();
		}
		for (int i = 0; i < 5; i++) {
			if (i < 2) {
				int randomNumber = rng.nextInt(0, allItems.size());
				Item reward = allItems.get(randomNumber);
				ArrayList<Monster> monsters = new ArrayList<>();
				randomNumber = rng.nextInt(allMonsters.size());
				Monster monster = new Monster(allMonsters.get(randomNumber));
				monsters.add(scaleMonster(monster, day - 3));
				int points = calculatePoints("Wild", monsters.size());
				battles.add(new WildBattle(reward, points, monsters));
			} else {
				int randomNumber = rng.nextInt(party.size() + 1);
				ArrayList<Monster> monsters = new ArrayList<>();
				for (int j = 0; j <= Math.min(randomNumber, 3); j++) {
					Monster monster = new Monster(allMonsters.get(rng.nextInt(allMonsters.size())));
					monsters.add(scaleMonster(monster, day - 1));
				}
				String trainer = trainers.get(rng.nextInt(0, trainers.size()));
				int gold = calculateGold(monsters.size());
				int points = calculatePoints("Trainer", monsters.size());
				battles.add(new TrainerBattle(gold, points, monsters, trainer));
			}
		}
	}
	
	public int calculatePoints(String battleType, int partySize) {
		int points = 50 * partySize;
		if (battleType != "Wild") {
			points += 100;
		}
		points *= ((day/totalDays) + 1);
		return points;
	}
	
	public int calculateGold(int partySize) {
		int gold = 200 * partySize + difficulty.battleGold;
		return gold;
	}

}

