package monsterfighter.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that models a player 
 */
public class Player {
	
	// The ArrayList containing the player's items
	private final List<ArrayList<Item>> inventory = new ArrayList<ArrayList<Item>>();
	
	// The ArrayList containing the player's monsters
	private ArrayList<Monster> party = new ArrayList<Monster>();
	
	// The name of the player using this manager
	private final String name;
	
	// The player's gold
	private int goldBalance = 0;
	
	// The total gold the player earned over the course of the game
	private int totalGold = 0;
	
	// The player's points
	private int points = 0;
	
	/**
	 * Constructs a player with a given name, and formats the player's
	 * inventory using the total number of items in the game.
	 * 
	 * @param name The name of the player
	 * @param numItems
	 */
	public Player(String name, int numItems) {
		this.name = name;
		//Adds in an ArrayList for each item 
		for (int i = 0; i < numItems; i++) {
			this.inventory.add(new ArrayList<Item>());
		}
	}
	
	/**
	 * Gets the players inventory.
	 * 
	 * @return An 2D containing items that acts as the players inventory
	 */
	public List<ArrayList<Item>> getInventory() {
		return inventory;
	}
	
	/**
	 * Adds an {@link Item} to the corresponding ArrayList in the 
	 * players inventory.
	 * 
	 * @param item The item to be added to the players inventory
	 */
	public void addItemToInventory(Item item) {
		inventory.get(item.getIndex()).add(item);
	}
	
	/**
	 * Removes an {@link Item} from the corresponding ArrayList in the 
	 * players inventory.
	 * 
	 * @param item The item type to be removed from the players inventory
	 */
	public void removeItemFromInventory(Item item) {
		inventory.get(item.getIndex()).remove(item);
	}
	
	/**
	 * Gets the player's party of {@link Monster}'s.
	 * 
	 * @return An array of monsters
	 */
	public ArrayList<Monster> getParty() {
		return party;
	}

	/**
	 * Adds a {@link Monster} to the player's party.
	 * 
	 * @param monster The monster to be added to the party
	 */
	public void addMonsterToParty(Monster monster) {
		if (party.size() >= 4) {
			throw new IllegalStateException("Party full, cannot add another monster to party!\n");	
		} else {
			party.add(monster);
		}
	}
	
	/**
	 * Removes a {@link Monster} from the player's party.
	 * 
	 * @param monster The monster to be removed from the party
	 */
	public void removeMonsterFromParty(Monster monster) {
		party.remove(monster);
	}	
	
	/**
	 * Gets the {@link Monster} at the first index of the player's party.
	 * 
	 * @return The monster at the head of the party
	 */
	public Monster getLeadingMonster() {
		return party.get(0);
	}

	/**
	 * Gets the player's name.
	 * 
	 * @return The name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get's the current gold balance of the player
	 * 
	 * @return the amount of gold the player has
	 */
	public int getGoldBalance() {
		return goldBalance;
	}

	/**
	 * Adds or subtracts the given amount of gold.
	 * 
	 * @param gold The Amount of gold to be added or removed 
	 * from the player's gold balance
	 */
	public void setGoldBalance(int gold) {
		this.goldBalance += gold;
		if (gold > 0) {
			increaseTotalGold(gold);
		}
	}

	/**
	 * Gets the total gold the player has ever earned.
	 * 
	 * @return The total gold that has ever been earned by the player
	 */
	public int getTotalGold() {
		return totalGold;
	}

	/**
	 * Increases the total gold the player has earned by the given amount.
	 * 
	 * @param gold The gold amount that the total amount of gold is increased by
	 */
	public void increaseTotalGold(int gold) {
		this.totalGold += gold;
	}

	/**
	 * Gets the players points.
	 * 
	 * @return The players points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Increases the players points by the given value.
	 * 
	 * @param points The amount of points to add onto the players point total.
	 */
	public void increasePoints(int points) {
		this.points += points;
	}
	
	/**
	 * Checks to see if the player's inventory is empty.
	 * 
	 * @return Boolean that's true if the arrays in the player's inventory are 
	 * empty and false otherwise
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
	 * Given the index of two {@link Monster}'s in the player's party, 
	 * switches them around.
	 * 
	 * @param monsterID1 Index of the monster that is to be switched in party
	 * @param monsterID2 Index of the monster that is to be switched with in party
	 */
	public void switchMonsters(int monsterID1, int monsterID2) {
		Collections.swap(party, monsterID1, monsterID2);
	}
	
	/**
	 * Returns a boolean based on whether there exists a conscious monster in party.
	 * 
	 * @return Boolean that's true if all the monster's in the player's party 
	 * are {@link Monster.Status#FAINTED} or false otherwise
	 */
	public boolean partyFainted() {
		boolean fainted = true;
		for (Monster monster: party) {
			if (monster.getStatus() == Monster.Status.CONSCIOUS) {
				fainted = false;
			}
		}
		return fainted;	
	}
	
	/**
	 * Returns the number of {@link Monster.Status#CONSCIOUS} {@link Monster}'s 
	 * within the player's party.
	 * 
	 * @return integer The number of monster's in the player's party that are conscious
	 */
	public int getConsciousMonsters() {
		int conscious = party.size();
		for (Monster monster: party ) {
			if (monster.getStatus() == Monster.Status.FAINTED) {
				conscious -= 1;
			}
		}
		return conscious;
	}

	/**
	 * Returns the number of {@link Item}'s in the player's inventory.
	 * 
	 * @return The total item count of the player's inventory
	 */
	public int inventoryNumItems() {
		int numItems = 0;
		for (ArrayList<Item> items: inventory) {
			for(Item item: items) {
				numItems += 1;
			}
		}
		return numItems;
	}
	
}
