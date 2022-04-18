package monsterfighter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import monsterfighter.core.GameEnvironment;
import monsterfighter.core.Monster;
import monsterfighter.ui.CmdLineUi;
import monsterfighter.ui.MonsterFighterUi;


public class Main {
	

	public static void main(String[] args) {
		final List<Monster> monsters = new ArrayList<>(6);
		
		monsters.add(new Monster("fireboy", Monster.Type.FIRE, 50, 20, 50));
		monsters.add(new Monster("watergirl", Monster.Type.WATER, 60, 15, 60));
		monsters.add(new Monster("BrightStar", Monster.Type.LIGHT, 70, 10, 70));
		monsters.add(new Monster("DarkStar", Monster.Type.DARK, 50, 20, 50));
		monsters.add(new Monster("dirt", Monster.Type.GRASS, 70, 10, 70));
		monsters.add(new Monster("normie", Monster.Type.NORMAL, 65, 14, 65));
		
		MonsterFighterUi ui;

       /** if (args.length > 0 && (args[0].equals("cmd"))) {
            ui = new CmdLineUi();
            GameEnvironment environment = new GameEnvironment(ui, monsters);
            manager.start();
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
