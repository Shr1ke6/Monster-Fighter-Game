package monsterfighter.ui;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.GameEnvironment.Difficulty;
import monsterfighter.core.Monster;

public class CmdLineUi implements GameEnvironmentUi {
	
    // The scanner used to read input from the console
    private final Scanner scanner;

    // The game environment this ui interacts with 
    private GameEnvironment gameEnvironment;

    // Flag to indicate when this ui should finish
    private boolean finish = false;

    // An enum representing the various actions the user can perform
    private enum Option {
        QUIT("Quit"),
        REST("Rest"),
        ITEMS("Items"),
        SHOP("Shop"),
        SHOW_MONSTERS("Show Monsters"),
        BATTLE("Battle");

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
			System.out.println("Pick an option:\n");
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
	public boolean confirmQuit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void quit() {
		// TODO fix quit class gives error quits program no matter what option
		
		finish = true;
		
	}

	@Override
	public void showError(String error) {
		// TODO need to be looked at later
		System.out.println("!!!!!!!! " + error + " !!!!!!!!");
	}
	


	 /**
     * Handles the given option by performing the appropriate action.
     *
     * @param option The selected option to be carried out
     */
    private void handleOption(Option option) {
        switch (option) {
            case SHOW_MONSTERS:

                break;
            case BATTLE:

                break;
            case ITEMS:

                break;
            case REST:

                break;
            case QUIT:

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
            	}
            	System.out.println(DAY_REQUIREMENTS);
            } catch (Exception e) {
                // Discard the unacceptable input
            	scanner.reset();
				scanner.next();
            }
        }
    }
	
	private Difficulty getDifficulty() {
		while (true) {
			System.out.println("Choose a difficulty:\n");
			printDifficulties();
			try {
				int option = scanner.nextInt();
				if (option >= 0 && option < 3) {
					return Difficulty.values()[option];
				}
	
			} catch (Exception e) {
				scanner.reset();
				scanner.next();
			}
		}
	}
		
	
	
	private void printDifficulties() {
		int i = 0;
        for (Difficulty difficulty : Difficulty.values()) {
           System.out.println("(" + i + ") " + difficulty);
           i += 1;
        }
	}

	private Monster getStartingMonster() {
		final List<Monster> startingMonsters = new ArrayList<>(gameEnvironment.getStartingMonsters());
		System.out.println("Select an option by inputting the corresponding number");
		while (true) {
			System.out.println("Select a starting monster:\n");
			printStartingMonsters(startingMonsters);
			try {
				int option = scanner.nextInt();
				if (option >= 0 && option < startingMonsters.size()) {
					return startingMonsters.get(option);
				}
	
			} catch (Exception e) {
				scanner.reset();
				scanner.next();
			}
		}
		
	}
	
	private void printStartingMonsters(List<Monster> startingMonsters) {
		int i = 0;
		for(Monster monster : startingMonsters) {
			System.out.println("(" + i + ") " + monster);
			i += 1;
		}
	}


private void printOptions() {
		int i = 0;
	    for (Option option : Option.values()) {
	       System.out.println("(" + i + ") " + option.name);
	       i += 1;
	    }
	}
}
