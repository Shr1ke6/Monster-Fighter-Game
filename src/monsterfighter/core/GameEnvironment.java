package monsterfighter.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
	
	// An ArrayList of all available wild battles
	private ArrayList<Battle> wildBattles = new ArrayList<Battle>(); 
	
	// An ArrayList of all available trainer battles
	private ArrayList<Battle> trainerBattles = new ArrayList<Battle>();
	
	// Random events that occur upon resting
	private RandomEvent randomEvents;
	
	// Random number generator
	private Random rng = new Random();
	
	//
	private boolean battleRunning = false;

	
	// Enum that stores the difficulty options for the game 
    public enum Difficulty {
	    EASY(100, 50, 0,"Easy"),
	    MEDIUM(75, 25, 50, "Medium"),
	    HARD(50, 0, 100, "Hard");

	    private final String name;
	    private final int startingGold;
	    private final int battleGold;
	    private final int battlePoints;

	    Difficulty(int startingGold, int battleGold, int battlePoints, String name){
	        this.startingGold = startingGold;
	        this.battleGold = battleGold;
	        this.battlePoints = battlePoints;
	        this.name = name;
	    }
	    
	    @Override
	    public String toString() {
			return name + ": Starting Gold: " + startingGold + " Bonus gold: " 
	    + battleGold + " Bonus points: " + battlePoints;
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
	

	

	public void start() {
		ui.setup(this);
	}
	
	

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
	
	public boolean getBattleRunning() {
		return battleRunning;
	}
	
	public List<Monster> getAllMonsters() {
		return Collections.unmodifiableList(allMonsters);
	}
	
	public List<Item> getAllItems() {
		return Collections.unmodifiableList(allItems);
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
		return Collections.unmodifiableList(wildBattles);
	}
	
	public List<Battle> getTrainerBattles() {
		return Collections.unmodifiableList(trainerBattles);
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
	
	public int inventorySize() {
		int inventorySize = 0;
			for (ArrayList<Item> items: inventory)
				inventorySize += items.size();
		return inventorySize;
	}
	
	public void switchMonsters(int option1, int option2) {
		try {
			if (battleRunning && party.get(option2).getStatus() == Monster.Status.FAINTED) {
				throw new IllegalStateException(party.get(option2).getNickname() + " is fainted. Choose another monster");
			}
			Monster monster1 = party.get(option1);
			Monster monster2 = party.get(option2);
			party.set(option1, monster2);
			party.set(option2, monster1);
		} catch (IllegalStateException e) {
			ui.showError(e.getMessage());
		}
	}
	
	
	public boolean partyFainted() {
		boolean fainted = true;
		for (Monster monster: party) {
			if (monster.getStatus() == Monster.Status.CONSCIOUS) {
				fainted = false;
			}
		}
		return fainted;	
	}
	
	public void addToParty(Monster monster) {
		try {
			if (party.size() >= 4) {
				throw new IllegalStateException("Party full, cannot add another monster to party!\n");	
			} else {
				party.add(monster);
			}
		} catch (IllegalStateException e) {
			ui.showError(e.getMessage());
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
			monster.receiveHealth(20);
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
			if (shop.size() == 0) {
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
			monster.receiveHealth(monster.getMaxHealth());
			monster.setFaintedToday(false);
			monster.resetWins();
		}
	}
			
	public void fillBattles() {
		fillWildBattles();
		fillTrainerBattles();
	}
			
	
	public void fillWildBattles() {
		// Clears out yesterdays wild battles
		if (wildBattles.size() > 0) {
			wildBattles.clear();
		}
		
		for (int i = 0; i < 2; i++) {
			// Selects a random item as a reward
			int randomNumber = rng.nextInt(0, allItems.size());
			Item reward = allItems.get(randomNumber);
			
			// Selects and scales a random monster
			ArrayList<Monster> monsters = new ArrayList<>();
			randomNumber = rng.nextInt(allMonsters.size());
			Monster monster = new Monster(allMonsters.get(randomNumber));
			monsters.add(scaleMonster(monster, day - 3));
			
			// Selects the correct amount of points
			int points = calculatePoints("Wild", monsters.size());
			
			wildBattles.add(new WildBattle(reward, points, monsters));
		}
	}
	
	public void fillTrainerBattles() {
		// Clears out yesterdays trainer battles
		if (trainerBattles.size() > 0) {
			trainerBattles.clear();
		}
		for (int i = 0; i < 3; i++) {
			// Selects a random party size based on the size of the users party
			int randomNumber = rng.nextInt(party.size() + 1);
			
			// Fills the party with scaled monsters equal to selected size
			ArrayList<Monster> monsters = new ArrayList<>();
			for (int j = 0; j <= Math.min(randomNumber, 3); j++) {
				Monster monster = new Monster(allMonsters.get(rng.nextInt(allMonsters.size())));
				monsters.add(scaleMonster(monster, day - 1));
			}
			
			// Selects a random name for the trainer
			String trainer = trainers.get(rng.nextInt(0, trainers.size()));
			
			// Selects the correct amount of gold
			int gold = calculateGold(monsters.size());
			
			//  Selects the correct amount of points
			int points = calculatePoints("Trainer", monsters.size());
			
			trainerBattles.add(new TrainerBattle(gold, points, monsters, trainer));
		}
	}
	
	
	public int calculatePoints(String battleType, int partySize) {
		int points = 50 * partySize + difficulty.battlePoints;
		if (battleType != "Wild") {
			points += 100;
		}
		return points;
	}
	
	public int calculateGold(int partySize) {
		int gold = 200 * partySize + difficulty.battleGold;
		return gold;
	}
	
	public void startBattle() {
		battleRunning = true;
	}
	
	public void manageBattle(Battle opponent, int battleID) {
		opponentTurn(opponent);
		battleRunning = opponent.getConsciousMonsters() > 0 && !partyFainted();
		if (!battleRunning) {
			rewards(opponent);
			if (opponent instanceof WildBattle) {
				opponent = wildBattles.remove(battleID);
			} else if (opponent instanceof TrainerBattle){
				opponent = trainerBattles.remove(battleID);
			}
		}
	}
	
	public void opponentTurn(Battle opponent) {
		if (opponent.getMonsters().get(0).getStatus() == Monster.Status.FAINTED) {
			Collections.rotate(opponent.getMonsters(), -1);
		} else {
			opponent.getMonsters().get(0).attack(party.get(0));
		}
		
	}

	public void rewards(Battle opponent) {
		if (partyFainted()) {
			points += 25 * (opponent.getMonsters().size() - opponent.getConsciousMonsters());
		} else if (opponent.getConsciousMonsters() == 0) {
			points += opponent.getPoints();
			if (opponent instanceof WildBattle) {
				WildBattle wildOpponent = (WildBattle) opponent;
				Item reward = wildOpponent.getReward();
				addToInventory(reward);
			} else if (opponent instanceof TrainerBattle) {
				TrainerBattle trainerOpponent = (TrainerBattle) opponent;
				goldBalance += trainerOpponent.getGold();
			}
			for (Monster monster: party) {
				monster.addWin(1);
			}
		}
	}
}

	



