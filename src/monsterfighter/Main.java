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
		
		monsters.add(new Monster(0, "Fireboy", Monster.Type.FIRE, 50, 20, 200));
		monsters.add(new Monster(1, "Watergirl", Monster.Type.WATER, 60, 15, 200));
		monsters.add(new Monster(2, "Dirt Monster", Monster.Type.GRASS, 70, 10, 200));
		monsters.add(new Monster(3, "BrightStar", Monster.Type.LIGHT, 70, 10, 200));
		monsters.add(new Monster(4, "DarkStar", Monster.Type.DARK, 50, 20, 200));
		monsters.add(new Monster(5, "Normie", Monster.Type.NORMAL, 65, 14, 200));
		
		
		final List<Item> items = new ArrayList<>(5);
		
		items.add(new Item(0, "Small Potion", 20, Item.Stat.CURRENTHEALTH, 100, 3));
		items.add(new Item(1, "Big Potion", 50, Item.Stat.CURRENTHEALTH, 100, 2));
		items.add(new Item(2, "Attack Snack", 10, Item.Stat.ATTACK, 100, 1));
		items.add(new Item(3, "Max Health Snack", 1000000, Item.Stat.CURRENTHEALTH, 100, 1));
		items.add(new Item(4, "Revive candy", 20, Item.Stat.STATUS, 100, 1));
		
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
