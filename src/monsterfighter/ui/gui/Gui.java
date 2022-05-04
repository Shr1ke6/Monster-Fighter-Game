package monsterfighter.ui.gui;

import monsterfighter.core.GameEnvironment;
import monsterfighter.ui.GameEnvironmentUi;

public class Gui implements GameEnvironmentUi{

	private GameEnvironment gameEnvironment;
	
	private Screen screen;
	
    private enum Option {
    	MAIN_MENU("Main Menu"),
        SHOP("Shop"),
        PARTY("Party"),
        INVENTORY("Inventory"),
        BATTLE("Battle"),
        BATTLE_SELECT("Battle Select"),
        GAME_OVER("Game Over");

        public final String name;

        Option(String name) {
            this.name = name;
        }
    }
	
    @Override
    public void setup(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        screen = new SetupScreen(gameEnvironment);
        screen.show();
    }

    @Override
    public void showError(String error) {
        screen.showError(error);
    }

    @Override
    public void start() {
        screen.quit();
        screen = new MainScreen(gameEnvironment);
        screen.show();
    }

    @Override
    public boolean confirmQuit() {
        return screen.confirmQuit();
    }

    @Override
    public void quit() {
        screen.quit();
    }
    
    public void transitionScreen(String name) {
    	Option option = Option.valueOf(name);
    	screen.quit();
    	 switch (option) {
	         case MAIN_MENU:
	        	 screen = new MainScreen(gameEnvironment);
	             break;
	         case SHOP:
	        	 screen = new ShopScreen(gameEnvironment);
	             break;
	         case INVENTORY:
	        	 screen = new InventoryScreen(gameEnvironment);
	             break;
	         case PARTY:
		         screen = new PartyScreen(gameEnvironment);
		         break;
	         case BATTLE:
	        	 screen = new BattleScreen(gameEnvironment);
		         break;
	         case BATTLE_SELECT:
	        	 screen = new BattleSelectionScreen(gameEnvironment);
		         break;
	         case GAME_OVER:
	        	 screen = new GameOverScreen(gameEnvironment);
		         break;  
	         default:
	        	 throw new IllegalStateException("Unexpected value: " + option);
    	 }
    	 screen.show();
    }

}
