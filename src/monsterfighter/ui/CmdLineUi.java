package monsterfighter.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Monster;
import seng201.rocketmanager.core.Rocket;


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
        final Difficulty difficulty = GameEnvironment.getDifficulty();
        final ArrayList<Monster> party = getStartingMonster();
        
        
        /*
         * 
        
	    gameEnvironment.onSetupFinished(name, days, party, difficulty);
	    */
	       
	}
	
	private List<Monster> getSelectedMonsters(int rocketCount) {
        final List<Monster> monsters = new ArrayList<>(rocketCount);
        final List<Monster> availableRockets = new ArrayList<>(rocketManager.getAvailableRockets());

        System.out.println("\nPlease select " + rocketCount + " rockets");

        while (rockets.size() < rocketCount) {
            Rocket rocket = chooseRocket("Select a rocket by entering the associated number followed by enter",
                    availableRockets);

            rockets.add(rocket);
            availableRockets.remove(rocket);
        }

        return rockets;
    }
	
	



	@Override
	public void start() {
		// TODO Auto-generated method stub
		
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
	/*
    private void handleOption(Option option) {
        switch (option) {
            case SHOW_MONSTERS:
                printMonsters(gameEnvironment.getParty());
                break;
            case BATTLE:
                launch();
                break;
            case ITEMS:
                clean();
                break;
            case REST:
                refuel();
                break;
            case QUIT:
                gameEnvironment.onFinish();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }
        
        QUIT("Quit"),
        REST("Rest"),
        ITEMS("Items"),
        SHOP("Shop"),
        SHOW_MONSTERS("Show Monsters"),
        BATTLE("Battle");
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
                scanner.nextInt();
            }
        }
    }

}
	


