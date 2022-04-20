package monsterfighter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Item;
import monsterfighter.core.Monster;
import monsterfighter.ui.CmdLineUi;
import monsterfighter.ui.GameEnvironmentUi;



public class Main {
	

	public static void main(String[] args) {
		final List<Monster> monsters = new ArrayList<>(6);
		
		monsters.add(new Monster("Fireboy", Monster.Type.FIRE, 50, 20, 5, 367, 300));
		monsters.add(new Monster("Watergirl", Monster.Type.WATER, 60, 15, 60, 367, 300));
		monsters.add(new Monster("Dirt Monster", Monster.Type.GRASS, 70, 10, 70, 367, 300));
		monsters.add(new Monster("BrightStar", Monster.Type.LIGHT, 70, 10, 70, 367, 300));
		monsters.add(new Monster("DarkStar", Monster.Type.DARK, 50, 20, 50, 367, 300));
		monsters.add(new Monster("Normie", Monster.Type.NORMAL, 65, 14, 65, 367, 300));
		
		final List<Item> items = new ArrayList<>(5);
		
		items.add(new Item(0, "Small Potion", 20, Item.Stat.CURRENTHEALTH));
		items.add(new Item(1, "Big Potion", 50, Item.Stat.CURRENTHEALTH));
		items.add(new Item(2, "Attack Snack", 10, Item.Stat.ATTACK));
		items.add(new Item(3, "Max Health Snack", 20, Item.Stat.CURRENTHEALTH));
		items.add(new Item(4, "Revive candy", 20, Item.Stat.STATUS));
		
		GameEnvironmentUi ui;

       // if (args.length > 0 && (args[0].equals("cmd"))) {
    	   
        ui = new CmdLineUi();
        GameEnvironment environment = new GameEnvironment(ui, monsters, items);
        environment.start();
        /*
        } else {
        	
            ui = new Gui();
            RocketManager manager = new RocketManager(ui, rockets);

            // Ensure the RocketManager is started on the Swing event dispatch thread (EDT). To be thread safe,
            // all swing code should run on this thread unless explicitly stated as being thread safe.
            SwingUtilities.invokeLater(() -> manager.start());
        }
        */
    }
}
