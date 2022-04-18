package monsterfighter.ui;

import java.util.List;
import java.util.Scanner;

import monsterfighter.core.GameEnvironment;

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

        public final String option;

        Option(String option) {
            this.option = option;
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
		/*
		// TODO Auto-generated method stub
        this.gameEnvironment = gameEnvironment;
        final String name = getName();
        final int rocketCount = getRocketCount();
        final List<Rocket> selectedRockets = getSelectedRockets(rocketCount);

	        rocketManager.onSetupFinished(name, selectedRockets);
	       */
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showError(String error) {
		// TODO Auto-generated method stub
		
	}

}
