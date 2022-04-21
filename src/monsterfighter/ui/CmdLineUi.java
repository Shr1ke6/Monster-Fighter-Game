package monsterfighter.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.GameEnvironment.Difficulty;
import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.core.Purchasable;
import monsterfighter.core.Shop;

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
        setMonsterNickname(startingMonster);
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

                break;
            case INVENTORY:
            	accessInventory();
                break;
            case SHOP:
            	accessShop();
            	break;
            case REST:
            	

                break;
            case QUIT:
            	quit();

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }
    }

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
	
	private int getDays() {
        while (true) {
            System.out.println("Pick how many days you would like. From 5 to 15");
            try {
            	int days = scanner.nextInt();
            	if (days <= MAX_DAYS && days >= MIN_DAYS) {
            		return days;
            	};
            } catch (Exception e) {
                // Discard the unacceptable input
            	scanner.nextLine();
            }
        }
    }
	
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
		
	private void printDifficulties() {
		int i = 0;
        for (Difficulty difficulty : Difficulty.values()) {
           System.out.println("(" + i + ") " + difficulty);
           i++;
        }
	}

	private Monster getStartingMonster() {
		final List<Monster> startingMonsters = new ArrayList<>(gameEnvironment.getStartingMonsters());
		System.out.println("Select an option by inputting the corresponding number");
		while (true) {
			System.out.println("Select a starting monster:");
			printStartingMonsters(startingMonsters);
			try {
				int option = scanner.nextInt();
				scanner.nextLine();
				if (option >= 0 && option < startingMonsters.size()) {
					return startingMonsters.get(option);
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}
	
	private void printStartingMonsters(List<Monster> startingMonsters) {
		int i = 0;
		for(Monster monster : startingMonsters) {
			System.out.println("(" + i + ") " + monster.basicDescription());
			i++;
		}
	}

	private void printOptions() {
			int i = 0;
		    for (Option option : Option.values()) {
		       System.out.println("(" + i + ") " + option.name);
		       i++;
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
                System.out.println(NAME_REQUIREMENTS);
            } catch (Exception e) {
            	System.out.println("cringe");
                // Discard the unacceptable input
                scanner.nextLine();
            }
        }
    }
    	
	private void accessParty() {
		final ArrayList<Monster> party = new ArrayList<>(gameEnvironment.getParty());
		while (true) {
			System.out.println("Party:\n"
					+ "-".repeat(7));
			printParty(party);
			System.out.println("\n(" + party.size() + ") Back" );
			try {
				int option = scanner.nextInt();;
				if (option >= 0 && option < party.size()) {
					partyOptions(option, party);
				} else if (option == party.size()) {
					start();
				}
				
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}

	private void partyOptions(int monsterID, ArrayList<Monster> party) {
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
						monsterToItem(monsterID, party);
						accessParty();
					} else {
						showError("Inventory is empty!\n");
					}
				} else if (option == 1) {
					switchMonsters(monsterID, party);
					accessParty();
				} else if (option == 2) {
					setMonsterNickname(party.get(monsterID));
					accessParty();
				} else if (option == 3) {
					accessParty();
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}
	
	private void switchMonsters(int monsterID, ArrayList<Monster> party) {
    	while (true) {
    		System.out.println("Select a monster to switch " + party.get(monsterID).getNickname() + " with:");
    		printParty(party);
    		System.out.println("\n(" + party.size() + ") Back");
			try {
				int option = scanner.nextInt();
				if (option >= 0 && option < party.size()) {
					gameEnvironment.switchMonsters(monsterID, option);
					break;
				} else if (option == party.size()) {
					partyOptions(monsterID, party);
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
    	}
		
	}
	
	private void monsterToItem(int monsterID, ArrayList<Monster> party){
		final ArrayList<ArrayList<Item>> inventory = new ArrayList<>(gameEnvironment.getInventory());
		while (true) {
			System.out.println("Select an item to give to " + party.get(monsterID).getNickname() + ":");
			int last = printInventory(inventory);
			System.out.println("\n(" + last + ") Back");
			try {
				int option = scanner.nextInt();
				if (option >= 0 && option < last) {
					Item item = gameEnvironment.getItem(option);
					gameEnvironment.useItem(monsterID, option);
					if (inventory.get(item.getIndex()).size() > 0) {
						System.out.println("Used " + item.getName() + " on " + party.get(option).getNickname() + ", " + inventory.get(item.getIndex()).size() + "x " + item.getName() + "'s left");
					} else {
						System.out.println("Used " + item.getName() + " on " + party.get(option).getNickname() + ", no " + item.getName() + "'s left");
						if (gameEnvironment.inventoryIsEmpty()) {
							showError("Inventory is empty!\n");
							partyOptions(monsterID, party);
						}
					}
				} else if (option == last) {
					partyOptions(monsterID, party);
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
    	}	
	}

	private void printParty(List<Monster> party) {
	    int i = 0;
	
	    for (Monster monster : party) {
	        System.out.println("(" + i + ") [Slot: " + (i+1) + "] " + monster);
	        i++;
	    }
	}
	
	private void accessInventory() {
		final ArrayList<ArrayList<Item>> inventory = new ArrayList<>(gameEnvironment.getInventory());
		while (true) {
			if (!gameEnvironment.inventoryIsEmpty()) {
				System.out.println("Inventory:\n"
						+ "-".repeat(11));
				int last = printInventory(inventory);
				System.out.println("\n(" + last + ") Back" );
				try {
					int option = scanner.nextInt();;
					if (option >= 0 && option < last) {
						inventoryOptions(option);
					} else if (option == last) {
						start();
					}
				} catch (Exception e) {
					scanner.nextLine();
				}
				
			} else {
				showError("Inventory is empty!\n");;
				start();
			}
		}
	}
	
	private void inventoryOptions(int itemID) {
		while (true) {
			System.out.println("Select an option:\n"
					+ "(0) Use "+ gameEnvironment.getItem(itemID).getName() + "\n"
					+ "\n(1) Back");
			try {
				int option = scanner.nextInt();
				scanner.nextLine();
				if (option == 0) {
					itemToMonster(itemID);
					accessInventory();
				} else if (option == 1) {
					accessInventory();
				} 
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}
	
	private void itemToMonster(int itemID) {
		final ArrayList<Monster> party = new ArrayList<>(gameEnvironment.getParty());
		final Item item = gameEnvironment.getItem(itemID);
		final ArrayList<ArrayList<Item>> inventory = new ArrayList<>(gameEnvironment.getInventory());
		while (true) {
			System.out.println("Select a monster to give " + gameEnvironment.getItem(itemID).getName() + ":");
			printParty(party);
			System.out.println("\n(" + party.size() + ") Back");
			try {
				int option = scanner.nextInt();
				if (option >= 0 && option < party.size()) {
					gameEnvironment.useItem(option, itemID);
					if (inventory.get(item.getIndex()).size() > 0) {
						System.out.println("Used " + item.getName() + " on " + party.get(option).getNickname() + ", " + inventory.get(item.getIndex()).size() + "x " + item.getName() + "'s left");
					} else {
						System.out.println("Used " + item.getName() + " on " + party.get(option).getNickname() + ", no " + item.getName() + "'s left!");
						accessInventory(); 
					}					
				} else if (option == party.size()) {
					accessInventory();
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
    	}	
	}
	
	private int printInventory(List<ArrayList<Item>> inventory) {
	    int i = 0;
		for (int j = 0; j < inventory.size(); j++) {
	    	if (inventory.get(j).size() > 0) {
		        System.out.println("(" + i + ") " + inventory.get(j).size() + "x " + inventory.get(j).get(0));
		        i++;
	    	}
		}
		return i;
	}
	
	private void accessShop() {
		while (true) {
			System.out.println("Select a shop option:\n"
					+ "(0) Buy\n"
					+ "(1) Sell Monster\n"
					+ "(2) Sell Item\n"
					+ "\n(3) Back\n");
			try {
				int option = scanner.nextInt();
				if (option == 0) {
					if (!gameEnvironment.shopIsEmpty()) {
						shopBuy();
					} else {
						showError("Shop is empty! Come back tomorrow for new items\n");
					}
				} else if (option == 1) {
					
				} else if (option == 2) {
					
				} else if (option == 3) {
					start();
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}
	
	private void shopBuy() {
		final Shop shop = gameEnvironment.getShop();
		while (true) {
			System.out.println("Shop:\n"
					+ "-".repeat(6) + "Select an item to purchase itGold: " + gameEnvironment.getGold());
			printShopInventory(shop);
			System.out.println("\n(" + shop.getShopInventory().size() + ") Back" );
			try {
				int option = scanner.nextInt();
				if (option >= 0 && option < shop.getShopInventory().size()) {
					
				} else if (option == shop.getShopInventory().size()) {
					start();
				}
			} catch (Exception e) {
				scanner.nextLine();
			}
		}
	}
	
	public void printShopInventory(Shop shop) {
		ArrayList<ArrayList<Purchasable>> shopInventory = shop.getShopInventory();
		for (int i = 0; i < shopInventory.size(); i++) {
			System.out.println("(" + i + ") " + shopInventory.get(i).size() + "x " + shopInventory.get(i).get(0).shopDescription());
	    	}
		}
	
	public void printPurchasable(Purchasable purchasable) {
		for (for )
	}
	
	
	
	
	

}
