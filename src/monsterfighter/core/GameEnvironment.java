package monsterfighter.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import monsterfighter.ui.GameEnvironmentUi;

public class GameEnvironment {

    // The user interface to be used by this manager
	private final GameEnvironmentUi ui;
	
	// The list of all {@link Monster}s
	private final List<Monster> allMonsters;

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
	
	private int points = 0;
	
	// The shop
	private ArrayList<ArrayList<Purchasable>> shop = new ArrayList<ArrayList<Purchasable>>();
	
	private ArrayList<Battle> battles = new ArrayList<Battle>(); 
	
	// Enum that stores the difficulty options for the game 
    public enum Difficulty {
	    EASY(100, 1.25, "Easy"),
	    NORMAL(75, 1.0, "Medium"),
	    HARD(50, 0.75, "Hard");

	    private final String name;
	    private final int startingGold;
	    private final double battleGold;

	    Difficulty(int startingGold, double battleGold, String name){
	        this.startingGold = startingGold;
	        this.battleGold = battleGold;
	        this.name = name;
	    }
	    
	    @Override
	    public String toString() {
			return "Difficulty: " + name + " Starting Gold: " + startingGold + " Gold per battle multiplier: " + battleGold ;
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
			this.startingMonsters.add(monsters.get(i));
		}
		this.allItems = items;
		for (int i = 0; i < items.size(); i++) {
			this.inventory.add(new ArrayList<Item>());
		}
		// Adds 3 potion's to the inventory 
		for (int i = 0; i < 3; i++) {
			addToInventory(allItems.get(1));
		}
		fillShop();
		for (int i = 0; i < 5; i++) {
			if (i < 3) {
				battles.add(new WildBattle());
			} else {
				battles.add(new TrainerBattle(monsters, i - 3));
				
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
	
	public void Brawls() {
		
	}
	
	public Monster scaleMonster(Monster monster) {
		for (int i = 0; i < day - 1; i++) {
			int randomNumber = ThreadLocalRandom.current().nextInt(0, 2);
			if (randomNumber == 0) {
				monster.setMaxHealth(20);
			} else {
				monster.setAttack(10);
			}
		}
		return monster;
	}
	
	public void fillShop() {
		final ArrayList<Monster> allMonstersCopy = new ArrayList<Monster>(allMonsters);
		if (shop.size() > 0) {
			shop.clear();
		}
		for (int i = 0; i <= allItems.size() + 1; i++) {
			shop.add(new ArrayList<Purchasable>());
			if (i > 1) {
				for (int j = 0; j < ((allItems.get(i-2).getStoreQuantity()) * ((int) Math.ceil((double)day / 3))) ; j++) {
					shop.get(i).add(allItems.get(i-2));
				}
			} else {
				int randomNumber = ThreadLocalRandom.current().nextInt(0, allMonstersCopy.size());
				shop.get(i).add(scaleMonster(allMonstersCopy.get(randomNumber)));
				allMonstersCopy.remove(randomNumber);
			}
		}
	}
	
	public void purchase(int shopID) {
		try {
			if (shop.get(shopID).size() == 0) {
				throw new IllegalStateException("None left");	
			}
			Purchasable object = shop.get(shopID).get(0);
			if (goldBalance >= object.getBuyPrice()) {
				if (object instanceof Monster) {
					if (party.size() >= 3) {
						throw new IllegalStateException("Party full, cannot buy another monster!");	
					} else {
						party.add((Monster) object);
					}
				} else {
					inventory.get(object.getIndex()).add((Item) object);
				}
				goldBalance -= object.getBuyPrice();
			} else {
				throw new IllegalStateException("Not enough gold!");
			}
		} catch (IllegalStateException e) {
			ui.showError(e.getMessage());
		}
	}
	
	public void fillBattles() {
		
	}
	
	
	public void nextDay() {
		day += 1;
		fillShop();
		fillBattles();
		for (Monster monster : party) {
			monster.receiveHealth(1000000);
		}
	}
	
	
	public String getWildBattle() {
		int index = (int)(Math.random() * allMonsters.size());
		Monster monster = allMonsters.get(index);
		return monster.basicDescription();
	}
	
	public String getTrainerBattle() {
		int index = (int)(Math.random() * allMonsters.size());
		Monster monster = allMonsters.get(index);
		return monster.basicDescription();
	}
	
	
	public void earntGold(int earntGold) {
		goldBalance += earntGold;
	}
	
	public void gainedPoints(int gainedPoints) {
		points += gainedPoints;
	}
	
	public void dropReward() {
        int index = (int)(Math.random() * allItems.size());
        Item item = allItems.get(index);
        inventory.get(item.getIndex()).add((Item) item);
	}

	
}



