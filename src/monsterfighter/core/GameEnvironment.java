package monsterfighter.core;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import monsterfighter.ui.GameEnvironmentUi;

public class GameEnvironment {
	
	


    // The user interface to be used by this manager
	private final GameEnvironmentUi ui;
	
	// The list of all {@link Monster}s
	private final List<Monster> allMonsters;

	// The list of available starting {@link Monster}s 
	private final List<Monster> startingMonsters;
	
	// The list of all {@link Items}s
	private final List<Item> allItems;

	// The array list representing the players inventory. 
	// Contains starting {@link items}s at initialisation.
	private final List<ArrayList<Item>> inventory = new ArrayList<ArrayList<Item>>();

	// The array list of Monsters in the users party
	private ArrayList<Monster> party = new ArrayList<Monster>();

	// The name of the user using this manager
	private String name;
	
	// The number of days the game will last
	private int days;
	
	// The game difficulty
	private Difficulty difficulty;
	
	// The users gold
	private int gold = 0;
	
	// Enum that stores the difficulty options for the game 
    public enum Difficulty {
	    EASY(100, 1.25, "Easy"),
	    NORMAL(50, 1.0, "Medium"),
	    HARD(25, 0.75, "Hard");

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
		this.startingMonsters = monsters.subList(0, 3);
		this.allItems = items;
		for (int i = 0; i < items.size(); i++) {
			this.inventory.add(new ArrayList<Item>());
		}
		// Adds 3 potion's to the inventory 
		for (int i = 0; i < 3; i++) {
			this.inventory.get(items.get(0).getIndex()).add(items.get(0));
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
	public void onSetupFinished(String name, int days, Monster startingMonster, Difficulty difficulty) {
		this.name = name;
		this.days = days;
		this.party.add(startingMonster);
		this.difficulty = difficulty;
		this.gold += difficulty.startingGold;
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
	
	public int getDays() {
		return days;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	public List<Monster> getStartingMonsters() {
		return Collections.unmodifiableList(startingMonsters);
	}
	
	public List<Monster> getParty() {
		return Collections.unmodifiableList(party);
	}
	
	public List<ArrayList<Item>> getInventory() {
		return Collections.unmodifiableList(inventory);
	}
	
	public void switchMonsters(int option1, int option2) {
		Monster monster1 = party.get(option1);
		Monster monster2 = party.get(option2);
		party.set(option1, monster2);
		party.set(option2, monster1);
	}
	
	public void useItem(int monster, int item) {
		
	}

}
