package monsterfighter.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import monsterfighter.ui.gui.Gui;

import monsterfighter.ui.GameEnvironmentUi;

public class GameEnvironment {

    // The user interface to be used by this manager
	private final GameEnvironmentUi ui;
	
	/**
	 * The maximum number of days that a game can last.
	 */
	public static final int MAX_DAYS = 15;
   
   /**
	* The minimum number of days that a game can last.
	*/
   	public static final int MIN_DAYS = 5;
	
	// The list of all {@link Monster}s
	private final List<Monster> allMonsters;
	
	// The list of available starting {@link Monster}s 
	private final List<Monster> startingMonsters = new ArrayList<Monster>();
	
	// The list of all {@link Items}s
	private final List<Item> allItems;

	// The total number of days the game will last
	private int totalDays;
	
	// The current day
	private int day = 1;
	
	// The game difficulty
	private Difficulty difficulty;

	// Random events that occur upon resting
	private RandomEvent randomEvents;
	
	// Random number generator
	private Random rng = new Random();
	
	// Boolean that signals whether a battle is running
	private boolean battleRunning = false;
	
	private Object selectedObject;
	
	private Player player;
	
	private Shop shop;
	
	private Battles battles;

	// Enum that stores the difficulty options for the game 
    public enum Difficulty {
	    EASY(200, 50, 0,"Easy"),
	    MEDIUM(100, 25, 50, "Medium"),
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
	    
	    public int getBattleGold() {
	    	return battleGold;
	    }
	    
	    public int getBattlePoints() {
	    	return battlePoints;
	    }
	    
	    @Override
	    public String toString() {
			return name + ": Starting Gold: " + startingGold + " Bonus gold: " 
	    + battleGold + " Bonus points: " + battlePoints;
		}
	}

    //rm
	public GameEnvironment(GameEnvironmentUi ui, List<Monster> monsters, List<Item> items) {
		this.ui = ui;
		this.allMonsters = monsters;
		for (int i = 0; i < 3; i++) {
			this.startingMonsters.add(new Monster(monsters.get(i)));
		}
		this.allItems = items;
	}
	

	
	//rm
	public void start() {
		ui.setup(this);
	}
	
	//rm
	public void onSetupFinished(String name, int totalDays, Monster startingMonster, String nickname, Difficulty difficulty) {
		this.player = new Player(name, allItems.size());
		this.totalDays = totalDays;
		player.addMonsterToParty(startingMonster);
		for (int i = 0; i < 3; i++) {
			player.addItemToInventory(new Item(allItems.get(0)));
		} 
		startingMonster.setNickname(nickname);
		this.difficulty = difficulty;
		player.setGoldBalance(difficulty.startingGold);
		this.shop = new Shop();
		shop.fillShop(day, allItems, allMonsters);
		this.battles = new Battles();
		battles.fillBattles(allItems, allMonsters, difficulty, day, player.getParty().size());
		ui.start();
	}
	
	public void transitionScreen(String option, String back, boolean closeScreen) {
		if (ui instanceof Gui) {
			((Gui) ui).transitionScreen(option, back, closeScreen);
		}
	}
	
	//rm
	public void onFinish() {
		if (ui.confirmQuit()) {
			ui.quit();
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Shop getShop() {
		return shop;
	}
	
	public Battles getBattles() {
		return battles;
	}
	
	public int getTotalDays() {
		return totalDays;
	}
	
	public int getDay() {
		return day;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
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
		
	public List<Monster> getStartingMonsters() {
		return Collections.unmodifiableList(startingMonsters);
	}
	
	/**
	 * Creates a copy of the players inventory that contains ArrayLists of size > 0
	 * 
	 * Used in the UI to display only existing items
	 * 
	 * @return
	 */
	public List<ArrayList<Item>> getInventoryUI() {
		ArrayList<ArrayList<Item>> inventoryUI = new ArrayList<ArrayList<Item>>();;
		for (ArrayList<Item> item : player.getInventory()) {
			if (!item.isEmpty()) {
				inventoryUI.add(item);
			}
		}
		return Collections.unmodifiableList(inventoryUI);
	}
	

	public Object getSelectedObject() {
		return selectedObject;
	}
	
	public void setSelectedObject(Object selectedObject) {
		this.selectedObject = selectedObject;
	}
	
	public void switchMonsters(Monster monster, Monster monsterSwitch) {
		try {
			if (battleRunning && monsterSwitch.getStatus() == Monster.Status.FAINTED) {
				throw new IllegalStateException(monsterSwitch.getNickname() + " is fainted. Choose another monster");
			}
			int monsterID = 10000;
			int monsterSwitchID = 10000;
			for (int i = 0; i < player.getParty().size(); i++) {
				if (player.getParty().get(i).equals(monster)) {
					monsterID = i;
				}
				if (player.getParty().get(i).equals(monsterSwitch)) {
					monsterSwitchID = i;
				}
			}
			player.switchMonsters(monsterID, monsterSwitchID);
		} catch (IllegalStateException e) {
			ui.showError(e.getMessage());
		}
	}
	
	public void useItem(Monster monster, Item item) {
		try {
			item.useItem(monster);
			player.removeItemFromInventory(item);
		} catch(IllegalStateException e) {
			ui.showError(e.getMessage());
		}
	}
	
	/**
	 * Sells an item from inventory 
	 * 
	 * @param itemID
	 */
	public void sellItem(Item item) {
		player.setGoldBalance(item.getSellPrice());
		player.removeItemFromInventory(item);
	}
	
	/**
	 * Sells a monster from party
	 * 
	 * @param monster
	 */
	public void sellMonster(Monster monster) {
		player.setGoldBalance(monster.getSellPrice());
		player.removeMonsterFromParty(monster);
	}
	
	public void purchase(Purchasable object) {
		try {
			if (shop.getShopInventory().size() == 0) {
				throw new IllegalStateException("No Items left! Come back tomorrow for new stock");	
			}
			if (player.getGoldBalance() >= object.getBuyPrice()) {
				if (object instanceof Monster) {
					player.addMonsterToParty((Monster) object);
				} else {
					player.addItemToInventory((Item) object);
				}
				player.setGoldBalance(-object.getBuyPrice());
				shop.removeObject(object);
			} else {
				throw new IllegalStateException("Not enough gold!\n");
			}
		} catch (IllegalStateException e) {
			ui.showError(e.getMessage());
		}
	}
	
	
	public void nextDay() {
		day += 1;
		shop.fillShop(day, allItems, allMonsters);
		battles.fillBattles(allItems, allMonsters, difficulty, day, player.getParty().size());
		randomEvents = new RandomEvent(player.getParty());
		for (int i = 0; i < player.getParty().size(); i++) {
			if (randomEvents.getLevelUp().get(i) && !randomEvents.getMonsterLeaves().get(i)) {
				player.getParty().get(i).levelUp();
			} 
		} for (int i = 0; i < player.getParty().size(); i++) {
			if (randomEvents.getMonsterLeaves().get(i)) {
				player.removeMonsterFromParty(player.getParty().get(i));
			}
		}
		if (randomEvents.getMonsterJoins()) {
			int randomNumber = rng.nextInt(allMonsters.size());
			Monster monster = allMonsters.get(randomNumber);
			player.addMonsterToParty(monster);
		}
		for (Monster monster : player.getParty()) {
			monster.receiveHealth(monster.getMaxHealth());
			monster.setFaintedToday(false);
			monster.resetWins();
		}
	}
			
	public void startBattle() {
		try {
			if (player.getParty().size() == 0 || player.partyFainted()) {
				throw new IllegalStateException("No available monsters to battle");
			}
			int i = 0;
			for (Monster monster: player.getParty()) {
				if (monster.getStatus().equals(Monster.Status.CONSCIOUS)) {
					player.switchMonsters(0,i);
					break;
				} i++;
			}
			battleRunning = true;
		} catch (IllegalStateException e) {
			ui.showError(e.getMessage());
		}
	}
	
	public void manageBattle(Battle opponent, int battleID) {
		opponentTurn(opponent);
		battleRunning = opponent.getConsciousMonsters() > 0 && !player.partyFainted();
		if (!battleRunning) {
			rewards(opponent);
			if (opponent instanceof WildBattle) {
				battles.removeWildBattle(opponent);
			} else if (opponent instanceof TrainerBattle){
				battles.removeTrainerBattle(opponent);
			}
		}
	}
	
	public void opponentTurn(Battle opponent) {
		if (opponent.getMonsters().get(0).getStatus() == Monster.Status.FAINTED) {
			opponent.nextMonster();
		} else {
			opponent.getMonsters().get(0).attack(player.getLeadingMonster());
		}
		
	}

	public void rewards(Battle opponent) {
		if (player.partyFainted()) {
			player.setPoints(opponent.getMonsters().size() - opponent.getConsciousMonsters());
		} else if (opponent.getConsciousMonsters() == 0) {
			player.setPoints(opponent.getPoints());
			if (opponent instanceof WildBattle) {
				WildBattle wildOpponent = (WildBattle) opponent;
				Item reward = wildOpponent.getReward();
				player.addItemToInventory(reward);
			} else if (opponent instanceof TrainerBattle) {
				TrainerBattle trainerOpponent = (TrainerBattle) opponent;
				player.setGoldBalance(trainerOpponent.getGold());
			}
			for (Monster monster: player.getParty()) {
				monster.addWin(1);
			}
		}
	}
	
}

	



