package monsterfighter.ui;


	import java.util.List;

	import monsterfighter.core.GameEnvironment;

	/**import seng201.rocketmanager.core.Rocket;
	import seng201.rocketmanager.core.RocketManager;

	*/

public interface GameEnvironmentUi {

    /**
     * A description of the naming convention for the users name
     */
    String NAME_REQUIREMENTS = "Name must contain only letters, must not be empty and have a max of 12 characters";
    
    /**
     * A description of the naming convention for a monsters nickname
     */
    String MONSTER_NAME_REQUIREMENTS = "Name must contain only letters and have a max of 12 characters";

    /**
     * A regex for validating the name against the specified name requirements.
     */
    String NAME_REGEX = "[a-zA-Z]{1,12}";
    
	 /**
	 * The maximum number of days that a game can last.
	 */
    int MAX_DAYS = 15;
    
    /**
	 * The minimum number of days that a game can last.
	 */
    int MIN_DAYS = 5;
    
    
    /**
     * A description of the day requirements for the player of a monster fighter.
     */
    String DAY_REQUIREMENTS = "The number of days must be between 5 and 15";
    
    /**
     * Initialises this UI and sets up the given GameEnvironment with the available monsters and items.
     * Note that setup need not be complete by the time this method returns.
     * Once setup is complete this UI must call {@link GameEnvironment#onSetupFinished(String, List)}.
     *
     * @param gameEnvironment The game environment that this UI interacts with
     */
    void setup(GameEnvironment gameEnvironment);

    /**
     * Starts this UI allowing the user to manage their selected {@link Rocket}s
     */
    void start();

    /**
     * Confirms that the user really wants to quit.
     *
     * @return true if the user wants to quit, false otherwise
     */

    void quit();

    /**
     * Reports the given error to the user.
     *
     * @param error The error to display
     */
    void showError(String error);
}