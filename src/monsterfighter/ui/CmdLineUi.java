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

public class CmdLineUi implements GameEnvironmentUi {
	
    // The scanner used to read input from the console
    private final Scanner scanner;

    // The game environment this ui interacts with 
    private GameEnvironment gameEnvironment;

    // Flag to indicate when this ui should finish
    private boolean finish = false;

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
		// TODO Auto-generated method stub
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
		while (true) {
			System.out.println(message);
			printMonsters(monsters, description);
			try {
				int monsterID = scanner.nextInt();
				scanner.nextLine();
				if (monsterID >= 0 && monsterID < monsters.size()) {
					return monsterID;
				} else if (description == 1 && monsterID == monsters.size()) {
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
			} System.out.println("\n(" + monsters.size() + ") Back" );
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
		List<Monster> party = gameEnvironment.getParty();
		if (party.size() > 0) {
			int monsterID = chooseMonster("Party:\n" + "-".repeat(7), party, 1);
			if (monsterID >= 0 && monsterID < party.size()) {
				partyOptions(monsterID);
			} else {
				start();
			}
		} else {
			showError("Party is empty!\n");
		}
	}

	private void partyOptions(int monsterID) {
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
					if (!gameEnvironment.inventoryIsEmpty()) {
						monsterToItem(monsterID);
						accessParty();
					} else {
						showError("Inventory is empty!\n");
					}
				} else if (option == 1) {
					switchMonsters(monsterID);
				} else if (option == 2) {
					setMonsterNickname(gameEnvironment.getParty().get(monsterID));
				} else if (option == 3) {
					accessParty();
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}
	
	private void switchMonsters(int monsterID) {
		final List<Monster> party = gameEnvironment.getParty();
		int monsterID2 = chooseMonster("Select a monster to switch " + party.get(monsterID).getNickname() + " with:", party, 1);
		if (monsterID2 >= 0 && monsterID2 < party.size()) {
				gameEnvironment.switchMonsters(monsterID, monsterID2);
		}
	}
	
	private void monsterToItem(int monsterID){
		final List<ArrayList<Item>> inventory = gameEnvironment.getInventory();
		final List<Monster> party = gameEnvironment.getParty();
		while (true) {
			if (gameEnvironment.inventoryIsEmpty()) {
				showError("Inventory is empty!\n");
				partyOptions(monsterID);
			}
			int inventoryID = chooseItem("Select an item to give to " + party.get(monsterID).getNickname() + ":", inventory);
			if (inventoryID >= 0 && inventoryID < inventory.size()) {
				int itemID = gameEnvironment.getItemID(inventoryID);
				final Item item = gameEnvironment.getAllItems().get(itemID);
				final int itemCount = inventory.get(item.getIndex()).size();
				System.out.println(item);
				gameEnvironment.useItem(monsterID, itemID);
				if (itemCount != inventory.get(item.getIndex()).size()) {
					if (inventory.get(item.getIndex()).size() > 0) {
						System.out.println("Used " + item.getName() + " on " + party.get(monsterID).getNickname() + ", " + inventory.get(itemID).size() + "x " + item.getName() + "'s left");
					} else {
						System.out.println("Used " + item.getName() + " on " + party.get(monsterID).getNickname() + ", no " + item.getName() + "'s left");
					}
				}
			} else if (inventoryID == inventory.size()) {
				partyOptions(monsterID);
			}
    	}	
	}
	
	private void accessInventory() {
		final List<ArrayList<Item>> inventory = gameEnvironment.getInventory();
		if (!gameEnvironment.inventoryIsEmpty()) {
			int inventoryID = chooseItem("Inventory:\n" + "-".repeat(11), inventory);
			if (inventoryID < inventory.size()) {
				inventoryOptions(gameEnvironment.getItemID(inventoryID));
			} else if (inventoryID == inventory.size()) {
				start();
			}
		} else {
			showError("Inventory is empty!\n");
		}
	}
	
	private int chooseItem(String message, List<ArrayList<Item>> inventory) {
		while (true) {
			System.out.println(message);
			printInventory(inventory);
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
	
	private void printInventory(List<ArrayList<Item>> inventory) {
		int i = 0;
		for (ArrayList<Item> items : inventory) {
			System.out.println("(" + i + ") " + items.size() + "x " + items.get(0));
		        i++;
	    }
		System.out.println("\n(" + inventory.size() + ") Back" );
	}
	
	private void inventoryOptions(int itemID) {
		while (true) {
			System.out.println("Select an option:\n"
					+ "(0) Use " + gameEnvironment.getAllItems().get(itemID).getName() + "\n"
					+ "\n(1) Back");
			try {
				int option = scanner.nextInt();
				scanner.nextLine();
				if (option == 0) {
					itemToMonster(itemID);
				} else if (option == 1) {
					accessInventory();
				} 
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}
	
	private void itemToMonster(int itemID) {
		final List<Monster> party = gameEnvironment.getParty();
		final ArrayList<ArrayList<Item>> inventory = new ArrayList<>(gameEnvironment.getInventory());
		final Item item = gameEnvironment.getAllItems().get(itemID);
		while (true) {
			if (party.size() == 0) {
				showError("Party is empty!\n");
				inventoryOptions(itemID);
			}
			int monsterID = chooseMonster("Select a monster to give " + item.getName() + ":", party, 1);
			if (monsterID >= 0 && monsterID < party.size()) {
				final int itemCount = inventory.get(item.getIndex()).size();
				gameEnvironment.useItem(monsterID, itemID);
				if (itemCount != inventory.get(item.getIndex()).size()) {
					if (inventory.get(item.getIndex()).size() > 0) {
						System.out.println("Used " + item.getName() + " on " + party.get(monsterID).getNickname() + ", " + inventory.get(itemID).size() + "x " + item.getName() + "'s left");
					} else {
						System.out.println("Used " + item.getName() + " on " + party.get(monsterID).getNickname() + ", no " + item.getName() + "'s left!");
						accessInventory(); 
					}			
				}
			} else  {
				inventoryOptions(itemID);
			}	
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
					if (!gameEnvironment.shopIsEmpty()) {
						shopBuy();
					} else {
						showError("Shop is empty! Come back tomorrow for new items\n");
					}
				} else if (option == 1) {
					if (gameEnvironment.getParty().size() > 0) {
						sell(option);
					} else {
						showError("Party is empty!\n");
					}
					
				} else if (option == 2) {
					if (!gameEnvironment.inventoryIsEmpty()) {
						sell(option);
					} else {
						showError("Inventory is empty!\n");
					}
				} else if (option == 3) {
					start();
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}

	private void shopBuy() {
		final List<ArrayList<Purchasable>> shop = gameEnvironment.getShop();
		while (true) {
			printShopInventory("Shop:\n" + "-".repeat(6) + "\nGold: " + gameEnvironment.getGoldBalance() + "\n", shop);
			try {
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
				int itemID = chooseItem("Pick an item to sell:", gameEnvironment.getInventory());
				if (itemID < gameEnvironment.getInventory().size()) {
					final Item item = inventory.get(itemID).get(0);
					gameEnvironment.sellItem(itemID);
					System.out.println("Sold " + item.getName() + " for " + item.getSellPrice() + " gold");
				} else {
					System.out.println("chocolate");
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
		final List<Battle> battles = gameEnvironment.getBattles();
		while (true) {
			System.out.println("Select an option:\n"
					+ "(0) Wild battles\n"
					+ "(1) Trainer battles\n"
					+ "(2) Back\n");
			try {
				int option = scanner.nextInt();
				scanner.nextLine();
				if (option == 0) {
					accessWildBattle();
					
				} else if (option == 1) {
					//int battleID = chooseBattle(battles.subList(3, 4), "trainer battles") + 3 ;
					
				} else if (option == 2) {
					start();
			}} catch (Exception e) {
				scanner.nextLine();
			}
        }	
	}
	
	
	
	private void accessWildBattle() {
		while (true) {
			System.out.println("Select an wild battle:\n"
					+ "(0) " + gameEnvironment.getWildBattle() + "\n"
					+ "(1) " + gameEnvironment.getWildBattle() + "\n"
					+ "(2) Back\n");
			try {
				int option = scanner.nextInt();
				scanner.nextLine();
				if (option == 0) {
					System.out.println("your fat" );
					System.exit(0);
					
				} else if (option == 1) {
					
					
				} else if (option == 2) {
					accessBattle();
			}} catch (Exception e) {
				scanner.nextLine();
			}
        }	
		
	}
	
	
	
	
	public void accessRest() {
		while (true) {
			System.out.println("Are you sure you want to rest? (y/n) ");
            try {
                String input = scanner.next();
                if (input.matches("[yY]")) {
                	gameEnvironment.nextDay();
                	break;
                } else if (input.matches("[nN]")) {
                	start();
                }
            } catch (Exception e) {
                // Discard the unacceptable input
                scanner.next();
            }
        }
	}
	
}
	


