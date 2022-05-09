package monsterfighter.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import monsterfighter.core.Battle;
import monsterfighter.core.GameEnvironment;
import monsterfighter.core.GameEnvironment.Difficulty;
import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.Purchasable;
import monsterfighter.core.RandomEvent;
import monsterfighter.core.TrainerBattle;
import monsterfighter.core.WildBattle;

public class CmdLineUi implements GameEnvironmentUi {
	
    // The scanner used to read input from the console
    private final Scanner scanner;

    // The game environment this ui interacts with 
    private GameEnvironment gameEnvironment;

    // Flag to indicate when this ui should finish
    //private boolean finish = false;

    // An enum representing the various actions the user can perform
    private enum Option {
    	BATTLE("Battle"),
        SHOP("Shop"),
        VIEW_PARTY("View Party"),
        INVENTORY("Inventory"),
        REST("Rest"),
    	QUIT("Quit");

        public final String name;

        Option(String name) {
            this.name = name;
        }
    }
    
    /**
     * Creates an instance of this UI
     */
    public CmdLineUi() {
        this.scanner = new Scanner(System.in);
    }
    
	@Override
	public void setup(GameEnvironment gameEnvironment) {

        this.gameEnvironment = gameEnvironment;
        final String name = getName();
        final int days = getDays();
        final Monster startingMonster = getStartingMonster();
        final Difficulty difficulty = getDifficulty();
	    gameEnvironment.onSetupFinished(name, days, startingMonster, difficulty);
	    
	       
	}

	@Override
	public void start() {
		while (true) {
			System.out.println("Day: " + gameEnvironment.getDays() + " out of " + gameEnvironment.getTotalDays() + " | Score: " + 10 );
			printOptions();
			try {
				int option = scanner.nextInt();
				if (option >= 0 && option < 6) {
					handleOption(Option.values()[option]);
				}
			} catch (Exception e) {
				scanner.reset();
				scanner.next();
			}
		}
	}
		
	@Override
	public void quit() {
		while (true) {
            System.out.println("Do you really want to quit this fun game? (y/n) ");
            try {
                String input = scanner.next();
                if (input.matches("[yY]")) {
                	System.exit(0);
                } else if (input.matches("[nN]")) {
                	start();
                }
          
            } catch (Exception e) {
                // Discard the unacceptable input
                scanner.next();
            }
        }
	}

	@Override
	public void showError(String error) {
		System.out.println(error);
	}
	
	/**
	 * Handles the given option by performing the appropriate action.
	 * 
	 * @param option The selected option to be carried out
	 */
    private void handleOption(Option option) {
    	
        switch (option) {
            case VIEW_PARTY:
            	accessParty();
                break;
            case BATTLE:
            	accessBattle();
                break;
            case INVENTORY:
            	accessInventory();
                break;
            case SHOP:
            	accessShop();
            	break;
            case REST:
            	accessRest();
                break;
            case QUIT:
            	quit();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }
    }

    /**
     * Gets the user's name
     * 
     * @return The user name
     */
    private String getName() {
        while (true) {
            System.out.println("Please enter your name:");
            try {
                String name = scanner.nextLine();
                if (name.matches(NAME_REGEX)) {
                    return name;
                }
                System.out.println(NAME_REQUIREMENTS);
            } catch (Exception e) {
                // Discard the unacceptable input
                scanner.nextLine();
            }
        }
    }
	
    /**
     * Gets the number of days that the user selected for the game duration
     * 
     * @return The total number of days
     */
	private int getDays() {
        while (true) {
            System.out.println("Pick how many days you would like. From 5 to 15");
            try {
            	int days = scanner.nextInt();
            	if (days <= MAX_DAYS && days >= MIN_DAYS) {
            		return days;
            	}
            } catch (Exception e) {
                // Discard the unacceptable input
            	scanner.nextLine();
            }
        }
    }
	
	/**
	 * Gets the difficulty selected by the user
	 * 
	 * @return The game difficulty
	 */
	private Difficulty getDifficulty() {
		while (true) {
			System.out.println("Choose a difficulty:");
			printDifficulties();
			try {
				int option = scanner.nextInt();
				if (option >= 0 && option < 3) {
					return Difficulty.values()[option];
				}
	
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}
	
	/**
	 * Outputs the difficulty options
	 */
	private void printDifficulties() {
		int i = 0;
        for (Difficulty difficulty : Difficulty.values()) {
           System.out.println("(" + i + ") " + difficulty);
           i++;
        }
	}

	/**
	 * Gets the starting monster selected by the user
	 * 
	 * @return The starting monster for the party
	 */
	private Monster getStartingMonster() {
		System.out.println("Select an option by inputting the corresponding number");
		int monsterID = chooseMonster("Pick a starter:", gameEnvironment.getStartingMonsters(), 0);
		Monster startingMonster = gameEnvironment.getStartingMonsters().get(monsterID);
        setMonsterNickname(startingMonster);
		return startingMonster;
	}
	
	/**
	 * 
	 * 
	 * @param message Description 
	 * @param monsters
	 * @param description
	 * @return The ID used to select a monster from the party
	 */
	private int chooseMonster(String message, List<Monster> monsters, int description) {
		boolean battleRunning = gameEnvironment.getBattleRunning();
		List<Monster> party = gameEnvironment.getParty();
		// Checks whether there exists a user monster currently fighting that has fainted
		boolean conscious = !battleRunning || party.get(0).getStatus().equals(Monster.Status.CONSCIOUS);
		while (true) {
			System.out.println(message);
			printMonsters(monsters, description);
			if (conscious && description != 0) {
				System.out.println("\n(" + monsters.size() + ") Back" );
			}
			try {
				int monsterID = scanner.nextInt();
				scanner.nextLine();
				if (monsterID >= 0 && monsterID < monsters.size()) {
					return monsterID;
				} else if (description == 1 && monsterID == monsters.size() && conscious) {
					return monsterID;
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}

	private void printMonsters(List<Monster> monsters, int description) {
		int i = 0;
		if (description == 0) {
			for(Monster monster : monsters) {
				System.out.println("(" + i + ") " + monster.basicDescription());
				i++;
			}
		} else if (description == 1) {
			for(Monster monster : monsters) {
				System.out.println("(" + i + ") [Slot: " + (i+1) + "] " + monster);
				i++;
			} 
		}
	}

	private void setMonsterNickname(Monster monster) {
        while (true) {
            System.out.println("Enter a nickname for " + monster.getName() + " or leave blank to skip");
            try {
                String name = scanner.nextLine();
                if (name.matches(NAME_REGEX)) {
                    monster.setNickname(name);
                    break;
                } else if (name == "") {
                	break;
                }
                System.out.println(MONSTER_NAME_REQUIREMENTS);
            } catch (Exception e) {
                // Discard the unacceptable input
                scanner.nextLine();
            }
        }
    }
	
	private void printOptions() {
			int i = 0;
		    for (Option option : Option.values()) {
		       System.out.println("(" + i + ") " + option.name);
		       i++;
		    }
		}
		
	private void accessParty() {
		while (true) {
			List<Monster> party = gameEnvironment.getParty();
			if (party.size() > 0) {
				int monsterID = chooseMonster("Party:\n" + "-".repeat(7), party, 1);
				if (monsterID >= 0 && monsterID < party.size()) {
					partyOptions(monsterID, party);
				} else {
					start();
				}
			} else {
				showError("Party is empty!\n");
			}
		}
	}
	
	private void switchMonsters(int monsterID, String message) {
		List<Monster> party = gameEnvironment.getParty();
		int monsterID2 = chooseMonster(message, party, 1);
		if (monsterID2 >= 0 && monsterID2 < party.size()) {
				gameEnvironment.switchMonsters(monsterID, monsterID2);
		}
	}

	private void partyOptions(int monsterID, List<Monster> party) {
		while (true) {
			System.out.println("Select an option:\n"
					+ "(0) Use Item\n"
					+ "(1) Switch Party Slot\n"
					+ "(2) Set Nickname\n"
					+ "\n(3) Back");
			try {
				int option = scanner.nextInt();
				scanner.nextLine();
				if (option == 0) {
					useItem(monsterID, 0);
				} else if (option == 1) {
					switchMonsters(monsterID, "Select a monster to switch " + party.get(monsterID).getNickname() + " with:");
				} else if (option == 2) {
					setMonsterNickname(gameEnvironment.getParty().get(monsterID));
				} else if (option == 3) {
					break;
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}
	
	private void inventoryOptions(int inventoryID) {
		while (true) {
			System.out.println("Select an option:\n"
					+ "(0) Use " + gameEnvironment.getAllItems().get(gameEnvironment.getItemID(inventoryID)).getName() + "\n"
					+ "\n(1) Back");
			try {
				int option = scanner.nextInt();
				scanner.nextLine();
				if (option == 0) {
					useItem(inventoryID, 1);
				} else if (option == 1) {
					accessInventory();
				} 
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}
	
	private void useItem(int objectID, int objectType){
		List<Monster> party = gameEnvironment.getParty();
		List<ArrayList<Item>> inventory = gameEnvironment.getInventory();
		boolean battleRunning = gameEnvironment.getBattleRunning();
		while (true) {
			if (objectType == 0) {
				if (gameEnvironment.inventoryIsEmpty()) {
					showError("Inventory is empty!\n");
					break;
				}
				int monsterID = objectID;
				int inventoryID = chooseItem("Choose an item to give to " + party.get(objectID).getNickname(), inventory);
				if (inventoryID == inventory.size()) {
					break;
				}
				int itemID = gameEnvironment.getItemID(inventoryID);
				int itemTotal = inventory.get(inventoryID).size();
				Item item = gameEnvironment.getAllItems().get(itemID);
				gameEnvironment.useItem(monsterID, itemID);
				if (itemTotal != inventory.get(inventoryID).size()) {
					if (battleRunning) {
						break;
					}
					if (inventory.get(inventoryID).size() > 0) {
						System.out.println("Used " + item.getName() + " on " + party.get(monsterID).getNickname() + ", " + inventory.get(inventoryID).size() + "x " + item.getName() + "'s left");
					} else {
						System.out.println("Used " + item.getName() + " on " + party.get(monsterID).getNickname() + ", no " + item.getName() + "'s left!");
					}
				}
			} else if (objectType == 1) {
				if (party.size() == 0) {
					showError("Party is empty!\n");
					break;
				}
				int itemID = gameEnvironment.getItemID(objectID);
				int monsterID = chooseMonster("Choose a monster to give a " + gameEnvironment.getAllItems().get(itemID).getName(), party, 1);
				int itemTotal = inventory.get(objectID).size();
				Item item = gameEnvironment.getAllItems().get(itemID);
				if (monsterID == party.size()) {
					break;
				}
				gameEnvironment.useItem(monsterID, itemID);
				if (itemTotal != inventory.get(objectID).size()) {
					if (inventory.get(objectID).size() > 0) {
						System.out.println("Used " + item.getName() + " on " + party.get(monsterID).getNickname() + ", " + inventory.get(objectID).size() + "x " + item.getName() + "'s left");
					} else {
						System.out.println("Used " + item.getName() + " on " + party.get(monsterID).getNickname() + ", no " + item.getName() + "'s left!");
						accessInventory();
					}
				}
			}
		}
	}

	
	
		
	private void accessInventory() {
		while (true) {
			final List<ArrayList<Item>> inventory = gameEnvironment.getInventory();
			if (!gameEnvironment.inventoryIsEmpty()) {
				int inventoryID = chooseItem("Inventory:\n" + "-".repeat(11), inventory);
				if (inventoryID < inventory.size()) {
					inventoryOptions(inventoryID);
				} else if (inventoryID == inventory.size()) {
					start();
				}
			} else {
				showError("Inventory is empty!\n");
			}
		}
	}
	
	private int chooseItem(String message, List<ArrayList<Item>> inventory) {
		while (true) {
			System.out.println(message);
			printInventory(inventory);
			System.out.println("\n(" + inventory.size() + ") Back" );
			try {
				int inventoryID = scanner.nextInt();
				scanner.nextLine();
				if (inventoryID >= 0 && inventoryID <= inventory.size()) {
					return inventoryID;
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}
	
	/*
	 * Outputs the given user inventory
	 * 
	 * @param inventory 
	 */
	private void printInventory(List<ArrayList<Item>> inventory) {
		int i = 0;
		for (ArrayList<Item> items : inventory) {
			System.out.println("(" + i + ") " + items.size() + "x " + items.get(0));
		        i++;
	    }
	}
	
	private void accessShop() {
		while (true) {
			System.out.println("Select a shop option:\n"
					+ "(0) Buy\n"
					+ "(1) Sell Monster\n"
					+ "(2) Sell Item\n"
					+ "\n(3) Back");
			try {
				int option = scanner.nextInt();
				if (option == 0) {
					shopBuy();
				} else if (option == 1 || option == 2) {
					sell(option);
				} else if (option == 3) {
					start();
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}

	private void shopBuy() {
		while (true) {
			if (gameEnvironment.shopIsEmpty()) {
				showError("Shop is empty! Come back tomorrow for new items\n");
				break;
			}
			try {
				List<ArrayList<Purchasable>> shop = gameEnvironment.getShop();
				printShopInventory("Shop:\n" + "-".repeat(6) + "\nGold: " + gameEnvironment.getGoldBalance() + "\n", shop);
				int shopID = scanner.nextInt();
				if (shopID >= 0 && shopID < shop.size()) {
					gameEnvironment.purchase(shopID);
				} else if (shopID == shop.size()) {
					accessShop();
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}
	
	private void sell(int option) {
		while (true) {
			if (option == 1) {
				List<Monster> party = gameEnvironment.getParty();
				if (party.size() == 0) {
					showError("Party is empty!\n");
					break;
				}
				int monsterID = chooseMonster("Pick a monster to sell:", party, 1);
				if (monsterID < gameEnvironment.getParty().size()) {
					final Monster monster = party.get(monsterID);
					gameEnvironment.sellMonster(monsterID);
					System.out.println("Sold " + monster.getNickname() + " for " + monster.getSellPrice() + " gold");
				} else {
					break;
				}
			} else {
				List<ArrayList<Item>> inventory = gameEnvironment.getInventory();
				if (gameEnvironment.inventoryIsEmpty()) {
					showError("Inventory is empty!\n");
					break;
				}
				int inventoryID = chooseItem("Pick an item to sell:", gameEnvironment.getInventory());
				if (inventoryID < gameEnvironment.getInventory().size()) {
					int itemID = gameEnvironment.getItemID(inventoryID);
					final Item item = inventory.get(inventoryID).get(0);
					gameEnvironment.sellItem(itemID);
					System.out.println("Sold " + item.getName() + " for " + item.getSellPrice() + " gold");
				} else {
					break;
				}
			}
		}	
	}
		
	public void printShopInventory(String message, List<ArrayList<Purchasable>> shop) {
		System.out.println(message);
		for (int i = 0; i < shop.size(); i++) {
			System.out.println("(" + i + ") " + shop.get(i).size() + "x " + shop.get(i).get(0).shopDescription());
		}
		System.out.println("\n(" + shop.size() + ") Back" );
	}

	private void accessBattle() {
		List<Battle> wildBattles = gameEnvironment.getWildBattles();
		List<Battle> trainerBattles = gameEnvironment.getTrainerBattles();
		while (true) {
			System.out.println("Select an option:\n"
					+ "(0) Wild battles\n"
					+ "(1) Trainer battles\n"
					+ "\n(2) Back");
			try {
				int option = scanner.nextInt();
				scanner.nextLine();
				if (option == 0) {
					if (wildBattles.size() > 0) {
						chooseBattle(wildBattles, option);
					} else {
						showError("No more wild battles! Come back tomorrow for new battles\n");
					}
				} else if (option == 1) {
					if (wildBattles.size() > 0) {
						chooseBattle(trainerBattles, option);
					} else {
						showError("No more trainer battles! Come back tomorrow for new battles\n");
					}
				} else if (option == 2) {
					start();
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
        }	
	}
	
	private void chooseBattle(List<Battle> battles, int battleType) {
		while (true) {
			if (battleType == 0) {
				printBattles("Select a wild battle:", battles);
				System.out.println("\n(" + battles.size() + ") Back");
				try {
					int battleID = scanner.nextInt();
					scanner.nextLine();
					if (gameEnvironment.partyFainted()) {
						showError("All your monsters have fainted and thus are unable to battle!\n");
						break;
					}
					if (battleID >= 0 && battleID < battles.size()) {
						// battleType == 0 for wild battle
						startBattle(battles.get(battleID), battleID);
					} else if (battleID == battles.size()) {
						break;
					}
				} catch (Exception e) {
					scanner.nextLine();
				}
			} else {
				printBattles("Select a trainer battle:", battles);
				System.out.println("\n(" + battles.size() + ") Back");
				try {
					int battleID = scanner.nextInt();
					scanner.nextLine();
					if (gameEnvironment.partyFainted()) {
						showError("All your monsters have fainted and thus are unable to battle!\n");
						break;
					}
					if (battleID >= 0 && battleID < battles.size()) {
						startBattle(battles.get(battleID), battleID);
					} else if (battleID == battles.size()) {
						break;
					}
				} catch (Exception e) {
					scanner.nextLine();
				}
			}
		}
	}
	
	private void printBattles(String message, List<Battle> battles) {
		System.out.println(message);
		int i = 0;
		for (Battle battle: battles) {
			System.out.println("(" + i + ") " + battle);
			i++;
		}
	}
	
	private void startBattle(Battle battle, int battleID) {
		gameEnvironment.startBattle();
		List<Monster> party = gameEnvironment.getParty();
		List<ArrayList<Item>> inventory = gameEnvironment.getInventory();
		boolean battleRunning = gameEnvironment.getBattleRunning();
		do {
			if (party.get(0).getStatus().equals(Monster.Status.FAINTED)) {
				switchMonsters(0, "Select a monster to send into battle");
			} else {
				 runBattle(battle, battleID, party, inventory, battleRunning);
			}
		} while(battleRunning);
		
	}
	
	private void runBattle(Battle battle, int battleID, List<Monster> party, List<ArrayList<Item>> inventory, boolean battleRunning) {
	System.out.println("Go " + party.get(0).getNickname() + "!\n");
		do {
			while (party.get(0).getStatus().equals(Monster.Status.FAINTED)) {
				Monster monster = new Monster(party.get(0));
				System.out.println(party.get(0).getName() + " fainted!");
				switchMonsters(0, "Select a monster to switch into battle");
				if (!monster.equals(party.get(0))) {
					System.out.println("Go get 'em " + monster.getNickname());
				}
			}
			takeTurn(battle, party, inventory);
			int health = party.get(0).getCurrentHealth();
			Monster opponentsMonsterCopy = new Monster(battle.getMonsters().get(0));
			gameEnvironment.manageBattle(battle, battleID);
			if (opponentsMonsterCopy.getStatus().equals(Monster.Status.CONSCIOUS)) {
				System.out.println("Opponent's " + opponentsMonsterCopy.getNickname() + " did " + (health - party.get(0).getCurrentHealth()) + " damage to " + party.get(0).getNickname() + "\n");
			}
			battleRunning = gameEnvironment.getBattleRunning();
		} while(battleRunning);
		battleFinish(battle, party, inventory);
	}
	
	private void takeTurn(Battle battle, List<Monster> party, List<ArrayList<Item>> inventory) {
		Monster activeMonster = party.get(0);
		Monster opponent = battle.getMonsters().get(0);
		Monster activeMonsterCopy = new Monster(party.get(0));
		while (true) {
			try {
				battleStatus(battle, gameEnvironment.getParty());
				System.out.println("(0) Attack\n"
						+ "(1) Use Item\n"
						+ "(2) Switch Monster");
			
				int battleOption = scanner.nextInt();
				scanner.nextLine();
				if (battleOption == 0) {
					int health = opponent.getCurrentHealth();
					party.get(0).attack(battle.getMonsters().get(0));
					System.out.println(activeMonster.getNickname() + " did " + (health - opponent.getCurrentHealth()) + " damage to opponent's " + opponent.getNickname());
					if (opponent.getStatus().equals(Monster.Status.FAINTED)) {
						System.out.println("Opponent's " + opponent.getNickname() + " fainted!");
					}
					System.out.print("\n");
					break;
				} else if (battleOption == 1) {
					int inventorySize = gameEnvironment.inventorySize();
					useItem(0,0);
					if (inventorySize != gameEnvironment.inventorySize()) {
						break;
					}
				} else if (battleOption == 2) {
					switchMonsters(0, "Select a monster to switch " + party.get(0).getNickname() + " with:");
					if (!activeMonsterCopy.sameMonster(activeMonster)) {
						System.out.println("Switched " + activeMonsterCopy.getNickname() + " with " + activeMonster.getNickname());
						break;
					}
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}
	
	private void battleStatus(Battle battle, List<Monster> party) {
		System.out.println(battle.battleStatus() + "\n");
		System.out.println(party.get(0).battleDescription() + "\n");
	}
	
	private void battleFinish(Battle battle, List<Monster> party, List<ArrayList<Item>> inventory) {
		if (!gameEnvironment.partyFainted()) {
			System.out.println("You Won!\n"
					+ "Received:\nPoints: " + battle.getPoints());
			if (battle instanceof WildBattle) {
				WildBattle wildOpponent = (WildBattle)  battle;
				System.out.println("Item: " + wildOpponent.getReward().getName());
			} else if (battle instanceof TrainerBattle) {
				TrainerBattle trainerOpponent = (TrainerBattle) battle;
				System.out.println("Gold: " + trainerOpponent.getGold());
			}
		} else {
			System.out.println("You lost...\n"
					+ "Received:\nPoints: " + (25 * (battle.getMonsters().size() - battle.getConsciousMonsters())));
			
		}
		System.out.print("\n");
		accessBattle();
	}

	public void accessRest() {
		while (true) {
			System.out.println("Are you sure you want to rest? (y/n) ");
            try {
                String input = scanner.next();
                if (input.matches("[yY]")) {
                	ArrayList<Monster> partyCopy = new ArrayList<>();
                	for (Monster monster: gameEnvironment.getParty()) {
                		partyCopy.add(new Monster(monster));
                	}
                	gameEnvironment.nextDay();
                	endOfTheWorld();
                	printChanges(partyCopy);
                	break;
                } else if (input.matches("[nN]")) {
                	break;
                }
            } catch (Exception e) {
                // Discard the unacceptable input
                scanner.next();
            }
        }
	}
	
	public void printChanges(ArrayList<Monster> partyCopy) {
		RandomEvent randomEvents = gameEnvironment.getRandomEvent();
		List<Monster> party = gameEnvironment.getParty();
		
		for (int i = 0; i < partyCopy.size(); i++) {
			if (randomEvents.getMonsterLeaves().get(i)) {
				System.out.println(partyCopy.get(i).getNickname() + " left your party overnight. Best of luck " + partyCopy.get(i).getNickname());
			} else if (randomEvents.getLevelUp().get(i) && !randomEvents.getMonsterLeaves().get(i)) {
				if (party.get(i).getAttack() != partyCopy.get(i).getAttack()) {
					System.out.println(partyCopy.get(i).getNickname() + "'s attack increased overnight!");
				} else {
					System.out.println(partyCopy.get(i).getNickname() + "'s health increased overnight!");
				}
				
			} 
		}
		if (randomEvents.getMonsterJoins()) {
			System.out.println(party.get(party.size() - 1).getName() + " joined your party overnight. Take good care of them!");
		}
		
	}
	
	public void endOfTheWorld() {
		gameEnvironment.getDays();
		gameEnvironment.getTotalDays();
		if (gameEnvironment.getDays() == gameEnvironment.getTotalDays()) {
			System.out.print("You reach the max amount of days therefore the game ends, during that time you, " + gameEnvironment.getName() + " has achieved: " + gameEnvironment.getPoints() + " points.");
			System.exit(0);
		}
	}
   

	
	
}
	


