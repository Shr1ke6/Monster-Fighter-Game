package monsterfighter.ui.gui;

import monsterfighter.core.GameEnvironment;
import monsterfighter.ui.GameEnvironmentUi;

public class Gui implements GameEnvironmentUi{

	private GameEnvironment gameEnvironment;
	
	private Screen screen;
	
	@Override
	public void setup(GameEnvironment gameEnvironment) {
		/*
		this.gameEnvironment = gameEnvironment;
        screen = new SetupScreen(gameEnvironment);
        screen.show();
        */
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
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
