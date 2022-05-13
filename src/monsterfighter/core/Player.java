package monsterfighter.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
	
	// The ArrayList containing the players Item's
	private final List<ArrayList<Item>> inventory = new ArrayList<ArrayList<Item>>();
	
	// The ArrayList containing the players Monster's
	private ArrayList<Monster> party = new ArrayList<Monster>();
	
	// The name of the user using this manager
	private String name;
	
	// The users gold
	private int goldBalance = 0;
	
	// The total gold the user earned over the course of the game
	private int totalGold = 0;
	
	// The users points
	private int points = 0;
	
	public Player(String name, int numItems) {
		this.name = name;
		//Adds in an ArrayList for each item 
		for (int i = 0; i < numItems; i++) {
			this.inventory.add(new ArrayList<Item>());
		}
	}
	
	public List<ArrayList<Item>> getInventory() {
		return inventory;
	}
	
	public void addItemToInventory(Item item) {
		inventory.get(item.getIndex()).add(item);
	}
	
	public void removeItemFromInventory(Item item) {
		inventory.get(item.getIndex()).remove(item);
	}
	
	public ArrayList<Monster> getParty() {
		return party;
	}

	public void addMonsterToParty(Monster monster) {
		if (party.size() >= 4) {
			throw new IllegalStateException("Party full, cannot add another monster to party!\n");	
		} else {
			party.add(monster);
		}
	}
	
	public void removeMonsterFromParty(Monster monster) {
		party.remove(monster);
	}	
	
	public Monster getLeadingMonster() {
		return party.get(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGoldBalance() {
		return goldBalance;
	}

	public void setGoldBalance(int gold) {
		this.goldBalance += gold;
		if (gold > 0) {
			increaseTotalGold(gold);
		}
	}

	public int getTotalGold() {
		return totalGold;
	}

	public void increaseTotalGold(int gold) {
		this.totalGold += gold;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * Checks to see if inventory is empty
	 * 
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
	 * Given the index of two monsters in party, switches them around.
	 * 
	 * @param monsterID_1 Index of the monster that is to be switched in party
	 * @param monsterID_2 Index of the monster that is to be switched with in party
	 */
	public void switchMonsters(int monsterID1, int monsterID2) {
		Collections.swap(party, monsterID1, monsterID2);
	}
	
	/**
	 * Returns a boolean based on whether there exists a conscious monster in party
	 * 
	 * @return boolean that's true if all the monster's in party are fainted 
	 * or false if there exists a conscious monster
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
	 * Returns the number of conscious Monster's within the player's party
	 * 
	 * @return integer The number of Monster's in party with Status.CONSCIOUS
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
