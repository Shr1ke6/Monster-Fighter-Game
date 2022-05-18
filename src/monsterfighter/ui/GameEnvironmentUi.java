package monsterfighter.ui;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Monster;

/**
 * Defines a user interface (UI) for a {@link GameEnvironment}.
 * Class adapted from the RocketManagerUI class in the seng201 RocketManager example project.
 */
public interface GameEnvironmentUi {

    /**
     * A description of the naming convention for the {@link Player}'s name
     */
    String NAME_REQUIREMENTS = "Name must contain only letters and may be from 1-12 characters";
    
    /**
     * A description of the naming convention for a {@link Monster}'s nickname
     */
    String MONSTER_NAME_REQUIREMENTS = "Nickname must contain only letters and have a max of 12 characters";

    /**
     * A regex for validating the name against the specified name requirements.
     */
    String NAME_REGEX = "[a-zA-Z]{1,12}";
    
    /**
     * A description of the day requirements for a game of Monster Fighter
     */
    String DAY_REQUIREMENTS = "The number of days must be between 5 and 15";
    
    /**
     * Initialises this UI and sets up the given GameEnvironment with the available monsters and items.
     * Note that setup need not be complete by the time this method returns.
     * Once setup is complete this UI must call {@link GameEnvironment#onSetupFinished(String, Integer, Monster, String, Monster.Difficulty)}.
     *
     * @param gameEnvironment The game environment that this UI interacts with
     */
    void setup(GameEnvironment gameEnvironment);

    /**
     * Starts this UI allowing the user to play Monster Fighter.
     */
    void start();

    /**
     * Confirms that the user really wants to quit.
     *
     * @return true if the user wants to quit, false otherwise
     */

	boolean confirmQuit();
    
	/**
	 * Quits the application
	 */
    void quit();

    /**
     * Reports the given error to the user.
     *
     * @param error The error to display
     */
    void showError(String error);


}