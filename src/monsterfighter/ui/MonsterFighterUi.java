package monsterfighter.ui;

import java.util.List;

import monsterfighter.core.GameEnvironment;

/**import seng201.rocketmanager.core.Rocket;
import seng201.rocketmanager.core.RocketManager;

*/

public interface MonsterFighterUi {

	
    /**
     * A description of the name requirements for the player of a monster fighter.
     */
    String NAME_REQUIREMENTS = "Your name must only contain letters and have a max of 12 characters";

    /**
     * A regex for validating the name against the specified name requirements.
     */
    String NAME_REGEX = "[a-zA-Z]{,12}";

    /**
     * Initialises this UI and sets up the given RocketManager with the rockets to be managed.
     * Note that setup need not be complete by the time this method returns.
     * Once setup is complete this UI must call {@link RocketManager#onSetupFinished(String, List)}.
     *
     * @param rocketManager The rocket manager that this UI interacts with
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
    boolean confirmQuit();

    /**
     * Quits the application.
     */
    void quit();

    /**
     * Reports the given error to the user.
     *
     * @param error The error to display
     */
    void showError(String error);
}

